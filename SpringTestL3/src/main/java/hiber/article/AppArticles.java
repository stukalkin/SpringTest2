package hiber.article;


import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AppArticles {

    public static void main(String[] args) {

        EntityManagerFactory entityFactory = new Configuration()
                .configure("article-hiber.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityFactory.createEntityManager();

        Category category = new Category();
        category.setName("Joke");

        createEntity(em, category);

        Author authorIvan = new Author();
        authorIvan.setFirstname("Ivan");
        authorIvan.setLastname("Ivanov");
        authorIvan.setEmail("some@email");

        createEntity(em, authorIvan);

        Article articleIvan = new Article();
        articleIvan.setTitle("Ivan article");
        articleIvan.setContent("I am a god");
        articleIvan.setCategory(readEntity(em, Category.class, 1L));
        articleIvan.setAuthor(readEntity(em, Author.class, 2L));

        createEntity(em, articleIvan);

        /*Article articleGoga = new Article();
        articleGoga.setTitle("Goga novel");
        articleGoga.setContent("I have visited all hotels on Turkey");
        articleGoga.setAuthor(new Author("Goga", "Grogorian", "goga@mail"));
        articleGoga.setCategory(new Category("novel"));
        articleGoga.setAuthor(saveEntity(em, new Author("Goga", "Grogorian", "goga@mail")));
        articleGoga.setCategory(saveEntity(em, new Category("novel")));
        createEntity(em, articleGoga);*/


    }

    private static <T> void createEntity(EntityManager em, T entity){

        System.out.println("Creating entity");
        //open transaction
        em.getTransaction().begin();
        //put person into persist area of Hibernate
        em.persist(entity);
        //commit/close transaction
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    private static <T> T readEntity(EntityManager em, Class<T> clazz, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        T person = em.find(clazz, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + person);
        return person;
    }

    private static <T> T saveEntity(EntityManager em, T entity){
        System.out.println("Start saving");

        em.getTransaction().begin();
        T savedEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + savedEntity);

        return savedEntity;
    }
}
