package br.com.ifood.cidade.application.service;

import br.com.ifood.cidade.application.api.dto.*;
import br.com.ifood.cidade.application.repository.CidadeRepository;
import br.com.ifood.cidade.domain.Cidade;
import br.com.ifood.estado.application.repository.EstadoRepository;
import br.com.ifood.estado.domain.Estado;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CidadeApplicationService implements CidadeService{

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    @Transactional
    @Override
    public CidadeResponse criaCidade(CidadeRequest cidadeRequest) {
        log.info("[inicia] CidadeApplicationService - criaCidade");
        Estado estado = estadoRepository.buscaEstadoAtravesId(cidadeRequest.getEstado().getIdEstado());
        Cidade cidade = cidadeRepository.salva(new Cidade(cidadeRequest,estado));
        log.info("[finaliza] CidadeApplicationService - criaCidade");
        return CidadeResponse.builder()
                .idCidade(cidade.getIdCidade())
                .nome(cidade.getNome())
                .estado(new EstadoResponse(estado))
                .build();
    }

    @Override
    public List<CidadeListResponse> listaTodasCidades() {
        log.info("[inicia] CidadeApplicationService - listaTodasCidades");
        List<Cidade> cidades = cidadeRepository.buscaTodasCidades();
        log.info("[finaliza] CidadeApplicationService - listaTodasCidades");
        return CidadeListResponse.converte(cidades);
    }

    @Override
    public CidadeDetalhadaReponse buscaCidadeAtravesId(Long idCidade) {
        log.info("[inicia] CidadeApplicationService - buscaCidadeAtravesId");
        Cidade cidade = cidadeRepository.buscaCidadeAtravesId(idCidade);
        log.info("[finaliza] CidadeApplicationService - buscaCidadeAtravesId");
        return new CidadeDetalhadaReponse(cidade);
    }
    @Transactional
    @Override
    public void deletaCidadeAtravesId(Long idCidade) {
        log.info("[inicia] CidadeApplicationService - deletaCidadeAtravesId");
        Cidade cidade = cidadeRepository.buscaCidadeAtravesId(idCidade);
        cidadeRepository.deletaCidade(cidade);
        log.info("[finaliza] CidadeApplicationService - deletaCidadeAtravesId");

    }
    @Transactional
    @Override
    public void alteraCidade(Long idCidade, CidadeAlteracaoRequest cidadeAlteracaoRequest) {
        log.info("[inicia] CidadeApplicationService - alteraCidade");
        Cidade cidade = cidadeRepository.buscaCidadeAtravesId(idCidade);
        Estado estado = estadoRepository.buscaEstadoAtravesId(cidadeAlteracaoRequest.getEstado().getIdEstado());
        cidade.altera(cidadeAlteracaoRequest,estado);
        cidadeRepository.salva(cidade);
        log.info("[finaliza] CidadeApplicationService - alteraCidade");
    }
}
