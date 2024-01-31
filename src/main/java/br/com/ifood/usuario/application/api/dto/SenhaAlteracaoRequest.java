package br.com.ifood.usuario.application.api.dto;

import lombok.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Value
public class SenhaAlteracaoRequest {

    @NotBlank
    private String senhaAtual;
    @NotBlank
//    @Size(min = 7, max = 30)
    private String novaSenha;
    @NotBlank
    private String confirmaSenha;


}
