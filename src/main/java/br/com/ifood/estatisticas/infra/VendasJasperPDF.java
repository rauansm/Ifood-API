package br.com.ifood.estatisticas.infra;

import br.com.ifood.filtro.VendaDiariaFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;

@Component
@RequiredArgsConstructor
@Log4j2
public class VendasJasperPDF  {

    private final VendasQueryImpl vendasQuery;

    public byte[] emitirVendasDiarias(VendaDiariaFiltro filtro) {
        log.info("[inicia] VendasJasperPDF - emitirVendasDiarias");
        try {
            var inputStream = this.getClass().getResourceAsStream(
                    "/relatorios/vendas-diarias.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendasQuery.consultarVendasDiarias(filtro);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            log.info("[finaliza] VendasJasperPDF - emitirVendasDiarias");

            return JasperExportManager.exportReportToPdf(jasperPrint);
        }  catch (Exception e) {
            e.printStackTrace(); // ou use um logger para registrar a exceção
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }
}
