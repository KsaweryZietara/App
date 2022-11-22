package demo.app.services;

import demo.app.dtos.auth.EmailDetails;

public interface EmailService {
    void sendSimpleMail(EmailDetails details);
}
