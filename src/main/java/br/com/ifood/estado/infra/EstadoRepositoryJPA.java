package br.com.ifood.estado.infra;

import br.com.ifood.estado.domain.Estado;
import br.com.ifood.estado.application.repository.EstadoRepository;
import br.com.ifood.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class EstadoRepositoryJPA implements EstadoRepository {

   private final EstadoSpringDataJPA estadoSpringDataJPA;

    @Override
    public Estado salva(Estado estado) {
        log.info("[inicia] EstadoRepositoryJPA - salva");
      Estado estadoCriado =  estadoSpringDataJPA.save(estado);
        log.info("[finaliza] EstadoRepositoryJPA - salva");
        return estadoCriado;
    }

    @Override
    public Estado buscaEstadoAtravesId(Long idEstado) {
        log.info("[inicia] EstadoRepositoryJPA - buscaEstadoAtravesId");
        Optional<Estado> estado = estadoSpringDataJPA.findById(idEstado);
        log.info("[finaliza] EstadoRepositoryJPA - buscaEstadoAtravesId");
        return estado.orElseThrow(() -> APIException.entidadeNaoEncontrada(String.format("Estado de Id %s não encontrado", idEstado)));
    }

    @Override
    public List<Estado> buscaTodosEstados() {
        log.info("[inicia] EstadoRepositoryJPA - buscaTodosEstados");
        List<Estado> estados = estadoSpringDataJPA.findAll();
        log.info("[finaliza] EstadoRepositoryJPA - buscaTodosEstados");
        return estados;

    }

    @Override
    public void deletaEstado(Estado estado) {
        try {
        log.info("[inicia] EstadoRepositoryJPA - deletaEstado");
        estadoSpringDataJPA.delete(estado);
        estadoSpringDataJPA.flush();
        log.info("[finaliza] EstadoRepositoryJPA - deletaEstado");
    } catch (DataIntegrityViolationException e) {
            throw APIException.entidadeEmUso(String.format("Estado de código %d não pode ser removido, pois está em uso", estado.getIdEstado()));
        }
    }
}
