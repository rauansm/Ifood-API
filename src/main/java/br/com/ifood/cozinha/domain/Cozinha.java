package br.com.ifood.cozinha.domain;

import br.com.ifood.cozinha.application.api.CozinhaAlteracaoRequest;
import br.com.ifood.cozinha.application.api.CozinhaRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (columnDefinition = "bigint", name = "id_cozinha", updatable = false, unique = true, nullable = false)
    private Long idCozinha;

    @Column(nullable = false)
    private String nome;


    public Cozinha(CozinhaRequest cozinhaRequest) {
        this.nome = cozinhaRequest.getNome();
    }

    public void altera(CozinhaAlteracaoRequest cozinhaAlteracaoRequest) {
        this.nome = cozinhaAlteracaoRequest.getNome();
    }
}
