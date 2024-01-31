package br.com.ifood.usuario.application.api;

import br.com.ifood.security.annotations.CheckSecurity;
import br.com.ifood.usuario.application.api.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/usuario")
public interface UsuarioAPI {


    @PostMapping("/novo-usuario")
    @ResponseStatus(code = HttpStatus.CREATED)
    UsuarioResponse postNovoUsuario (@Valid @RequestBody UsuarioRequest usuarioRequest);

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<UsuarioResponse> getTodosUsuarios();

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping("/{idUsuario}")
    @ResponseStatus(code = HttpStatus.OK)
    UsuarioResponse getUsuarioAtravesId (@PathVariable String idUsuario);

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
    @PatchMapping("/{idUsuario}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchAlteraUsuario (@PathVariable String idUsuario,
                             @Valid @RequestBody AlteraUsuarioRequest alteraUsuarioRequest);

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropiaSenha
    @PatchMapping("/{idUsuario}/senha")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchAlteraSenhaDoUsuario (@PathVariable String idUsuario,
                                  @Valid @RequestBody SenhaAlteracaoRequest senhaAlteracaoRequest);

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping("/{idUsuario}/grupos")
    @ResponseStatus(code = HttpStatus.OK)
    List<UsuarioGruposReponse> getTodosGruposDoUsuario (@PathVariable String idUsuario);

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PatchMapping("/{idUsuario}/grupo/{idGrupo}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void associarGrupo (@PathVariable String idUsuario, @PathVariable Long idGrupo);

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{idUsuario}/grupo/{idGrupo}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void desassociarGrupo (@PathVariable String idUsuario, @PathVariable Long idGrupo);
}
