package hiberL4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import hiberL4.domain.Category;

public interface CategoryJpaDAO extends JpaRepository<Category, Long> {
}
