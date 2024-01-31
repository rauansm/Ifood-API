package br.com.ifood.usuario.application.api;

import br.com.ifood.usuario.application.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UsuarioController implements UsuarioAPI{

    private final UsuarioService usuarioService;

    @Override
    public UsuarioResponse postNovoUsuario(UsuarioRequest usuarioRequest) {
        log.info("[inicia] UsuarioController - postNovoUsuario");
        UsuarioResponse usuarioCriado = usuarioService.criaNovoUsuario(usuarioRequest);
        log.info("[finaliza] UsuarioController - postNovoUsuario");
        return usuarioCriado;
    }

    @Override
    public List<UsuarioResponse> getTodosUsuarios() {
        log.info("[inicia] UsuarioController - getTodosUsuarios");
        List<UsuarioResponse> usuarios = usuarioService.listaTodosUsuarios();
        log.info("[finaliza] UsuarioController - getTodosUsuarios");
        return usuarios;
    }

    @Override
    public UsuarioResponse getUsuarioAtravesId(String idUsuario) {
        log.info("[inicia] UsuarioController - getUsuarioAtravesId");
        UsuarioResponse usuario = usuarioService.buscaUsuarioAtravesId(idUsuario);
        log.info("[finaliza] UsuarioController - getUsuarioAtravesId");
        return usuario;
    }

    @Override
    public void patchAlteraUsuario(String idUsuario, AlteraUsuarioRequest alteraUsuarioRequest) {
        log.info("[inicia] UsuarioController - patchAlteraUsuario");
        usuarioService.alteraUsuario(idUsuario,alteraUsuarioRequest);
        log.info("[finaliza] UsuarioController - patchAlteraUsuario");
    }

    @Override
    public void patchAlteraSenhaDoUsuario(String idUsuario, SenhaAlteracaoRequest senhaAlteracaoRequest) {
        log.info("[inicia] UsuarioController - patchAlteraSenhaDoUsuario");
        usuarioService.alteraSenhaDoUsuario(idUsuario,senhaAlteracaoRequest);
        log.info("[finaliza] UsuarioController - patchAlteraSenhaDoUsuario");
    }

    @Override
    public List<UsuarioGruposReponse> getTodosGruposDoUsuario(String idUsuario) {
        log.info("[inicia] UsuarioController - getTodosGruposDoUsuario");
        List<UsuarioGruposReponse> gruposUsuario  = usuarioService.listaTodosGruposDoUsuario(idUsuario);
        log.info("[finaliza] UsuarioController - getTodosGruposDoUsuario");
        return gruposUsuario;
    }

    @Override
    public void associarGrupo(String idUsuario, Long idGrupo) {
        log.info("[inicia] UsuarioController - associarGrupo");
        usuarioService.associaGrupo(idGrupo,idUsuario);
        log.info("[finaliza] UsuarioController - associarGrupo");
    }

    @Override
    public void desassociarGrupo(String idUsuario, Long idGrupo) {
        log.info("[inicia] UsuarioController - desassociaGrupo");
        usuarioService.desassociaGrupo(idGrupo,idUsuario);
        log.info("[finaliza] UsuarioController - desassociaGrupo");

    }
}
