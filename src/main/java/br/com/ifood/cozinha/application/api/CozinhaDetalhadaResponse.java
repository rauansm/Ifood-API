package br.com.ifood.cozinha.application.api;

import br.com.ifood.cozinha.domain.Cozinha;
import lombok.Value;

@Value
public class CozinhaDetalhadaResponse {

    private Long idCozinha;
    private String nome;

    public CozinhaDetalhadaResponse(Cozinha cozinhaDetalhada) {
        this.idCozinha = cozinhaDetalhada.getIdCozinha();
        this.nome = cozinhaDetalhada.getNome();
    }
}
