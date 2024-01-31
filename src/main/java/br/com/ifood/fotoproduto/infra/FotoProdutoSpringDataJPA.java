package br.com.ifood.fotoproduto.infra;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FotoProdutoSpringDataJPA extends JpaRepository<FotoProduto, Long> {
    @Query("from FotoProduto where produto_id = :produto and id_foto_produto = :fotoProduto")
   Optional<FotoProduto> findByProduto(@Param("produto") Produto produto,
                                       @Param("fotoProduto") Long idFotoProduto);
}
