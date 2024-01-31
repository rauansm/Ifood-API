package br.com.ifood.restaurante.application.service;

import br.com.ifood.cozinha.application.repository.CozinhaRepository;
import br.com.ifood.cozinha.domain.Cozinha;
import br.com.ifood.cidade.application.repository.CidadeRepository;
import br.com.ifood.cidade.domain.Cidade;
import br.com.ifood.formapagamento.application.repository.FormaPagamentoRepository;
import br.com.ifood.formapagamento.domain.FormaPagamento;
import br.com.ifood.restaurante.application.api.dto.*;
import br.com.ifood.restaurante.application.repository.RestauranteRepository;
import br.com.ifood.restaurante.domain.Restaurante;
import br.com.ifood.usuario.application.repository.UsuarioRepository;
import br.com.ifood.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RestauranteApplicationService implements RestauranteService{

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;
    private final CidadeRepository cidadeRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;
    private final UsuarioRepository usuarioRepository;
    @Transactional
    @Override
    public RestauranteResponse criaRestaurante(RestauranteRequest restauranteRequest) {
        log.info("[inicia] RestauranteApplicationService - criaRestaurante");
        Cozinha cozinha = cozinhaRepository.buscaCozinhaPeloId(restauranteRequest.getIdCozinha());
        Cidade cidade = cidadeRepository.buscaCidadeAtravesId(restauranteRequest.getEndereco().getCidade());
        Restaurante restaurante = restauranteRepository.salva(new Restaurante(restauranteRequest, cozinha, cidade));
        log.info("[finaliza] RestauranteApplicationService - criaRestaurante");
        return new RestauranteResponse(restaurante);
    }

    @Override
    public List<RestauranteListResponse> listaTodosRestaurantes() {
        log.info("[inicia] RestauranteApplicationService - listaTodosRestaurantes");
        List<Restaurante> restaurantes = restauranteRepository.buscaTodosRestaurantes();
        log.info("[finaliza] RestauranteApplicationService - listaTodosRestaurantes");
        return RestauranteListResponse.converte(restaurantes);
    }

    @Override
    public List<RestauranteListResponse> listaTodosRestaurantesComFreteGratis() {
        log.info("[inicia] RestauranteApplicationService - listaTodosRestaurantesComFreteGratis");
        List<Restaurante> restaurantesFreteGratis = restauranteRepository.buscaTodosRestaurantesComFreteGratis();
        log.info("[finaliza] RestauranteApplicationService - listaTodosRestaurantesComFreteGratis");
        return RestauranteListResponse.converte(restaurantesFreteGratis);
    }
    @Override
    public RestauranteResponse buscaRestauranteAtravesId(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - buscaRestauranteAtravesId");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        log.info("[finaliza] RestauranteApplicationService - buscaRestauranteAtravesId");
        return new RestauranteResponse(restaurante);
    }
    @Transactional
    @Override
    public void alteraRestaurante(Long idRestaurante, RestauranteRequest restauranteAlteracaoRequest) {
        log.info("[inicia] RestauranteApplicationService - alteraRestaurante");
        Cozinha cozinha = cozinhaRepository.buscaCozinhaPeloId(restauranteAlteracaoRequest.getIdCozinha());
        Cidade cidade = cidadeRepository.buscaCidadeAtravesId(restauranteAlteracaoRequest.getEndereco().getCidade());
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        restaurante.altera(restauranteAlteracaoRequest, cidade, cozinha);
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - alteraRestaurante");
    }
    @Transactional
    @Override
    public void ativaRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - ativaRestaurante");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        restaurante.ativar();
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - ativaRestaurante");
    }
    @Transactional
    @Override
    public void desativaRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - desativaRestaurante");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        restaurante.desativar();
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - desativaRestaurante");
    }
    @Transactional
    @Override
    public void abrirRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - abrirRestaurante");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        restaurante.abrir();
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - abrirRestaurante");
    }

    @Transactional
    @Override
    public void fecharRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - fecharRestaurante");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        restaurante.fechar();
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - fecharRestaurante");
    }
    @Override
    public Collection<RestauranteFormaPagamentoResponse> buscaFormasPagamentoRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - buscaFormaPagamentoRestaurante");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        log.info("[finaliza] RestauranteApplicationService - buscaFormaPagamentoRestaurante");
        return RestauranteFormaPagamentoResponse.converte(restaurante.getFormasPagamento());
    }
    @Transactional
    @Override
    public void associaFormaPagamento(Long idRestaurante, Long idPagamento) {
        log.info("[inicia] RestauranteApplicationService - associaFormaPagamento");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoRepository.buscaFormaPagamentoAtravesId(idPagamento);
        restaurante.associaFormaPagamento(formaPagamento);
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - associaFormaPagamento");
    }

    @Transactional
    @Override
    public void desassociaFormaPagamento(Long idPagamento, Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - desassociaFormaPagamento");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoRepository.buscaFormaPagamentoAtravesId(idPagamento);
        restaurante.desassociaFormaPagamento(formaPagamento);
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - desassociaFormaPagamento");
    }
    @Override
    public List<ResponsaveisRestauranteResponse> listaTodosResponsaveisRestaurante(Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - listaTodosResponsaveisRestaurante");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        log.info("[finaliza] RestauranteApplicationService - listaTodosResponsaveisRestaurante");
        return ResponsaveisRestauranteResponse.converte(restaurante.getResponsaveis());
    }
    @Transactional
    @Override
    public void associaResponsavel(String idResponsavel, Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - associaResponsavel");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idResponsavel);
        restaurante.associaResponsavel(usuario);
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - associaResponsavel");
    }

    @Transactional
    @Override
    public void desassociaResponsavel(String idResponsavel, Long idRestaurante) {
        log.info("[inicia] RestauranteApplicationService - desassociaResponsavel");
        Restaurante restaurante = restauranteRepository.buscaRestauranteAtravesId(idRestaurante);
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idResponsavel);
        restaurante.desassociaResponsavel(usuario);
        restauranteRepository.salva(restaurante);
        log.info("[finaliza] RestauranteApplicationService - desassociaResponsavel");

    }


}


