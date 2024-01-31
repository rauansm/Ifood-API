package br.com.ifood.produto.application.service;

import br.com.ifood.produto.application.api.dto.ProdutoRequest;
import br.com.ifood.produto.application.api.dto.ProdutoResponse;
import br.com.ifood.produto.application.repository.ProdutoRepository;
import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.application.repository.RestauranteRepository;
import br.com.ifood.restaurante.domain.Restaurante;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProdutoApplicationService implements ProdutoService {

    private final RestauranteRepository restauranteRepository;
    private final ProdutoRepository produtoRepository;
    @Transactional
    @Override
    public ProdutoResponse criaProduto(Long idRestaurante, ProdutoRequest produtoRequest) {
        log.info("[inicia] ProdutoApplicationService - criaProduto");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        Produto produto = produtoRepository.salva(new Produto(restaurante, produtoRequest));
        log.info("[finaliza] ProdutoApplicationService - criaProduto");
        return new ProdutoResponse(produto);
    }

    @Override
    public List<ProdutoResponse> listaProdutosDoRestauranteAtravesId(Long idRestaurante, Boolean incluirInativos) {
        log.info("[inicia] ProdutoApplicationService - listaProdutosDoRestauranteAtravesId");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        List<Produto> produtos ;
        if (incluirInativos) {
            produtos = produtoRepository.buscaTodosProdutosDoRestaurante(restaurante);
        } else {
            produtos = produtoRepository.buscaTodosProdutosAtivoDoRestaurante(restaurante);
        }
        log.info("[finaliza] ProdutoApplicationService - listaProdutosDoRestauranteAtravesId");
        return ProdutoResponse.converte(produtos);
    }

    @Override
    public ProdutoResponse buscaProdutoDoRestaurante(Long idRestaurante, Long idProduto) {
        log.info("[inicia] ProdutoApplicationService - buscaProdutoDoRestaurante");
        Produto produto = produtoRepository.buscaProdutoDoRestaurante( idRestaurante,idProduto);
        log.info("[finaliza] ProdutoApplicationService - buscaProdutoDoRestaurante");
        return new ProdutoResponse(produto);
    }
    @Transactional
    @Override
    public void alteraProduto(Long idProduto, Long idRestaurante, ProdutoRequest produtoRequest) {
        log.info("[inicia] ProdutoApplicationService - alteraProduto");
        Produto produto = produtoRepository.buscaProdutoDoRestaurante(idRestaurante, idProduto);
        produto.altera(produtoRequest);
        produtoRepository.salva(produto);
        log.info("[finaliza] ProdutoApplicationService - alteraProduto");

    }

//    @Override
//    public FotoProdutoResponse adicionarFoto(Long idRestaurante, Long idProduto,   FotoProdutoRequest fotoProdutoRequest) {
//        log.info("[inicia] ProdutoApplicationService - adicionarFoto");
//        Produto produto = produtoRepository.buscaProdutoDoRestaurante(idRestaurante,idProduto);
//        FotoProduto foto = produtoRepository.salvaFotoProduto(new FotoProduto(produto,fotoProdutoRequest));
//        log.info("[finaliza] ProdutoApplicationService - adicionarFoto");
//    return new FotoProdutoResponse(foto);
//    }
}
