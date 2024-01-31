package br.com.ifood.grupo.application.repository;

import br.com.ifood.grupo.domain.Grupo;

import java.util.List;

public interface GrupoRepository {
    Grupo salva(Grupo grupo);

    List<Grupo> buscaTodosGrupos();

    Grupo buscaGrupoAtravesId(Long idGrupo);

    void deletaGrupo(Grupo grupo);
}
