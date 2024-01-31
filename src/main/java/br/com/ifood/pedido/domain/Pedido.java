package br.com.ifood.pedido.domain;

import br.com.ifood.cidade.domain.Cidade;
import br.com.ifood.endereco.domain.Endereco;
import br.com.ifood.formapagamento.domain.FormaPagamento;
import br.com.ifood.handler.APIException;
import br.com.ifood.pedido.application.api.dto.PedidoRequest;
import br.com.ifood.restaurante.domain.Restaurante;
import br.com.ifood.usuario.domain.Usuario;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_pedido", updatable = false, unique = true, nullable = false)
    private Long idPedido;

    private String codigoPedido;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();


    public Pedido(PedidoRequest pedidoRequest, Usuario usuario, FormaPagamento formaPagamento, Restaurante restaurante, Cidade cidade) {
        this.codigoPedido = UUID.randomUUID().toString();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.enderecoEntrega = new Endereco(pedidoRequest.getEnderecoEntrega(), cidade);
        this.status = StatusPedido.CRIADO;
        this.formaPagamento = formaPagamento;
        this.restaurante = restaurante;
        this.cliente = usuario;
    }


    public void calculaValorTotal() {
        this.getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItens().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmaPedido() {
        if (status.equals(StatusPedido.ENTREGUE) || status.equals(StatusPedido.CANCELADO) || status.equals(StatusPedido.CONFIRMADO )){
            throw APIException.Negocio("Pedido não pode ser Confirmado!");}
            this.status = StatusPedido.CONFIRMADO;
            this.dataConfirmacao = LocalDateTime.now();

            registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void entregaPedido() {
        if (status.equals(StatusPedido.CRIADO) || status.equals(StatusPedido.CANCELADO) || status.equals(StatusPedido.ENTREGUE )) {
            throw APIException.Negocio("Pedido não pode ser Entregue!");}
        this.status = StatusPedido.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();
    }

    public void cancelaPedido() {
        if (status.equals(StatusPedido.CONFIRMADO) || status.equals(StatusPedido.ENTREGUE) || status.equals(StatusPedido.CANCELADO ) ) {
            throw APIException.Negocio("Pedido não pode ser Cancelado!");}
        this.status = StatusPedido.CANCELADO;
        this.dataCancelamento = LocalDateTime.now();
    }
}