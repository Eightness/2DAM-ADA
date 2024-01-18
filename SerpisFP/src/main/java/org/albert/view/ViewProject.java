package org.albert.view;

import org.albert.model.Project;
import org.albert.providers.ViewInterface;

import java.util.List;

public class ViewProject implements ViewInterface<Project> {
    @Override
    public void showEntity(Project project) {
        if (project != null) {
            System.out.format("+-------------+--------------------------------+------------------+------------+%n");
            System.out.format("| CODPROYECTO | TITULO                         | NIA              | CODGRUPO   |%n");
            System.out.format("+-------------+--------------------------------+------------------+------------+%n");
            System.out.format("| %-11s | %-30s | %-16s | %-10s |%n",
                    project.getId(), project.getTitle(),
                    project.getStudent() != null ? project.getStudent().getNia() : "",
                    project.getStudent() != null && project.getStudent().getGroup() != null ?
                            project.getStudent().getGroup().getGroupCode() : "");
            System.out.format("+-------------+--------------------------------+------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'ha pogut mostrar el projecte.");
        }
    }

    @Override
    public void showEntities(List<Project> projects) {
        if (projects != null && !projects.isEmpty()) {
            System.out.format("+-------------+--------------------------------+------------------+------------+%n");
            System.out.format("| CODPROYECTO | TITULO                         | NIA              | CODGRUPO   |%n");
            System.out.format("+-------------+--------------------------------+------------------+------------+%n");

            for (Project project : projects) {
                System.out.format("| %-11s | %-30s | %-16s | %-10s |%n",
                        project.getId(), project.getTitle(),
                        project.getStudent() != null ? project.getStudent().getNia() : "",
                        project.getStudent() != null && project.getStudent().getGroup() != null ?
                                project.getStudent().getGroup().getGroupCode() : "");
            }

            System.out.format("+-------------+--------------------------------+------------------+------------+%n");
        } else {
            System.out.println("[❌] ERROR! No s'han pogut mostrar els projectes.");
        }
    }
}
