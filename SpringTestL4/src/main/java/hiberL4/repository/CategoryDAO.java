package hiberL4.repository;

import hiberL4.domain.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category author);
    void update(Category author);
    void delete(Category author);
}
