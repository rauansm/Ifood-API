package br.com.ifood.produto.application.service;

import br.com.ifood.fotoproduto.application.api.FotoProdutoRequest;
import br.com.ifood.produto.application.api.ProdutoRequest;
import br.com.ifood.produto.application.api.ProdutoResponse;

import java.util.List;

public interface ProdutoService {
    ProdutoResponse criaProduto(Long idRestaurante, ProdutoRequest produtoRequest);

    List<ProdutoResponse> listaProdutosDoRestauranteAtravesId(Long idRestaurante, Boolean incluirInativos);

    ProdutoResponse buscaProdutoDoRestaurante(Long idRestaurante, Long idProduto);

    void alteraProduto(Long idProduto, Long idRestaurante, ProdutoRequest produtoRequest);

//    FotoProdutoResponse adicionarFoto(Long idRestaurante, Long idProduto,   FotoProdutoRequest fotoProdutoRequest);
}
