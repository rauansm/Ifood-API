package br.com.ifood.estado.application.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
    public class EstadoRequest {

    private String nome;

    @JsonCreator
    public EstadoRequest(@JsonProperty("nome") String nome) {

        this.nome = nome;
    }
}
