package br.com.ifood.cidade.application.service;

import br.com.ifood.cidade.application.api.*;

import java.util.List;

public interface CidadeService {
    CidadeResponse criaCidade(CidadeRequest cidadeRequest);

    List<CidadeListResponse> listaTodasCidades();

    CidadeDetalhadaReponse buscaCidadeAtravesId(Long idCidade);

    void deletaCidadeAtravesId(Long idCidade);

    void alteraCidade(Long idCidade, CidadeAlteracaoRequest cidadeAlteracaoRequest);
}
