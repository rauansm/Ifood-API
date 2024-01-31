package br.com.ifood.produto.application.api;

import br.com.ifood.produto.application.api.dto.ProdutoRequest;
import br.com.ifood.produto.application.api.dto.ProdutoResponse;
import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurante/{idRestaurante}/produto")
public interface ProdutoAPI {

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ProdutoResponse postProduto (@PathVariable Long idRestaurante,
                                 @RequestBody ProdutoRequest produtoRequest);

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<ProdutoResponse> getProdutosDoRestauranteAtravesId (@PathVariable Long idRestaurante,
                                                            @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos);
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/{idProduto}")
    @ResponseStatus(code = HttpStatus.OK)
    ProdutoResponse getProdutoDoRestaurante (@PathVariable Long idRestaurante, @PathVariable Long idProduto);

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PutMapping("/{idProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void putAlteraProduto (@PathVariable Long idRestaurante, @PathVariable Long idProduto,
                                 @RequestBody ProdutoRequest produtoRequest);

}
