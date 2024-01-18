package org.albert.view;

import org.albert.model.Student;
import org.albert.providers.ViewInterface;

import java.util.List;

public class ViewStudent implements ViewInterface<Student> {
    @Override
    public void showEntity(Student student) {
        if (student != null) {
            System.out.format("+-----+---------------+------------------+------------+%n");
            System.out.format("| NIA | NOMBRE        | APELLIDOS        | CODGRUPO   |%n");
            System.out.format("+-----+---------------+------------------+------------+%n");
            System.out.format("| %-3s | %-13s | %-16s | %-10s |%n",
                    student.getNia(), student.getName(), student.getSurnames(),
                    student.getGroup() != null ? student.getGroup().getGroupCode() : "");
            System.out.format("+-----+---------------+------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar l'estudiant.");
        }
    }

    @Override
    public void showEntities(List<Student> students) {
        if (students != null && !students.isEmpty()) {
            System.out.format("+-----+---------------+------------------+------------+%n");
            System.out.format("| NIA | NOMBRE        | APELLIDOS        | CODGRUPO   |%n");
            System.out.format("+-----+---------------+------------------+------------+%n");

            for (Student student : students) {
                System.out.format("| %-3s | %-13s | %-16s | %-10s |%n",
                        student.getNia(), student.getName(), student.getSurnames(),
                        student.getGroup() != null ? student.getGroup().getGroupCode() : "");
            }

            System.out.format("+-----+---------------+------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els estudiants.");
        }
    }
}
