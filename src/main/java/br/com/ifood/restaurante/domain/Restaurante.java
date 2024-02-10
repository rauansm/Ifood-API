package br.com.ifood.restaurante.domain;

import br.com.ifood.cozinha.domain.Cozinha;
import br.com.ifood.cidade.domain.Cidade;
import br.com.ifood.endereco.domain.Endereco;
import br.com.ifood.formapagamento.domain.FormaPagamento;
import br.com.ifood.handler.APIException;
import br.com.ifood.produto.domain.Produto;
import br.com.ifood.restaurante.application.api.dto.RestauranteRequest;
import br.com.ifood.usuario.domain.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_restaurante", updatable = false, unique = true, nullable = false)
    private Long idRestaurante;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.FALSE;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> responsaveis = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    public Restaurante(RestauranteRequest restauranteRequest, Cozinha cozinha, Cidade cidade) {
        this.nome = restauranteRequest.getNome();
        this.taxaFrete = restauranteRequest.getTaxaFrete();
        this.cozinha = cozinha;
        this.endereco = new Endereco(restauranteRequest.getEndereco(), cidade);
    }

    public void altera(RestauranteRequest restauranteAlteracaoRequest, Cidade cidade, Cozinha cozinha) {
        this.nome = restauranteAlteracaoRequest.getNome();
        this.taxaFrete = restauranteAlteracaoRequest.getTaxaFrete();
        this.cozinha = cozinha;
        this.endereco = new Endereco(restauranteAlteracaoRequest.getEndereco(), cidade);
    }

    public void ativar() {
        this.ativo = Boolean.TRUE;
    }

    public void desativar() {
        this.ativo = Boolean.FALSE;
    }

    public void abrir() {
        this.aberto = Boolean.TRUE;
    }

    public void fechar() {
        this.aberto = Boolean.FALSE;
    }

    public void associaFormaPagamento(FormaPagamento formaPagamento) {
        this.formasPagamento.add(formaPagamento);
    }

    public void desassociaFormaPagamento(FormaPagamento formaPagamento) {
        this.formasPagamento.remove(formaPagamento);
    }

    public void associaResponsavel(Usuario usuario) {
        this.getResponsaveis().add(usuario);
    }

    public void desassociaResponsavel(Usuario usuario) {
        this.getResponsaveis().remove(usuario);
    }

    public void naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
        if (!this.formasPagamento.contains(formaPagamento)) {
            throw APIException.negocio(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }
}
