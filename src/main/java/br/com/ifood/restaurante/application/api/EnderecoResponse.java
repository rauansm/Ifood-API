package br.com.ifood.restaurante.application.api;

import br.com.ifood.endereco.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Value;

@Value
public class EnderecoResponse {

    private String cep;
    private String logradouro;
    private String numero;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String complemento;
    private String bairro;
    private String cidade;

    public EnderecoResponse(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade().getNome();
    }
}
