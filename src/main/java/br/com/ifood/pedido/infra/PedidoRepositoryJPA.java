package br.com.ifood.pedido.infra;

import br.com.ifood.filtro.PedidoFiltro;
import br.com.ifood.handler.APIException;
import br.com.ifood.pedido.application.repository.PedidoRepository;
import br.com.ifood.pedido.domain.Pedido;
import br.com.ifood.pedido.infra.spec.PedidoSpecs;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PedidoRepositoryJPA implements PedidoRepository {
    private final PedidoSpringDataJPA pedidoSpringDataJPA;

    @Override
    public void salva(Pedido pedido) {
        log.info("[inicia] PedidoRepositoryJPA - salva");
        pedidoSpringDataJPA.save(pedido);
        log.info("[finaliza] PedidoRepositoryJPA - salva");
    }

    @Override
    public Pedido buscaPedidoAtravesCodigo(String codigoPedido) {
        log.info("[inicia] PedidoRepositoryJPA - buscaPedidoAtravesCodigo");
        Optional<Pedido> pedido = pedidoSpringDataJPA.findByCodigoPedido(codigoPedido);
        log.info("[finaliza] PedidoRepositoryJPA - buscaPedidoAtravesCodigo");
        return pedido.orElseThrow(() -> APIException.EntidadeNaoEncontrada(String.format("Pedido de código %s não encontrado", codigoPedido)));
    }

    @Override
    public Page<Pedido> buscaTodosPedidos(PedidoFiltro pedidoFiltro, Pageable pageable) {
        log.info("[inicia] PedidoRepositoryJPA - buscaTodosPedidos");
        Page<Pedido> pedidosPage = pedidoSpringDataJPA.findAll(PedidoSpecs.usandoFiltro(pedidoFiltro), pageable);
        log.info("[finaliza] PedidoRepositoryJPA - buscaTodosPedidos");
        return pedidosPage;
    }
}
