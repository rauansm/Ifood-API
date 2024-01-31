package br.com.ifood.cidade.application.api.dto;

import br.com.ifood.estado.domain.Estado;
import lombok.Value;

@Value

public class EstadoResponse {

    private String nome;

    public EstadoResponse(Estado estado) {
        this.nome = estado.getNome();
    }
}
