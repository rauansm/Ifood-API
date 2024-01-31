package br.com.ifood.grupo.application.service;

import br.com.ifood.grupo.application.api.dto.GrupoRequest;
import br.com.ifood.grupo.application.api.dto.GrupoResponse;
import br.com.ifood.grupo.application.repository.GrupoRepository;
import br.com.ifood.grupo.domain.Grupo;
import br.com.ifood.grupo.application.api.dto.GrupoPermissaoResponse;
import br.com.ifood.permissao.application.repository.PermissaoRepository;
import br.com.ifood.permissao.domain.Permissao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class GrupoApplicationService implements GrupoService{

    private final GrupoRepository grupoRepository;
    private final PermissaoRepository permissaoRepository;

    @Transactional
    @Override
    public GrupoResponse criaGrupo(GrupoRequest grupoRequest) {
        log.info("[inicia] GrupoApplicationService - criaGrupo");
        Grupo novoGrupo = grupoRepository.salva(new Grupo(grupoRequest));
        log.info("[finaliza] GrupoApplicationService - criaGrupo");
        return new GrupoResponse(novoGrupo);
    }

    @Override
    public List<GrupoResponse> listaTodosGrupos() {
        log.info("[inicia] GrupoApplicationService - listaTodosGrupos");
        List<Grupo> grupos = grupoRepository.buscaTodosGrupos();
        log.info("[finaliza] GrupoApplicationService - listaTodosGrupos");
        return GrupoResponse.converte(grupos);
    }

    @Override
    public GrupoResponse buscaGrupoAtravesId(Long idGrupo) {
        log.info("[inicia] GrupoApplicationService - buscaGrupoAtravesId");
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        log.info("[finaliza] GrupoApplicationService - buscaGrupoAtravesId");
        return new GrupoResponse(grupo);
    }
    @Transactional
    @Override
    public void alteraGrupo(Long idGrupo, GrupoRequest grupoRequest) {
        log.info("[inicia] GrupoApplicationService - alteraGrupo");
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        grupo.altera(grupoRequest);
        grupoRepository.salva(grupo);
        log.info("[finaliza] GrupoApplicationService - alteraGrupo");
    }
    @Transactional
    @Override
    public void deletaGrupo(Long idGrupo) {
        log.info("[inicia] GrupoApplicationService - deletaGrupo");
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        grupoRepository.deletaGrupo(grupo);
        log.info("[finaliza] GrupoApplicationService - deletaGrupo");

    }

    @Override
    public List<GrupoPermissaoResponse> listaTodasPermissoesDoGrupo(Long idGrupo) {
        log.info("[inicia] GrupoApplicationService - listaTodasPermissoesDoGrupo");
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        log.info("[finaliza] GrupoApplicationService - listaTodasPermissoesDoGrupo");
        return GrupoPermissaoResponse.converte(grupo.getPermissoes());
    }
    @Transactional
    @Override
    public void associarPermissao(Long idGrupo, Long idPermissao) {
        log.info("[inicia] GrupoApplicationService - associarPermissao");
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        Permissao permissao = permissaoRepository.buscaPermissaoAtravesId(idPermissao);
        grupo.associaPermissao(permissao);
        grupoRepository.salva(grupo);
        log.info("[finaliza] GrupoApplicationService - associarPermissao");

    }
    @Transactional
    @Override
    public void desassociarPermissao(Long idGrupo, Long idPermissao) {
        log.info("[inicia] GrupoApplicationService - desassociarPermissao");
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        Permissao permissao = permissaoRepository.buscaPermissaoAtravesId(idPermissao);
        grupo.desassociaPermissao(permissao);
        grupoRepository.salva(grupo);
        log.info("[finaliza] GrupoApplicationService - desassociarPermissao");
    }

}
