package br.com.ifood.usuario.application.api;

import br.com.ifood.grupo.domain.Grupo;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class UsuarioGruposReponse {

    private Long idGrupo;
    private String nome;

    public static List<UsuarioGruposReponse> converte(Set<Grupo> grupos) {
        return grupos.stream()
                .map(UsuarioGruposReponse::new)
                .collect(Collectors.toList());
    }

    public UsuarioGruposReponse(Grupo grupo) {
        this.idGrupo = grupo.getIdGrupo();
        this.nome = grupo.getNome();

    }
}
