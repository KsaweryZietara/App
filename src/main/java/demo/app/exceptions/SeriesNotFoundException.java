package demo.app.exceptions;

public class SeriesNotFoundException extends RuntimeException {
    public SeriesNotFoundException(String name) {
        super("Could not find the series " + name);
    }
}
