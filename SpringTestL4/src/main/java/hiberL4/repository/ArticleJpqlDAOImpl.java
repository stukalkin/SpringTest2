package hiberL4.repository;


import hiberL4.domain.Article;

import javax.persistence.EntityManager;
import java.util.List;

public class ArticleJpqlDAOImpl implements ArticleDAO {

    private final EntityManager em;

    public ArticleJpqlDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("SELECT a from Article a", Article.class).getResultList();
    }

    @Override
    public Article findById(Long id) {
        return em.createQuery("SELECT a from Article a where a.id = :id", Article.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(Article author) {
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
    }

    @Override
    public void update(Article author) {
        em.getTransaction().begin();
        em.merge(author);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Article author) {
        em.getTransaction().begin();
        em.remove(author);
        em.getTransaction().commit();
    }
}
