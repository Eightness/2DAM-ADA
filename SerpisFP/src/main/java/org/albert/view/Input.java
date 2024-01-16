package org.albert.view;

import org.albert.model.Group;
import org.albert.model.Project;
import org.albert.model.Student;
import org.albert.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    //Attributes
    private final Scanner scanner = new Scanner(System.in);

    //Methods

    //GET INPUT
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
                System.out.println("[!] No s'ha introduït un nombre enter.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return num;
    }

    public int getInt(String message, int firstOption, int secondOption) {
        int num = 0;
        boolean validated = false;
        while(!validated) {
            System.out.println();
            System.out.print(message);
            try {
                num = scanner.nextInt();
                if (num == 1 || num == 0) {
                    validated = true;
                } else {
                    System.out.println();
                    System.out.println("Per favor, introdueix " + firstOption + " o " + secondOption + ".");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("[!] No s'ha introduït un nombre enter.");
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
        System.out.println("Quan vulgues parar, introdueix un -1.");
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

    public double getDouble(String message) {
        double num = 0.0;
        boolean validated = false;
        while(!validated) {
            System.out.println();
            System.out.print(message);
            try {
                num = scanner.nextDouble();
                validated = true;
            } catch (Exception e) {
                System.out.println();
                System.out.println("[!] No s'ha introduït un nombre real.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return num;
    }

    public String getString(String message) {
        System.out.println();
        System.out.print(message);
        return scanner.nextLine();
    }

    //METHODS THAT REQUIRE USER'S INPUT
    //--------------------------------------------------------------------------

    //ENTITY GROUP.
    public Group createGroup() {
        return null;
    }

    public List<Group> createGroups() {
        return null;
    }

    //ENTITY PROJECT.
    public Project createProject() {
        return null;
    }

    public List<Project> createProjects() {
        return null;
    }

    //ENTITY STUDENT.
    public Student createStudent() {
        return null;
    }

    public List<Student> createStudents() {
        return null;
    }

    //ENTITY SUBJECT.
    public Subject createSubject() {
        return null;
    }

    public List<Subject> createSubjects() {
        return null;
    }
}
