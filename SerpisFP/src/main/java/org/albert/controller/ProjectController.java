package org.albert.controller;

import org.albert.model.Project;
import org.albert.providers.CRUDInterface;
import org.albert.providers.ControllerManager;
import java.util.List;

/**
 * Class ProjectController. Project CRUD's Controller.
 */
public class ProjectController extends ControllerManager implements CRUDInterface<Project, String> {
    //Methods.
    @Override
    public void createEntity(Project entity) {
        projectDAO.createEntity(entity);
    }

    @Override
    public void createEntities(List<Project> entities) {
        projectDAO.createEntities(entities);
    }

    @Override
    public Project readEntityById(String primaryKey) {
        viewProject.showEntity(projectDAO.readEntityById(primaryKey));
        return projectDAO.readEntityById(primaryKey);
    }

    @Override
    public List<Project> readEntitiesById(List<String> primaryKeys) {
        viewProject.showEntities(projectDAO.readEntitiesById(primaryKeys));
        return projectDAO.readEntitiesById(primaryKeys);
    }

    @Override
    public List<Project> readAllEntities() {
        viewProject.showEntities(projectDAO.readAllEntities());
        return projectDAO.readAllEntities();
    }

    @Override
    public void updateEntityById(Project entity, String primaryKey) {
        projectDAO.updateEntityById(entity, primaryKey);
    }

    @Override
    public void updateEntitiesById(List<Project> entities, List<String> primaryKeys) {
        projectDAO.updateEntitiesById(entities, primaryKeys);
    }

    @Override
    public void deleteEntityById(String primaryKey) {
        projectDAO.deleteEntityById(primaryKey);
    }

    @Override
    public void deleteEntitiesById(List<String> primaryKeys) {
        projectDAO.deleteEntitiesById(primaryKeys);
    }

    @Override
    public void deleteAllEntities() {
        projectDAO.deleteAllEntities();
    }

    @Override
    public boolean exists(String primaryKey) {
        return projectDAO.exists(primaryKey);
    }

    @Override
    public boolean existsAtLeastOneEntry() {
        return projectDAO.existsAtLeastOneEntry();
    }
}
