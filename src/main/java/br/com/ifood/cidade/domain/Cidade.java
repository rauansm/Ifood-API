package br.com.ifood.cidade.domain;

import br.com.ifood.cidade.application.api.dto.CidadeAlteracaoRequest;
import br.com.ifood.cidade.application.api.dto.CidadeRequest;
import br.com.ifood.estado.domain.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_cidade", updatable = false, unique = true, nullable = false)
    private Long idCidade;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name= "estado_id", nullable = false)
    private Estado estado;



    public Cidade(CidadeRequest cidadeRequest, Estado estado) {
        this.nome = cidadeRequest.getNome();
        this.estado = estado;
    }

    public void altera(CidadeAlteracaoRequest cidadeAlteracaoRequest, Estado estado) {
        this.nome = cidadeAlteracaoRequest.getNome();
        this.estado = estado;
    }
}
