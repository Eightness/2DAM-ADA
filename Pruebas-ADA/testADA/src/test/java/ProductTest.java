/**
 * @author Albert Lozano Blasco
 * @version 1.0
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.serpis.model.Product;
import org.serpis.model.Section;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProductTest {
    //Logger creation.
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        //Entity Manager.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //Start transaction.
        tx.begin();

        //Persistence object.
        //Product product = new Product("Taladro atornillador a batería", em.find(Section.class, 1), 45.99, "Perfecta para montar muebles.");
        Product product = em.find(Product.class, 2);

        if (product != null) {
            System.out.println("Sección del producto " + product.getName() + ": " + product.getSection().getSection());
        }

        //Persisting the object.
        //em.persist(product);
        //log.debug("Objeto a persistir: " + product);

        //Finishing transaction.
        tx.commit();
        em.close();
    }
}