package br.com.ifood.grupo.infra;

import br.com.ifood.grupo.domain.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoSpringDataJPA extends JpaRepository<Grupo,Long> {
}
