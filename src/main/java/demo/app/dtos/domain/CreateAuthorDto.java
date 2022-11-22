package demo.app.dtos.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public record CreateAuthorDto(
        @NotNull
        @Size(min = 3, max = 80)
        String name,

        @NotNull
        @Size(min = 3, max = 40)
        String birthplace,

        @NotNull
        Date birthdate,

        Date deathDate
) { }