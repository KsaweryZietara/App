package demo.app.exceptions;

public class UserNotEnabledException extends RuntimeException{
    public UserNotEnabledException(String username) {
        super("User " + username + " is not enabled");
    }
}
