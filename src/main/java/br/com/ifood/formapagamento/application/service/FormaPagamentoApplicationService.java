package br.com.ifood.formapagamento.application.service;

import br.com.ifood.formapagamento.application.api.FormaPagamentoRequest;
import br.com.ifood.formapagamento.application.api.FormaPagamentoResponse;
import br.com.ifood.formapagamento.application.repository.FormaPagamentoRepository;
import br.com.ifood.formapagamento.domain.FormaPagamento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class FormaPagamentoApplicationService implements FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;
    @Transactional
    @Override
    public FormaPagamentoResponse criaFormaPagamento(FormaPagamentoRequest formaPagamentoRequest) {
        log.info("[inicia] FormaPagamentoApplicationService - criaFormaPagamento");
        FormaPagamento formaPagamento = formaPagamentoRepository.salva(new FormaPagamento(formaPagamentoRequest));
        log.info("[finaliza] FormaPagamentoApplicationService - criaFormaPagamento");
        return new FormaPagamentoResponse(formaPagamento);
    }

    @Override
    public List<FormaPagamentoResponse> listaTodasFormasPagamento() {
        log.info("[inicia] FormaPagamentoApplicationService - listaTodasFormasPagamento");
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.buscaTodasFormasPagamento();
        log.info("[finaliza] FormaPagamentoApplicationService - listaTodasFormasPagamento");
        return FormaPagamentoResponse.converte(formasPagamento);
    }

    @Override
    public FormaPagamentoResponse buscaFormaPagamentoAtravesId(Long idPagamento) {
        log.info("[inicia] FormaPagamentoApplicationService - buscaFormaPagamentoAtravesId");
        FormaPagamento formaPagamento = formaPagamentoRepository.buscaFormaPagamentoAtravesId(idPagamento);
        log.info("[finaliza] FormaPagamentoApplicationService - buscaFormaPagamentoAtravesId");
        return new FormaPagamentoResponse(formaPagamento);
    }
    @Transactional
    @Override
    public void deletaFormaPagamentoAtravesId(Long idPagamento) {
        log.info("[inicia] FormaPagamentoApplicationService - deletaFormaPagamentoAtravesId");
        FormaPagamento formaPagamento = formaPagamentoRepository.buscaFormaPagamentoAtravesId(idPagamento);
        formaPagamentoRepository.deletaFormaPagamento(formaPagamento);
        log.info("[finaliza] FormaPagamentoApplicationService - deletaFormaPagamentoAtravesId");

    }
    @Transactional
    @Override
    public void alteraFormaPagamento(Long idPagamento, FormaPagamentoRequest formaPagamentoRequest) {
        log.info("[inicia] FormaPagamentoApplicationService - alteraFormaPagamento");
        FormaPagamento formaPagamento = formaPagamentoRepository.buscaFormaPagamentoAtravesId(idPagamento);
        formaPagamento.altera(formaPagamentoRequest);
        formaPagamentoRepository.salva(formaPagamento);
        log.info("[finaliza] FormaPagamentoApplicationService - alteraFormaPagamento");
    }
}
