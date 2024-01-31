package br.com.ifood.estatisticas.application.api;

import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.filtro.VendaDiariaFiltro;
import br.com.ifood.security.annotations.CheckSecurity;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/estatisticas")
public interface EstatisticasAPI {

    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    List<VendaDiaria> getConsultarVendasDiarias(VendaDiariaFiltro filtro);

    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> getConsultarVendasDiariasPDF (VendaDiariaFiltro filtro) throws JRException;
}
