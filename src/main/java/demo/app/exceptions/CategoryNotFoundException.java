package demo.app.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String name) {
        super("Could not find the category " + name);
    }
}
