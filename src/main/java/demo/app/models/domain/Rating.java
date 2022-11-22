package demo.app.models.domain;

import demo.app.models.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ratings")
public class Rating {
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

    @Min(1)
    @Max(10)
    private int rating;

    public Rating(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        creationDate = new Date(cal.getTime().getTime());
    }
}
