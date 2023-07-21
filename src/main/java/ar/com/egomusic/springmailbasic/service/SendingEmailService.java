package ar.com.egomusic.springmailbasic.service;

import ar.com.egomusic.springmailbasic.model.MailModelDTO;
import freemarker.template.TemplateException;
import java.io.IOException;
import javax.mail.MessagingException;

/**
 *
 * @author jose.roldan
 */
public interface SendingEmailService {

    void sendEmail(MailModelDTO mailModelDTO)throws MessagingException, IOException, TemplateException;
}
