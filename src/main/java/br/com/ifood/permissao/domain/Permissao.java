package br.com.ifood.permissao.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_permissao", updatable = false, unique = true, nullable = false)
    private Long idPermissao;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;
}
