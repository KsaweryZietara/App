package demo.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserNotEnabledAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotEnabledException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String UserNotEnabledHandler(UserNotEnabledException ex){
        return ex.getMessage();
    }
}
