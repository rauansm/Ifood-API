package br.com.ifood.estatisticas.infra;

import br.com.ifood.estatisticas.domain.VendaDiaria;
import br.com.ifood.filtro.VendaDiariaFiltro;
import br.com.ifood.pedido.domain.Pedido;
import br.com.ifood.pedido.domain.StatusPedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Log4j2
@RequiredArgsConstructor
@Component
public class VendasQueryImpl {

    @PersistenceContext
    private final EntityManager manager;
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro filtro) {
        log.info("[inicia] VendasQueryImpl - consultarVendasDiarias");
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class,
                root.get("dataCriacao"), builder.literal("+00:00"), builder.literal("-03:00"));

        var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);

        var predicates = new ArrayList<Predicate>();

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao, builder.count(root.get("idPedido")),
               builder.sum(root.get("valorTotal")));

        if (filtro.getIdRestaurante() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("idRestaurante"), filtro.getIdRestaurante()));
        }
        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }
        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }
        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));


        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy((functionDateDataCriacao));


        log.info("[finaliza] VendasQueryImpl - consultarVendasDiarias");
        return manager.createQuery(query).getResultList();
    }
}
