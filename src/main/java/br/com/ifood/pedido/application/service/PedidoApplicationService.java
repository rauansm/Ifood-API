package br.com.ifood.pedido.application.service;

import br.com.ifood.cidade.application.repository.CidadeRepository;
import br.com.ifood.cidade.domain.Cidade;
import br.com.ifood.filtro.PedidoFiltro;
import br.com.ifood.formapagamento.application.repository.FormaPagamentoRepository;
import br.com.ifood.formapagamento.domain.FormaPagamento;
import br.com.ifood.pedido.application.api.*;
import br.com.ifood.pedido.application.repository.PedidoRepository;
import br.com.ifood.pedido.domain.ItemPedido;
import br.com.ifood.pedido.domain.Pedido;
import br.com.ifood.produto.application.repository.ProdutoRepository;
import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.application.repository.RestauranteRepository;
import br.com.ifood.restaurante.domain.Restaurante;
import br.com.ifood.usuario.application.repository.UsuarioRepository;
import br.com.ifood.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService{

    private final PedidoRepository pedidoRepository;
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;

    @Transactional
    @Override
    public PedidoResponse emitirPedido(String idCliente, PedidoRequest pedidoRequest) {
        log.info("[inicia] PedidoApplicationService - emitirPedido");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idCliente);
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(pedidoRequest.getIdRestaurante());
        FormaPagamento formaPagamento = formaPagamentoRepository.buscaFormaPagamentoAtravesId(pedidoRequest.getIdFormaPagamento());
        Cidade cidade = cidadeRepository.buscaCidadeAtravesId(pedidoRequest.getEnderecoEntrega().getIdCidade());
        restaurante.naoAceitaFormaPagamento(formaPagamento);
        Pedido pedido = new Pedido(pedidoRequest,usuario,formaPagamento, restaurante,cidade);
        pedido.setItens(transformarListaItensPedido(pedidoRequest.getItens(), restaurante,pedido));
        pedido.calculaValorTotal();
        pedidoRepository.salva(pedido);
        log.info("[finaliza] PedidoApplicationService - emitirPedido");
        return new PedidoResponse(pedido);
    }

    private  List<ItemPedido>  transformarListaItensPedido(List<ItemPedidoResquest> itensRequest, Restaurante restaurante, Pedido pedido) {
                    List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoResquest itemRequest : itensRequest) {
            Produto produto = produtoRepository.buscaProdutoDoRestaurante(restaurante.getIdRestaurante(), itemRequest.getIdProduto());

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setObservacao(itemRequest.getObservacao());
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemRequest.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());

            itens.add(itemPedido);
        }

        return itens;
    }


    @Override
    public PedidoDetalhadoResponse buscaPedidoAtravesCodigo(String codigoPedido) {
        log.info("[inicia] PedidoApplicationService - buscaPedidoAtravesCodigo");
        Pedido pedido = pedidoRepository.buscaPedidoAtravesCodigo(codigoPedido);
        log.info("[finaliza] PedidoApplicationService - buscaPedidoAtravesCodigo");
        return new PedidoDetalhadoResponse(pedido);
    }

    @Override
    public Page<PedidoListResponse> listaTodosPedidos(PedidoFiltro pedidoFiltro, Pageable pageable) {
        log.info("[inicia] PedidoApplicationService - listaTodosPedidos");
        Page<Pedido> pedidos = pedidoRepository.buscaTodosPedidos(pedidoFiltro, pageable);
        List<PedidoListResponse> pageResponse = PedidoListResponse.converte(pedidos);
        Page<PedidoListResponse> pedidosPage = new PageImpl<>(pageResponse, pageable, pedidos.getTotalElements());
        log.info("[finaliza] PedidoApplicationService - listaTodosPedidos");
        return pedidosPage;
    }
    @Transactional
    @Override
    public void confirmaPedido(String codigoPedido) {
        log.info("[inicia] PedidoApplicationService - confirmaPedido");
        Pedido pedido = pedidoRepository.buscaPedidoAtravesCodigo(codigoPedido);
        pedido.confirmaPedido();
        pedidoRepository.salva(pedido);
        log.info("[finaliza] PedidoApplicationService - confirmaPedido");

    }
    @Transactional
    @Override
    public void entregaPedido(String codigoPedido) {
        log.info("[inicia] PedidoApplicationService - entregaPedido");
        Pedido pedido = pedidoRepository.buscaPedidoAtravesCodigo(codigoPedido);
        pedido.entregaPedido();
        pedidoRepository.salva(pedido);
        log.info("[inicia] PedidoApplicationService - entregaPedido");
    }
    @Transactional
    @Override
    public void cancelaPedido(String codigoPedido) {
        log.info("[inicia] PedidoApplicationService - cancelaPedido");
        Pedido pedido = pedidoRepository.buscaPedidoAtravesCodigo(codigoPedido);
        pedido.cancelaPedido();
        pedidoRepository.salva(pedido);
        log.info("[finaliza] PedidoApplicationService - cancelaPedido");
    }
}
