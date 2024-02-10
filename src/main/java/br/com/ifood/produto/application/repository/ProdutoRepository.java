package br.com.ifood.produto.application.repository;

import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.domain.Restaurante;

import java.util.List;

public interface ProdutoRepository {
    Produto salva(Produto produto);

    List<Produto> buscaTodosProdutosDoRestaurante(Restaurante restaurante);

    List<Produto> buscaTodosProdutosAtivoDoRestaurante(Restaurante restaurante);

   Produto buscaProdutoDoRestaurante(Long idRestaurante, Long idProduto);

}
