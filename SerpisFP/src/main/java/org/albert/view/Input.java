package org.albert.view;

import org.albert.model.*;
import org.albert.providers.ControllerManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input extends ControllerManager {
    //Attributes
    private final Scanner scanner = new Scanner(System.in);

    //Methods

    //GET INPUT.
    //--------------------------------------------------------------------------

    public int getInt(String message) {
        int num = 0;
        boolean validated = false;
        while(!validated) {
            System.out.println();
            System.out.print(message);
            try {
                num = scanner.nextInt();
                validated = true;
            } catch (Exception e) {
                System.out.println();
                System.out.println("[❗] No s'ha introduït un nombre enter.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return num;
    }

    public List<Integer> getInts(String message) {
        List<Integer> nums = new ArrayList<>();
        boolean keepAsking = true;

        System.out.println();
        System.out.println("[❔] Quan vulgues parar, introdueix un -1.");
        while(keepAsking) {
            int num = getInt(message);
            if (num == -1) {
                keepAsking = false;
            } else {
                nums.add(num);
            }
        }

        return nums;
    }

    public String getStringNotNull(String message) {
        System.out.println();
        System.out.print(message);
        return scanner.nextLine();
    }

    public String getStringNullable(String message) {
        System.out.println();
        System.out.print(message);
        String response = scanner.nextLine();
        if (response.isBlank()) {
            return null;
        } else {
            return response;
        }
    }

    public List<String> getStrings(String message) {
        List<String> stringList = new ArrayList<>();
        boolean keepAsking = true;

        System.out.println();
        System.out.println("[❔] Quan vulgues parar, introdueix una línia en blanc.");
        while (keepAsking) {
            String input = getStringNotNull(message);

            if (input.isBlank()) {
                keepAsking = false;
            } else {
                stringList.add(input);
            }
        }

        return stringList;
    }

    //METHODS THAT REQUIRE USER'S INPUT.
    //--------------------------------------------------------------------------

    //ENTITY GROUP.
    public Group createGroup() {
        int groupCode = getInt("CODGRUPO: ");
        String description = getStringNotNull("Descripció: ");
        String classroom = getStringNotNull("Aula: ");

        return new Group(groupCode, description, classroom, new ArrayList<>());
    }

    public List<Group> createGroups() {
        List<Group> groups = new ArrayList<>();

        while (true) {
            Group group = createGroup();
            groups.add(group);

            System.out.print("Vols afegir un altre grup? (S/N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (!response.equals("S")) {
                break;
            }
        }

        return groups;
    }

    //ENTITY PROJECT.
    public Project createProject() {
        if (studentDAO.readAllStudentsWithoutAProject().isEmpty()) {
            System.out.println("[❌] No es pot crear un nou projecte. No hi ha alumnes sense projecte disponibles.");
            return null;
        }
        String id = getStringNotNull("CODPROYECTO: ");
        String title = getStringNotNull("Títol: ");

        System.out.println();
        System.out.println("[❕] Alumnes (sense projectes) en la base de dades: ");
        viewStudent.showEntities(studentDAO.readAllStudentsWithoutAProject());

        String nia = getStringNotNull("Introdueix el NIA del alumne: ");

        Student student = studentDAO.readEntityById(nia);

        if (student != null) {
            return new Project(id, title, student);
        } else {
            return new Project(id, title, null);
        }

    }

    public List<Project> createProjects() {
        List<Project> projects = new ArrayList<>();

        while (true) {
            Project project = createProject();
            projects.add(project);

            System.out.print("[❔] Vols afegir un altre projecte? (S/N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (!response.equals("S")) {
                break;
            }
        }

        return projects;
    }

    //ENTITY STUDENT.
    public Student createStudent() {
        if (!genericDAO.existsAtLeastOneEntity("Group")) {
            System.out.println("[❌] No es pot crear un nou alumne. No hi ha ningún grup creat en la BBDD.");
            return null;
        }
        String nia = getStringNotNull("NIA: ");
        String name = getStringNotNull("Nom: ");
        String surnames = getStringNotNull("Cognoms: ");

        System.out.println();
        System.out.println("[❕] Grups en la base de dades: ");
        viewGroup.showEntities(groupDAO.readAllEntities());

        int groupCode = getInt("CODGRUPO: ");
        Group group = groupDAO.readEntityById(groupCode);

        if (group != null) {
            Student newStudent = new Student(nia, name, surnames, group, new ArrayList<>(), null);
            group.getStudents().add(newStudent);
            return newStudent;
        } else {
            System.out.println("[❌] El grup no existeix. No es pot crear l'alumne.");
            return null;
        }
    }


    public List<Student> createStudents() {
        List<Student> students = new ArrayList<>();

        while (true) {
            Student student = createStudent();
            students.add(student);

            System.out.print("[❔] Vols afegir un altre estudiant? (S/N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (!response.equals("S")) {
                break;
            }
        }

        return students;
    }

    //ENTITY SUBJECT.
    public Subject createSubject() {
        String subjectCode = getStringNotNull("CODMODULO: ");
        String description = getStringNotNull("Descripció: ");
        int numHours = getInt("Hores: ");

        return new Subject(subjectCode, description, numHours);
    }

    public List<Subject> createSubjects() {
        List<Subject> subjects = new ArrayList<>();

        while (true) {
            Subject subject = createSubject();
            subjects.add(subject);

            System.out.print("[❔] Vols afegir una altra assignatura? (S/N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (!response.equals("S")) {
                break;
            }
        }

        return subjects;
    }

    //ENTITY ENROLLMENT.
    public Enrollment createEnrollment() {
        if (!studentDAO.existsAtLeastOneEntry() || !subjectDAO.existsAtLeastOneEntry()) {
            System.out.println("[❌] No es pot crear una nova matrícula. No hi ha alumnes/mòduls creats.");
            return null;
        }
        int enrollmentId = getInt("IDMATRICULA: ");
        String description = getStringNotNull("Descripció: ");

        System.out.println();
        System.out.println("[❕] Alumnes en la base de dades: ");
        viewStudent.showEntities(studentDAO.readAllEntities());

        String nia = getStringNotNull("NIA: ");
        Student student = studentDAO.readEntityById(nia);

        System.out.println();
        System.out.println("[❕] Mòduls en la base de dades: ");
        viewSubject.showEntities(subjectDAO.readAllEntities());

        String subjectCode = getStringNotNull("CODMODULO: ");
        Subject subject = subjectDAO.readEntityById(subjectCode);

        if (student != null && subject != null) {
            return new Enrollment(enrollmentId, student, subject, description);
        } else {
            System.out.println("[❌] No s'ha pogut crear la matrícula. L'alumne o el mòdul no existeixen.");
            return null;
        }
    }

    public List<Enrollment> createEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();

        while (true) {
            Enrollment enrollment = createEnrollment();
            enrollments.add(enrollment);

            System.out.print("[❔] Vols afegir una altra matrícula? (S/N): ");
            String response = scanner.nextLine().trim().toUpperCase();

            if (!response.equals("S")) {
                break;
            }
        }

        return enrollments;
    }
}
