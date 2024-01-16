package org.albert.dao;

import org.albert.model.Subject;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;
import java.util.List;

/**
 * Class SubjectDAO. Contains Subject's CRUD logic.
 */
public class SubjectDAO extends DAOManager implements CRUDInterface<Subject, Integer> {
    //Methods.
    @Override
    public void createEntity(Subject entity) {

    }

    @Override
    public void createEntities(List<Subject> entities) {

    }

    @Override
    public Subject readEntityById(Integer primaryKey) {
        return null;
    }

    @Override
    public List<Subject> readEntitiesById(List<Integer> primaryKeys) {
        return null;
    }

    @Override
    public void updateEntityById(Subject entity, Integer primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Subject> entities, List<Integer> primaryKeys) {

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
