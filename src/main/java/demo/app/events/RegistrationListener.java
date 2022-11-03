package demo.app.events;

import demo.app.models.User;
import demo.app.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final IUserService userService;

    @Autowired
    public RegistrationListener(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        //TODO send email
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        log.info("Activation link: {}", confirmationUrl);
    }
}
