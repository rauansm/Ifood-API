package br.com.ifood.grupo.application.api;

import br.com.ifood.grupo.application.service.GrupoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class GrupoController implements GrupoAPI{
    private final GrupoService grupoService;
    @Override
    public GrupoResponse postNovoGrupo(GrupoRequest grupoRequest) {
        log.info("[inicia] GrupoController - postNovoGrupo");
        GrupoResponse grupoCriado = grupoService.criaGrupo(grupoRequest);
        log.info("[finaliza] GrupoController - postNovoGrupo");
        return grupoCriado;
    }

    @Override
    public List<GrupoResponse> getTodosGrupos() {
        log.info("[inicia] GrupoController - getTodosGrupos");
        List<GrupoResponse> grupos = grupoService.listaTodosGrupos();
        log.info("[finaliza] GrupoController - getTodosGrupos");
        return grupos;
    }

    @Override
    public GrupoResponse getGrupoAtravesId(Long idGrupo) {
        log.info("[inicia] GrupoController - getGrupoAtravesId");
        GrupoResponse grupo = grupoService.buscaGrupoAtravesId(idGrupo);
        log.info("[finaliza] GrupoController - getGrupoAtravesId");
        return grupo;
    }

    @Override
    public void patchAlteraGrupo(Long idGrupo, GrupoRequest grupoRequest) {
        log.info("[inicia] GrupoController - patchAlteraGrupo");
        grupoService.alteraGrupo(idGrupo, grupoRequest);
        log.info("[finaliza] GrupoController - patchAlteraGrupo");
    }

    @Override
    public void deletaGrupo(Long idGrupo) {
        log.info("[inicia] GrupoController - deletaGrupo");
        grupoService.deletaGrupo(idGrupo);
        log.info("[finaliza] GrupoController - deletaGrupo");

    }

    @Override
    public List<GrupoPermissaoResponse> getTodasPermissoesDoGrupo(Long idGrupo) {
        log.info("[inicia] GrupoController - getTodasPermissoesDoGrupo");
        List<GrupoPermissaoResponse> permissoes = grupoService.listaTodasPermissoesDoGrupo(idGrupo);
        log.info("[finaliza] GrupoController - getTodasPermissoesDoGrupo");
        return permissoes;
    }

    @Override
    public void associarPermissao(Long idGrupo, Long idPermissao) {
        log.info("[inicia] GrupoController - associarPermissao");
        grupoService.associarPermissao(idGrupo,idPermissao);
        log.info("[finaliza] GrupoController - associarPermissao");

    }

    @Override
    public void desassociarPermissao(Long idGrupo, Long idPermissao) {
        log.info("[inicia] GrupoController - desassociarPermissao");
        grupoService.desassociarPermissao(idGrupo,idPermissao);
        log.info("[finaliza] GrupoController - desassociarPermissao");

    }


}
