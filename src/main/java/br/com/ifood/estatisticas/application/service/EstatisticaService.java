package br.com.ifood.estatisticas.application.service;

import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.filtro.VendaDiariaFiltro;
import net.sf.jasperreports.engine.JRException;

import java.util.List;

public interface EstatisticaService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro filtro);

    byte[] consultarVendasDiariasPDF(VendaDiariaFiltro filtro) throws JRException;
}
