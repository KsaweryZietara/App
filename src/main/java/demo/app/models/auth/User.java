package demo.app.models.auth;

import demo.app.models.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 20)
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    private boolean enable;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "addedBy")
    private List<Book> createdBooks;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Series> series;

    @OneToMany(mappedBy = "user")
    private List<Publisher> publishers;

    @OneToMany(mappedBy = "user")
    private List<Author> authors;

    public User(){
        this.enable = false;
    }
}
