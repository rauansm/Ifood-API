package br.com.ifood.restaurante.application.api.dto;

import br.com.ifood.usuario.domain.Usuario;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class ResponsaveisRestauranteResponse {
    private String idUsuario;
    private String nome;
    private String email;

    public ResponsaveisRestauranteResponse(Usuario responsavel) {
        this.idUsuario = responsavel.getIdUsuario();
        this.nome = responsavel.getNome();
        this.email = responsavel.getEmail();
    }


    public static List<ResponsaveisRestauranteResponse> converte(Set<Usuario> responsaveis) {
        return responsaveis.stream()
                .map(ResponsaveisRestauranteResponse::new)
                .collect(Collectors.toList());
    }
}
