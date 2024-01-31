package br.com.ifood.estado.application.repository;

import br.com.ifood.estado.domain.Estado;

import java.util.List;

public interface EstadoRepository {

    Estado salva(Estado estado);

    Estado buscaEstadoAtravesId(Long idEstado);

    List<Estado> buscaTodosEstados();

    void deletaEstado(Estado estado);
}
