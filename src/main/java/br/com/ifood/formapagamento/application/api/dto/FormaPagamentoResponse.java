package br.com.ifood.formapagamento.application.api.dto;

import br.com.ifood.formapagamento.domain.FormaPagamento;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class FormaPagamentoResponse {

    private Long idPagamento;
    private String descricao;

    public static List<FormaPagamentoResponse> converte(List<FormaPagamento> formasPagamento) {
       return formasPagamento.stream()
                .map(FormaPagamentoResponse::new)
                .collect(Collectors.toList());
    }

    public FormaPagamentoResponse(FormaPagamento formaPagamento) {
        this.idPagamento = formaPagamento.getIdPagamento();
        this.descricao = formaPagamento.getDescricao();
    }
}
