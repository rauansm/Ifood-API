package br.com.ifood.restaurante.application.api.dto;

import br.com.ifood.restaurante.domain.Restaurante;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class RestauranteListResponse {

    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private String cozinha;


    public static List<RestauranteListResponse> converte(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(RestauranteListResponse::new)
                .collect(Collectors.toList());
    }

    public RestauranteListResponse(Restaurante restaurante) {
        this.id = restaurante.getIdRestaurante();
        this.nome = restaurante.getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.cozinha = restaurante.getCozinha().getNome();
    }
}
