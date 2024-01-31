package br.com.ifood.endereco.domain;

import br.com.ifood.cidade.domain.Cidade;
import br.com.ifood.pedido.application.api.EnderecoEntrega;
import br.com.ifood.restaurante.application.api.EnderecoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Embeddable
@NoArgsConstructor
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;


    public Endereco(EnderecoRequest endereco, Cidade cidade) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = cidade;
    }

    public Endereco(EnderecoEntrega enderecoEntrega, Cidade cidade) {
        this.cep = enderecoEntrega.getCep();
        this.logradouro = enderecoEntrega.getLogradouro();
        this.numero = enderecoEntrega.getNumero();
        this.complemento = enderecoEntrega.getComplemento();
        this.bairro = enderecoEntrega.getBairro();
        this.cidade = cidade;
    }
}
