package br.com.ifood.usuario.application.repository;

import br.com.ifood.usuario.domain.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario salva(Usuario usuario);

    List<Usuario> buscaTodosUsuarios();

    Usuario buscaUsuarioAtravesId(String idUsuario);

    Usuario findByEmail(String email);
}
