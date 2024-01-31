package br.com.ifood.cidade.application.api;

import br.com.ifood.cidade.domain.Cidade;
import lombok.Value;

@Value
public class CidadeDetalhadaReponse {

    private Long idCidade;
    private String nome;
    private  EstadoResponse estado;

    public CidadeDetalhadaReponse(Cidade cidade) {
        this.idCidade = cidade.getIdCidade();
        this.nome = cidade.getNome();
        this.estado = new EstadoResponse(cidade.getEstado());
    }
}
