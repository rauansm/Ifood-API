package br.com.ifood.cozinha.application.repository;

import br.com.ifood.cozinha.application.api.CozinhaRequest;
import br.com.ifood.cozinha.domain.Cozinha;

import java.util.List;
import java.util.Optional;

public interface CozinhaRepository {
    Cozinha salva(Cozinha cozinha);

    List<Cozinha> buscaTodasCozinhas();

    Cozinha buscaCozinhaPeloId(Long idCozinha);

    void deletaCozinha(Cozinha cozinha);

}
