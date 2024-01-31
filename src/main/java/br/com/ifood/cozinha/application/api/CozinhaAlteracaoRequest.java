package br.com.ifood.cozinha.application.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;
@Value
public class CozinhaAlteracaoRequest {
    @NotBlank
    private String nome;

    @JsonCreator
    public CozinhaAlteracaoRequest(@JsonProperty("nome") String nome) {
        this.nome = nome;
    }
}
