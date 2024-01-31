package br.com.ifood.usuario.application.service;

import br.com.ifood.grupo.application.repository.GrupoRepository;
import br.com.ifood.grupo.domain.Grupo;
import br.com.ifood.usuario.application.api.dto.*;
import br.com.ifood.usuario.application.repository.UsuarioRepository;
import br.com.ifood.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsuarioApplicationService implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    @Transactional
    @Override
    public UsuarioResponse criaNovoUsuario(UsuarioRequest usuarioRequest) {
        log.info("[inicia] UsuarioApplicationService - criaNovoUsuario");
        usuarioRequest.validaSenha();
        Usuario usuarioNovo = usuarioRepository.salva(new Usuario(usuarioRequest));
        log.info("[finaliza] UsuarioApplicationService - criaNovoUsuario");
        return new UsuarioResponse(usuarioNovo);
    }

    @Override
    public List<UsuarioResponse> listaTodosUsuarios() {
        log.info("[inicia] UsuarioApplicationService - listaTodosUsuarios");
        List<Usuario> usuarios = usuarioRepository.buscaTodosUsuarios();
        log.info("[finaliza] UsuarioApplicationService - listaTodosUsuarios");
        return UsuarioResponse.converte(usuarios);
    }

    @Override
    public UsuarioResponse buscaUsuarioAtravesId(String idUsuario) {
        log.info("[inicia] UsuarioApplicationService - buscaUsuarioAtravesId");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idUsuario);
        log.info("[finaliza] UsuarioApplicationService - buscaUsuarioAtravesId");
        return new UsuarioResponse(usuario);
    }
    @Transactional
    @Override
    public void alteraUsuario(String idUsuario, AlteraUsuarioRequest alteraUsuarioRequest) {
        log.info("[inicia] UsuarioApplicationService - alteraUsuario");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idUsuario);
        usuario.altera(alteraUsuarioRequest);
        usuarioRepository.salva(usuario);
        log.info("[finaliza] UsuarioApplicationService - alteraUsuario");
    }
    @Transactional
    @Override
    public void alteraSenhaDoUsuario(String idUsuario, SenhaAlteracaoRequest senhaAlteracaoRequest) {
        log.info("[inicia] UsuarioApplicationService - alteraSenhaDoUsuario");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idUsuario);
        usuario.verificaSenha(senhaAlteracaoRequest);
        usuario.alteraSenha(senhaAlteracaoRequest);
        usuarioRepository.salva(usuario);
        log.info("[finaliza] UsuarioApplicationService - alteraSenhaDoUsuario");
    }

    @Override
    public List<UsuarioGruposReponse> listaTodosGruposDoUsuario(String idUsuario) {
        log.info("[inicia] UsuarioApplicationService - listaTodosGruposDoUsuario");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idUsuario);
        log.info("[finaliza] UsuarioApplicationService - listaTodosGruposDoUsuario");
        return UsuarioGruposReponse.converte(usuario.getGrupos());
    }
    @Transactional
    @Override
    public void associaGrupo(Long idGrupo, String idUsuario) {
        log.info("[inicia] UsuarioApplicationService - associaGrupo");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idUsuario);
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        usuario.associaGrupo(grupo);
        usuarioRepository.salva(usuario);
        log.info("[finaliza] UsuarioApplicationService - associaGrupo");
    }
    @Transactional
    @Override
    public void desassociaGrupo(Long idGrupo, String idUsuario) {
        log.info("[inicia] UsuarioApplicationService - desassociaGrupo");
        Usuario usuario = usuarioRepository.buscaUsuarioAtravesId(idUsuario);
        Grupo grupo = grupoRepository.buscaGrupoAtravesId(idGrupo);
        usuario.desassociaGrupo(grupo);
        usuarioRepository.salva(usuario);
        log.info("[finaliza] UsuarioApplicationService - desassociaGrupo");

    }
}
