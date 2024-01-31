package br.com.ifood.restaurante.application.service;

import br.com.ifood.restaurante.application.api.dto.*;

import java.util.Collection;
import java.util.List;

public interface RestauranteService {
    RestauranteResponse criaRestaurante(RestauranteRequest restauranteRequest);

    List<RestauranteListResponse> listaTodosRestaurantes();

    RestauranteResponse buscaRestauranteAtravesId(Long idRestaurante);

    void alteraRestaurante(Long idRestaurante, RestauranteRequest restauranteAlteracaoRequest);

    void ativaRestaurante(Long idRestaurante);

    void desativaRestaurante(Long idRestaurante);

    void abrirRestaurante(Long idRestaurante);

    void fecharRestaurante(Long idRestaurante);

    Collection<RestauranteFormaPagamentoResponse> buscaFormasPagamentoRestaurante(Long idRestaurante);

    void associaFormaPagamento(Long idRestaurante, Long idPagamento);

    void desassociaFormaPagamento(Long idPagamento, Long idRestaurante);

    List<ResponsaveisRestauranteResponse> listaTodosResponsaveisRestaurante(Long idRestaurante);

    void associaResponsavel(String idResponsavel, Long idRestaurante);

    void desassociaResponsavel(String idResponsavel, Long idRestaurante);

    List<RestauranteListResponse> listaTodosRestaurantesComFreteGratis();
}
