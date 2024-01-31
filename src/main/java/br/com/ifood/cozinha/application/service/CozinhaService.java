package br.com.ifood.cozinha.application.service;

import br.com.ifood.cozinha.application.api.*;

import java.util.List;

public interface CozinhaService {

    CozinhaResponse criaCozinha(CozinhaRequest cozinhaRequest);

    List<CozinhaListResponse> listaTodasCozinhas();

    CozinhaDetalhadaResponse buscaCozinhaAtravesDoId(Long idCozinha);

    void deletaCozinhaAtravesId(Long idCozinha);

    void alteraCozinha(Long idCozinha, CozinhaAlteracaoRequest cozinhaAlteracaoRequest);
}
