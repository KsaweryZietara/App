package demo.app.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 4, max = 20, message = "Invalid size of username")
        String username,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 30, message = "Invalid size of password")
        String password
) { }
