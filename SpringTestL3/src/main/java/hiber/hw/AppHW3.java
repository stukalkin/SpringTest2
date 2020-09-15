package hiber.hw;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AppHW3 {

    public static void main(String[] args) {

        EntityManagerFactory entityFactory = new Configuration()
                .configure("hw3.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityFactory.createEntityManager();

        {
            Buyer buyer1 = new Buyer();
            buyer1.setFirstname("Alex");
            createEntity(em, buyer1);
        }
        {
            Buyer buyer2 = new Buyer();
            buyer2.setFirstname("Mike");
            createEntity(em, buyer2);
        }
        {
            Buyer buyer3 = new Buyer();
            buyer3.setFirstname("Robert");
            createEntity(em, buyer3);
        }
        {
            Product product1 = new Product();
            product1.setTitle("Milk");
            product1.setPrice(46.4);
            product1.setBuyers(readEntity(em, Buyer.class, 1L));
            createEntity(em, product1);
        }
        {
            Product product2 = new Product();
            product2.setTitle("Cheese");
            product2.setPrice(249.6);
            product2.setBuyers(readEntity(em, Buyer.class, 2L));
            createEntity(em, product2);
        }
        {
            Product product3 = new Product();
            product3.setTitle("Chocolate");
            product3.setPrice(56.0);
            product3.setBuyers(readEntity(em, Buyer.class, 3L));
            createEntity(em, product3);
        }
        {
            Product product4 = new Product();
            product4.setTitle("Bread");
            product4.setPrice(30.2);
            product4.setBuyers(readEntity(em, Buyer.class, 1L));
            createEntity(em, product4);
        }
        {
            Product product5 = new Product();
            product5.setTitle("Lemonade");
            product5.setPrice(90.8);
            product5.setBuyers(readEntity(em, Buyer.class, 2L));
            createEntity(em, product5);
        }
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
}
