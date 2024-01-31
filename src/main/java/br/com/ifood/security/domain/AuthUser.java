package br.com.ifood.security.domain;

import br.com.ifood.usuario.domain.Usuario;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
@Getter
public class AuthUser extends User {

    private String idUsuario;
    public AuthUser(Usuario usuario, Collection<SimpleGrantedAuthority> authorities) {
        super(usuario.getEmail(),usuario.getSenha(), authorities);
        this.idUsuario = usuario.getIdUsuario();
    }
}
