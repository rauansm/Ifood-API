package br.com.ifood.grupo.infra;

import br.com.ifood.grupo.application.repository.GrupoRepository;
import br.com.ifood.grupo.domain.Grupo;
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
public class GrupoRepositoryJPA implements GrupoRepository {

    private final GrupoSpringDataJPA grupoSpringDataJPA;

    @Override
    public Grupo salva(Grupo grupo) {
        log.info("[inicia] GrupoRepositoryJPA - salva");
        grupoSpringDataJPA.save(grupo);
        log.info("[finaliza] GrupoRepositoryJPA - salva");
        return grupo;
    }

    @Override
    public List<Grupo> buscaTodosGrupos() {
        log.info("[inicia] GrupoRepositoryJPA - buscaTodosGrupos");
        List<Grupo> grupos = grupoSpringDataJPA.findAll();
        log.info("[finaliza] GrupoRepositoryJPA - buscaTodosGrupos");
        return grupos;
    }

    @Override
    public Grupo buscaGrupoAtravesId(Long idGrupo) {
        log.info("[inicia] GrupoRepositoryJPA - buscaGrupoAtravesId");
        Optional<Grupo> grupo = grupoSpringDataJPA.findById(idGrupo);
        log.info("[finaliza] GrupoRepositoryJPA - buscaGrupoAtravesId");
        return grupo.orElseThrow(() -> APIException.EntidadeNaoEncontrada(String.format("Grupo com Id %s não encontrado", idGrupo)));
    }

    @Override
    public void deletaGrupo(Grupo grupo) {
        try {
        log.info("[inicia] GrupoRepositoryJPA - deletaGrupo");
        grupoSpringDataJPA.delete(grupo);
        grupoSpringDataJPA.flush();
        log.info("[finaliza] GrupoRepositoryJPA - deletaGrupo");
    } catch (DataIntegrityViolationException e) {
        throw APIException.EntidadeEmUso(String.format("Grupo de código %d não pode ser removida, pois está em uso", grupo.getIdGrupo()));
    }
    }
}
