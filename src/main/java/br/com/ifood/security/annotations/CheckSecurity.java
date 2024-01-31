package br.com.ifood.security.annotations;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    public @interface Cozinhas {
    @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface PodeEditar {

    }

    @PreAuthorize("isAuthenticated()")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface PodeConsultar {

        }
    }

    public @interface Restaurantes {

        @PreAuthorize("hasAuthority('EDITAR_RESTAURANTES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeGerenciarCadastro {
        }

        @PreAuthorize("hasAuthority('EDITAR_RESTAURANTES') or "
                + "@ifoodSecurity.gerenciaRestaurante(#idRestaurante)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeGerenciarFuncionamento {
        }

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {

        }
    }
        public @interface Pedidos {
            @PreAuthorize("isAuthenticated()")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(ElementType.METHOD)
            public @interface PodeCriar {

            }
            @PreAuthorize("isAuthenticated()")
            @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or" +
                    "@ifoodSecurity.getUsuarioId() == returnObject.cliente.idUsuario or" +
                    "@ifoodSecurity.gerenciaRestaurante(returnObject.restaurante.idRestaurante)")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(ElementType.METHOD)
            public @interface PodeBuscar {
            }

            @PreAuthorize("isAuthenticated() and hasAuthority('CONSULTAR_PEDIDOS') or" +
                    "@ifoodSecurity.getUsuarioId() == #pedidoFiltro.idUsuario or" +
                    "@ifoodSecurity.gerenciaRestaurante(#pedidoFiltro.restauranteId)")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(ElementType.METHOD)
            public @interface PodePesquisar {
            }
            @PreAuthorize("isAuthenticated() and hasAuthority('GERENCIAR_PEDIDOS') or" +
                    "@ifoodSecurity.gerenciaRestauranteDoPedido(#codigoPedido)")
            @Retention(RetentionPolicy.RUNTIME)
            @Target(ElementType.METHOD)
            public @interface PodeGerenciarPedidos {

            }
        }

    public @interface FormasPagamento {
        @PreAuthorize("hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeEditar {

        }

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {

        }
    }

    public @interface Cidades {
        @PreAuthorize("hasAuthority('EDITAR_CIDADES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeEditar {

        }

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {

        }
    }

    public @interface Estados {
        @PreAuthorize("hasAuthority('EDITAR_ESTADOS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeEditar {

        }

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {

        }
    }

    public @interface UsuariosGruposPermissoes {
        @PreAuthorize("isAuthenticated() and @ifoodSecurity.getUsuarioId == #idUsuario")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeAlterarPropiaSenha {

        }

        @PreAuthorize("hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or " +
                "@ifoodSecurity.getUsuarioId == #idUsuario")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeAlterarUsuario {

        }
        @PreAuthorize("hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeEditar {

        }

        @PreAuthorize("hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {

        }
    }

    public @interface Estatisticas {
        @PreAuthorize("hasAuthority('GERAR_RELATORIOS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar {

        }
    }
    }
