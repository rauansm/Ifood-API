package br.com.ifood.estado.application.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EstadoAlteracaoRequest {
    private String nome;

    @JsonCreator
    public EstadoAlteracaoRequest(@JsonProperty("nome") String nome) {

        this.nome = nome;
    }
}
