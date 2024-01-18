package org.albert.view;

import org.albert.model.Subject;
import org.albert.providers.ViewInterface;

import java.util.List;

public class ViewSubject implements ViewInterface<Subject> {
    @Override
    public void showEntity(Subject subject) {
        if (subject != null) {
            System.out.format("+------------+------------------+------------+%n");
            System.out.format("| CODMODULO  | DESCRIPCION      | HORAS      |%n");
            System.out.format("+------------+------------------+------------+%n");
            System.out.format("| %-10s | %-16s | %-10s |%n",
                    subject.getSubjectCode(), subject.getDescription(), subject.getNumHours());
            System.out.format("+------------+------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar el mòdul.");
        }
    }

    @Override
    public void showEntities(List<Subject> subjects) {
        if (subjects != null && !subjects.isEmpty()) {
            System.out.format("+------------+------------------+------------+%n");
            System.out.format("| CODMODULO  | DESCRIPCION      | HORAS      |%n");
            System.out.format("+------------+------------------+------------+%n");

            for (Subject subject : subjects) {
                System.out.format("| %-10s | %-16s | %-10s |%n",
                        subject.getSubjectCode(), subject.getDescription(), subject.getNumHours());
            }

            System.out.format("+------------+------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els mòduls.");
        }
    }
}
