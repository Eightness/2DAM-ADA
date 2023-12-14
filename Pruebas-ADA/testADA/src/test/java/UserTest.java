/**
 * @author Albert Lozano Blasco
 * @version 1.0
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.serpis.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserTest {
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
        User user = new User("Albert", "1234", 2);

        //Persisting the object.
        em.persist(user);
        log.debug("Objeto a persistir: " + user);

        //Finishing transaction.
        tx.commit();
        em.close();
    }
}
