package br.com.ifood.permissao.application.service;

import br.com.ifood.permissao.application.api.PermissaoResponse;

import java.util.List;

public interface PermissaoService {

    List<PermissaoResponse> listaTodasPermissoes();

    PermissaoResponse buscaPermissaoAtravesId(Long idPermissao);
}
