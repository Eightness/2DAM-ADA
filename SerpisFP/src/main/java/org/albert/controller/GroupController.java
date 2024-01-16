package org.albert.controller;

import org.albert.model.Group;
import org.albert.providers.CRUDInterface;
import org.albert.providers.ControllerManager;
import java.util.List;

/**
 * Class GroupController. Group CRUD's Controller.
 */
public class GroupController extends ControllerManager implements CRUDInterface<Group, Integer> {
    //Methods.
    @Override
    public void createEntity(Group entity) {
        groupDAO.createEntity(entity);
    }

    @Override
    public void createEntities(List<Group> entities) {
        groupDAO.createEntities(entities);
    }

    @Override
    public Group readEntityById(Integer primaryKey) {
        return groupDAO.readEntityById(primaryKey);
    }

    @Override
    public List<Group> readEntitiesById(List<Integer> primaryKeys) {
        return groupDAO.readEntitiesById(primaryKeys);
    }

    @Override
    public void updateEntityById(Group entity, Integer primaryKey) {
        groupDAO.updateEntityById(entity, primaryKey);
    }

    @Override
    public void updateEntitiesById(List<Group> entities, List<Integer> primaryKeys) {
        groupDAO.updateEntitiesById(entities, primaryKeys);
    }

    @Override
    public void deleteEntityById(Integer primaryKey) {
        groupDAO.deleteEntityById(primaryKey);
    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {
        groupDAO.deleteEntitiesById(primaryKeys);
    }

    @Override
    public boolean exists(Integer primaryKey) {
        return groupDAO.exists(primaryKey);
    }
}
