package br.com.ifood.cidade.application.api.dto;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Value
public class CidadeAlteracaoRequest {

    @NotBlank
    private String nome;
    @Valid
    private EstadoRequestDTO estado;

}
