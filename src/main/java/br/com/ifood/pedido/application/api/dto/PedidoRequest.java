package br.com.ifood.pedido.application.api.dto;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Value
public class PedidoRequest {

    @Valid
    @NotNull
    private Long idRestaurante;

    @Valid
    @NotNull
    private EnderecoEntrega enderecoEntrega;

    @Valid
    @NotNull
    private Long idFormaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoResquest> itens;
}
