package br.com.ifood.cidade.infra;

import br.com.ifood.cidade.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeSpringDataJPA  extends JpaRepository<Cidade, Long> {
}
