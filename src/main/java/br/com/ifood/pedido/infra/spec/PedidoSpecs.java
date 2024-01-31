package br.com.ifood.pedido.infra.spec;

import br.com.ifood.filtro.PedidoFiltro;
import br.com.ifood.pedido.domain.Pedido;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFiltro filtro) {
        return (root, query, builder) -> {
            if (Pedido.class.equals((query.getResultType()))) {
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");
            }
            var predicates = new ArrayList<Predicate>();

            if (filtro.getIdUsuario() != null) {
                predicates.add(builder.equal(root.get("cliente").get("idUsuario"), filtro.getIdUsuario()));
            }
            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante").get("idRestaurante"), filtro.getRestauranteId()));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoFim()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
