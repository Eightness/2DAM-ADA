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

    }

    @Override
    public void createEntities(List<Enrollment> entities) {

    }

    @Override
    public Enrollment readEntityById(Integer primaryKey) {
        return null;
    }

    @Override
    public List<Enrollment> readEntitiesById(List<Integer> primaryKeys) {
        return null;
    }

    @Override
    public void updateEntityById(Enrollment entity, Integer primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Enrollment> entities, List<Integer> primaryKeys) {

    }

    @Override
    public void deleteEntityById(Integer primaryKey) {

    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {

    }

    @Override
    public boolean exists(Integer primaryKey) {
        return false;
    }
}
