package br.com.ifood.produto.application.api;

import br.com.ifood.produto.application.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ProdutoController implements ProdutoAPI{
    private final ProdutoService produtoService;

    @Override
    public ProdutoResponse postProduto(Long idRestaurante, ProdutoRequest produtoRequest) {
        log.info("[inicia] ProdutoController - postProduto");
        ProdutoResponse produtoCriado = produtoService.criaProduto(idRestaurante,produtoRequest);
        log.info("[finaliza] ProdutoController - postProduto");
        return produtoCriado;
    }

    @Override
    public List<ProdutoResponse> getProdutosDoRestauranteAtravesId(Long idRestaurante, Boolean incluirInativos) {
        log.info("[inicia] ProdutoController - getProdutosDoRestauranteAtravesId");
        List<ProdutoResponse> produtosRestaurante = produtoService.listaProdutosDoRestauranteAtravesId(idRestaurante, incluirInativos);
        log.info("[finaliza] ProdutoController - getProdutosDoRestauranteAtravesId");
        return produtosRestaurante;
    }

    @Override
    public ProdutoResponse getProdutoDoRestaurante(Long idRestaurante, Long idProduto) {
        log.info("[inicia] ProdutoController - getProdutoDoRestaurante");
        ProdutoResponse produto = produtoService.buscaProdutoDoRestaurante(idRestaurante,idProduto);
        log.info("[finaliza] ProdutoController - getProdutoDoRestaurante");
        return produto;
    }

    @Override
    public void putAlteraProduto(Long idRestaurante, Long idProduto, ProdutoRequest produtoRequest) {
        log.info("[inicia] ProdutoController - putAlteraProduto");
        produtoService.alteraProduto(idProduto,idRestaurante, produtoRequest);
        log.info("[finaliza] ProdutoController - putAlteraProduto");
    }

//    @Override
//    public FotoProdutoResponse putAdicionarFoto(Long idRestaurante, Long idProduto, FotoProdutoRequest fotoProdutoRequest) {
//        log.info("[inicia] ProdutoController - putAdicionarFoto");
//        FotoProdutoResponse  foto = produtoService.adicionarFoto(idRestaurante,idProduto,fotoProdutoRequest);
//        log.info("[finaliza] ProdutoController - putAdicionarFoto");
//        return foto;

}
