package br.com.ifood.security;

import br.com.ifood.handler.APIException;
import br.com.ifood.handler.ProblemType;
import br.com.ifood.security.domain.ValidaConteudoAuthorizationHeader;
import br.com.ifood.security.service.AutenticacaoSecurityService;
import br.com.ifood.security.service.TokenService;
import br.com.ifood.usuario.application.repository.UsuarioRepository;
import br.com.ifood.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class FiltroToken extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AutenticacaoSecurityService user;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("[inicio] Filtro - filtrando requisicao");
        String token = recuperaToken(request);
        autenticaCliente(token);
        log.info("[finaliza] Filtro - filtrando requisicao");
        filterChain.doFilter(request, response);
    }

    private void autenticaCliente(String token) {
        log.info("[inicio] autenticacaoCliente - utilizando token válido para autenticar o usuário");
        Optional<String> email = tokenService.getUsuario(token);
        String userEmail = email.orElseThrow(() -> new RuntimeException("Email não encontrado no token"));
        UserDetails userDeteils = user.loadUserByUsername(userEmail);
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDeteils,null, userDeteils.getAuthorities() );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("[finaliza] autenticacaoCliente - utilizando token válido para autenticar o usuário");
    }

    private String recuperaToken(HttpServletRequest requestOpt) {
        log.info("[inicio] recuperaToken - extraindo o token dos cabecalhos da requisicao");
        var AuthorizationHeaderValueOpt = Optional.ofNullable(recuperaValorAuthorizationHeader(requestOpt));
        String AuthorizationHeaderValue = AuthorizationHeaderValueOpt.filter(new ValidaConteudoAuthorizationHeader())
                .orElseThrow(() -> APIException.build(HttpStatus.FORBIDDEN, "Token inválido!", ProblemType.ACESSO_NEGADO));
        log.info("[finaliza] recuperaToken - extraindo o token dos cabecalhos da requisicao");
        return AuthorizationHeaderValue.substring(7, AuthorizationHeaderValue.length());
    }

    private String recuperaValorAuthorizationHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .orElseThrow(() -> APIException.build(HttpStatus.UNAUTHORIZED, "Token não está presente na requisição!", ProblemType.ACESSO_NEGADO));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("/public/")||path.contains("/swagger-ui/") ||path.contains("/novo-usuario") ;
    }

}
