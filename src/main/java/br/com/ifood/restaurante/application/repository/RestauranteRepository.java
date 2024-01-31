package br.com.ifood.restaurante.application.repository;

import br.com.ifood.restaurante.domain.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    Restaurante salva(Restaurante restaurante);

    List<Restaurante> buscaTodosRestaurantes();

    Restaurante buscaRestauranteAtravesId(Long idRestaurante);

    List<Restaurante> buscaTodosRestaurantesComFreteGratis();
}
