package br.com.ifood.pedido.application.api.dto;

import br.com.ifood.formapagamento.domain.FormaPagamento;
import lombok.Value;

@Value
public class PedidoFormaPagamento {
    private Long idPagamento;
    public String descricao;

    public PedidoFormaPagamento(FormaPagamento formaPagamento) {
        this.idPagamento = formaPagamento.getIdPagamento();
        this.descricao = formaPagamento.getDescricao();
    }
}
