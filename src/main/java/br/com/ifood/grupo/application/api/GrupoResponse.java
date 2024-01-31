package br.com.ifood.grupo.application.api;

import br.com.ifood.grupo.domain.Grupo;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class GrupoResponse {
    private Long idGrupo;
    private String nome;

    public GrupoResponse(Grupo novoGrupo) {
        this.idGrupo = novoGrupo.getIdGrupo();
        this.nome = novoGrupo.getNome();
    }

    public static List<GrupoResponse> converte(List<Grupo> grupos) {
        return grupos.stream()
                .map(GrupoResponse::new)
                .collect(Collectors.toList());
    }
}
