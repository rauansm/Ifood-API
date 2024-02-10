package br.com.ifood.usuario.infra;

import br.com.ifood.handler.APIException;
import br.com.ifood.usuario.application.repository.UsuarioRepository;
import br.com.ifood.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UsuarioRepositoryJPA implements UsuarioRepository {

    private final UsuarioSpringDataJPA usuarioSpringDataJPA;

    @Override
    public Usuario salva(Usuario usuario) {
        log.info("[inicia] UsuarioRepositoryJPA - salva");
        usuarioSpringDataJPA.save(usuario);
        log.info("[inicia] UsuarioRepositoryJPA - salva");
        return usuario;
    }

    @Override
    public List<Usuario> buscaTodosUsuarios() {
        log.info("[inicia] UsuarioRepositoryJPA - buscaTodosUsuarios");
        List<Usuario> usuarios = usuarioSpringDataJPA.findAll();
        log.info("[finaliza] UsuarioRepositoryJPA - buscaTodosUsuarios");
        return usuarios;
    }

    @Override
    public Usuario buscaUsuarioAtravesId(String idUsuario) {
        log.info("[inicia] UsuarioRepositoryJPA - buscaUsuarioAtravesId");
        Optional<Usuario> usuario = usuarioSpringDataJPA.findById(idUsuario);
        log.info("[finaliza] UsuarioRepositoryJPA - buscaUsuarioAtravesId");
        return usuario.orElseThrow(() -> APIException.entidadeNaoEncontrada(String.format("Usuario com Id %s não encontrado", idUsuario)));
    }

    public Usuario findByEmail(String email) {
        log.info("[inicia] UsuarioRepositoryJPA - findByEmail");
        Optional<Usuario> usuario = usuarioSpringDataJPA.findByEmail(email);
        log.info("[finaliza] UsuarioRepositoryJPA - findByEmail");
        return usuario.orElseThrow(() -> APIException.entidadeNaoEncontrada(String.format("Usuario com email %s não encontrado", email)));
    }
}
