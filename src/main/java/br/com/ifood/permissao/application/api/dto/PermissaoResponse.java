package br.com.ifood.permissao.application.api.dto;

import br.com.ifood.permissao.domain.Permissao;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class PermissaoResponse {

    private Long idPermissao;
    private String nome;
    private String descricao;

    public PermissaoResponse(Permissao permissao) {
    this.idPermissao = permissao.getIdPermissao();
    this.nome = permissao.getNome();
    this.descricao = permissao.getDescricao();
    }

    public static List<PermissaoResponse> converte(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(PermissaoResponse::new)
                .collect(Collectors.toList());
    }
}
