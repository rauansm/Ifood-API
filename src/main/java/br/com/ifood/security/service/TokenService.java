package br.com.ifood.security.service;

import br.com.ifood.security.domain.AuthUser;
import br.com.ifood.usuario.domain.Usuario;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@Log4j2
public class TokenService {
    @Value("${ifood.jwt.expiracao}")
    private String expiracao;
    @Value("${ifood.jwt.chave}")
    private String chave;

    public String gerarToken(Authentication authentication) {
        return gerarToken((AuthUser) authentication.getPrincipal());
    }

    public String gerarToken(AuthUser usuario) {
        log.info("[inicio] TokenService - criação de token");
        String token = Jwts.builder()
                .setIssuer("API do Ifood")
                .setSubject(usuario.getUsername())
                .claim("permissões",usuario.getAuthorities())
                .claim("IdUsuario", usuario.getIdUsuario())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now()
                        .plusMinutes(Long.valueOf(expiracao))
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS256, chave)
                .compact();
        log.info("[finaliza] TokenService - criação de token");
		return token;
    }

    public Optional<String> getUsuario(String token) {
        try {
            log.info("[inicio] TokenService - extração do ID do Token");
            var claims = Jwts.parser().setSigningKey(chave).parseClaimsJws(token).getBody();
            log.info("[finaliza] TokenService - extração do ID do Token");
            return Optional.ofNullable(claims.getSubject());
        } catch (SignatureException ex) {
            return Optional.empty();
        } catch (ExpiredJwtException ex) {
            var claims = ex.getClaims();
            log.info("[finaliza] TokenService - extração do ID do Token");
            return Optional.ofNullable(claims.getSubject());
        }
    }

    public Optional<String> getUsuarioByBearerToken(String bearerToken) {
        log.info("[inicio] TokenService - getUsuarioByBearerToken");
        String token = bearerToken.substring(7, bearerToken.length());
        log.info(token);
        log.info("[finaliza] TokenService - getUsuarioByBearerToken");
        return this.getUsuario(token);
    }

}
