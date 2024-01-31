package br.com.ifood.cozinha.application.api;

import br.com.ifood.cozinha.application.api.dto.*;
import br.com.ifood.cozinha.application.service.CozinhaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CozinhaController implements CozinhaAPI {

    private final CozinhaService cozinhaService;

    @Override
    public CozinhaResponse postCozinha(CozinhaRequest cozinhaRequest) {
        log.info("[inicia] CozinhaController - postCozinha");
        CozinhaResponse cozinhaCriada = cozinhaService.criaCozinha(cozinhaRequest);
        log.info("[finaliza] CozinhaController - postCozinha");
        return cozinhaCriada;
    }

    @Override
    public List<CozinhaListResponse> getTodasCozinhas() {
        log.info("[inicia] CozinhaController - getTodasCozinhas");
        List<CozinhaListResponse> cozinhas = cozinhaService.listaTodasCozinhas();
        log.info("[finaliza] CozinhaController - getTodasCozinhas");
        return cozinhas;
    }

    @Override
    public CozinhaDetalhadaResponse getCozinhasAtravesId(Long idCozinha) {
        log.info("[inicia] CozinhaController - getCozinhasAtravesId");
        CozinhaDetalhadaResponse cozinhaDetalhada = cozinhaService.buscaCozinhaAtravesDoId(idCozinha);
        log.info("[finaliza] CozinhaController - getCozinhasAtravesId");
        return cozinhaDetalhada;
    }

    @Override
    public void deletaCozinhaAtravesId(Long idCozinha) {
        log.info("[inicia] CozinhaController - deletaCozinhaAtravesId");
        cozinhaService.deletaCozinhaAtravesId(idCozinha);
        log.info("[finaliza] CozinhaController - deletaCozinhaAtravesId");

    }

    @Override
    public void putAlteraCozinha(Long idCozinha, CozinhaAlteracaoRequest cozinhaAlteracaoRequest) {
        log.info("[inicia] CozinhaController - patchAlteraCozinha");
        cozinhaService.alteraCozinha(idCozinha,cozinhaAlteracaoRequest);
        log.info("[finaliza] CozinhaController - patchAlteraCozinha");
    }
}
