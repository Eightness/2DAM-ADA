package org.albert.view;

import org.albert.model.Group;
import org.albert.model.Student;
import org.albert.providers.ViewInterface;

import java.util.List;

public class ViewGroup implements ViewInterface<Group> {
    @Override
    public void showEntity(Group group) {
        if (group != null) {
            System.out.format("+------------+------------------+-------------+%n");
            System.out.format("| CODGRUPO   | DESCRIPCION      | AULA        |%n");
            System.out.format("+------------+------------------+-------------+%n");
            System.out.format("| %-10s | %-16s | %-11s |%n", group.getGroupCode(), group.getDescription(), group.getClassroom());
            System.out.format("+------------+------------------+-------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar el grup.");
        }
    }

    @Override
    public void showEntities(List<Group> groups) {
        if (groups != null && !groups.isEmpty()) {
            System.out.format("+------------+------------------+-------------+%n");
            System.out.format("| CODGRUPO   | DESCRIPCION      | AULA        |%n");
            System.out.format("+------------+------------------+-------------+%n");

            for (Group group : groups) {
                System.out.format("| %-10s | %-16s | %-11s |%n", group.getGroupCode(), group.getDescription(), group.getClassroom());
            }

            System.out.format("+------------+------------------+-------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els grups.");
        }
    }
}
