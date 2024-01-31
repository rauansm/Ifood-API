package br.com.ifood.restaurante.application.api;

import br.com.ifood.restaurante.application.api.dto.*;
import br.com.ifood.restaurante.application.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class RestauranteController implements RestauranteAPI {


    private final RestauranteService restauranteService;

    @Override
    public RestauranteResponse postRestaurante(RestauranteRequest restauranteRequest) {
        log.info("[inicia] RestauranteController - postRestaurante");
        RestauranteResponse restauranteCriado = restauranteService.criaRestaurante(restauranteRequest);
        log.info("[finaliza] RestauranteController - postRestaurante");
        return restauranteCriado;
    }

    @Override
    public List<RestauranteListResponse> getTodosRestaurantes() {
        log.info("[inicia] RestauranteController - getTodosRestaurantes");
        List<RestauranteListResponse> restaurantes = restauranteService.listaTodosRestaurantes();
        log.info("[finaliza] RestauranteController - getTodosRestaurantes");
        return restaurantes;
    }

    @Override
    public List<RestauranteListResponse> getTodosRestaurantesFreteGratis() {
        log.info("[inicia] RestauranteController - getTodosRestaurantesFreteGratis");
        List<RestauranteListResponse> restaurantesFreteGratis = restauranteService.listaTodosRestaurantesComFreteGratis();
        log.info("[finaliza] RestauranteController - getTodosRestaurantesFreteGratis");
        return restaurantesFreteGratis;
    }

    @Override
    public RestauranteResponse getRestauranteAtravesId(Long idRestaurante) {
        log.info("[inicia] RestauranteController - getRestauranteAtravesId");
        RestauranteResponse restaurante = restauranteService.buscaRestauranteAtravesId(idRestaurante);
        log.info("[finaliza] RestauranteController - getRestauranteAtravesId");
        return restaurante;
    }

    @Override
    public void patchAlteraRestaurante(Long idRestaurante, RestauranteRequest restauranteAlteracaoRequest) {
        log.info("[inicia] RestauranteController - patchAlteraRestaurante");
        restauranteService.alteraRestaurante(idRestaurante, restauranteAlteracaoRequest);
        log.info("[finaliza] RestauranteController - patchAlteraRestaurante");

    }

    @Override
    public void ativaRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteController - ativarRestaurante");
        restauranteService.ativaRestaurante(idRestaurante);
        log.info("[finaliza] RestauranteController - ativarRestaurante");
    }

    @Override
    public void desativaRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteController - desativaRestaurante");
        restauranteService.desativaRestaurante(idRestaurante);
        log.info("[finaliza] RestauranteController - desativaRestaurante");
    }

    @Override
    public void abrirRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteController - abrirRestaurante");
        restauranteService.abrirRestaurante(idRestaurante);
        log.info("[finaliza] RestauranteController - abrirRestaurante");
    }

    @Override
    public void fecharRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteController - fecharRestaurante");
        restauranteService.fecharRestaurante(idRestaurante);
        log.info("[finaliza] RestauranteController - fecharRestaurante");
    }

    @Override
    public void associarFormaPagamento(Long idRestaurante, Long idPagamento) {
        log.info("[inicia] RestauranteController - associarFormaPagamento");
        restauranteService.associaFormaPagamento(idRestaurante, idPagamento);
        log.info("[finaliza] RestauranteController - associarFormaPagamento");
    }

    @Override
    public void desassociarFormaPagamento(Long idRestaurante, Long idPagamento) {
        log.info("[inicia] RestauranteController - desassociarFormaPagamento");
        restauranteService.desassociaFormaPagamento(idPagamento,idRestaurante );
        log.info("[finaliza] RestauranteController - desassociarFormaPagamento");
    }

    @Override
    public Collection<RestauranteFormaPagamentoResponse> getFormasPagamentoRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteController - getFormasPagamentoRestaurante");
        Collection<RestauranteFormaPagamentoResponse> restauranteFormaPagamento = restauranteService.buscaFormasPagamentoRestaurante(idRestaurante);
        log.info("[finaliza] RestauranteController - getFormasPagamentoRestaurante");
        return restauranteFormaPagamento;
    }

    @Override
    public List<ResponsaveisRestauranteResponse> getTodosResponsaveisRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteController - getTodosResponsaveisRestaurante");
        List<ResponsaveisRestauranteResponse> restauranteResponsaveis = restauranteService.listaTodosResponsaveisRestaurante(idRestaurante);
        log.info("[finaliza] RestauranteController - getTodosResponsaveisRestaurante");
        return restauranteResponsaveis;
    }

    @Override
    public void associarResponsavel(Long idRestaurante, String idResponsavel) {
        log.info("[inicia] RestauranteController - associarResponsavel");
        restauranteService.associaResponsavel(idResponsavel,idRestaurante);
        log.info("[finaliza] RestauranteController - associarResponsavel");

    }

    @Override
    public void desassociarResponsavel(Long idRestaurante, String idResponsavel) {
        log.info("[inicia] RestauranteController - desassociarResponsavel");
        restauranteService.desassociaResponsavel(idResponsavel, idRestaurante);
        log.info("[finaliza] RestauranteController - desassociarResponsavel");

    }
}
