package ar.com.egomusic.springmailbasic04.controller;

import ar.com.egomusic.springmailbasic04.model.User;
import ar.com.egomusic.springmailbasic04.service.EmailSender;
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
@RequestMapping("/v4/sendmail")
public class SendEmailController {

    @Autowired
    private EmailSender emailSender;

    @PostMapping
    public ResponseEntity<?> restPostLoanRequest(@RequestBody User user) throws MessagingException, Exception {

        emailSender.sendEmailUsingVelocityTemplate("Test Spring Email using Velocity Template Library",
				"Email Message using MimeMessagePreparator and Velocity Template Library", "ocomur@gmail.com",
				"jose.roldan@actdigital.com", new User(user.getUserName(), user.getEmailAddress()));
        return ResponseEntity.ok().body(user.toString());
        
    }


}
