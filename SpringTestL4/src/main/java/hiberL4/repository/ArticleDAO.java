package hiberL4.repository;

import hiberL4.domain.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> findAll();
    Article findById(Long id);
    void save(Article author);
    void update(Article author);
    void delete(Article author);
}
