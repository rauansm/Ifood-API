package br.com.ifood.cidade.application.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CidadeResponse {

    private Long idCidade;
    private String nome;
    private  EstadoResponse estado;



}
