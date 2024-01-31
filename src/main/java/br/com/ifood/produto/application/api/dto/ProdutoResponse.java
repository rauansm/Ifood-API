package br.com.ifood.produto.application.api.dto;

import br.com.ifood.produto.domain.Produto;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ProdutoResponse {

    private Long idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

    public ProdutoResponse(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.ativo = produto.getAtivo();
    }

    public static List<ProdutoResponse> converte(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoResponse::new)
                .collect(Collectors.toList());
    }
}
