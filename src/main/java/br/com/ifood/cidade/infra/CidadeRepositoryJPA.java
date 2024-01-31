package br.com.ifood.cidade.infra;

import br.com.ifood.cidade.application.repository.CidadeRepository;
import br.com.ifood.cidade.domain.Cidade;
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
public class CidadeRepositoryJPA implements CidadeRepository {


    private final CidadeSpringDataJPA cidadeSpringDataJPA;


    @Override
    public Cidade salva(Cidade cidade) {
        log.info("[inicio] CidadeRepositoryJPA - salva");
        Cidade cidadeCriada = cidadeSpringDataJPA.save(cidade);
        log.info("[finaliza] CidadeRepositoryJPA - salva");
        return cidadeCriada;
    }

    @Override
    public List<Cidade> buscaTodasCidades() {
        log.info("[inicio] CidadeRepositoryJPA - buscaTodasCidades");
        List<Cidade> cidades = cidadeSpringDataJPA.findAll();
        log.info("[finaliza] CidadeRepositoryJPA - buscaTodasCidades");
        return cidades;
    }

    @Override
    public Cidade buscaCidadeAtravesId(Long idCidade) {
        log.info("[inicio] CidadeRepositoryJPA - buscaCidadeAtravesId");
        Optional<Cidade> cidade = cidadeSpringDataJPA.findById(idCidade);
        log.info("[finaliza] CidadeRepositoryJPA - buscaCidadeAtravesId");
        return cidade.orElseThrow(() -> APIException.EntidadeNaoEncontrada(String.format("Cidade de Id %s não encontrada", idCidade)));
    }

    @Override
    public void deletaCidade(Cidade cidade) {
       try {
           log.info("[inicio] CidadeRepositoryJPA - deletaCidadeAtravesId");
           cidadeSpringDataJPA.delete(cidade);
           cidadeSpringDataJPA.flush();
           log.info("[finaliza] CidadeRepositoryJPA - deletaCidadeAtravesId");
       }catch (DataIntegrityViolationException e) {
           throw APIException.EntidadeEmUso(String.format("Cidade de código %d não pode ser removida, pois está em uso", cidade.getIdCidade()));
       }
    }
}
