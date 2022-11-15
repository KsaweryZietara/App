package demo.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    private Date creationDate;

    private String title;

    @ManyToOne
    @NotNull
    private Author author;

    @ManyToOne
    @NotNull
    private Category category;

    private String description;

    @ManyToOne
    @NotNull
    private Publisher publisher;

    private Date publicationDate;

    private int numberOfPages;

    @ManyToOne
    @NotNull
    private Series series;

    private int volume;

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
