package br.com.ifood.produto.infra;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import br.com.ifood.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

//    @Override
//    public void excluirFoto(Long fotoId) {
//
//    }


}
