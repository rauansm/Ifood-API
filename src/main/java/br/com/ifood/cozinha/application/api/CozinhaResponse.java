package br.com.ifood.cozinha.application.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CozinhaResponse {

    private Long idCozinha;
    private String nome;

}
