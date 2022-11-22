package demo.app.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String name) {
        super("Could not find the author " + name);
    }
}
