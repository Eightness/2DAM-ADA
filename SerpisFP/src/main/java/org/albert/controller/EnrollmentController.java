package org.albert.controller;

import org.albert.model.Enrollment;
import org.albert.providers.CRUDInterface;
import org.albert.providers.ControllerManager;

import java.util.List;

/**
 * Class EnrollmentController. Enrollment CRUD's Controller.
 */
public class EnrollmentController extends ControllerManager implements CRUDInterface<Enrollment, Integer> {
    @Override
    public void createEntity(Enrollment entity) {
        enrollmentDAO.createEntity(entity);
    }

    @Override
    public void createEntities(List<Enrollment> entities) {
        enrollmentDAO.createEntities(entities);
    }

    @Override
    public Enrollment readEntityById(Integer primaryKey) {
        return enrollmentDAO.readEntityById(primaryKey);
    }

    @Override
    public List<Enrollment> readEntitiesById(List<Integer> primaryKeys) {
        return enrollmentDAO.readEntitiesById(primaryKeys);
    }

    @Override
    public void updateEntityById(Enrollment entity, Integer primaryKey) {
        enrollmentDAO.updateEntityById(entity, primaryKey);
    }

    @Override
    public void updateEntitiesById(List<Enrollment> entities, List<Integer> primaryKeys) {
        enrollmentDAO.updateEntitiesById(entities, primaryKeys);
    }

    @Override
    public void deleteEntityById(Integer primaryKey) {
        enrollmentDAO.deleteEntityById(primaryKey);
    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {
        enrollmentDAO.deleteEntitiesById(primaryKeys);
    }

    @Override
    public boolean exists(Integer primaryKey) {
        return enrollmentDAO.exists(primaryKey);
    }
}
