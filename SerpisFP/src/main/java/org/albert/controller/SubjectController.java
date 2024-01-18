package org.albert.controller;

import org.albert.model.Subject;
import org.albert.providers.CRUDInterface;
import org.albert.providers.ControllerManager;
import java.util.List;

/**
 * Class SubjectController. Subject CRUD's Controller.
 */
public class SubjectController extends ControllerManager implements CRUDInterface<Subject, String> {
    //Methods.
    @Override
    public void createEntity(Subject entity) {
        subjectDAO.createEntity(entity);
    }

    @Override
    public void createEntities(List<Subject> entities) {
        subjectDAO.createEntities(entities);
    }

    @Override
    public Subject readEntityById(String primaryKey) {
        return subjectDAO.readEntityById(primaryKey);
    }

    @Override
    public List<Subject> readEntitiesById(List<String> primaryKeys) {
        return subjectDAO.readEntitiesById(primaryKeys);
    }

    @Override
    public List<Subject> readAllEntities() {
        return subjectDAO.readAllEntities();
    }

    @Override
    public void updateEntityById(Subject entity, String primaryKey) {
        subjectDAO.updateEntityById(entity, primaryKey);
    }

    @Override
    public void updateEntitiesById(List<Subject> entities, List<String> primaryKeys) {
        subjectDAO.updateEntitiesById(entities, primaryKeys);
    }

    @Override
    public void deleteEntityById(String primaryKey) {
        subjectDAO.deleteEntityById(primaryKey);
    }

    @Override
    public void deleteEntitiesById(List<String> primaryKeys) {
        subjectDAO.deleteEntitiesById(primaryKeys);
    }

    @Override
    public void deleteAllEntities() {
        subjectDAO.deleteAllEntities();
    }

    @Override
    public boolean exists(String primaryKey) {
        return false;
    }
}
