package br.com.ifood.produto.application.repository;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.domain.Restaurante;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Produto salva(Produto produto);

    List<Produto> buscaTodosProdutosDoRestaurante(Restaurante restaurante);

    List<Produto> buscaTodosProdutosAtivoDoRestaurante(Restaurante restaurante);

   Produto buscaProdutoDoRestaurante(Long idRestaurante, Long idProduto);

//    FotoProduto salvaFotoProduto(FotoProduto fotoProduto);


    Optional<FotoProduto> findFotoById(Long idProduto, Long idRestaurante);

//    void deletaFoto(Long fotoExistente);
}
