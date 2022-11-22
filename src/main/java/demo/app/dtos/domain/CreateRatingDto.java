package demo.app.dtos.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public record CreateRatingDto(
        @NotNull
        @Size(min = 3, max = 50)
        String bookTitle,

        @Min(1)
        @Max(10)
        int rating
) { }