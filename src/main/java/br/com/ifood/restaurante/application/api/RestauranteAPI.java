package br.com.ifood.restaurante.application.api;

import br.com.ifood.restaurante.application.api.dto.*;
import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurante")
public interface RestauranteAPI {
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    RestauranteResponse postRestaurante (@Valid @RequestBody RestauranteRequest restauranteRequest);

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<RestauranteListResponse> getTodosRestaurantes();

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/frete-gratis")
    @ResponseStatus(code = HttpStatus.OK)
    List<RestauranteListResponse> getTodosRestaurantesFreteGratis();

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/{idRestaurante}")
    @ResponseStatus(code = HttpStatus.OK)
    RestauranteResponse getRestauranteAtravesId(@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PatchMapping("/{idRestaurante}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchAlteraRestaurante (@PathVariable Long idRestaurante,
                                 @Valid @RequestBody RestauranteRequest restauranteAlteracaoRequest);

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PatchMapping("/ativa-restaurante/{idRestaurante}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void ativaRestaurante(@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PatchMapping("/desativa-restaurante/{idRestaurante}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void desativaRestaurante(@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PatchMapping("/abrir-restaurante/{idRestaurante}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void abrirRestaurante(@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PatchMapping("/fechar-restaurante/{idRestaurante}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void fecharRestaurante(@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PatchMapping("/{idRestaurante}/forma-pagamento/{idPagamento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void associarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idPagamento);

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @DeleteMapping("/{idRestaurante}/forma-pagamento/{idPagamento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void desassociarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idPagamento);

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/{idRestaurante}/forma-pagamento")
    @ResponseStatus(code = HttpStatus.OK)
    Collection<RestauranteFormaPagamentoResponse> getFormasPagamentoRestaurante(@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @GetMapping("/{idRestaurante}/responsaveis")
    @ResponseStatus(code = HttpStatus.OK)
    List<ResponsaveisRestauranteResponse> getTodosResponsaveisRestaurante (@PathVariable Long idRestaurante);

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PatchMapping("/{idRestaurante}/responsavel/{idResponsavel}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void associarResponsavel (@PathVariable Long idRestaurante, @PathVariable String idResponsavel);

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{idRestaurante}/responsavel/{idResponsavel}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void desassociarResponsavel (@PathVariable Long idRestaurante, @PathVariable String idResponsavel);
}
