package br.com.ifood.security.service;

import br.com.ifood.security.domain.AuthUser;
import br.com.ifood.usuario.application.repository.UsuarioRepository;
import br.com.ifood.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AutenticacaoSecurityService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("[inicio] AutenticacaoSecurityService - Buscando Usuario Pelo Email");
        Usuario usuario = usuarioRepository.findByEmail(email);
        log.info("[finaliza] AutenticacaoSecurityService - Buscando Usuario Pelo Email");
        return new AuthUser(usuario, getAuthorities(usuario));
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(Usuario usuario) {
        return usuario.getGrupos().stream()
                .flatMap(grupo -> grupo.getPermissoes().stream())
                .map(permissao -> new SimpleGrantedAuthority(permissao.getNome()))
                .collect(Collectors.toSet());
    }
}
