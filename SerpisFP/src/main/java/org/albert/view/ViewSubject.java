package org.albert.view;

import org.albert.model.Enrollment;
import org.albert.model.Student;
import org.albert.model.Subject;
import org.albert.providers.ViewInterface;
import org.albert.providers.ViewManager;

import java.util.List;
import java.util.Scanner;

public class ViewSubject extends ViewManager implements ViewInterface<Subject> {
    //Attributes.
    private final Scanner scanner = new Scanner(System.in);

    private String waitForResponse() {
        System.out.print("Pulsa enter per a seguir veient mòduls...");
        return scanner.nextLine();
    }

    @Override
    public void showEntity(Subject subject) {
        if (subject != null) {
            System.out.format("+------------+------------------+------------+%n");
            System.out.format("| CODMODULO  | DESCRIPCION      | HORAS      |%n");
            System.out.format("+------------+------------------+------------+%n");
            System.out.format("| %-10s | %-16s | %-10s |%n",
                    subject.getSubjectCode(), subject.getDescription(), subject.getNumHours());
            System.out.format("+------------+------------------+------------+%n");

            System.out.println("Matrícules associades a aquest mòdul:");
            List<Enrollment> enrollments = subjectDAO.getEnrollmentsFromThisSubject(subject);
            if (enrollments != null && !enrollments.isEmpty()) {
                System.out.format("+-------------+------------+-----------------------+%n");
                System.out.format("| IDMATRICULA | NIA        | DESCRIPCIÓN           |%n");
                System.out.format("+-------------+------------+-----------------------+%n");
                for (Enrollment enrollment : enrollments) {
                    System.out.format("| %-11s | %-10s | %-21s |%n",
                            enrollment.getId(),
                            enrollment.getStudent() != null ? enrollment.getStudent().getNia() : "",
                            enrollment.getDescription());
                }
                System.out.format("+-------------+------------+-----------------------+%n");
            } else {
                System.out.println("[❕] Aquest mòdul no té cap matrícula.");
            }

        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar el mòdul.");
        }
    }


    @Override
    public void showEntities(List<Subject> subjects) {
        if (subjects != null && !subjects.isEmpty()) {
            int index = 0;
            for (Subject subject : subjects) {
                if (index % 2 == 0 && index != 0) {
                    waitForResponse();
                    System.out.println();
                }
                System.out.println("[🟢] Mòdul " + (index + 1) + ":");
                showEntity(subject);
                System.out.println();
                index++;
            }
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els mòduls (no hi ha).");
        }
    }
}
