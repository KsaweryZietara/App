package demo.app.services;

import demo.app.dtos.domain.*;
import demo.app.exceptions.*;
import demo.app.models.auth.User;
import demo.app.models.domain.*;
import demo.app.repos.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final PublisherRepo publisherRepo;
    private final RatingRepo ratingRepo;
    private final ReviewRepo reviewRepo;
    private final SeriesRepo seriesRepo;

    @Autowired
    public BookServiceImpl(AuthorRepo authorRepo,
                           BookRepo bookRepo,
                           CategoryRepo categoryRepo,
                           PublisherRepo publisherRepo,
                           RatingRepo ratingRepo,
                           ReviewRepo reviewRepo,
                           SeriesRepo seriesRepo) {

        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.publisherRepo = publisherRepo;
        this.ratingRepo = ratingRepo;
        this.reviewRepo = reviewRepo;
        this.seriesRepo = seriesRepo;
    }

    @Override
    public Author createAuthor(CreateAuthorDto authorDto, User user) {
        Author author = new Author();
        author.setUser(user);
        author.setName(authorDto.name());
        author.setBirthplace(authorDto.birthplace());
        author.setBirthdate(authorDto.birthdate());
        author.setDeathDate(authorDto.deathDate());

        log.info("Saving new author {} added by {} to the database", author.getName(), user.getUsername());
        return authorRepo.save(author);
    }

    @Override
    public Book createBook(CreateBookDto bookDto, User user) {
        log.info("Fetching author {}", bookDto.authorName());
        Author author = authorRepo.findByName(bookDto.authorName());
        if(author == null) throw new AuthorNotFoundException(bookDto.authorName());

        log.info("Fetching category {}", bookDto.categoryName());
        Category category = categoryRepo.findByName(bookDto.categoryName());
        if(category == null) throw new CategoryNotFoundException(bookDto.categoryName());

        log.info("Fetching publisher {}", bookDto.publisherName());
        Publisher publisher = publisherRepo.findByName(bookDto.publisherName());
        if(category == null) throw new PublisherNotFoundException(bookDto.publisherName());

        log.info("Fetching series {}", bookDto.seriesName());
        Series series = seriesRepo.findByName(bookDto.seriesName());
        if(series == null) throw new SeriesNotFoundException(bookDto.seriesName());

        Book book = new Book();
        book.setAddedBy(user);
        book.setTitle(bookDto.title());
        book.setAuthor(author);
        book.setCategory(category);
        book.setDescription(bookDto.description());
        book.setPublisher(publisher);
        book.setPublicationDate(bookDto.publicationDate());
        book.setNumberOfPages(bookDto.numberOfPages());
        book.setSeries(series);
        book.setVolume(bookDto.volume());
        book.setLanguage(bookDto.language());

        log.info("Saving new book {} added by {} to the database", book.getTitle(), user.getUsername());
        return bookRepo.save(book);
    }

    @Override
    public Category createCategory(CreateCategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.name());

        log.info("Saving new category {} to the database", category.getName());
        return categoryRepo.save(category);
    }

    @Override
    public Publisher createPublisher(CreatePublisherDto publisherDto, User user) {
        Publisher publisher = new Publisher();
        publisher.setUser(user);
        publisher.setName(publisherDto.name());

        log.info("Saving new publisher {} added by {} to the database", publisher.getName(), user.getUsername());
        return publisherRepo.save(publisher);
    }

    @Override
    public Rating createRating(CreateRatingDto ratingDto, User user) {
        log.info("Fetching book {}", ratingDto.bookTitle());
        Book book = bookRepo.findByTitle(ratingDto.bookTitle());
        if(book == null) throw new BookNotFoundException(ratingDto.bookTitle());

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(ratingDto.rating());

        log.info("Saving new rating added by {} to book {}", user.getUsername(), ratingDto.bookTitle());
        return ratingRepo.save(rating);
    }

    @Override
    public Review createReview(CreateReviewDto reviewDto, User user) {
        log.info("Fetching book {}", reviewDto.bookTitle());
        Book book = bookRepo.findByTitle(reviewDto.bookTitle());
        if(book == null) throw new BookNotFoundException(reviewDto.bookTitle());

        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setReview(reviewDto.review());

        log.info("Saving new review added by {} to book {}", user.getUsername(), reviewDto.bookTitle());
        return reviewRepo.save(review);
    }

    @Override
    public Series createSeries(CreateSeriesDto seriesDto, User user) {
        Series series = new Series();
        series.setUser(user);
        series.setName(seriesDto.name());

        log.info("Saving new series {} added by {} to the database", series.getName(), user.getUsername());
        return seriesRepo.save(series);
    }
}
