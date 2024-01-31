package br.com.ifood.pedido.application.api;

import br.com.ifood.core.data.PageableTranslator;
import br.com.ifood.filtro.PedidoFiltro;
import br.com.ifood.pedido.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PedidoController implements PedidoAPI{
   private final PedidoService pedidoService;

    @Override
    public PedidoResponse postEmitirPedido(String idCliente, PedidoRequest pedidoRequest) {
        log.info("[inicia] PedidoController - postEmitirPedido");
        PedidoResponse pedidoCriado = pedidoService.emitirPedido(idCliente,pedidoRequest);
        log.info("[finaliza] PedidoController - postEmitirPedido");
        return pedidoCriado;
    }

    @Override
    public PedidoDetalhadoResponse getPedidoAtravesCodigo(String codigoPedido) {
        log.info("[inicia] PedidoController - getPedidoAtravesId");
        PedidoDetalhadoResponse pedidoDetalhado = pedidoService.buscaPedidoAtravesCodigo(codigoPedido);
        log.info("[finaliza] PedidoController - getPedidoAtravesId");
        return pedidoDetalhado;
    }

    @Override
    public Page<PedidoListResponse> getPesquisar(PedidoFiltro pedidoFiltro, Pageable pageable) {
        log.info("[inicia] PedidoController - getTodosPedidos");
        Pageable pageableTraduzido = traduzirPageable(pageable);
        Page<PedidoListResponse> pedidos = pedidoService.listaTodosPedidos(pedidoFiltro, pageableTraduzido);
        log.info("[finaliza] PedidoController - getTodosPedidos");
        return pedidos;
    }

    @Override
    public void patchConfirmarPedido(String codigoPedido) {
        log.info("[inicia] PedidoController - patchConfirmarPedido");
        pedidoService.confirmaPedido(codigoPedido);
        log.info("[finaliza] PedidoController - patchConfirmarPedido");
    }

    @Override
    public void patchEntregarPedido(String codigoPedido) {
        log.info("[inicia] PedidoController - patchEntregarPedido");
        pedidoService.entregaPedido(codigoPedido);
        log.info("[finaliza] PedidoController - patchEntregarPedido");
    }

    @Override
    public void patchCancelarPedido(String codigoPedido) {
        log.info("[inicia] PedidoController - patchCancelarPedido");
        pedidoService.cancelaPedido(codigoPedido);
        log.info("[finaliza] PedidoController - patchCancelarPedido");
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        log.info("[inicia] PedidoController - traduzirPageable");
        var mapeamento = Map.of(
                "codigo", "codigo",
                "subtotal", "subtotal",
                "taxaFrete", "taxaFrete",
                "valorTotal", "valorTotal",
                "dataCriacao", "dataCriacao",
                "restaurante.nome", "restaurante.nome",
                "restaurante.id", "restaurante.idRestaurante",
                "cliente.id", "cliente.idCliente",
                "cliente.nome", "cliente.nome");
        log.info("[finaliza] PedidoController - traduzirPageable");
        return PageableTranslator.translate(apiPageable, mapeamento);
    }

}
