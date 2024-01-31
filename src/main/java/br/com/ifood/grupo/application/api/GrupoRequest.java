package br.com.ifood.grupo.application.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class GrupoRequest {
    @NotBlank
    private String nome;

    @JsonCreator
    public GrupoRequest(@JsonProperty("nome") String nome) {
        this.nome = nome;
    }
}
