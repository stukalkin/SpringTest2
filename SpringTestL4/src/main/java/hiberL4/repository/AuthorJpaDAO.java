package hiberL4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import hiberL4.domain.Author;

public interface AuthorJpaDAO extends JpaRepository<Author, Long> {
}
