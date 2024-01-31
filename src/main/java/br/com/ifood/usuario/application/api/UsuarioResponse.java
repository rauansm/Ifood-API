package br.com.ifood.usuario.application.api;

import br.com.ifood.usuario.domain.Usuario;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class UsuarioResponse {

    private String idUsuario;
    private String nome;
    private String email;

    public UsuarioResponse(Usuario usuarioNovo) {
        this.idUsuario = usuarioNovo.getIdUsuario();
        this.nome = usuarioNovo.getNome();
        this.email = usuarioNovo.getEmail();
    }

    public static List<UsuarioResponse> converte(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
    }
}
