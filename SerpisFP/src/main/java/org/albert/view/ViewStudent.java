package org.albert.view;

import org.albert.model.Enrollment;
import org.albert.model.Project;
import org.albert.model.Student;
import org.albert.providers.ViewInterface;
import org.albert.providers.ViewManager;

import java.util.List;
import java.util.Scanner;

public class ViewStudent extends ViewManager implements ViewInterface<Student> {
    //Attributes.
    private final Scanner scanner = new Scanner(System.in);

    private String waitForResponse() {
        System.out.print("Pulsa enter per a seguir veient alumnes...");
        return scanner.nextLine();
    }

    @Override
    public void showEntity(Student student) {
        if (student != null) {
            System.out.format("+------------+---------------+------------------+------------+%n");
            System.out.format("| NIA        | NOMBRE        | APELLIDOS        | CODGRUPO   |%n");
            System.out.format("+------------+---------------+------------------+------------+%n");
            System.out.format("| %-10s | %-13s | %-16s | %-10s |%n",
                    student.getNia(), student.getName(), student.getSurnames(),
                    student.getGroup() != null ? student.getGroup().getGroupCode() : "");
            System.out.format("+------------+---------------+------------------+------------+%n");

            System.out.println("Matrícules:");
            List<Enrollment> enrollments = studentDAO.getEnrollmentsFromThisStudent(student);
            if (enrollments != null && !enrollments.isEmpty()) {
                System.out.format("+------------+------------------+%n");
                System.out.format("| CODMODULO  | DESCRIPCIÓN      |%n");
                System.out.format("+------------+------------------+%n");
                for (Enrollment enrollment : enrollments) {
                    System.out.format("| %-10s | %-16s |%n",
                            enrollment.getSubject() != null ? enrollment.getSubject().getSubjectCode() : "",
                            enrollment.getDescription());
                }
                System.out.format("+------------+------------------+%n");
            } else {
                System.out.println("[❕] Aquest alumne no té cap matrícula.");
            }

            System.out.println("Projecte:");
            Project project = studentDAO.getProjectFromThisStudent(student);
            if (project != null) {
                System.out.format("+-------------+----------------------------------------------------+%n");
                System.out.format("| CODPROYECTO | TÍTULO                                             |%n");
                System.out.format("+-------------+----------------------------------------------------+%n");
                System.out.format("| %-11s | %-50s |%n",
                        project.getId(), project.getTitle());
                System.out.format("+-------------+----------------------------------------------------+%n");
            } else {
                System.out.println("[❕] Aquest alumne no té cap projecte.");
            }
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar l'estudiant.");
        }
    }


    @Override
    public void showEntities(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            int index = 0;
            for (Student student : students) {
                if (index % 2 == 0 && index != 0) {
                    waitForResponse();
                    System.out.println();
                }
                System.out.println("[🟢] Alumne " + (index + 1) + ":");
                showEntity(student);
                System.out.println();
                index++;
            }
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els estudiants.");
        }
    }
}
