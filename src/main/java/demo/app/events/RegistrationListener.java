package demo.app.events;

import demo.app.dtos.auth.EmailDetails;
import demo.app.models.auth.User;
import demo.app.services.EmailService;
import demo.app.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public RegistrationListener(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        log.info("Activation link: {}", confirmationUrl);

        String subject = "Verify your account";
        String msgBody = "Activation link: " + confirmationUrl;
        EmailDetails emailDetails = new EmailDetails(user.getEmail(), subject, msgBody);
        emailService.sendSimpleMail(emailDetails);
    }
}
