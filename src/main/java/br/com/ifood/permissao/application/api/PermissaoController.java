package br.com.ifood.permissao.application.api;

import br.com.ifood.permissao.application.api.dto.PermissaoResponse;
import br.com.ifood.permissao.application.service.PermissaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PermissaoController implements PermissaoAPI{
    private final PermissaoService permissaoService;

    @Override
    public List<PermissaoResponse> getTodasPermissoes() {
        log.info("[inicia] PermissaoController - getTodasPermissoes");
        List<PermissaoResponse> permissoes = permissaoService.listaTodasPermissoes();
        log.info("[finaliza] PermissaoController - getTodasPermissoes");
        return permissoes;
    }

    @Override
    public PermissaoResponse getPermissaoAtravesId(Long idPermissao) {
        log.info("[inicia] PermissaoController - getPermissaoAtravesId");
        PermissaoResponse permissao = permissaoService.buscaPermissaoAtravesId(idPermissao);
        log.info("[finaliza] PermissaoController - getPermissaoAtravesId");
        return permissao;
    }
}
