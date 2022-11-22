package demo.app.dtos.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public record CreateReviewDto(
        @NotNull
        @Size(min = 3, max = 50)
        String bookTitle,

        @NotNull
        @Size(min = 1, max = 200)
        String review
) { }