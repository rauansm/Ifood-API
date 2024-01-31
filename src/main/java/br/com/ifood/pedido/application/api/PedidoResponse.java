package br.com.ifood.pedido.application.api;

import br.com.ifood.pedido.domain.Pedido;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Value
public class PedidoResponse {
    private String codigo;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private String status;

    private OffsetDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;

    private LocalDateTime dataEntrega;

    private LocalDateTime dataCancelamento;

    private PedidoRestaurante restaurante;
    private PedidoUsuario cliente;
    private PedidoFormaPagamento formaPagamento;
    private PedidoEndereco enderecoEntrega;
    private List<PedidoItemPedido> itens;

    public PedidoResponse(Pedido pedido) {
        this.codigo = pedido.getCodigoPedido();
        this.subtotal = pedido.getSubtotal();
        this.taxaFrete = pedido.getTaxaFrete();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus().name();
        this.dataCriacao = pedido.getDataCriacao();
        this.dataConfirmacao = pedido.getDataConfirmacao();
        this.dataEntrega = pedido.getDataEntrega();
        this.dataCancelamento = pedido.getDataCancelamento();
        this.restaurante = new PedidoRestaurante(pedido.getRestaurante());
        this.cliente = new PedidoUsuario(pedido.getCliente());
        this.formaPagamento = new PedidoFormaPagamento(pedido.getFormaPagamento());
        this.enderecoEntrega = new PedidoEndereco(pedido.getEnderecoEntrega());
        this.itens = PedidoItemPedido.converte(pedido.getItens());
    }
}
