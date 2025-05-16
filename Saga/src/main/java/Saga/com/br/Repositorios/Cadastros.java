package Saga.com.br.Repositorios;

import java.util.Optional;
import Saga.com.br.Entidades.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cadastros extends JpaRepository<Cadastro, Long> {
    Optional<Cadastro> findByCpfAndSenha(String cpf, String senha);
}
