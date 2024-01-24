package org.albert.view;

import org.albert.model.Group;
import org.albert.model.Student;
import org.albert.providers.ViewInterface;
import org.albert.providers.ViewManager;
import java.util.List;
import java.util.Scanner;

public class ViewGroup extends ViewManager implements ViewInterface<Group> {
    //Attributes.
    private final Scanner scanner = new Scanner(System.in);

    private String waitForResponse() {
        System.out.print("Pulsa enter per a seguir veient grups...");
        return scanner.nextLine();
    }

    @Override
    public void showEntity(Group group) {
        if (group != null) {
            System.out.format("+------------+------------------+-------------+%n");
            System.out.format("| CODGRUPO   | DESCRIPCION      | AULA        |%n");
            System.out.format("+------------+------------------+-------------+%n");
            System.out.format("| %-10s | %-16s | %-11s |%n", group.getGroupCode(), group.getDescription(), group.getClassroom());
            System.out.format("+------------+------------------+-------------+%n");

            System.out.println("Alumnes:");
            List<Student> students = groupDAO.getStudentsFromThisGroup(group);
            if (students != null && !students.isEmpty()) {
                System.out.format("+------------+---------------------+--------------------------+%n");
                System.out.format("| NIA        | NOM                 | COGNOMS                  |%n");
                System.out.format("+------------+---------------------+--------------------------+%n");
                for (Student student : students) {
                    System.out.format("| %-10s | %-19s | %-24s |%n", student.getNia(), student.getName(), student.getSurnames());
                }
                System.out.format("+------------+---------------------+--------------------------+%n");
            } else {
                System.out.println("[❕] Aquest grup no té cap alumne.");
            }
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar el grup.");
        }
    }

    @Override
    public void showEntities(List<Group> groups) {
        if (groups != null && !groups.isEmpty()) {
            int index = 0;
            for (Group group : groups) {
                if (index % 2 == 0 && index != 0) {
                    waitForResponse();
                    System.out.println();
                }
                System.out.println("[🟢] Grup " + (index + 1) + ":");
                showEntity(group);
                System.out.println();
                index++;
            }
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els grups.");
        }
    }
}
