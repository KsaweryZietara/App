package demo.app.models.domain;

import demo.app.models.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private User addedBy;

    @NotNull
    private Date creationDate;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String title;

    @ManyToOne
    @NotNull
    private Author author;

    @ManyToOne
    @NotNull
    private Category category;

    @NotNull
    @Size(min = 5, max = 200)
    private String description;

    @ManyToOne
    @NotNull
    private Publisher publisher;

    @NotNull
    private Date publicationDate;

    @Min(1)
    @Max(5000)
    private int numberOfPages;

    @ManyToOne
    private Series series;

    @Min(1)
    @Max(100)
    private int volume;

    @NotNull
    @Size(min = 3, max = 30)
    private String language;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    public Book() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        creationDate = new Date(cal.getTime().getTime());
    }
}
