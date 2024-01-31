package br.com.ifood.cidade.application.api.dto;

import br.com.ifood.cidade.domain.Cidade;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class CidadeListResponse {
    private Long idCidade;
    private String nome;
    private EstadoResponse estado;

    public CidadeListResponse(Cidade cidade) {
        this.idCidade   = cidade.getIdCidade();
        this.nome   = cidade.getNome();
        this.estado = new EstadoResponse(cidade.getEstado());

    }

    public static List<CidadeListResponse> converte(List<Cidade> cidades) {
        return cidades.stream()
                .map(CidadeListResponse::new)
                .collect(Collectors.toList());
    }
}
