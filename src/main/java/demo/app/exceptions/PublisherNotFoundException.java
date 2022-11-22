package demo.app.exceptions;

public class PublisherNotFoundException extends RuntimeException{
    public PublisherNotFoundException(String name) {
        super("Could not find the publisher " + name);
    }
}
