/**
 * @author Albert Lozano Blasco
 * @version 1.0
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.serpis.model.Product;
import org.serpis.model.Section;

import javax.persistence.*;
import java.util.List;

public class SectionTest {
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
        //Section s = new Section("Herramientas", "Albert Lozano");
        Query query = em.createQuery(("select s from Section s where s.sectionId = ?1"));

        query.setParameter(1, 4);

        Section section = (Section) query.getSingleResult();

        List<Product> products = section.getProducts();

        for(Product product : products) {
            System.out.println("Producto: " + product.getName());
        }

        //Persisting the object.
        //em.persist(section);
        //log.debug("Objeto a persistir: " + section);

        //Finishing transaction.
        tx.commit();
        em.close();
    }
}