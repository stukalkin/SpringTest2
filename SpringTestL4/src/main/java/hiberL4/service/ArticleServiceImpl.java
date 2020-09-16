package hiberL4.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hiberL4.domain.Article;
import hiberL4.domain.Author;
import hiberL4.domain.Category;
import hiberL4.repository.ArticleJpaDAO;
import hiberL4.repository.AuthorJpaDAO;
import hiberL4.repository.CategoryJpaDAO;


@Service
public class ArticleServiceImpl {

    private final ArticleJpaDAO articleJpaDAO;
    private final AuthorJpaDAO authorJpaDAO;
    private final CategoryJpaDAO categoryJpaDAO;

    public ArticleServiceImpl(ArticleJpaDAO articleJpaDAO, AuthorJpaDAO authorJpaDAO, CategoryJpaDAO categoryJpaDAO) {
        this.articleJpaDAO = articleJpaDAO;
        this.authorJpaDAO = authorJpaDAO;
        this.categoryJpaDAO = categoryJpaDAO;
    }

    @Transactional
    public void saveAndSet(Article article, Author author, Category category){
        Author savedAuthor = authorJpaDAO.save(author);
        Category savedCategory = categoryJpaDAO.save(category);

        article.setAuthor(savedAuthor);
        article.setCategory(savedCategory);

        articleJpaDAO.save(article);
    }

    @Transactional(readOnly = true)
    public Article findById(Long id){
        return articleJpaDAO.findById(id).orElse(null);
    }

}
