package demo.app.repos.domain;

import demo.app.models.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating, Long> {
}