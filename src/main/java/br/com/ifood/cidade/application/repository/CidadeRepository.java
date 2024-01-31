package br.com.ifood.cidade.application.repository;

import br.com.ifood.cidade.domain.Cidade;

import java.util.List;

public interface CidadeRepository {


    Cidade salva(Cidade cidade);

    List<Cidade> buscaTodasCidades();

    Cidade buscaCidadeAtravesId(Long idCidade);

    void deletaCidade(Cidade cidade);
}
