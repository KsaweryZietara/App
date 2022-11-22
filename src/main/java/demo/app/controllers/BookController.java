package demo.app.controllers;

import demo.app.dtos.domain.*;
import demo.app.models.auth.User;
import demo.app.models.domain.*;
import demo.app.security.SecurityConstants;
import demo.app.services.BookService;
import demo.app.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/book/")
public class BookController {
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BookController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("author")
    public ResponseEntity<String> createAuthor(@Valid @RequestBody CreateAuthorDto authorDto,
                                               @RequestHeader (name="Authorization") String token){
        User user = getUserFromJWT(token);
        Author author = bookService.createAuthor(authorDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/author/").toUriString());
        return ResponseEntity.created(uri).body("Author has been created");
    }

    @PostMapping()
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateBookDto bookDto,
                                             @RequestHeader (name="Authorization") String token){
        User user = getUserFromJWT(token);
        Book book = bookService.createBook(bookDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/").toUriString());
        return ResponseEntity.created(uri).body("Book has been created");
    }

    @PostMapping("category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CreateCategoryDto categoryDto,
                                                 @RequestHeader (name = "Authorization") String token){
        Category category = bookService.createCategory(categoryDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/category/").toUriString());
        return ResponseEntity.created(uri).body("Category has been created");
    }

    @PostMapping("publisher")
    public ResponseEntity<String> createPublisher(@Valid @RequestBody CreatePublisherDto publisherDto,
                                                  @RequestHeader (name = "Authorization") String token){
        User user = getUserFromJWT(token);
        Publisher publisher = bookService.createPublisher(publisherDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/publisher/").toUriString());
        return ResponseEntity.created(uri).body("Publisher has been created");
    }

    @PostMapping("rating")
    public ResponseEntity<String> createRating(@Valid @RequestBody CreateRatingDto ratingDto,
                                               @RequestHeader (name = "Authorization") String token){
        User user = getUserFromJWT(token);
        Rating rating = bookService.createRating(ratingDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/rating/").toUriString());
        return ResponseEntity.created(uri).body("Rating has been created");
    }

    @PostMapping("review")
    public ResponseEntity<String> createReview(@Valid @RequestBody CreateReviewDto reviewDto,
                                               @RequestHeader (name = "Authorization") String token){
        User user = getUserFromJWT(token);
        Review review = bookService.createReview(reviewDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/review/").toUriString());
        return ResponseEntity.created(uri).body("Review has been created");
    }

    @PostMapping("series")
    public ResponseEntity<String> createSeries(@Valid @RequestBody CreateSeriesDto seriesDto,
                                               @RequestHeader (name = "Authorization") String token){
        User user = getUserFromJWT(token);
        Series series = bookService.createSeries(seriesDto, user);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/book/series/").toUriString());
        return ResponseEntity.created(uri).body("Series has been created");
    }

    private User getUserFromJWT(String token){
        token = token.substring(7, token.length());
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return userService.getUser(claims.getSubject());
    }
}
