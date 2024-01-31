package br.com.ifood.permissao.application.service;

import br.com.ifood.permissao.application.api.PermissaoResponse;
import br.com.ifood.permissao.application.repository.PermissaoRepository;
import br.com.ifood.permissao.domain.Permissao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PermissaoApplicationService implements PermissaoService{
    private final PermissaoRepository permissaoRepository;
    @Override
    public List<PermissaoResponse> listaTodasPermissoes() {
        log.info("[inicia] PermissaoApplicationService - listaTodasPermissoes");
        List<Permissao> permissoes = permissaoRepository.buscaTodasPermissoes();
        log.info("[finaliza] PermissaoApplicationService - listaTodasPermissoes");
        return PermissaoResponse.converte(permissoes);
    }

    @Override
    public PermissaoResponse buscaPermissaoAtravesId(Long idPermissao) {
        log.info("[inicia] PermissaoApplicationService - buscaPermissaoAtravesId");
        Permissao permissao = permissaoRepository.buscaPermissaoAtravesId(idPermissao);
        log.info("[inicia] PermissaoApplicationService - buscaPermissaoAtravesId");
        return new PermissaoResponse(permissao);
    }
}
