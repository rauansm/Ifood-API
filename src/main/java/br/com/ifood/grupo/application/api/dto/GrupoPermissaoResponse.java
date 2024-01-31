package br.com.ifood.grupo.application.api.dto;

import br.com.ifood.permissao.domain.Permissao;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class GrupoPermissaoResponse {
    private Long idPermissao;
    private String nome;
    private String descricao;

    public GrupoPermissaoResponse(Permissao permissao) {
        this.idPermissao = permissao.getIdPermissao();
        this.nome = permissao.getNome();
        this.descricao = permissao.getDescricao();
    }

    public static List<GrupoPermissaoResponse> converte(Set<Permissao> permissoes) {
       return permissoes.stream()
                .map(GrupoPermissaoResponse::new)
                .collect(Collectors.toList());
    }
}
