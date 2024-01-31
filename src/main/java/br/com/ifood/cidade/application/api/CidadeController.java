package br.com.ifood.cidade.application.api;

import br.com.ifood.cidade.application.api.dto.*;
import br.com.ifood.cidade.application.service.CidadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CidadeController implements CidadeAPI{


    private final CidadeService cidadeService;

    @Override
    public CidadeResponse postCidade(CidadeRequest cidadeRequest) {
        log.info("[inicia] CidadeController - postCidade");
        CidadeResponse cidadeCriada = cidadeService.criaCidade(cidadeRequest);
        log.info("[finaliza] CidadeController - postCidade");
        return cidadeCriada;
    }

    @Override
    public List<CidadeListResponse> getTodasCidades() {
        log.info("[inicia] CidadeController - getTodasCidades");
        List<CidadeListResponse> cidades = cidadeService.listaTodasCidades();
        log.info("[finaliza] CidadeController - getTodasCidades");
        return cidades;
    }

    @Override
    public CidadeDetalhadaReponse getCidadeAtravesId(Long idCidade) {
        log.info("[inicia] CidadeController - getCidadeAtravesId");
        CidadeDetalhadaReponse cidade = cidadeService.buscaCidadeAtravesId(idCidade);
        log.info("[finaliza] CidadeController - getCidadeAtravesId");
        return cidade;
    }

    @Override
    public void deletaCidadeAtravesId(Long idCidade) {
        log.info("[inicia] CidadeController - deletaCidadeAtravesId");
        cidadeService.deletaCidadeAtravesId(idCidade);
        log.info("[finaliza] CidadeController - deletaCidadeAtravesId");

    }

    @Override
    public void patchAlteraCidade(Long idCidade, CidadeAlteracaoRequest cidadeAlteracaoRequest) {
        log.info("[inicia] CidadeController - patchAlteraCidade");
        cidadeService.alteraCidade(idCidade,cidadeAlteracaoRequest);
        log.info("[finaliza] CidadeController - patchAlteraCidade");
    }
}
