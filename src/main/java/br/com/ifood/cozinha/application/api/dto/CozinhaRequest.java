package br.com.ifood.cozinha.application.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
@Builder
@Value
public class CozinhaRequest {
    @NotBlank
    private String nome;

    @JsonCreator
    public CozinhaRequest(@JsonProperty("nome") String nome) {
        this.nome = nome;
    }
}
