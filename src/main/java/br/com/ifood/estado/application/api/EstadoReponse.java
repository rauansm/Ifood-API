package br.com.ifood.estado.application.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EstadoReponse {

    private Long idEstado;
    private String nome;

}
