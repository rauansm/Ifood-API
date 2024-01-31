package br.com.ifood.formapagamento.infra;

import br.com.ifood.formapagamento.domain.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoSpringDataJPA extends JpaRepository<FormaPagamento, Long> {
}
