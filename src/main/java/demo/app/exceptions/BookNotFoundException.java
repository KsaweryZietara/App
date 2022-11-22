package demo.app.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String title) {
        super("Could not find the book " + title);
    }
}
