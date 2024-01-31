package br.com.ifood.security;

import br.com.ifood.pedido.infra.PedidoSpringDataJPA;
import br.com.ifood.restaurante.infra.RestauranteSpringDataJPA;
import br.com.ifood.security.domain.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IfoodSecurity {
    private final RestauranteSpringDataJPA restauranteSpringDataJPA;
    private final PedidoSpringDataJPA pedidoSpringDataJPA;

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUsuarioId() {
        AuthUser userAutenticado = (AuthUser) getAuthentication().getPrincipal();
        return userAutenticado != null ? userAutenticado.getIdUsuario() : null;
    }


    public boolean gerenciaRestaurante(Long idRestaurante){
        if (idRestaurante == null) {
            return false;
        }
        return restauranteSpringDataJPA.existsResponsavel(idRestaurante,getUsuarioId());
    }
    public boolean gerenciaRestauranteDoPedido(String codigoPedido){
        return pedidoSpringDataJPA.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
    }

}
