package br.com.ifood.grupo.application.api;

import br.com.ifood.grupo.application.api.dto.GrupoPermissaoResponse;
import br.com.ifood.grupo.application.api.dto.GrupoRequest;
import br.com.ifood.grupo.application.api.dto.GrupoResponse;
import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/grupo")
public interface GrupoAPI {

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    GrupoResponse postNovoGrupo (@Valid @RequestBody GrupoRequest grupoRequest);

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<GrupoResponse> getTodosGrupos ();

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping("/{idGrupo}")
    @ResponseStatus(code = HttpStatus.OK)
    GrupoResponse getGrupoAtravesId (@PathVariable Long idGrupo);

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PatchMapping("/{idGrupo}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchAlteraGrupo(@PathVariable Long idGrupo,
                          @Valid @RequestBody GrupoRequest grupoRequest);

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaGrupo (@PathVariable Long idGrupo);

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping("/{idGrupo}/permissao")
    @ResponseStatus(code = HttpStatus.OK)
    List<GrupoPermissaoResponse> getTodasPermissoesDoGrupo (@PathVariable Long idGrupo);

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PatchMapping("/{idGrupo}/permissao/{idPermissao}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void associarPermissao (@PathVariable Long idGrupo, @PathVariable Long idPermissao);

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{idGrupo}/permissao/{idPermissao}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void desassociarPermissao (@PathVariable Long idGrupo, @PathVariable Long idPermissao);

}
