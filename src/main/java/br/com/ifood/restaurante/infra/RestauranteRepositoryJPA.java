package br.com.ifood.restaurante.infra;

import br.com.ifood.handler.APIException;
import br.com.ifood.restaurante.application.repository.RestauranteRepository;
import br.com.ifood.restaurante.domain.Restaurante;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class RestauranteRepositoryJPA implements RestauranteRepository {

    private  final RestauranteSpringDataJPA restauranteSpringDataJPA;
    @Override
    public Restaurante salva(Restaurante restaurante) {
        log.info("[inicia] RestauranteRepositoryJPA - salva");
        restauranteSpringDataJPA.save(restaurante);
        log.info("[finaliza] RestauranteRepositoryJPA - salva");
        return restaurante;
    }

    @Override
    public List<Restaurante> buscaTodosRestaurantes() {
        log.info("[inicia] RestauranteRepositoryJPA - buscaTodosRestaurantes");
       List<Restaurante> restaurantes = restauranteSpringDataJPA.findAll();
        log.info("[finaliza] RestauranteRepositoryJPA - buscaTodosRestaurantes");
        return restaurantes;
    }

    @Override
    public Restaurante buscaRestauranteAtravesId(Long idRestaurante) {
        log.info("[inicia] RestauranteRepositoryJPA - buscaRestauranteAtravesId");
        Optional<Restaurante> restaurante = restauranteSpringDataJPA.findById(idRestaurante);
        log.info("[finaliza] RestauranteRepositoryJPA - buscaRestauranteAtravesId");
        return restaurante.orElseThrow(() -> APIException.EntidadeNaoEncontrada(String.format("Restaurante de Id %s não encontrado", idRestaurante)));
    }

    @Override
    public List<Restaurante> buscaTodosRestaurantesComFreteGratis() {
        log.info("[inicia] RestauranteRepositoryJPA - buscaTodosRestaurantesComFreteGratis");
        List<Restaurante> restaurantesFreteGratis = restauranteSpringDataJPA.findAllComFreteGratis();
        log.info("[finaliza] RestauranteRepositoryJPA - buscaTodosRestaurantesComFreteGratis");
        return restaurantesFreteGratis;
    }
}
