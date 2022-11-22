package demo.app.services;

import demo.app.dtos.domain.*;
import demo.app.models.auth.User;
import demo.app.models.domain.*;

public interface BookService {
    Author createAuthor(CreateAuthorDto authorDto, User user);
    Book createBook(CreateBookDto bookDto, User user);
    Category createCategory(CreateCategoryDto categoryDto);
    Publisher createPublisher(CreatePublisherDto publisherDto, User user);
    Rating createRating(CreateRatingDto ratingDto, User user);
    Review createReview(CreateReviewDto reviewDto, User user);
    Series createSeries(CreateSeriesDto seriesDto, User user);
}
