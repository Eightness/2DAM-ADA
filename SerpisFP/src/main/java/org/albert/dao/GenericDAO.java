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
        return null;
    }

    public void deleteAllItemsFromDatabase() {
        try {
            entityTransaction.begin();

            //Delete Items.
            Query deleteStudents = entityManager.createQuery("DELETE FROM alumno_al15");
            deleteStudents.executeUpdate();

            Query deleteGroups = entityManager.createQuery("DELETE FROM GROUP");
            deleteGroups.executeUpdate();

            Query deleteEnrollments = entityManager.createQuery("DELETE FROM ENROLLMENT");
            deleteEnrollments.executeUpdate();

            Query deleteSubjects = entityManager.createQuery("DELETE FROM SUBJECT");
            deleteSubjects.executeUpdate();

            Query deleteProjects = entityManager.createQuery("DELETE FROM PROJECT");
            deleteProjects.executeUpdate();

            entityTransaction.commit();

            System.out.println("[!] S'han esborrat totes les dades de la BBDD correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
            System.out.println("[!] ERROR! No s'han pogut esborrar les dades de la BBDD.");
        }
    }
}
