package br.com.ifood.permissao.application.repository;

import br.com.ifood.permissao.domain.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> buscaTodasPermissoes();

    Permissao buscaPermissaoAtravesId(Long idPermissao);
}
