package demo.app.repos.domain;

import demo.app.models.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Publisher findByName(String name);
}