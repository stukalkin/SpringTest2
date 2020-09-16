package hiberL4;


import hiberL4.config.SpringDataConfig;
import hiberL4.repository.ArticleJpaDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import hiberL4.domain.Article;
import hiberL4.domain.Author;
import hiberL4.domain.Category;
import java.util.Arrays;
import hiberL4.service.ArticleServiceImpl;

public class AppSpringDataJpa {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);

        ArticleServiceImpl articleService = context.getBean(ArticleServiceImpl.class);

//        InitData.initArticlesData();
//        articleJpaDAO.saveAll(
//                Arrays.asList(InitData.getArticle1(),
//                        InitData.getArticle2(),
//                        InitData.getArticle3(),
//                        InitData.getArticle4())
//        );
        Article article = InitData.getArticle1();
        System.out.println(article);
        Category category = InitData.getCategory1();
        Author author = InitData.getAuthor1();

        articleService.saveAndSet(article, author, category);


//        System.out.println("FIND ALL");
//        articleJpaDAO.findAll().forEach(System.out::println);
//
//        System.out.println("FIND BY ID");
//        articleJpaDAO.findById(2L).ifPresent(System.out::println);
//
//        System.out.println("FIND BY LIKE TITLE");
//        articleJpaDAO.findAllByTitleLike("%02%").forEach(System.out::println);
//
//        System.out.println("FIND BY BETWEEN ID");
//        articleJpaDAO.findAllByIdBetween(1L, 3L).forEach(System.out::println);
        System.out.println(articleService.findById(3L));


    }
}
