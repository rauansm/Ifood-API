package br.com.ifood.restaurante.infra;

import br.com.ifood.restaurante.domain.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestauranteSpringDataJPA extends JpaRepository<Restaurante, Long> {
@Query("select case when count(1) > 0 then true else false end " +
        "from Restaurante rest join rest.responsaveis resp " +
        "where rest.id = :idRestaurante and resp.id = :idUsuario")
    boolean existsResponsavel(@Param("idRestaurante") Long idRestaurante,
                              @Param("idUsuario") String idUsuario);
    @Query("Select rest From Restaurante rest where rest.taxaFrete = 0")
    List<Restaurante> findAllComFreteGratis();
}
