package br.com.ifood.estado.infra;

import br.com.ifood.estado.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoSpringDataJPA extends JpaRepository<Estado,Long> {
}
