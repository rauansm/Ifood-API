package br.com.ifood.produto.infra;

import br.com.ifood.handler.APIException;
import br.com.ifood.produto.application.repository.ProdutoRepository;
import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.domain.Restaurante;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProdutoRepositoryJPA implements ProdutoRepository {

    private final ProdutoSpringDataJPA produtoSpringDataJPA;

    @Override
    public Produto salva(Produto produto) {
        log.info("[inicia] ProdutoRepositoryJPA - salva");
        produtoSpringDataJPA.save(produto);
        log.info("[finaliza] ProdutoRepositoryJPA - salva");
        return produto;
    }

    @Override
    public List<Produto> buscaTodosProdutosDoRestaurante(Restaurante restaurante) {
        log.info("[inicia] ProdutoRepositoryJPA - buscaTodosProdutosDoRestaurante");
        List<Produto> todosProdutos = produtoSpringDataJPA.findTodosByRestaurante(restaurante);
        log.info("[finaliza] ProdutoRepositoryJPA - buscaTodosProdutosDoRestaurante");
        return todosProdutos;
    }

    @Override
    public List<Produto> buscaTodosProdutosAtivoDoRestaurante(Restaurante restaurante) {
        log.info("[inicia] ProdutoRepositoryJPA - buscaTodosProdutosAtivoDoRestaurante");
        List<Produto> produtosAtivos = produtoSpringDataJPA.findAtivosByRestaurante(restaurante);
        log.info("[finaliza] ProdutoRepositoryJPA - buscaTodosProdutosAtivoDoRestaurante");
        return produtosAtivos;
    }

    @Override
    public Produto buscaProdutoDoRestaurante(Long idRestaurante, Long idProduto) {
        log.info("[inicia] ProdutoRepositoryJPA - buscaProdutoDoRestaurante");
        Optional<Produto> produto = produtoSpringDataJPA.findByRestaurante(idRestaurante, idProduto);
        log.info("[finaliza] ProdutoRepositoryJPA - buscaProdutoDoRestaurante");
        return produto.orElseThrow(() -> APIException.entidadeNaoEncontrada(String.format("Produto com Id %s não encontrado", idProduto)));
    }

}
