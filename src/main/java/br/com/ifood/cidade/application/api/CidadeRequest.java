package br.com.ifood.cidade.application.api;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Value
public class CidadeRequest {
    @NotBlank
    private String nome;
    @Valid
    private EstadoRequestDTO estado;


}
