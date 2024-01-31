package br.com.ifood.pedido.application.service;

import br.com.ifood.pedido.application.api.dto.PedidoDetalhadoResponse;
import br.com.ifood.pedido.application.api.dto.PedidoListResponse;
import br.com.ifood.pedido.application.api.dto.PedidoRequest;
import br.com.ifood.pedido.application.api.dto.PedidoResponse;
import br.com.ifood.filtro.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {
    PedidoDetalhadoResponse buscaPedidoAtravesCodigo(String codigoPedido);

    Page<PedidoListResponse> listaTodosPedidos(PedidoFiltro pedidoFiltro, Pageable pageable);

    PedidoResponse emitirPedido(String idCliente, PedidoRequest pedidoRequest);

    void confirmaPedido(String codigoPedido);

    void entregaPedido(String codigoPedido);

    void cancelaPedido(String codigoPedido);
}
