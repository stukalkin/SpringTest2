package hiberL4;

import hiberL4.domain.*;
import hiberL4.repository.ArticleJpqlDAOImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppArticles {

    public static void main(String[] args) {

        SessionFactory entityFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityFactory.createEntityManager();

        InitData.initData(em);

        em.close();

//        EntityManager emNew = entityFactory.createEntityManager();
//        Object singleResult = emNew.createNativeQuery("SELECT max(a.date) from article a").getSingleResult();
//        System.out.println("Max sql Date -> " + singleResult);
        Session session = entityFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);

        Root<Article> root = criteriaQuery.from(Article.class);
//        criteriaQuery.select(root);
        criteriaQuery
                .select(root)
                .where(criteriaBuilder.between(root.get("id"), 7, 9));

        List<Article> resultList = session.createQuery(criteriaQuery).getResultList();
        resultList.forEach(System.out::println);


        session.close();

        entityFactory.close();
    }

    private static void exampleNamedQuery(EntityManager emNew) {

        {
            System.out.println("Query Article.findAll");
            List<Article> list = emNew.createNamedQuery("Article.findAll", Article.class).getResultList();
            list.forEach(System.out::println);
        }

        {
            System.out.println("Query Article.findByLowerId");
            List<Article> list = emNew.createNamedQuery("Article.findByLowerId", Article.class)
                    .setParameter("id", 9L).getResultList();
            list.forEach(System.out::println);
        }

//        emNew.close();
//
//        entityFactory.close();
    }

    private static void exampleJpqlDao(EntityManager emNew) {
        ArticleJpqlDAOImpl jpqlDAO = new ArticleJpqlDAOImpl(emNew);

        System.out.println("Getting All");
        List<Article> all = jpqlDAO.findAll();
        all.forEach(System.out::println);

        System.out.println("Getting by id");
        System.out.println(jpqlDAO.findById(all.get(0).getId()));

//        emNew.close();
//
//        entityFactory.close();
    }

    private static void exampleCascadePersist(EntityManager em) {
        //save holder
        {

            em.getTransaction().begin();

            CategoryHolderCascadePersist holder = new CategoryHolderCascadePersist();
            Category category_persist = new Category("Category persist");
            holder.setTitle("Holder Persist");
            holder.setCategory(category_persist);

            em.persist(holder);

            em.getTransaction().commit();
        }
        //find holder and change category through holder
        {
            em.getTransaction().begin();

            CategoryHolderCascadePersist foundHolder = em.find(CategoryHolderCascadePersist.class, 11L);
            foundHolder.setTitle(foundHolder.getTitle() + " updated");
            Category category = foundHolder.getCategory();
            category.setName(category.getName() + " updated");

            em.merge(foundHolder);

            em.getTransaction().commit();
        }

    }

    private static void exampleCascadeRemove(EntityManager em) {
        //https://stackoverflow.com/questions/18373383/jpa-onetoone-difference-between-cascade-merge-and-persist#:~:text=Persist%20and%20merge%20are%20designed,the%20object%20may%20already%20exist.
        //save holder
        {

            em.getTransaction().begin();

            Category category = em.merge(new Category("Category remove"));

            CategoryHolderCascadeRemove holder = new CategoryHolderCascadeRemove();
            holder.setTitle("Holder remove");
            holder.setCategory(category);

            em.persist(holder);

            em.getTransaction().commit();
        }
        //find holder and change category through holder
        {
            em.getTransaction().begin();

            CategoryHolderCascadeRemove foundHolder = em.find(CategoryHolderCascadeRemove.class, 12L);
            em.remove(foundHolder);

            em.getTransaction().commit();
        }

    }

    private static void exampleCascadeAll(EntityManager em) {
        em.getTransaction().begin();

        CategoryHolderCascadeAll holder = new CategoryHolderCascadeAll();
        holder.setTitle("Holder ALL");
        holder.setCategory(new Category("Category depended"));

        em.persist(holder);

        em.getTransaction().commit();
    }

    private static void examplePersistenceArea(EntityManager em) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Object mutex = new Object();  //создание монитора для синхронизации потоков

        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    Category category1 = em.find(Category.class, InitData.getCategory1().getId());
                    category1.setName("Updated Name");
                    em.merge(category1);

                    System.out.println("Changed name.Waiting notification...");

                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Continue working after waiting");
                }
            }
        };

        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    Category category1 = em.find(Category.class, InitData.getCategory1().getId());
                    System.out.println("Read value: " + category1.getName());
                    mutex.notifyAll();
                }
            }
        };

        em.getTransaction().begin();

        threadPool.submit(run1);
        ThreadUtil.uncheckedSleep(2);
        threadPool.submit(run2);

        em.getTransaction().rollback();

        threadPool.shutdown();

    }

    private static void exampleFetching(EntityManager em){

        em.getTransaction().begin();

        Article article = em.find(Article.class, InitData.getArticle1().getId());
        System.out.println("Before reading category");
        System.out.println(article.getCategory());

        System.out.println("before reading lazy category");
        System.out.println(article.getCategoryLazy());

        em.getTransaction().commit();
    }
}
