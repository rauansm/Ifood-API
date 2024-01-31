package br.com.ifood.fotoproduto.application.service;

import br.com.ifood.fotoproduto.application.api.FotoProdutoRequest;
import br.com.ifood.fotoproduto.application.repository.FotoProdutoRepository;
import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.fotoproduto.infra.FotoStorage;
import br.com.ifood.produto.application.repository.ProdutoRepository;
import br.com.ifood.fotoproduto.application.api.FotoProdutoResponse;
import br.com.ifood.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Log4j2
@RequiredArgsConstructor
public class FotoProdutoApplicationService implements FotoProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FotoProdutoRepository fotoProdutoRepository;
    private final FotoStorage fotoStorage;
    @Transactional
    @Override
    public FotoProdutoResponse adicionaNovaFoto(Long idProduto, FotoProdutoRequest fotoProdutoRequest, Long idRestaurante) throws IOException {
        log.info("[inicia] FotoProdutoApplicationService - adicionaNovaFoto");
        Produto produto = produtoRepository.buscaProdutoDoRestaurante(idRestaurante,idProduto);
        FotoProduto foto = fotoProdutoRepository.salvaFotoProduto(new FotoProduto(produto,fotoProdutoRequest));
        FotoStorage.NovaFoto novaFoto = FotoStorage.NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(fotoProdutoRequest.getArquivo().getInputStream())
                .contentType(foto.getContentType())
                .build();
        fotoStorage.armazenar(novaFoto);
        log.info("[finaliza] FotoProdutoApplicationService - adicionaNovaFoto");
        return new FotoProdutoResponse(foto);
    }


    @Transactional
    @Override
    public void deletaFotoProduto(Long idFotoProduto, Long idRestaurante, Long idProduto) {
        log.info("[inicia] FotoProdutoApplicationService - deletaFotoProduto");
        Produto produto = produtoRepository.buscaProdutoDoRestaurante(idRestaurante,idProduto);
        FotoProduto fotoProduto = fotoProdutoRepository.buscaFotoProduto(produto, idFotoProduto);
        fotoStorage.remover(fotoProduto.getNomeArquivo());
        fotoProdutoRepository.deletaFoto(fotoProduto);
        log.info("[finaliza] FotoProdutoApplicationService - deletaFotoProduto");
    }

    @Override
    public FotoStorage.FotoRecuperada recuperaFoto(Long idRestaurante, Long idProduto, Long idFotoProduto) {
        log.info("[inicia] FotoProdutoApplicationService - recuperaFoto");
        Produto produto = produtoRepository.buscaProdutoDoRestaurante(idRestaurante,idProduto);
        FotoProduto fotoProduto = fotoProdutoRepository.buscaFotoProduto(produto, idFotoProduto);
        FotoStorage.FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
        log.info("[finaliza] FotoProdutoApplicationService - recuperaFoto");
        return fotoRecuperada;
    }

}
