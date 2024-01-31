package br.com.ifood.cozinha.application.service;

import br.com.ifood.cozinha.application.api.*;
import br.com.ifood.cozinha.application.repository.CozinhaRepository;
import br.com.ifood.cozinha.domain.Cozinha;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CozinhaApplicationService implements CozinhaService{

    private final CozinhaRepository cozinhaRepository;
    @Transactional
    @Override
    public CozinhaResponse criaCozinha(CozinhaRequest cozinhaRequest) {
        log.info("[inicio] CozinhaApplicationService - criaCozinha");
        Cozinha cozinha = cozinhaRepository.salva(new Cozinha(cozinhaRequest));
        log.info("[finaliza] CozinhaApplicationService - criaCozinha");
        return CozinhaResponse.builder()
                .idCozinha(cozinha.getIdCozinha())
                .nome(cozinha.getNome())
                .build();
    }

    @Override
    public List<CozinhaListResponse> listaTodasCozinhas() {
        log.info("[inicio] CozinhaApplicationService - listaTodasCozinhas");
        List<Cozinha> cozinhas = cozinhaRepository.buscaTodasCozinhas();
        log.info("[finaliza] CozinhaApplicationService - listaTodasCozinhas");
        return CozinhaListResponse.converte(cozinhas);
    }

    @Override
    public CozinhaDetalhadaResponse buscaCozinhaAtravesDoId(Long idCozinha) {
        log.info("[inicio] CozinhaApplicationService - buscaCozinhaAtravesDoId");
        Cozinha cozinhaDetalhada = cozinhaRepository.buscaCozinhaPeloId(idCozinha);
        log.info("[finaliza] CozinhaApplicationService - buscaCozinhaAtravesDoId");
        return new CozinhaDetalhadaResponse(cozinhaDetalhada);
    }
    @Transactional
    @Override
    public void deletaCozinhaAtravesId(Long idCozinha) {
        log.info("[inicio] CozinhaApplicationService - deletaCozinhaAtravesId");
        Cozinha cozinha = cozinhaRepository.buscaCozinhaPeloId(idCozinha);
        cozinhaRepository.deletaCozinha(cozinha);
        log.info("[finaliza] CozinhaApplicationService - deletaCozinhaAtravesId");


    }
    @Transactional
    @Override
    public void alteraCozinha(Long idCozinha, CozinhaAlteracaoRequest cozinhaAlteracaoRequest) {
        log.info("[inicio] CozinhaApplicationService - alteraCozinha");
        Cozinha cozinha = cozinhaRepository.buscaCozinhaPeloId(idCozinha);
        cozinha.altera(cozinhaAlteracaoRequest);
        cozinhaRepository.salva(cozinha);
        log.info("[finaliza] CozinhaApplicationService - alteraCozinha");

    }
}
