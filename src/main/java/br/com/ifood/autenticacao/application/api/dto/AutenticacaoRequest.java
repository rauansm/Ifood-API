package br.com.ifood.autenticacao.application.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class AutenticacaoRequest {
	@NotNull
	@NotBlank(message = "O usuário não pode estar em branco!")
	@Email
	private String usuario;
	@NotBlank(message = "A senha não pode estar em branco!")
	@Size(min = 7, max = 20)
	private String senha;

	public UsernamePasswordAuthenticationToken getUserPassToken() {
		return new UsernamePasswordAuthenticationToken(usuario, senha );
	}
}
