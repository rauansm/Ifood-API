package br.com.ifood.restaurante.application.api.dto;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Value
public class RestauranteRequest {

    @NotBlank
    private String nome;
    @PositiveOrZero
    private BigDecimal taxaFrete;
    @NotNull
    private Long idCozinha;
    @NotNull
    @Valid
    private EnderecoRequest endereco;

}
