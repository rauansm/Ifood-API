package br.com.ifood.produto.infra;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepositoryQueries {
    FotoProduto save (FotoProduto foto);


//    @Modifying
//    @Query("DELETE FROM FotoProduto f WHERE f.idProduto = :fotoId")
//    void excluirFoto(@Param("fotoId") Long fotoId);
}
