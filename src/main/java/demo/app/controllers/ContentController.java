package demo.app.controllers;

import demo.app.dtos.domain.*;
import demo.app.models.auth.User;
import demo.app.models.domain.*;
import demo.app.security.JWTGenerator;
import demo.app.services.BookService;
import demo.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/content/")
public class ContentController {
    private final UserService userService;
    private final BookService bookService;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public ContentController(UserService userService, BookService bookService, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.bookService = bookService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("author")
    public ResponseEntity<String> createAuthor(@Valid @RequestBody CreateAuthorDto authorDto,
                                               @RequestHeader (name="Authorization") String token){
        String username = jwtGenerator.getUsername(token);
        User user = userService.getUser(username);
        Author author = bookService.createAuthor(authorDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/author").toUriString());
        return ResponseEntity.created(uri).body("Author has been created");
    }

    @PostMapping("book")
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateBookDto bookDto,
                                             @RequestHeader (name="Authorization") String token){
        String username = jwtGenerator.getUsername(token);
        User user = userService.getUser(username);
        Book book = bookService.createBook(bookDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book").toUriString());
        return ResponseEntity.created(uri).body("Book has been created");
    }

    @PostMapping("publisher")
    public ResponseEntity<String> createPublisher(@Valid @RequestBody CreatePublisherDto publisherDto,
                                                  @RequestHeader (name = "Authorization") String token){
        String username = jwtGenerator.getUsername(token);
        User user = userService.getUser(username);
        Publisher publisher = bookService.createPublisher(publisherDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/publisher").toUriString());
        return ResponseEntity.created(uri).body("Publisher has been created");
    }

    @PostMapping("rating")
    public ResponseEntity<String> createRating(@Valid @RequestBody CreateRatingDto ratingDto,
                                               @RequestHeader (name = "Authorization") String token){
        String username = jwtGenerator.getUsername(token);
        User user = userService.getUser(username);
        Rating rating = bookService.createRating(ratingDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/rating").toUriString());
        return ResponseEntity.created(uri).body("Rating has been created");
    }

    @PostMapping("review")
    public ResponseEntity<String> createReview(@Valid @RequestBody CreateReviewDto reviewDto,
                                               @RequestHeader (name = "Authorization") String token){
        String username = jwtGenerator.getUsername(token);
        User user = userService.getUser(username);
        Review review = bookService.createReview(reviewDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/review").toUriString());
        return ResponseEntity.created(uri).body("Review has been created");
    }

    @PostMapping("series")
    public ResponseEntity<String> createSeries(@Valid @RequestBody CreateSeriesDto seriesDto,
                                               @RequestHeader (name = "Authorization") String token){
        String username = jwtGenerator.getUsername(token);
        User user = userService.getUser(username);
        Series series = bookService.createSeries(seriesDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/series").toUriString());
        return ResponseEntity.created(uri).body("Series has been created");
    }
}
