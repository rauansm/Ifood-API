package br.com.ifood.permissao.application.api;

import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/permissao")
public interface PermissaoAPI {

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<PermissaoResponse> getTodasPermissoes();

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping("/{idPermissao}")
    @ResponseStatus(code = HttpStatus.OK)
    PermissaoResponse getPermissaoAtravesId(@PathVariable Long idPermissao);
}
