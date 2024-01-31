package br.com.ifood.pedido.application.api.dto;

import br.com.ifood.usuario.domain.Usuario;
import lombok.Value;

@Value
public class PedidoUsuario {
    private String idUsuario;
    private String nome;
    private String email;

    public PedidoUsuario(Usuario cliente) {
        this.idUsuario = cliente.getIdUsuario();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
}
