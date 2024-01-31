package br.com.ifood.formapagamento.infra;

import br.com.ifood.formapagamento.application.repository.FormaPagamentoRepository;
import br.com.ifood.formapagamento.domain.FormaPagamento;
import br.com.ifood.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class FormaPagamentoRepositoryJPA implements FormaPagamentoRepository {

    private final FormaPagamentoSpringDataJPA formaPagamentoSpringDataJPA;

    @Override
    public FormaPagamento salva(FormaPagamento formaPagamento) {
        log.info("[inicia] FormaPagamentoRepositoryJPA - salva");
        formaPagamentoSpringDataJPA.save(formaPagamento);
        log.info("[finaliza] FormaPagamentoRepositoryJPA - salva");
        return formaPagamento;
    }

    @Override
    public List<FormaPagamento> buscaTodasFormasPagamento() {
        log.info("[inicia] FormaPagamentoRepositoryJPA - buscaTodasFormasPagamento");
        List<FormaPagamento> formasPagamento = formaPagamentoSpringDataJPA.findAll();
        log.info("[finaliza] FormaPagamentoRepositoryJPA - buscaTodasFormasPagamento");
        return formasPagamento;
    }

    @Override
    public FormaPagamento buscaFormaPagamentoAtravesId(Long idPagamento) {
        log.info("[inicia] FormaPagamentoRepositoryJPA - buscaFormaPagamentoAtravesId");
        Optional<FormaPagamento> formaPagamento = formaPagamentoSpringDataJPA.findById(idPagamento);
        log.info("[finaliza] FormaPagamentoRepositoryJPA - buscaFormaPagamentoAtravesId");
        return formaPagamento.orElseThrow(() -> APIException.EntidadeNaoEncontrada(String.format("Forma de Pagamento com Id %s não encontrada", idPagamento)));
    }

    @Override
    public void deletaFormaPagamento(FormaPagamento formaPagamento) {
       try {
        log.info("[inicia] FormaPagamentoRepositoryJPA - deletaFormaPagamento");
        formaPagamentoSpringDataJPA.delete(formaPagamento);
        formaPagamentoSpringDataJPA.flush();
        log.info("[finaliza] FormaPagamentoRepositoryJPA - deletaFormaPagamento");
    } catch (DataIntegrityViolationException e) {
        throw APIException.EntidadeEmUso(String.format("Forma de Pagamento de código %d não pode ser removida, pois está em uso", formaPagamento.getIdPagamento()));
    }

    }
}
