package br.com.ifood.estatisticas.application.api;

import br.com.ifood.estatisticas.application.service.EstatisticaService;
import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.estatisticas.infra.VendasJasperPDF;
import br.com.ifood.filtro.VendaDiariaFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class EstatisticasController implements EstatisticasAPI{

    private final EstatisticaService estatisticaService;
    private final VendasJasperPDF vendasJasperPDF;
    @Override
    public List<VendaDiaria> getConsultarVendasDiarias(VendaDiariaFiltro filtro) {
      log.info("[inicia] EstatisticasController - getConsultarVendasDiarias");
        List<VendaDiaria> vendasDiarias = estatisticaService.consultarVendasDiarias(filtro);
      log.info("[finaliza] EstatisticasController - getConsultarVendasDiarias");
        return vendasDiarias;
    }

    @Override
    public ResponseEntity<byte[]> getConsultarVendasDiariasPDF(VendaDiariaFiltro filtro) throws JRException {
        log.info("[inicia] EstatisticasController - getConsultarVendasDiariasPDF");
        byte[] consultaPDF = estatisticaService.consultarVendasDiariasPDF(filtro);
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
        log.info("[finaliza] EstatisticasController - getConsultarVendasDiariasPDF");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(consultaPDF);
    }
}
