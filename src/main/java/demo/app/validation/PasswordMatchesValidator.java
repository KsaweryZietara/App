package demo.app.validation;

import demo.app.dtos.auth.RegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RegisterDto user = (RegisterDto) o;
        return user.password().equals(user.matchingPassword());
    }
}
