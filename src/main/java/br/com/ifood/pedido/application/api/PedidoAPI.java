package br.com.ifood.pedido.application.api;

import br.com.ifood.filtro.PedidoFiltro;
import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pedido")
public interface PedidoAPI {
    @CheckSecurity.Pedidos.PodeCriar
    @PostMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.CREATED)
    PedidoResponse postEmitirPedido (@PathVariable String idCliente,
                                     @Valid @RequestBody PedidoRequest pedidoRequest);
    @CheckSecurity.Pedidos.PodeBuscar
    @GetMapping("/{codigoPedido}")
    @ResponseStatus(code = HttpStatus.OK)
    PedidoDetalhadoResponse getPedidoAtravesCodigo (@PathVariable String codigoPedido);

    @CheckSecurity.Pedidos.PodePesquisar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    Page<PedidoListResponse> getPesquisar(PedidoFiltro pedidoFiltro,
                                          @PageableDefault(size = 10) Pageable pageable);

    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @PatchMapping("/{codigoPedido}/confirmacao")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchConfirmarPedido (@PathVariable String codigoPedido);

    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @PatchMapping("/{codigoPedido}/entregar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchEntregarPedido (@PathVariable String codigoPedido);

    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @PatchMapping("/{codigoPedido}/cancelamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchCancelarPedido(@PathVariable String codigoPedido);
}
