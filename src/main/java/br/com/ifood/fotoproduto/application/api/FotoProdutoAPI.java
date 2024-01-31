package br.com.ifood.fotoproduto.application.api;

import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/v1/restaurante/{idRestaurante}/produto/{idProduto}/foto")
public interface FotoProdutoAPI {

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    FotoProdutoResponse postAdicionarFoto(@PathVariable Long idRestaurante, @PathVariable Long idProduto,
                                         @Valid FotoProdutoRequest fotoProdutoRequest) throws IOException;
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(value = "/{idFotoProduto}" , produces = MediaType.ALL_VALUE)
    ResponseEntity<?> getFotoProduto (@PathVariable Long idRestaurante, @PathVariable Long idProduto,
                                      @PathVariable Long idFotoProduto);
    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @DeleteMapping("/{idFotoProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteFotoProduto (@PathVariable Long idRestaurante, @PathVariable Long idProduto,
                            @PathVariable Long idFotoProduto);
}
