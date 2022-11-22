package demo.app.repos.domain;

import demo.app.models.domain.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepo extends JpaRepository<Series, Long> {
    Series findByName(String name);
}