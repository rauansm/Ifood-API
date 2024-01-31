package br.com.ifood.grupo.domain;

import br.com.ifood.grupo.application.api.GrupoRequest;
import br.com.ifood.permissao.domain.Permissao;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_grupo", updatable = false, unique = true, nullable = false)
    private Long idGrupo;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> permissoes = new HashSet<>();


    public Grupo(GrupoRequest grupoRequest) {
        this.nome = grupoRequest.getNome();
    }

    public void altera(GrupoRequest grupoRequest) {
        this.nome = grupoRequest.getNome();
    }

    public void associaPermissao(Permissao permissao) {
        getPermissoes().add(permissao);
    }

    public void desassociaPermissao(Permissao permissao) {
        getPermissoes().remove(permissao);
    }
}
