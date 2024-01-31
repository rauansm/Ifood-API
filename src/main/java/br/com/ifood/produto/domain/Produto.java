package br.com.ifood.produto.domain;

import br.com.ifood.produto.application.api.ProdutoRequest;
import br.com.ifood.restaurante.domain.Restaurante;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_produto", unique = true, nullable = false)
    private Long idProduto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;


    public Produto(Restaurante restaurante, ProdutoRequest produtoRequest) {
        this.nome = produtoRequest.getNome();
        this.descricao = produtoRequest.getDescricao();
        this.preco = produtoRequest.getPreco();
        this.ativo = produtoRequest.getAtivo();
        this.restaurante = restaurante;
    }


    public void altera(ProdutoRequest produtoRequest) {
        this.nome = produtoRequest.getNome();
        this.descricao = produtoRequest.getDescricao();
        this.preco = produtoRequest.getPreco();
        this.ativo = produtoRequest.getAtivo();
    }



}
