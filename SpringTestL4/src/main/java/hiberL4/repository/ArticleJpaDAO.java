package hiberL4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hiberL4.domain.Article;

import java.util.List;

//@Repository
public interface ArticleJpaDAO extends JpaRepository<Article, Long> {
    List<Article> findAllByTitleLike(String title);
    List<Article> findAllByIdBetween(Long startId, Long endId);
}
