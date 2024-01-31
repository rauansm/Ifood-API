package br.com.ifood.usuario.infra;

import br.com.ifood.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioSpringDataJPA extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
