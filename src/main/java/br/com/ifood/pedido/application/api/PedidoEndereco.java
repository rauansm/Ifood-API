package br.com.ifood.pedido.application.api;

import br.com.ifood.endereco.domain.Endereco;
import lombok.Value;

@Value
public class PedidoEndereco {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    public PedidoEndereco(Endereco enderecoEntrega) {
        this.cep = enderecoEntrega.getCep();
        this.logradouro = enderecoEntrega.getLogradouro();
        this.numero = enderecoEntrega.getNumero();
        this.complemento = enderecoEntrega.getComplemento();
        this.bairro = enderecoEntrega.getBairro();
        this.cidade = enderecoEntrega.getCidade().getNome();
        this.estado = enderecoEntrega.getCidade().getEstado().getNome();
    }
}
