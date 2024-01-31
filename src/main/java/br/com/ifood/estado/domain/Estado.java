package br.com.ifood.estado.domain;

import br.com.ifood.estado.application.api.EstadoAlteracaoRequest;
import br.com.ifood.estado.application.api.EstadoRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "idEstado", updatable = false, unique = true, nullable = false)
    private Long idEstado;

    private String nome;



    public Estado(EstadoRequest estadoRequest) {
        this.nome = estadoRequest.getNome();
    }

    public void altera(EstadoAlteracaoRequest estadoAlteracaoRequest) {
        this.nome = estadoAlteracaoRequest.getNome();
    }
}
