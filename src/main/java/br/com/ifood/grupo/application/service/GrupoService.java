package br.com.ifood.grupo.application.service;

import br.com.ifood.grupo.application.api.dto.GrupoRequest;
import br.com.ifood.grupo.application.api.dto.GrupoResponse;
import br.com.ifood.grupo.application.api.dto.GrupoPermissaoResponse;

import java.util.List;

public interface GrupoService {
    GrupoResponse criaGrupo(GrupoRequest grupoRequest);

    List<GrupoResponse> listaTodosGrupos();

    GrupoResponse buscaGrupoAtravesId(Long idGrupo);

    void alteraGrupo(Long idGrupo, GrupoRequest grupoRequest);

    void deletaGrupo(Long idGrupo);

    List<GrupoPermissaoResponse> listaTodasPermissoesDoGrupo(Long idGrupo);

    void associarPermissao(Long idGrupo, Long idPermissao);

    void desassociarPermissao(Long idGrupo, Long idPermissao);
}
