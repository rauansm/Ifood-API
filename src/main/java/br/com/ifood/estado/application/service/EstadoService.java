package br.com.ifood.estado.application.service;

import br.com.ifood.estado.application.api.dto.*;

import java.util.List;

public interface EstadoService {
    EstadoReponse criaEstado(EstadoRequest estadoRequest);

    EstadoDetalhadoResponse buscaEstadoAtravesId(Long idEstado);

    List<EstadoListResponse> listaTodosEstados();

    void deletaEstadoAtravesId(Long idEstado);

    void alteraEstado(Long idEstado, EstadoAlteracaoRequest estadoAlteracaoRequest);
}
