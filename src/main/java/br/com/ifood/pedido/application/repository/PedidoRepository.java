package br.com.ifood.pedido.application.repository;

import br.com.ifood.filtro.PedidoFiltro;
import br.com.ifood.pedido.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoRepository {
    Pedido buscaPedidoAtravesCodigo(String codigoPedido);

    Page<Pedido> buscaTodosPedidos(PedidoFiltro pedidoFiltro, Pageable pageable);

    void salva(Pedido pedido);
}
