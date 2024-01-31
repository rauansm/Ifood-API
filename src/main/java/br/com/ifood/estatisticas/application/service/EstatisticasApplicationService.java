package br.com.ifood.estatisticas.application.service;

import br.com.ifood.estatisticas.application.repository.EstatisticaRepository;
import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.filtro.VendaDiariaFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EstatisticasApplicationService implements EstatisticaService{
    private final EstatisticaRepository estatisticaRepository;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro filtro) {
        log.info("[inicia] EstatisticasApplicationService - consultarVendasDiarias");
        List<VendaDiaria> vendasDiarias = estatisticaRepository.consultarVendasDiarias(filtro);
        log.info("[finaliza] consultarVendasDiarias - consultarVendasDiarias");
        return vendasDiarias;
    }

    @Override
    public byte[] consultarVendasDiariasPDF(VendaDiariaFiltro filtro) throws JRException {
        log.info("[inicia] EstatisticasApplicationService - consultarVendasDiariasPDF");
        byte[] vendasDiariasPDF = estatisticaRepository.emitirVendasDiariasPDF(filtro);
        log.info("[finaliza] EstatisticasApplicationService - consultarVendasDiariasPDF");
        return vendasDiariasPDF;
    }
}
