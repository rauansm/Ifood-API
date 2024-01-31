package br.com.ifood.formapagamento.application.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class FormaPagamentoRequest {

    @NotBlank
    private String descricao;

    @JsonCreator
    public FormaPagamentoRequest(@JsonProperty("descricao") String descricao) {
        this.descricao = descricao;
    }
}
