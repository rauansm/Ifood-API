package br.com.ifood.cozinha.infra;

import br.com.ifood.cozinha.domain.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;

public interface CozinhaSpringDataJPA extends JpaRepository<Cozinha, Long> {
}
