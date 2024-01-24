package org.albert.controller;

import org.albert.model.Subject;
import org.albert.providers.CRUDInterface;
import org.albert.providers.ControllerManager;
import java.util.List;

/**
 * Class SubjectController. Subject CRUD's Controller.
 */
public class SubjectController extends ControllerManager implements CRUDInterface<Subject, Integer> {
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
    public Subject readEntityById(Integer primaryKey) {
        viewSubject.showEntity(subjectDAO.readEntityById(primaryKey));
        return subjectDAO.readEntityById(primaryKey);
    }

    @Override
    public List<Subject> readEntitiesById(List<Integer> primaryKeys) {
        viewSubject.showEntities(subjectDAO.readEntitiesById(primaryKeys));
        return subjectDAO.readEntitiesById(primaryKeys);
    }

    @Override
    public List<Subject> readAllEntities() {
        viewSubject.showEntities(subjectDAO.readAllEntities());
        return subjectDAO.readAllEntities();
    }

    @Override
    public void updateEntityById(Subject entity, Integer primaryKey) {
        subjectDAO.updateEntityById(entity, primaryKey);
    }

    @Override
    public void updateEntitiesById(List<Subject> entities, List<Integer> primaryKeys) {
        subjectDAO.updateEntitiesById(entities, primaryKeys);
    }

    @Override
    public void deleteEntityById(Integer primaryKey) {
        subjectDAO.deleteEntityById(primaryKey);
    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {
        subjectDAO.deleteEntitiesById(primaryKeys);
    }

    @Override
    public void deleteAllEntities() {
        subjectDAO.deleteAllEntities();
    }

    @Override
    public boolean exists(Integer primaryKey) {
        return false;
    }

    @Override
    public boolean existsAtLeastOneEntry() {
        return subjectDAO.existsAtLeastOneEntry();
    }
}
