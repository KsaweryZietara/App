package demo.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DomainExceptionsAdvice {
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String BookNotFoundHandler(BookNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String AuthorNotFoundHandler(AuthorNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CategoryNotFoundHandler(CategoryNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PublisherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String PublisherNotFoundHandler(PublisherNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SeriesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String SeriesNotFoundHandler(SeriesNotFoundException ex){
        return ex.getMessage();
    }
}
