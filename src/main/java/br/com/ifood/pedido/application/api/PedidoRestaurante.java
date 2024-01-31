package br.com.ifood.pedido.application.api;

import br.com.ifood.restaurante.domain.Restaurante;
import lombok.Value;

@Value
public class PedidoRestaurante {
    private Long idRestaurante;
    private String nome;

    public PedidoRestaurante(Restaurante restaurante) {
        this.idRestaurante = restaurante.getIdRestaurante();
        this.nome = restaurante.getNome();
    }
}
