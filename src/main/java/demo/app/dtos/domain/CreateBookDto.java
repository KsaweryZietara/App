package demo.app.dtos.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public record CreateBookDto(
        @NotNull
        @Size(min = 3, max = 50)
        String title,

        @NotNull
        @Size(min = 3, max = 80)
        String authorName,

        @NotNull
        @Size(min = 5, max = 35)
        String categoryName,

        @NotNull
        @Size(min = 5, max = 200)
        String description,

        @NotNull
        @Size(min = 5, max = 50)
        String publisherName,

        @NotNull
        Date publicationDate,

        @Min(1)
        @Max(5000)
        int numberOfPages,

        @Size(min = 5, max = 50)
        String seriesName,

        @Min(1)
        @Max(100)
        int volume,

        @NotNull
        @Size(min = 3, max = 30)
        String language
) { }