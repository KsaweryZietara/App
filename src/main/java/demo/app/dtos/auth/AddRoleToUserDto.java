package demo.app.dtos.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record AddRoleToUserDto(
        @NotNull
        @Size(min = 4, max = 20)
        String username,

        @NotBlank
        String roleName
) { }
