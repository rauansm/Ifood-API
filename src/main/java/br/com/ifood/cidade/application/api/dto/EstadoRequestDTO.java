package br.com.ifood.cidade.application.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EstadoRequestDTO {

    @NotNull
    private Long idEstado;


    @JsonCreator
    public EstadoRequestDTO(@JsonProperty("idEstado") Long idEstado) {
        this.idEstado = idEstado;
    }

}
