package demo.app.repos.domain;

import demo.app.models.domain.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepo extends PagingAndSortingRepository<Book, Long> {
    Book findByTitle(String title);
}