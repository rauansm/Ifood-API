package br.com.ifood.permissao.infra;

import br.com.ifood.permissao.domain.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoSpringDataJPA extends JpaRepository<Permissao, Long> {
}
