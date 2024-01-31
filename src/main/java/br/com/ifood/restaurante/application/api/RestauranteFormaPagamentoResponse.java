package br.com.ifood.restaurante.application.api;

import br.com.ifood.formapagamento.domain.FormaPagamento;
import lombok.Value;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class RestauranteFormaPagamentoResponse {
    private Long idPagamento;
    private String descricao;

    public RestauranteFormaPagamentoResponse(FormaPagamento formaPagamento) {
        this.idPagamento = formaPagamento.getIdPagamento();
        this.descricao = formaPagamento.getDescricao();
    }


    public static Collection<RestauranteFormaPagamentoResponse> converte(Set<FormaPagamento> formasPagamento) {
        return formasPagamento.stream()
                .map(RestauranteFormaPagamentoResponse::new)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
