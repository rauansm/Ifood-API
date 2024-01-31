package br.com.ifood.usuario.domain;

import br.com.ifood.grupo.domain.Grupo;
import br.com.ifood.handler.APIException;
import br.com.ifood.usuario.application.api.AlteraUsuarioRequest;
import br.com.ifood.usuario.application.api.SenhaAlteracaoRequest;
import br.com.ifood.usuario.application.api.UsuarioRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(columnDefinition = "varchar", name = "id_usuario", updatable = false, unique = true, nullable = false)
    private String idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private Set<Grupo> grupos = new HashSet<>();

    public Usuario(UsuarioRequest usuarioRequest) {
        this.idUsuario = UUID.randomUUID().toString();
        this.nome = usuarioRequest.getNome();
        this.email = usuarioRequest.getEmail();
        var encriptador = new BCryptPasswordEncoder();
        this.senha = encriptador.encode(usuarioRequest.getSenha());
    }

    public void altera(AlteraUsuarioRequest alteraUsuarioRequest) {
        this.nome = alteraUsuarioRequest.getNome();
        this.email = alteraUsuarioRequest.getEmail();
    }

    public void verificaSenha(SenhaAlteracaoRequest senhaAlteracaoRequest) {
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
        if (!encriptador.matches(senhaAlteracaoRequest.getSenhaAtual(), this.senha)) {
            throw APIException.Negocio("Senha incorreta!");
        }
        if (!senhaAlteracaoRequest.getNovaSenha().equals(senhaAlteracaoRequest.getConfirmaSenha())) {
            throw APIException.Negocio("Senhas não coincidem!");
        }
        if (encriptador.matches(senhaAlteracaoRequest.getNovaSenha(), this.senha)) {
            throw APIException.Negocio("Senha usada anteriormente!");
        }
    }

    public void alteraSenha(SenhaAlteracaoRequest senhaAlteracaoRequest) {
        var encriptador = new BCryptPasswordEncoder();
        this.senha = encriptador.encode(senhaAlteracaoRequest.getNovaSenha());

    }

    public void associaGrupo(Grupo grupo) {
        getGrupos().add(grupo);
    }

    public void desassociaGrupo(Grupo grupo) {
        getGrupos().remove(grupo);
    }
}

