package ar.com.egomusic.springmailbasic04.service;

import ar.com.egomusic.springmailbasic04.model.User;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.Base64;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    ResourceLoader resourceLoader;

    public void sendEmailUsingVelocityTemplate(final String subject, final String message,
            final String fromEmailAddress, final String toEmailAddress, User user) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(toEmailAddress);
                message.setFrom(new InternetAddress(fromEmailAddress));

//                File resource = new ClassPathResource("classpath:img/test.png").getFile();
//                Resource resource = (Resource) resourceLoader.getResource("classpath:img/test.png");
//                InputStream input = resourceLoader.getResource("classpath:img/test.png").getInputStream();

                File file = resourceLoader.getResource("classpath:img/teste04.png").getFile();
                byte[] bytes = Files.readAllBytes(file.toPath());

                VelocityContext velocityContext = new VelocityContext();
                velocityContext.put("user", user);
                velocityContext.put("image", Base64.getEncoder().encodeToString(bytes));
                velocityContext.put("cid", file);

                StringWriter stringWriter = new StringWriter();

                velocityEngine.mergeTemplate("velocity/email-template.vm", "UTF-8", velocityContext, stringWriter);

                message.setSubject(subject);
                message.setText(stringWriter.toString(), true);
            }
        };

        try {
            mailSender.send(preparator);

            System.out.println("Email sending complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
