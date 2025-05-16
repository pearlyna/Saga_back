package Saga.com.br.Servicos;

import Saga.com.br.Entidades.Cadastro;
import Saga.com.br.Repositorios.Cadastros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private Cadastros cadastrar;

    public Cadastro findById(Long id) {
        Optional<Cadastro> cadastro = cadastrar.findById(id);
        return cadastro.orElse(null);
    }

    public List<Cadastro> findAll() {
        return cadastrar.findAll();
    }


    public Cadastro salvarCadastro(Cadastro cadastro) {
        if (!isValidCPF(cadastro.getCpf())) {
            throw new IllegalArgumentException("CPF inválido: " + cadastro.getCpf());
        }
        return cadastrar.save(cadastro);
    }

    public void deletarCadastro(Long id) {
        cadastrar.deleteById(id);
    }

    public Cadastro atualizarCadastro(Long id, Cadastro cadastro) {
        Cadastro existente = findById(id);
        if (existente != null) {
            existente.setNome(cadastro.getNome());
            existente.setEmail(cadastro.getEmail());
            existente.setSenha(cadastro.getSenha());
            existente.setCpf(cadastro.getCpf());

            if (!isValidCPF(existente.getCpf())) {
                throw new IllegalArgumentException("CPF inválido: " + existente.getCpf());
            }

            return cadastrar.save(existente);
        }
        return null;
    }

    private boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * pesos[i];
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;

            if (primeiroDigito != (cpf.charAt(9) - '0')) return false;

            pesos = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * pesos[i];
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;

            return segundoDigito == (cpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }
    public Cadastro findByCpfAndSenha(String cpf, String senha) {
        Optional<Cadastro> cadastro = cadastrar.findByCpfAndSenha(cpf, senha);
        return cadastro.orElse(null);
    }

}
