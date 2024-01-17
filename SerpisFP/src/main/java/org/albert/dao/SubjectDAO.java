package org.albert.dao;

import org.albert.model.Subject;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;
import java.util.List;

/**
 * Class SubjectDAO. Contains Subject's CRUD logic.
 */
public class SubjectDAO extends DAOManager implements CRUDInterface<Subject, String> {
    //Methods.
    @Override
    public void createEntity(Subject entity) {

    }

    @Override
    public void createEntities(List<Subject> entities) {

    }

    @Override
    public Subject readEntityById(String primaryKey) {
        return null;
    }

    @Override
    public List<Subject> readEntitiesById(List<String> primaryKeys) {
        return null;
    }

    @Override
    public void updateEntityById(Subject entity, String primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Subject> entities, List<String> primaryKeys) {

    }

    @Override
    public void deleteEntityById(String primaryKey) {

    }

    @Override
    public void deleteEntitiesById(List<String> primaryKeys) {

    }

    @Override
    public boolean exists(String primaryKey) {
        return false;
    }
}
