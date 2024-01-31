package br.com.ifood.core.email;

import br.com.ifood.handler.APIException;
import br.com.ifood.handler.ProblemType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
@Log4j2
public class SmtpEnvioEmail implements EnvioEmail {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailProperties emailProperties;
    @Autowired
    private Configuration freemakerConfig;

    @Override
    public void enviar(Mensagem mensagem) {
        log.info("[inicia] SmtpEnvioEmail - enviar");
        try {
            String corpo = processarTemplate(mensagem);

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setSubject(mensagem.getAssunto());
            helper.setFrom(emailProperties.getRemetente());
            helper.setText(corpo, true);
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            mailSender.send(mimeMessage);

            log.info("[finaliza] SmtpEnvioEmail - enviar");
        } catch (Exception e) {
            throw APIException.build(HttpStatus.INTERNAL_SERVER_ERROR,"Não foi possivel enviar e-mail",
                    e, ProblemType.ERRO_DE_SISTEMA);
        }
    }

    private String processarTemplate(Mensagem mensagem) throws IOException, TemplateException {
        log.info("[inicia] SmtpEnvioEmail - processarTemplate");
        Template template = freemakerConfig.getTemplate(mensagem.getCorpo());
        log.info("[finaliza] SmtpEnvioEmail - processarTemplate");
        return FreeMarkerTemplateUtils.processTemplateIntoString(
                template,mensagem.getVariaveis());
    }

}
