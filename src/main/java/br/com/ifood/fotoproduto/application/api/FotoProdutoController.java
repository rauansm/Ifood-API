package br.com.ifood.fotoproduto.application.api;

import br.com.ifood.fotoproduto.application.api.dto.FotoProdutoRequest;
import br.com.ifood.fotoproduto.application.api.dto.FotoProdutoResponse;
import br.com.ifood.fotoproduto.application.service.FotoProdutoService;
import br.com.ifood.fotoproduto.infra.FotoStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FotoProdutoController implements FotoProdutoAPI {

    private final FotoProdutoService fotoProdutoService;

    @Override
    public FotoProdutoResponse postAdicionarFoto(Long idRestaurante, Long idProduto, FotoProdutoRequest fotoProdutoRequest) throws IOException {
        log.info("[inicia] FotoProdutoController - postAdicionarFoto");
        FotoProdutoResponse fotoProduto = fotoProdutoService.adicionaNovaFoto(idProduto, fotoProdutoRequest, idRestaurante);
        log.info("[finaliza] FotoProdutoController - postAdicionarFoto");
        return fotoProduto;
    }

    @Override
    public ResponseEntity<?> getFotoProduto(Long idRestaurante, Long idProduto, Long idFotoProduto) {
        log.info("[inicia] FotoProdutoController - getFotoProduto");
        FotoStorage.FotoRecuperada fotoRecuperada = fotoProdutoService.recuperaFoto(idRestaurante, idProduto, idFotoProduto);
        log.info("[finaliza] FotoProdutoController - getFotoProduto");
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                .build();
    }

    @Override
    public void deleteFotoProduto(Long idRestaurante, Long idProduto, Long idFotoProduto) {
        log.info("[inicia] FotoProdutoController - deleteFotoProduto");
        fotoProdutoService.deletaFotoProduto(idFotoProduto,idRestaurante,idProduto);
        log.info("[finaliza] FotoProdutoController - deleteFotoProduto");

    }
}
