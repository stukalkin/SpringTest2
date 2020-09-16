package hiberL4.repository;

import hiberL4.domain.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> findAll();
    Author findById(Long id);
    void save(Author author);
    void update(Author author);
    void delete(Author author);
}
