package br.com.ifood.fotoproduto.infra;

import br.com.ifood.fotoproduto.application.repository.FotoProdutoRepository;
import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.handler.APIException;
import br.com.ifood.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class FotoProdutoRepositoryJPA implements FotoProdutoRepository {
    private final FotoProdutoSpringDataJPA fotoProdutoSpringDataJPA;
    @Override
    public FotoProduto salvaFotoProduto(FotoProduto fotoProduto) {
        log.info("[inicia] FotoProdutoRepositoryJPA - salvaFotoProduto");
        fotoProdutoSpringDataJPA.save(fotoProduto);
        log.info("[finaliza] FotoProdutoRepositoryJPA - salvaFotoProduto");
        return fotoProduto;
    }

    @Override
    public FotoProduto buscaFotoProduto(Produto produto, Long idFotoProduto) {
        log.info("[inicia] FotoProdutoRepositoryJPA - buscaFotoProduto");
        Optional<FotoProduto> fotoProduto = fotoProdutoSpringDataJPA.findByProduto(produto,idFotoProduto);
        log.info("[finaliza] FotoProdutoRepositoryJPA - buscaFotoProduto");
        return fotoProduto.orElseThrow(() -> APIException.entidadeNaoEncontrada(String.format("Foto do Produto %s com Id %s não encontrada", produto.getNome(),idFotoProduto)));
    }

    @Override
    public void deletaFoto(FotoProduto fotoProduto) {
        log.info("[inicia] FotoProdutoRepositoryJPA - deletaFoto");
        fotoProdutoSpringDataJPA.delete(fotoProduto);
        log.info("[finaliza] FotoProdutoRepositoryJPA - deletaFoto");

    }
}
