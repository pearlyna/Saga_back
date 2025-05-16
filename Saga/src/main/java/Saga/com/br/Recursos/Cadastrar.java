package Saga.com.br.Recursos;

import Saga.com.br.Entidades.Cadastro;
import Saga.com.br.Servicos.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 33600)
@RestController
@RequestMapping(value = "/cadastro")
public class Cadastrar {
    @Autowired
    private CadastroService cadastroService;

    @PostMapping(value = "/login")
    public ResponseEntity<Cadastro> realizarLogin(@RequestBody Cadastro loginRequest) {
        Cadastro cadastro = cadastroService.findByCpfAndSenha(loginRequest.getCpf(), loginRequest.getSenha());
        if (cadastro != null) {
            return ResponseEntity.ok(cadastro);
        }
        return ResponseEntity.status(401).build(); // Retorna 401 se não encontrar
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cadastro> findById(@PathVariable Long id) {
        Cadastro cadastro = cadastroService.findById(id);
        return ResponseEntity.ok().body(cadastro);
    }

    @GetMapping
    public ResponseEntity<List<Cadastro>> findAll() {
        List<Cadastro> cadastros = cadastroService.findAll();
        return ResponseEntity.ok().body(cadastros);
    }

    @PostMapping
    public ResponseEntity<Cadastro> salvarCadastro(@RequestBody Cadastro cadastro) {
        cadastro = cadastroService.salvarCadastro(cadastro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cadastro.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastro);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCadastro(@PathVariable Long id) {
        cadastroService.deletarCadastro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cadastro> atualizarCadastro(@PathVariable Long id, @RequestBody Cadastro cadastro) {
        Cadastro atualizado = cadastroService.atualizarCadastro(id, cadastro);
        return ResponseEntity.ok().body(atualizado);
    }
}
