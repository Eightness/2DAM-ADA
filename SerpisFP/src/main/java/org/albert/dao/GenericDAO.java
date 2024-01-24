package org.albert.dao;

import org.albert.model.*;
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
            long groupCount = (long) entityManager.createQuery("SELECT COUNT(g) FROM Group g").getSingleResult();
            long projectCount = (long) entityManager.createQuery("SELECT COUNT(p) FROM Project p").getSingleResult();
            long studentCount = (long) entityManager.createQuery("SELECT COUNT(s) FROM Student s").getSingleResult();
            long subjectCount = (long) entityManager.createQuery("SELECT COUNT(sub) FROM Subject sub").getSingleResult();
            long enrollmentCount = (long) entityManager.createQuery("SELECT COUNT(en) FROM Enrollment en").getSingleResult();
            long totalCount = groupCount + projectCount + studentCount + subjectCount + enrollmentCount;

            itemsCount.add((int) totalCount);
            itemsCount.add((int) groupCount);
            itemsCount.add((int) projectCount);
            itemsCount.add((int) studentCount);
            itemsCount.add((int) subjectCount);
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

            Query disableConstraintsQuery = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
            disableConstraintsQuery.executeUpdate();

            entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();
            entityManager.createQuery("DELETE FROM Group").executeUpdate();
            entityManager.createQuery("DELETE FROM Project").executeUpdate();
            entityManager.createQuery("DELETE FROM Student").executeUpdate();
            entityManager.createQuery("DELETE FROM Subject").executeUpdate();

            Query enableConstraintsQuery = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
            enableConstraintsQuery.executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres de totes les taules.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres de totes les taules. Motiu: " + e.getMessage());
        }
    }

    public void loadDemoData() {
        //Creating groups.
        Group group1 = new Group(1, "2DAMC", "16", new ArrayList<>());
        Group group2 = new Group(2, "2DAMR", "15", new ArrayList<>());
        Group group3 = new Group(3, "2ASIRC", "14", new ArrayList<>());

        //Creating students.
        Student myself = new Student("1", "Albert", "Lozano Blasco", group1, null, null);
        Student president = new Student("2", "Antonio", "Molines Martín", group1, null, null);
        Student student2 = new Student("3", "Cristian", "Popica", group2, null, null);
        Student student3 = new Student("4", "Daniel", "Ruiz Montero", group3, null, null);
        Student student4 = new Student("5", "David", "Guaita Onsurbe", group3, null, null);

        //Creating subjects.
        Subject din = new Subject(1, "DIN", 8);
        Subject ada = new Subject(2, "ADA", 6);
        Subject pmdm = new Subject(3, "PMDM", 5);
        Subject sge = new Subject(4, "SGE", 5);
        Subject psp = new Subject(5, "PSP", 3);

        //Creating project.
        Project project = new Project("1234", "App per a reptes personals", president);

        //Creating enrollments.
        Enrollment myEnrollment = new Enrollment(1, myself, ada, "2023/2024");
        Enrollment presidentEnrollment1 = new Enrollment(2, president, psp, "2023/2024");
        Enrollment presidentEnrollment2 = new Enrollment(3, president, ada, "2023/2024");

        try {
            entityTransaction.begin();

            entityManager.persist(group1);
            entityManager.persist(group2);
            entityManager.persist(group3);

            entityManager.persist(myself);
            entityManager.persist(president);
            entityManager.persist(student2);
            entityManager.persist(student3);
            entityManager.persist(student4);

            entityManager.persist(din);
            entityManager.persist(ada);
            entityManager.persist(pmdm);
            entityManager.persist(sge);
            entityManager.persist(psp);

            entityManager.persist(project);

            entityManager.persist(myEnrollment);
            entityManager.persist(presidentEnrollment1);
            entityManager.persist(presidentEnrollment2);

            entityTransaction.commit();
            System.out.println("[✅] S'han carregat totes les dades per defecte correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut carregar les dades per defecte. Motiu: " + e.getMessage());
        }
    }

    public boolean existsAtLeastOneEntity(String entityName) {
        try {
            entityTransaction.begin();

            Long count = entityManager.createQuery("SELECT COUNT(e) FROM " + entityName + " e", Long.class).getSingleResult();

            entityTransaction.commit();

            return count > 0;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut comprovar si hi ha mínim 1 registre en la BBDD. Motiu: " + e.getMessage());
        }
        return false;
    }

}
