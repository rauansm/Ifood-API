package br.com.ifood.formapagamento.domain;

import br.com.ifood.formapagamento.application.api.FormaPagamentoRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", name = "id_pagamento", updatable = false, unique = true, nullable = false)
    private Long idPagamento;

    @Column(nullable = false)
    private String descricao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;


    public FormaPagamento(FormaPagamentoRequest formaPagamentoRequest){
        this.descricao = formaPagamentoRequest.getDescricao();
    }

    public void altera(FormaPagamentoRequest formaPagamentoRequest) {
        this.descricao = formaPagamentoRequest.getDescricao();
    }
}
