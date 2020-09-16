package hiberL4;

import hiberL4.domain.Article;
import hiberL4.domain.Author;
import hiberL4.domain.Category;

import javax.persistence.EntityManager;
import java.util.Date;

public class InitData {

    private static long ind = 99;

    private static Category CATEGORY_1 = new Category();
    private static Category CATEGORY_2 = new Category();
    private static Category CATEGORY_3 = new Category();

    private static Author AUTHOR_1 = new Author();
    private static Author AUTHOR_2 = new Author();
    private static Author AUTHOR_3 = new Author();

    private static Article ARTICLE_1 = new Article();
    private static Article ARTICLE_2 = new Article();
    private static Article ARTICLE_3 = new Article();
    private static Article ARTICLE_4 = new Article();

    static {
        //Category
        {
            CATEGORY_1.setName("Joke");

            CATEGORY_2.setName("News");

            CATEGORY_3.setName("Sport");
        }
        //Author
        {
            AUTHOR_1.setFirstname("Ivan");
            AUTHOR_1.setLastname("Ivanov");
            AUTHOR_1.setEmail("ivanov@email");

            AUTHOR_2.setFirstname("Petr");
            AUTHOR_2.setLastname("Petrov");
            AUTHOR_2.setEmail("petrov@email");

            AUTHOR_3.setFirstname("Roman");
            AUTHOR_3.setLastname("Romanov");
            AUTHOR_3.setEmail("romanov@email");
        }
    }

    public static void initData(EntityManager em){

        initCategory(em);
        initAuthor(em);
        initArticles(em);
    }

    public static void initArticlesData(){

        ARTICLE_1.setTitle("Article " + ++ind);
        ARTICLE_1.setContent("Article's content " + ind);
//        ARTICLE_1.setCategory(CATEGORY_1);
//        ARTICLE_1.setCategoryLazy(CATEGORY_2);
//        ARTICLE_1.setAuthor(AUTHOR_1);
        ARTICLE_1.setDate(new Date(2020, 1, 1));

        ARTICLE_2.setTitle("Article " + ++ind);
        ARTICLE_2.setContent("Article's content " + ind);
//        ARTICLE_2.setCategory(CATEGORY_1);
//        ARTICLE_2.setAuthor(AUTHOR_2);
        ARTICLE_2.setDate(new Date(2020, 1, 10));

        ARTICLE_3.setTitle("Article " + ++ind);
        ARTICLE_3.setContent("Article's content " + ind);
//        ARTICLE_3.setCategory(CATEGORY_2);
//        ARTICLE_3.setAuthor(AUTHOR_2);
        ARTICLE_3.setDate(new Date(2020, 2, 10));

        ARTICLE_4.setTitle("Article " + ++ind);
        ARTICLE_4.setContent("Article's content " + ind);
//        ARTICLE_4.setCategory(CATEGORY_1);
//        ARTICLE_4.setAuthor(AUTHOR_1);
        ARTICLE_4.setDate(new Date(2020, 2, 10));
    }

    private static void initArticles(EntityManager em) {

        initArticlesData();

        em.getTransaction().begin();

        System.out.println("Articles initialized");
        ARTICLE_1 = em.merge(ARTICLE_1);
        ARTICLE_2 = em.merge(ARTICLE_2);
        ARTICLE_3 = em.merge(ARTICLE_3);
        ARTICLE_4 = em.merge(ARTICLE_4);

        em.getTransaction().commit();
    }

    private static void initAuthor(EntityManager em) {
        em.getTransaction().begin();
        System.out.println("Author initialized");
        AUTHOR_1 = em.merge(AUTHOR_1);
        AUTHOR_2 = em.merge(AUTHOR_2);
        AUTHOR_3 = em.merge(AUTHOR_3);
        em.getTransaction().commit();
    }


    private static void initCategory(EntityManager em) {
        em.getTransaction().begin();

        System.out.println("Category initialized");
        CATEGORY_1 = em.merge(CATEGORY_1);
        CATEGORY_2 = em.merge(CATEGORY_2);
        CATEGORY_3 = em.merge(CATEGORY_3);

        em.getTransaction().commit();
    }

    public static Category getCategory1() {
        return CATEGORY_1;
    }

    public static Category getCategory2() {
        return CATEGORY_2;
    }

    public static Category getCategory3() {
        return CATEGORY_3;
    }

    public static Author getAuthor1() {
        return AUTHOR_1;
    }

    public static Author getAuthor2() {
        return AUTHOR_2;
    }

    public static Author getAuthor3() {
        return AUTHOR_3;
    }

    public static Article getArticle1() {
        return ARTICLE_1;
    }

    public static Article getArticle2() {
        return ARTICLE_2;
    }

    public static Article getArticle3() {
        return ARTICLE_3;
    }

    public static Article getArticle4() {
        return ARTICLE_4;
    }

}
