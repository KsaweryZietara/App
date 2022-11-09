package demo.app.services;

import demo.app.dtos.EmailDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void EmailService_SendSimpleMail_ReturnsNothing(){
        EmailDetails emailDetails = new EmailDetails("testRecipient",
                "testSubject",
                "testMsgBody");

        assertAll(() -> emailService.sendSimpleMail(emailDetails));
    }
}
