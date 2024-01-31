package br.com.ifood.estado.application.api.dto;

import br.com.ifood.estado.domain.Estado;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class EstadoListResponse {
    private Long idEstado;
    private String nome;

    public static List<EstadoListResponse> converte(List<Estado> estados) {
       return estados.stream()
                .map(EstadoListResponse::new)
                .collect(Collectors.toList());
    }

    public EstadoListResponse(Estado estado) {
        this.idEstado = estado.getIdEstado();
        this.nome =   estado.getNome();
    }
}
