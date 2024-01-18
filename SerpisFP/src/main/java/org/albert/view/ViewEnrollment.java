package org.albert.view;

import org.albert.model.Enrollment;
import org.albert.providers.ViewInterface;

import java.util.List;

public class ViewEnrollment implements ViewInterface<Enrollment> {
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
            System.out.format("+-------------+-----+------------+%n");
            System.out.format("| IDMATRICULA | NIA | CODMODULO  |%n");
            System.out.format("+-------------+-----+------------+%n");

            for (Enrollment enrollment : enrollments) {
                System.out.format("| %-11s | %-3s | %-10s |%n",
                        enrollment.getId(), enrollment.getStudent().getNia(),
                        enrollment.getSubject().getSubjectCode());
            }

            System.out.format("+-------------+-----+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar les matricules.");
        }
    }
}
