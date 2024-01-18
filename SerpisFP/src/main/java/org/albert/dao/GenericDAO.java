package org.albert.dao;

import org.albert.model.Student;
import org.albert.providers.DAOManager;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Class GenericDAO. Contains basic methods to operate with the database.
 */
public class GenericDAO extends DAOManager {
    //Methods.
    public ArrayList<Integer> getItemsCountFromDatabase() {
        ArrayList<Integer> itemsCount = new ArrayList<>();

        try {
            long totalCount = (long) entityManager.createQuery("SELECT COUNT(e) FROM Enrollment e").getSingleResult();
            itemsCount.add((int) totalCount);

            long groupCount = (long) entityManager.createQuery("SELECT COUNT(g) FROM Group g").getSingleResult();
            itemsCount.add((int) groupCount);

            long projectCount = (long) entityManager.createQuery("SELECT COUNT(p) FROM Project p").getSingleResult();
            itemsCount.add((int) projectCount);

            long studentCount = (long) entityManager.createQuery("SELECT COUNT(s) FROM Student s").getSingleResult();
            itemsCount.add((int) studentCount);

            long subjectCount = (long) entityManager.createQuery("SELECT COUNT(sub) FROM Subject sub").getSingleResult();
            itemsCount.add((int) subjectCount);

            long enrollmentCount = (long) entityManager.createQuery("SELECT COUNT(en) FROM Enrollment en").getSingleResult();
            itemsCount.add((int) enrollmentCount);

            System.out.println("[✅] S'han actualitzat els comptadors d'elements.");
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'han pogut obtenir els comptadors d'elements. Motiu: " + e.getMessage());
        }

        return itemsCount;
    }


    public void deleteAllItemsFromDatabase() {
        try {
            entityTransaction.begin();

            entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();
            entityManager.createQuery("DELETE FROM Group").executeUpdate();
            entityManager.createQuery("DELETE FROM Project").executeUpdate();
            entityManager.createQuery("DELETE FROM Student").executeUpdate();
            entityManager.createQuery("DELETE FROM Subject").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres de totes les taules.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres de totes les taules. Motiu: " + e.getMessage());
        }
    }

}
