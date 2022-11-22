package demo.app.dtos.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public record CreateCategoryDto(
        @NotNull
        @Size(min = 5, max = 35)
        String name
) { }