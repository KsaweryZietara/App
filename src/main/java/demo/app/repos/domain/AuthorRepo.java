package demo.app.repos.domain;

import demo.app.models.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findByName(String name);
}