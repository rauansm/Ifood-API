package br.com.ifood.estado.application.api;

import br.com.ifood.estado.application.service.EstadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class EstadoController implements EstadoAPI{

    private final EstadoService estadoService;

    @Override
    public EstadoReponse postEstado(EstadoRequest estadoRequest) {
        log.info("[inicia] EstadoController - postEstado");
        EstadoReponse estadoCriado = estadoService.criaEstado(estadoRequest);
        log.info("[finaliza] EstadoController - postEstado");
        return estadoCriado;
    }

    @Override
    public EstadoDetalhadoResponse getEstadoAtravesId(Long idEstado) {
        log.info("[inicia] EstadoController - getEstadoAtravesId");
        EstadoDetalhadoResponse estado = estadoService.buscaEstadoAtravesId(idEstado);
        log.info("[finaliza] EstadoController - getEstadoAtravesId");
        return estado;
    }

    @Override
    public List<EstadoListResponse> getTodosEstados() {
        log.info("[inicia] EstadoController - getTodosEstados");
        List<EstadoListResponse> estados = estadoService.listaTodosEstados();
        log.info("[finaliza] EstadoController - getTodosEstados");
        return estados;
    }

    @Override
    public void deletaEstadoAtravesId(Long idEstado) {
        log.info("[inicia] EstadoController - deletaEstadoAtravesId");
        estadoService.deletaEstadoAtravesId(idEstado);
        log.info("[finaliza] EstadoController - deletaEstadoAtravesId");

    }

    @Override
    public void putAlteraEstado(Long idEstado, EstadoAlteracaoRequest estadoAlteracaoRequest) {
        log.info("[inicia] EstadoController - putAlteraEstado");
        estadoService.alteraEstado(idEstado,estadoAlteracaoRequest);
        log.info("[finaliza] EstadoController - putAlteraEstado");
    }
}
