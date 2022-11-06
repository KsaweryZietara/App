package demo.app.services;

import demo.app.dtos.EmailDetails;

public interface IEmailService {
    void sendSimpleMail(EmailDetails details);
}
