package br.com.ifood.cozinha.infra;

import br.com.ifood.cozinha.application.repository.CozinhaRepository;
import br.com.ifood.cozinha.domain.Cozinha;
import br.com.ifood.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class CozinhaRepositoryJPA implements CozinhaRepository {

    private final CozinhaSpringDataJPA cozinhaSpringDataJPA;


    @Override
    public Cozinha salva(Cozinha cozinha) {
        log.info("[inicio] CozinhaRepositoryJPA - salva");
             cozinhaSpringDataJPA.save(cozinha);
        log.info("[finaliza] CozinhaRepositoryJPA - salva");
        return cozinha;
    }

    @Override
    public List<Cozinha> buscaTodasCozinhas() {
        log.info("[inicio] CozinhaRepositoryJPA - buscaTodasCozinhas");
        List<Cozinha> cozinhas = cozinhaSpringDataJPA.findAll();
        log.info("[finaliza] CozinhaRepositoryJPA - buscaTodasCozinhas");
        return cozinhas;
    }

    @Override
    public Cozinha buscaCozinhaPeloId(Long idCozinha) {
        log.info("[inicio] CozinhaRepositoryJPA - buscaCozinhaPeloId");
        Optional<Cozinha> cozinha = cozinhaSpringDataJPA.findById(idCozinha);
        log.info("[finaliza] CozinhaRepositoryJPA - buscaCozinhaPeloId");
        return cozinha.orElseThrow(() ->  APIException.EntidadeNaoEncontrada(String.format("Cozinha de id %s não encontrada", idCozinha)));
    }

    @Override
    public void deletaCozinha(Cozinha cozinha) {
        try {
            log.info("[inicio] CozinhaRepositoryJPA - deletaCozinha");
            cozinhaSpringDataJPA.delete(cozinha);
            cozinhaSpringDataJPA.flush();
            log.info("[finaliza] CozinhaRepositoryJPA - deletaCozinha");
        } catch (DataIntegrityViolationException e) {
        throw APIException.EntidadeEmUso(String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinha.getIdCozinha()));
        }
    }
}
