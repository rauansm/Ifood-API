package br.com.ifood.estado.application.service;

import br.com.ifood.estado.application.api.dto.*;
import br.com.ifood.estado.application.repository.EstadoRepository;
import br.com.ifood.estado.domain.Estado;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EstadoApplicationService implements EstadoService{


    private final EstadoRepository estadoRepository;
    @Transactional
    @Override
    public EstadoReponse criaEstado(EstadoRequest estadoRequest) {
        log.info("[inicia] EstadoApplicationService - criaEstado");
        Estado estado = estadoRepository.salva(new Estado(estadoRequest));
        log.info("[finaliza] EstadoApplicationService - criaEstado");
        return EstadoReponse.builder()
                .idEstado(estado.getIdEstado())
                .nome(estado.getNome())
                .build();
    }

    @Override
    public EstadoDetalhadoResponse buscaEstadoAtravesId(Long idEstado) {
        log.info("[inicia] EstadoApplicationService - buscaEstadoAtravesId");
        Estado estado = estadoRepository.buscaEstadoAtravesId(idEstado);
        log.info("[finaliza] EstadoApplicationService - buscaEstadoAtravesId");
        return new EstadoDetalhadoResponse(estado);
    }

    @Override
    public List<EstadoListResponse> listaTodosEstados() {
        log.info("[inicia] EstadoApplicationService - listaTodosEstados");
        List<Estado> estados = estadoRepository.buscaTodosEstados();
        log.info("[finaliza] EstadoApplicationService - listaTodosEstados");
        return EstadoListResponse.converte(estados);
    }
    @Transactional
    @Override
    public void deletaEstadoAtravesId(Long idEstado) {
        log.info("[inicia] EstadoApplicationService - deletaEstadoAtravesId");
        Estado estado = estadoRepository.buscaEstadoAtravesId(idEstado);
        estadoRepository.deletaEstado(estado);
        log.info("[finaliza] EstadoApplicationService - deletaEstadoAtravesId");

    }
    @Transactional
    @Override
    public void alteraEstado(Long idEstado, EstadoAlteracaoRequest estadoAlteracaoRequest) {
        log.info("[inicia] EstadoApplicationService - alteraEstado");
        Estado estado = estadoRepository.buscaEstadoAtravesId(idEstado);
        estado.altera(estadoAlteracaoRequest);
        estadoRepository.salva(estado);
        log.info("[finaliza] EstadoApplicationService - alteraEstado");
    }
}
