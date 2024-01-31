package br.com.ifood.fotoproduto.infra;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FotoStorage {
    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    @Builder
    @Getter
    class NovaFoto {

        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;


    }

    @Builder
    @Getter
    class FotoRecuperada {

        private InputStream inputStream;
        private String url;


    }
}
