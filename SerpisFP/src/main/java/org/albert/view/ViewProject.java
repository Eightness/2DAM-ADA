package org.albert.view;

import org.albert.model.Project;
import org.albert.model.Student;
import org.albert.model.Subject;
import org.albert.providers.ViewInterface;

import java.util.List;
import java.util.Scanner;

public class ViewProject implements ViewInterface<Project> {
    //Attributes.
    private final Scanner scanner = new Scanner(System.in);

    private String waitForResponse() {
        System.out.print("Pulsa enter per a seguir veient projectes...");
        return scanner.nextLine();
    }

    @Override
    public void showEntity(Project project) {
        if (project != null) {
            Student student = project.getStudent();
            String nia = student.getNia();
            String name = student.getName();
            String surnames = student.getSurnames();
            int groupCode = student.getGroup().getGroupCode();

            System.out.format("+-------------+--------------------------------+------------+------------+--------------------------+------------+%n");
            System.out.format("| CODPROYECTO | TITULO                         |    NIA     |   NOMBRE   |        APELLIDOS         | CODGRUPO   |%n");
            System.out.format("+-------------+--------------------------------+------------+------------+--------------------------+------------+%n");
            System.out.format("| %-11s | %-30s | %-10s | %-10s | %-24s | %-10s |%n",
                    project.getId(), project.getTitle(), nia, name, surnames, groupCode);
            System.out.format("+-------------+--------------------------------+------------+------------+--------------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar el projecte.");
        }
    }

    @Override
    public void showEntities(List<Project> projects) {
        if (projects != null && !projects.isEmpty()) {
            int index = 0;
            for (Project project : projects) {
                if (index % 2 == 0 && index != 0) {
                    waitForResponse();
                    System.out.println();
                }
                System.out.println("[🟢] Projecte " + (index + 1) + ":");
                showEntity(project);
                System.out.println();
                index++;
            }
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els projectes (no hi ha).");
        }
    }
}
