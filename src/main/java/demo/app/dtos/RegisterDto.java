package demo.app.dtos;

import demo.app.validation.PasswordMatches;
import demo.app.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
public record RegisterDto(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 4, max = 20, message = "Invalid size of username")
        String username,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 30, message = "Invalid size of password")
        String password,
        @NotBlank(message = "Matching password is mandatory")
        @Size(min = 8, max = 30, message = "Invalid size of matching password")
        String matchingPassword,
        @NotBlank(message = "Email is mandatory")
        @ValidEmail
        String email
) { }
