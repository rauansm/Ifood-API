package br.com.ifood.estado.application.api;

import br.com.ifood.estado.domain.Estado;
import lombok.Value;

@Value
public class EstadoDetalhadoResponse {

    private Long idEstado;
    private String nome;

    public EstadoDetalhadoResponse(Estado estado) {
        this.nome = estado.getNome();
        this.idEstado = estado.getIdEstado();
    }
}
