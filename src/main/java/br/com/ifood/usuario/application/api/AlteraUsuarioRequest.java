package br.com.ifood.usuario.application.api;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class AlteraUsuarioRequest {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;

}
