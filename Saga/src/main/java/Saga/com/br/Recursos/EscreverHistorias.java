package Saga.com.br.Recursos;

import Saga.com.br.DTO.HistoriaCapaDTO;
import Saga.com.br.Entidades.Historia;
import Saga.com.br.Servicos.FuncaoHistoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 33600)
@RestController
@RequestMapping(value = "/historia")
public class EscreverHistorias {

    @Autowired
    private FuncaoHistoria funcaoHistoria;

    // diretório onde as imagens serão salvas
    private final String uploadDirectory = "uploads/";

    @GetMapping(value = "/{id}")
    public ResponseEntity<Historia> findById(@PathVariable Integer id) {
        Historia historia = funcaoHistoria.findyById(id);
        return ResponseEntity.ok().body(historia);
    }

    // get todas as historias
    @GetMapping
    public ResponseEntity<List<HistoriaCapaDTO>> findAll() {
        List<Historia> historias = funcaoHistoria.findAll();

        // converter cada história para um DTO contendo id, título e imagem
        List<HistoriaCapaDTO> historiaDTOs = historias.stream()
                .map(historia -> new HistoriaCapaDTO(historia.getId(), historia.getTitulo(), historia.getImagem()))
                .toList();
        return ResponseEntity.ok().body(historiaDTOs);
    }


    // criar uma nova historia
    @PostMapping
    public ResponseEntity<Historia> salvarHistoria(@RequestBody Historia historia) {
        historia = funcaoHistoria.salvarHistoria(historia);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(historia.getId()).toUri();
        return ResponseEntity.created(uri).body(historia);
    }

    // post de imagem para uma história existente
    @PostMapping(value = "/{id}/imagem")
    public ResponseEntity<Void> uploadImagem(@PathVariable Integer id, @RequestParam("imagem") MultipartFile imagem) throws IOException {
        Historia historia = funcaoHistoria.findyById(id);

        // excluir a imagem anterior se houver
        if (historia.getImagem() != null) {
            Path oldImagePath = Paths.get(uploadDirectory, historia.getImagem());
            if (Files.exists(oldImagePath)) {
                Files.delete(oldImagePath);
            }
        }

        // salvar a nova imagem
        String imagemNome = salvarImagem(imagem);
        historia.setImagem(imagemNome);

        // atualizar a história no banco de dados
        funcaoHistoria.atualizarHistoria(id, historia);
        return ResponseEntity.ok().build();
    }


    // endpoint para acessar a imagem
    @GetMapping(value = "/imagem/{nomeImagem}")
    public ResponseEntity<byte[]> getImagem(@PathVariable String nomeImagem) throws IOException {
        // caminho para a imagem
        Path caminho = Paths.get(uploadDirectory, nomeImagem);

        // verificar se o arquivo existe
        if (Files.exists(caminho)) {
            byte[] imagemBytes = Files.readAllBytes(caminho);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg")  // ajuste para o tipo correto da imagem
                    .body(imagemBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // atualizar a historia
    @PutMapping(value = "/{id}")
    public ResponseEntity<Historia> atualizarHistoria(@PathVariable Integer id, @RequestBody Historia historia) {
        Historia alterado = funcaoHistoria.atualizarHistoria(id, historia);
        return ResponseEntity.ok().body(alterado);
    }

    // atualizar uma imagem de uma história existente
    @PutMapping(value = "/{id}/imagem")
    public ResponseEntity<Void> atualizarImagem(@PathVariable Integer id, @RequestParam("imagem") MultipartFile imagem) throws IOException {
        Historia historia = funcaoHistoria.findyById(id);
        String imagemNome = salvarImagem(imagem);

        // atualizar o campo de imagem da história no banco de dados
        historia.setImagem(imagemNome);
        funcaoHistoria.atualizarHistoria(id, historia);

        return ResponseEntity.ok().build();
    }

    // deletar a historia by id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarHistoria(@PathVariable Integer id) {
        funcaoHistoria.deletarHistoria(id);
        return ResponseEntity.noContent().build();
    }

    // metodo auxiliar para salvar a imagem no disco
    private String salvarImagem(MultipartFile imagem) throws IOException {
        // gerar o caminho do arquivo
        String imagemNome = imagem.getOriginalFilename();
        Path caminho = Paths.get(uploadDirectory, imagemNome);

        // criar o diretorio de uploads, se não existir
        Files.createDirectories(caminho.getParent());

        // escrever o arquivo no disco
        Files.write(caminho, imagem.getBytes());

        return imagemNome;
    }
}
