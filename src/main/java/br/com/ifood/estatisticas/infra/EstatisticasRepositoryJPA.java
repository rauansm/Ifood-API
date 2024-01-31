package br.com.ifood.estatisticas.infra;
import br.com.ifood.estatisticas.application.repository.EstatisticaRepository;
import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.filtro.VendaDiariaFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class EstatisticasRepositoryJPA implements EstatisticaRepository {

    private final VendasQueryImpl vendasQuery;
    private final VendasJasperPDF vendasJasperPDF;
    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro filtro) {
        log.info("[inicia] EstatisticasRepositoryJPA - consultarVendasDiarias");
        List<VendaDiaria> vendasDiarias = vendasQuery.consultarVendasDiarias(filtro);
        log.info("[finaliza] EstatisticasRepositoryJPA - consultarVendasDiarias");
        return vendasDiarias;
    }

    @Override
    public byte[] emitirVendasDiariasPDF(VendaDiariaFiltro filtro)  {
        log.info("[inicia] EstatisticasRepositoryJPA - emitirVendasDiariasPDF");
        byte[] vendasPDF = vendasJasperPDF.emitirVendasDiarias(filtro);
        log.info("[finaliza] EstatisticasRepositoryJPA - emitirVendasDiariasPDF");
        return vendasPDF;
    }
}
