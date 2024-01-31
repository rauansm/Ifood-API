package br.com.ifood.fotoproduto.application.service;

import br.com.ifood.fotoproduto.application.api.dto.FotoProdutoRequest;
import br.com.ifood.fotoproduto.application.api.dto.FotoProdutoResponse;
import br.com.ifood.fotoproduto.infra.FotoStorage;

import java.io.IOException;

public interface FotoProdutoService {
    FotoProdutoResponse adicionaNovaFoto(Long idProduto, FotoProdutoRequest fotoProdutoRequest,Long idRestaurante) throws IOException;

    void deletaFotoProduto(Long idFotoProduto, Long idRestaurante, Long idProduto);

    FotoStorage.FotoRecuperada recuperaFoto(Long idRestaurante, Long idProduto, Long idFotoProduto);
}
