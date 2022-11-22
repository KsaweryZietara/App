package demo.app.repos.domain;

import demo.app.models.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(String name);
}