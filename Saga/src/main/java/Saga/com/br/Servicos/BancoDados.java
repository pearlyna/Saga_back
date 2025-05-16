package Saga.com.br.Servicos;

import Saga.com.br.Entidades.Cadastro;
import Saga.com.br.Repositorios.Historias;
import Saga.com.br.Repositorios.Cadastros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class BancoDados {
    @Autowired
    private Historias historias;

    @Autowired
    private Cadastros cadastros;

    @Bean
    public void instaciarBD() {
        Cadastro cadastroteste = new Cadastro();
        cadastroteste.setCpf("11122345221");
        cadastroteste.setEmail("maumau@123.com");
        cadastroteste.setNome("Mauricio");
        cadastroteste.setSenha("123");

        cadastros.saveAll(asList(cadastroteste));
    }
}
