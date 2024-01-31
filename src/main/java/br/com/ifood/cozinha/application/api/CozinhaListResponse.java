package br.com.ifood.cozinha.application.api;

import br.com.ifood.cozinha.domain.Cozinha;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class CozinhaListResponse {

    private Long idCozinha;
    private String nome;

    public static List<CozinhaListResponse> converte(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(c-> new CozinhaListResponse(c))
                .collect(Collectors.toList());
    }

    public CozinhaListResponse(Cozinha c) {
        this.idCozinha = c.getIdCozinha();
        this.nome = c.getNome();
    }
}

