package ar.com.egomusic.springmailbasic.controller;

import ar.com.egomusic.springmailbasic.model.MailModelDTO;
import ar.com.egomusic.springmailbasic.service.SendingEmailService;
import freemarker.template.TemplateException;
import java.io.IOException;
import javax.mail.MessagingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author jose.roldan
 */
@Log4j2
@RestController
@RequestMapping("/v1/sendmail")
public class SendEmailController {

    @Autowired
    private SendingEmailService sendingEmailService;

    @PostMapping
    public ResponseEntity<?> restPostLoanRequest(@RequestBody MailModelDTO mailModelDTO) throws MessagingException, IOException, TemplateException {

        sendingEmailService.sendEmail(mailModelDTO);
        return ResponseEntity.ok().body(mailModelDTO.toString());
    }
    
//        @PostMapping
//    public ResponseEntity<?> restPostLoanRequest(@RequestBody MailModelDTO mailModelDTO) {
//
//        try {
//            sendingEmailService.sendEmail(mailModelDTO);
//            return ResponseEntity.ok().body(mailModelDTO.toString());
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return ResponseEntity.ok().body(e.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.ok().body(e.getMessage());
//        } catch (TemplateException e) {
//            e.printStackTrace();
//            return ResponseEntity.ok().body(e.getMessage());
//        }
//    }
}
