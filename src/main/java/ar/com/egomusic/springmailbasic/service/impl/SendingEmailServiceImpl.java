package ar.com.egomusic.springmailbasic.service.impl;

import ar.com.egomusic.springmailbasic.model.MailModelDTO;
import ar.com.egomusic.springmailbasic.service.SendingEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author jose.roldan
 */
@Service
@Log4j2
public class SendingEmailServiceImpl implements SendingEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Override
    public void sendEmail(MailModelDTO mailModelDTO) throws MessagingException, IOException, TemplateException {

        Map model = new HashMap();
        model.put("name", mailModelDTO.getName());
        model.put("location", "Brasilia - Brazil");
        model.put("signature", "https://bradesco.seguros.br");
        model.put("content", mailModelDTO.getContent());

        /**
         * Add below line if you need to create a token to verification emails
         * and uncomment line:32 in "email.ftl"
         * model.put("token",UUID.randomUUID().toString());
         */
        mailModelDTO.setModel(model);

        log.info("Sending Email to: " + mailModelDTO.getTo());

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/test.png"));

        Template template = emailConfig.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModelDTO.getModel());

        mimeMessageHelper.setTo(mailModelDTO.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModelDTO.getSubject());
        mimeMessageHelper.setFrom(mailModelDTO.getFrom());

        emailSender.send(message);

    }
}
