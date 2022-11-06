package demo.app.services;

import demo.app.dtos.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements IEmailService{
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMail(EmailDetails details) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.recipient());
            mailMessage.setSubject(details.subject());
            mailMessage.setText(details.msgBody());

            javaMailSender.send(mailMessage);
            log.info("Email was successfully send to {}", details.recipient());
        } catch (Exception e){
            log.info("Error {} occurred during send verification email to {}",
                    e.getMessage(),
                    details.recipient());
        }
    }
}
