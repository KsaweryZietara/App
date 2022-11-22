package demo.app.dtos.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record CreatePublisherDto(
        @NotNull
        @Size(min = 5, max = 50)
        String name
) { }