package demo.app.models.domain;

import demo.app.models.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private Date creationDate;

    @ManyToOne
    @NotNull
    private Book book;

    @NotNull
    @Size(min = 1, max = 200)
    private String review;

    public Review(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        creationDate = new Date(cal.getTime().getTime());
    }
}
