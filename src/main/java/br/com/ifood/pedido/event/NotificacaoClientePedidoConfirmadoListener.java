package br.com.ifood.pedido.event;

import br.com.ifood.core.email.EnvioEmail;
import br.com.ifood.pedido.domain.Pedido;
import br.com.ifood.pedido.domain.PedidoConfirmadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
    @Autowired
    private EnvioEmail envioEmail;

    @TransactionalEventListener
    public void aoConfirmarPedido (PedidoConfirmadoEvent event){
        Pedido pedido = event.getPedido();
        var mensagem = EnvioEmail.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();
        envioEmail.enviar(mensagem);
    }
}
