package org.albert.view;

import org.albert.model.Enrollment;
import org.albert.model.Subject;
import org.albert.providers.ViewInterface;

import java.util.List;
import java.util.Scanner;

public class ViewEnrollment implements ViewInterface<Enrollment> {
    //Attributes.
    private final Scanner scanner = new Scanner(System.in);

    private String waitForResponse() {
        System.out.print("Pulsa enter per a seguir veient matrícules...");
        return scanner.nextLine();
    }

    @Override
    public void showEntity(Enrollment enrollment) {
        if (enrollment != null) {
            System.out.format("+-------------+-----+------------+%n");
            System.out.format("| IDMATRICULA | NIA | CODMODULO  |%n");
            System.out.format("+-------------+-----+------------+%n");
            System.out.format("| %-11s | %-3s | %-10s |%n",
                    enrollment.getId(), enrollment.getStudent().getNia(),
                    enrollment.getSubject().getSubjectCode());
            System.out.format("+-------------+-----+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar la matrícula.");
        }
    }

    @Override
    public void showEntities(List<Enrollment> enrollments) {
        if (enrollments != null && !enrollments.isEmpty()) {
            int index = 0;
            for (Enrollment enrollment : enrollments) {
                if (index % 2 == 0 && index != 0) {
                    waitForResponse();
                    System.out.println();
                }
                System.out.println("[🟢] Matrícula " + (index + 1) + ":");
                showEntity(enrollment);
                System.out.println();
                index++;
            }
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar les matricules.");
        }
    }
}
