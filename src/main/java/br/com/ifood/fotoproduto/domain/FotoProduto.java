package br.com.ifood.fotoproduto.domain;

import br.com.ifood.fotoproduto.application.api.FotoProdutoRequest;
import br.com.ifood.produto.domain.Produto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FotoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto_produto")
    private Long idFotoProduto;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    public FotoProduto(Produto produto, FotoProdutoRequest fotoProdutoRequest) {
    this.produto = produto;
    this.nomeArquivo = fotoProdutoRequest.getArquivo().getOriginalFilename();
    this.descricao = fotoProdutoRequest.getDescricao();
    this.contentType = fotoProdutoRequest.getArquivo().getContentType();
    this.tamanho = fotoProdutoRequest.getArquivo().getSize();
    }
}
