package demo.app.dtos.auth;

import javax.validation.constraints.NotBlank;

public record CreateRoleDto (
        @NotBlank
        String name
) { }
