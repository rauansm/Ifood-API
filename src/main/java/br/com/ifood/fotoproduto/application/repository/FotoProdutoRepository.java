package br.com.ifood.fotoproduto.application.repository;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.produto.domain.Produto;

public interface FotoProdutoRepository {
    FotoProduto salvaFotoProduto(FotoProduto fotoProduto);

    FotoProduto buscaFotoProduto(Produto produto, Long idFotoProduto);

    void deletaFoto(FotoProduto fotoProduto);
}
