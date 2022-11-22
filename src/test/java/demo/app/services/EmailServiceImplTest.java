package demo.app.services;

import demo.app.dtos.auth.EmailDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailServiceImpl;

    @Test
    public void EmailService_SendSimpleMail_ReturnsNothing(){
        EmailDetails emailDetails = new EmailDetails("testRecipient",
                "testSubject",
                "testMsgBody");

        assertAll(() -> emailServiceImpl.sendSimpleMail(emailDetails));
    }
}
