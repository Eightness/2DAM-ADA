package org.albert.controller;

import org.albert.model.*;
import org.albert.providers.ControllerManager;

import java.util.ArrayList;

/**
 * Class GenericController. Basic database operations.
 */
public class GenericController extends ControllerManager {
    //Methods.
    public ArrayList<Integer> getItemsCountFromDatabase() {
        return genericDAO.getItemsCountFromDatabase();
    }

    public void deleteAllItemsFromDatabase() {
        genericDAO.deleteAllItemsFromDatabase();
    }

    public void loadDemoData() {
        //Creating groups.
        Group group1 = new Group(1, "2DAMC", "16");
        Group group2 = new Group(2, "2DAMR", "15");
        Group group3 = new Group(3, "2ASIRC", "14");
        groupDAO.createEntity(group1);
        groupDAO.createEntity(group2);
        groupDAO.createEntity(group3);

        //Creating students.
        Student myself = new Student("1", "Albert", "Lozano Blasco", group1);
        Student president = new Student("2", "Antonio Vicente", "Molines Martín", group1);
        Student student2 = new Student("3", "Cristian", "Popica", group2);
        Student student3 = new Student("4", "Daniel", "Ruiz Montero", group3);
        Student student4 = new Student("5", "David", "Guaita Onsurbe", group3);
        studentDAO.createEntity(myself);
        studentDAO.createEntity(president);
        studentDAO.createEntity(student2);
        studentDAO.createEntity(student3);
        studentDAO.createEntity(student4);

        //Creating subjects.
        Subject din = new Subject("1", "DIN", 8);
        Subject ada = new Subject("2", "ADA", 6);
        Subject pmdm = new Subject("3", "PMDM", 5);
        Subject sge = new Subject("4", "SGE", 5);
        Subject psp = new Subject("5", "PSP", 3);
        subjectDAO.createEntity(din);
        subjectDAO.createEntity(ada);
        subjectDAO.createEntity(pmdm);
        subjectDAO.createEntity(sge);
        subjectDAO.createEntity(psp);

        //Creating project.
        Project project = new Project("1234", "App per a reptes personals", president);
        projectDAO.createEntity(project);

        //Creating enrollments.
        Enrollment myEnrollment = new Enrollment(1, myself, ada, "2023/2024");
        Enrollment presidentEnrollment1 = new Enrollment(2, president, psp, "2023/2024");
        Enrollment presidentEnrollment2 = new Enrollment(3, president, ada, "2023/2024");
        enrollmentDAO.createEntity(myEnrollment);
        enrollmentDAO.createEntity(presidentEnrollment1);
        enrollmentDAO.createEntity(presidentEnrollment2);

        System.out.println("[✅] S'han carregat totes les dades per defecte correctament.");
    }
}
