package org.albert.dao;

import org.albert.model.Group;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;
import java.util.List;

/**
 * Class GroupDAO. Contains Group's CRUD logic.
 */
public class GroupDAO extends DAOManager implements CRUDInterface<Group, Integer> {
    //Methods.
    @Override
    public void createEntity(Group entity) {

    }

    @Override
    public void createEntities(List<Group> entities) {

    }

    @Override
    public Group readEntityById(Integer primaryKey) {
        return null;
    }

    @Override
    public List<Group> readEntitiesById(List<Integer> primaryKeys) {
        return null;
    }

    @Override
    public void updateEntityById(Group entity, Integer primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Group> entities, List<Integer> primaryKeys) {

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
