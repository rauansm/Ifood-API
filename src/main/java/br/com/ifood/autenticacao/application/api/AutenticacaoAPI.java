package br.com.ifood.autenticacao.application.api;

import br.com.ifood.autenticacao.application.api.dto.AutenticacaoRequest;
import br.com.ifood.autenticacao.application.api.dto.TokenResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/public/v1/autenticacao")
public interface AutenticacaoAPI {

    @PostMapping
    TokenResponse autentica(@RequestBody @Valid AutenticacaoRequest autenticacaoRequest) throws AuthenticationException;
    @PostMapping("/reativacao")
    TokenResponse reativaAutenticacao(@RequestHeader("Authorization") String tokenExpirado) throws AuthenticationException;
}
