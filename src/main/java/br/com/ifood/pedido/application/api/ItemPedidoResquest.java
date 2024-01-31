package br.com.ifood.pedido.application.api;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Value
public class ItemPedidoResquest {

    @NotNull
    private Long idProduto;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    private String observacao;
}
