package br.com.ifood.formapagamento.application.api;

import br.com.ifood.formapagamento.application.api.dto.FormaPagamentoRequest;
import br.com.ifood.formapagamento.application.api.dto.FormaPagamentoResponse;
import br.com.ifood.formapagamento.application.service.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class FormaPagamentoController implements FormaPagamentoAPI{

    private final FormaPagamentoService formaPagamentoService;

    @Override
    public FormaPagamentoResponse postFormaPagamento(FormaPagamentoRequest formaPagamentoRequest) {
        log.info("[inicia] FormaPagamentoController - postFormaPagamento");
        FormaPagamentoResponse formaPagamentoCriado = formaPagamentoService.criaFormaPagamento(formaPagamentoRequest);
        log.info("[finaliza] FormaPagamentoController - postFormaPagamento");
        return formaPagamentoCriado;
    }

    @Override
    public List<FormaPagamentoResponse> getTodasFormasPagamento() {
        log.info("[inicia] FormaPagamentoController - getTodasFormasPagamentos");
        List<FormaPagamentoResponse> formasPagamento = formaPagamentoService.listaTodasFormasPagamento();
        log.info("[finaliza] FormaPagamentoController - getTodasFormasPagamentos");
        return formasPagamento;
    }

    @Override
    public FormaPagamentoResponse getFormaPagamentoAtravesId(Long idPagamento) {
        log.info("[inicia] FormaPagamentoController - getFormaPagamentoAtravesId");
        FormaPagamentoResponse formaPagamento = formaPagamentoService.buscaFormaPagamentoAtravesId(idPagamento);
        log.info("[finaliza] FormaPagamentoController - getFormaPagamentoAtravesId");
        return formaPagamento;
    }

    @Override
    public void deletaFormaPagamentoAtravesId(Long idPagamento) {
        log.info("[inicia] FormaPagamentoController - deletaFormaPagamentoAtravesId");
        formaPagamentoService.deletaFormaPagamentoAtravesId(idPagamento);
        log.info("[finaliza] FormaPagamentoController - deletaFormaPagamentoAtravesId");
    }

    @Override
    public void putAlteraFormaPagamento(Long idPagamento, FormaPagamentoRequest formaPagamentoRequest) {
        log.info("[inicia] FormaPagamentoController - putAlteraFormaPagamento");
        formaPagamentoService.alteraFormaPagamento(idPagamento, formaPagamentoRequest);
        log.info("[finaliza] FormaPagamentoController - putAlteraFormaPagamento");
    }


}
