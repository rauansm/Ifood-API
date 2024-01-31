package br.com.ifood.permissao.infra;

import br.com.ifood.permissao.application.repository.PermissaoRepository;
import br.com.ifood.permissao.domain.Permissao;
import br.com.ifood.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PermissaoRepositoryJPA implements PermissaoRepository {

        private final PermissaoSpringDataJPA permissaoSpringDataJPA;
    @Override
    public List<Permissao> buscaTodasPermissoes() {
        log.info("[inicia] PermissaoRepositoryJPA - buscaTodasPermissoes");
        List<Permissao> permissoes = permissaoSpringDataJPA.findAll();
        log.info("[finaliza] PermissaoRepositoryJPA - buscaTodasPermissoes");
        return permissoes;
    }

    @Override
    public Permissao buscaPermissaoAtravesId(Long idPermissao) {
        log.info("[inicia] PermissaoRepositoryJPA - buscaPermissaoAtravesId");
        Optional<Permissao> permissao = permissaoSpringDataJPA.findById(idPermissao);
        log.info("[finaliza] PermissaoRepositoryJPA - buscaPermissaoAtravesId");
        return permissao.orElseThrow(() -> APIException.EntidadeNaoEncontrada(String.format("Permissão com Id %s não encontrada", idPermissao)));
    }
}
