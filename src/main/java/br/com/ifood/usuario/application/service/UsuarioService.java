package br.com.ifood.usuario.application.service;

import br.com.ifood.usuario.application.api.dto.*;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse criaNovoUsuario(UsuarioRequest usuarioRequest);

    List<UsuarioResponse> listaTodosUsuarios();

    UsuarioResponse buscaUsuarioAtravesId(String idUsuario);

    void alteraUsuario(String idUsuario, AlteraUsuarioRequest alteraUsuarioRequest);

    void alteraSenhaDoUsuario(String idUsuario,  SenhaAlteracaoRequest senhaAlteracaoRequest);

    List<UsuarioGruposReponse> listaTodosGruposDoUsuario(String idUsuario);

    void associaGrupo(Long idGrupo, String idUsuario);

    void desassociaGrupo(Long idGrupo, String idUsuario);
}
