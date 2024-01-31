package br.com.ifood.cidade.application.api;

import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/v1/cidade")
public interface CidadeAPI {

    @CheckSecurity.Cidades.PodeEditar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    CidadeResponse postCidade (@Valid @RequestBody CidadeRequest cidadeRequest);

    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<CidadeListResponse> getTodasCidades();

    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping("/{idCidade}")
    @ResponseStatus(code = HttpStatus.OK)
    CidadeDetalhadaReponse getCidadeAtravesId(@PathVariable Long idCidade);

    @CheckSecurity.Cidades.PodeEditar
    @DeleteMapping("{idCidade}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaCidadeAtravesId(@PathVariable Long idCidade);

    @CheckSecurity.Cidades.PodeEditar
    @PutMapping("/{idCidade}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchAlteraCidade (@PathVariable Long idCidade,
                            @Valid @RequestBody CidadeAlteracaoRequest cidadeAlteracaoRequest);

}
