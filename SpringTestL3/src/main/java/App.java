import org.hibernate.cfg.Configuration;
import hiber.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory entityFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityFactory.createEntityManager();

        Person person = new Person();
        person.setFirstname("Vasiliy");
        person.setLastname("Ivanov");

        Person readEntity = readEntity(em, 1L);
        readEntity.setFirstname("New Name");

        saveEntity(em, readEntity);


    }

    private static void createEntity(EntityManager em, Person entity){

        System.out.println("Creating entity");
        //open transaction
        em.getTransaction().begin();
        //put person into persist area of Hibernate
        em.persist(entity);
        //commit/close transaction
        em.getTransaction().commit();

        System.out.println("Creating finished");

    }

    private static Person readEntity(EntityManager em, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + person);
        return person;
    }

    private static void saveEntity(EntityManager em, Person entity){
        System.out.println("Start saving");

        em.getTransaction().begin();
        Person savedEntity = em.merge(entity);
        em.getTransaction().commit();

        System.out.println("Saving completed->" + savedEntity);
    }



}
