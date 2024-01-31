package br.com.ifood.restaurante.application.api.dto;

import br.com.ifood.restaurante.domain.Restaurante;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RestauranteResponse {


    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private String cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoResponse endereco;


    public RestauranteResponse(Restaurante restaurante) {
        this.id = restaurante.getIdRestaurante();
        this.nome = restaurante.getNome();
        this.cozinha = restaurante.getCozinha().getNome();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.aberto = restaurante.getAberto();
        this.ativo = restaurante.getAtivo();
        this.endereco = new EnderecoResponse(restaurante.getEndereco());
    }
}
