package br.com.ifood.formapagamento.application.service;

import br.com.ifood.formapagamento.application.api.dto.FormaPagamentoRequest;
import br.com.ifood.formapagamento.application.api.dto.FormaPagamentoResponse;

import java.util.List;

public interface FormaPagamentoService {
    FormaPagamentoResponse criaFormaPagamento(FormaPagamentoRequest formaPagamentoRequest);

    List<FormaPagamentoResponse> listaTodasFormasPagamento();

    FormaPagamentoResponse buscaFormaPagamentoAtravesId(Long idPagamento);

    void deletaFormaPagamentoAtravesId(Long idPagamento);

    void alteraFormaPagamento(Long idPagamento, FormaPagamentoRequest formaPagamentoRequest);
}
