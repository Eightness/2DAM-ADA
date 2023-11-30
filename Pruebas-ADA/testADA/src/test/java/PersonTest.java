/**
 * @author Albert Lozano Blasco
 * @version 1.0
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.serpis.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersonTest {
    //Logger creation.
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        //Entity Manager.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Start transaction.
        tx.begin();

        //Persistence object.
        Person person = new Person("Duncan", "Rua Valiente", "duncanrv@gmail.com", "722683623");

        //Persisting the object.
        em.persist(person);
        log.debug("Objeto a persistir: " + person);

        //Finishing transaction.
        tx.commit();
        em.close();
    }
}
