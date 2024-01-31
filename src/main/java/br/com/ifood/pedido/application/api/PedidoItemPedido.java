package br.com.ifood.pedido.application.api;

import br.com.ifood.pedido.domain.ItemPedido;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PedidoItemPedido {

    private Long idProduto;

    private String produtoNome;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

    private String observacao;

    public PedidoItemPedido(ItemPedido itemPedido) {
        this.idProduto = itemPedido.getProduto().getIdProduto();
        this.produtoNome = itemPedido.getProduto().getNome();
        this.quantidade = itemPedido.getQuantidade();
        this.precoUnitario = itemPedido.getPrecoUnitario();
        this.precoTotal = itemPedido.getPrecoTotal();
        this.observacao = itemPedido.getObservacao();
    }


    public static List<PedidoItemPedido> converte(List<ItemPedido> itens) {
        return itens.stream()
                .map(PedidoItemPedido::new)
                .collect(Collectors.toList());
    }
}
