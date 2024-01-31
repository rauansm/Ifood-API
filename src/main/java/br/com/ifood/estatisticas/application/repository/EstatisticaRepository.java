package br.com.ifood.estatisticas.application.repository;

import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.filtro.VendaDiariaFiltro;
import net.sf.jasperreports.engine.JRException;

import java.util.List;

public interface EstatisticaRepository {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro filtro);

    byte[] emitirVendasDiariasPDF(VendaDiariaFiltro filtro) throws JRException;
}
