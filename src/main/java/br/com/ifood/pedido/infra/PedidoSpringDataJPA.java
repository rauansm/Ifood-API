package br.com.ifood.pedido.infra;

import br.com.ifood.pedido.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoSpringDataJPA extends JpaRepository<Pedido, String>,
        JpaSpecificationExecutor<Pedido> {
    Optional<Pedido> findByCodigoPedido(String codigoPedido);


    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

    @Query("select case when count(1) > 0 then true else false end " +
            "from Pedido ped join ped.restaurante rest join rest.responsaveis resp " +
            "where ped.codigoPedido = :codigoPedido and resp.id = :idUsuario")
    boolean isPedidoGerenciadoPor(@Param("codigoPedido") String codigoPedido,
                                  @Param("idUsuario") String idUsuario);
}
