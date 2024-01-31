package br.com.ifood.usuario.application.api.dto;

import br.com.ifood.handler.APIException;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class UsuarioRequest {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 7, max = 30)
    private String senha;
    @NotBlank
    private String confirmaSenha;

    public void validaSenha() {
        if (!this.senha.equals(this.confirmaSenha)) {
            throw APIException.Negocio("Senhas não coincidem!");
        }
    }
}
