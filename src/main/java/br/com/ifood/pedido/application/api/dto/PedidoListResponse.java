package br.com.ifood.pedido.application.api.dto;

import br.com.ifood.pedido.domain.Pedido;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PedidoListResponse {


    private String codigo;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private String status;

    private OffsetDateTime dataCriacao;

    private PedidoRestaurante restaurante;
    private PedidoUsuario cliente;

    public static List<PedidoListResponse> converte(Page<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoListResponse::new)
                .collect(Collectors.toList());
    }

    public PedidoListResponse(Pedido pedido) {
        this.codigo = pedido.getCodigoPedido();
        this.subtotal = pedido.getSubtotal();
        this.taxaFrete = pedido.getTaxaFrete();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus().toString();
        this.dataCriacao = pedido.getDataCriacao();
        this.restaurante = new PedidoRestaurante(pedido.getRestaurante());
        this.cliente = new PedidoUsuario(pedido.getCliente());
    }


}
