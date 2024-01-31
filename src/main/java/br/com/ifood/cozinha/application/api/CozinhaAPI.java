package br.com.ifood.cozinha.application.api;

import br.com.ifood.security.annotations.CheckSecurity;
import br.com.ifood.security.annotations.PodeConsultarCozinhas;
import br.com.ifood.security.annotations.PodeEditarCozinhas;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/cozinha")
public interface CozinhaAPI {
    @CheckSecurity.Cozinhas.PodeEditar
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    CozinhaResponse postCozinha (@Valid @RequestBody CozinhaRequest cozinhaRequest);

    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<CozinhaListResponse> getTodasCozinhas();

    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping("/{idCozinha}")
    @ResponseStatus(code = HttpStatus.OK)
    CozinhaDetalhadaResponse getCozinhasAtravesId(@PathVariable Long idCozinha);

    @CheckSecurity.Cozinhas.PodeEditar
    @DeleteMapping("/{idCozinha}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaCozinhaAtravesId(@PathVariable Long idCozinha);

    @CheckSecurity.Cozinhas.PodeEditar
    @PutMapping("/{idCozinha}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void putAlteraCozinha(@PathVariable Long idCozinha, @Valid @RequestBody CozinhaAlteracaoRequest cozinhaAlteracaoRequest);

}
