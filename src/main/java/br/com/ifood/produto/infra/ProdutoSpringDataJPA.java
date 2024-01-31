package br.com.ifood.produto.infra;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.domain.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoSpringDataJPA extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {

    List<Produto> findTodosByRestaurante(Restaurante restaurante);

    @Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
    List<Produto> findAtivosByRestaurante (Restaurante restaurante);

    @Query("from Produto where restaurante_id = :restaurante and id_produto = :produto")
    Optional<Produto> findByRestaurante (@Param("restaurante") Long idRrestaurante,
                               @Param("produto") Long idProduto);
    @Query("select f from FotoProduto f join f.produto p where p.restaurante.id = :idRestaurante and f.produto = :idProduto")
    Optional<FotoProduto> findFotoById(Long idRestaurante, Long idProduto);

}

