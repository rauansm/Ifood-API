package br.com.ifood.estado.application.api;

import br.com.ifood.estado.application.api.dto.*;
import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/estado")
public interface EstadoAPI {

    @CheckSecurity.Estados.PodeEditar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    EstadoReponse postEstado (@RequestBody EstadoRequest estadoRequest);

    @CheckSecurity.Estados.PodeConsultar
    @GetMapping("/{idEstado}")
    @ResponseStatus(code = HttpStatus.OK)
    EstadoDetalhadoResponse getEstadoAtravesId (@PathVariable Long idEstado);

    @CheckSecurity.Estados.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<EstadoListResponse> getTodosEstados();

    @CheckSecurity.Estados.PodeEditar
    @DeleteMapping("/{idEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletaEstadoAtravesId(@PathVariable Long idEstado);

    @CheckSecurity.Estados.PodeEditar
    @PutMapping("/{idEstado}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void putAlteraEstado(@PathVariable Long idEstado,
                         @Valid @RequestBody EstadoAlteracaoRequest estadoAlteracaoRequest);
}
