package br.com.ifood.formapagamento.application.repository;

import br.com.ifood.formapagamento.domain.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    FormaPagamento salva(FormaPagamento formaPagamento);

    List<FormaPagamento> buscaTodasFormasPagamento();

    FormaPagamento buscaFormaPagamentoAtravesId(Long idPagamento);

    void deletaFormaPagamento(FormaPagamento formaPagamento);
}
