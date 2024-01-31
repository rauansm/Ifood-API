package br.com.ifood.fotoproduto.application.api.dto;

import br.com.ifood.fotoproduto.domain.FotoProduto;
import lombok.Value;

@Value
public class FotoProdutoResponse {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    public FotoProdutoResponse(FotoProduto foto) {
        this.nomeArquivo = foto.getNomeArquivo();
        this.descricao = foto.getDescricao();
        this.contentType = foto.getContentType();
        this.tamanho = foto.getTamanho();
    }
}
