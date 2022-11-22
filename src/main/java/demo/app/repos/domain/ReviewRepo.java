package demo.app.repos.domain;

import demo.app.models.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}