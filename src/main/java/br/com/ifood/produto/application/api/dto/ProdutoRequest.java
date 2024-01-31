package br.com.ifood.produto.application.api.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Value
public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @NotNull
    private Boolean ativo;

}
