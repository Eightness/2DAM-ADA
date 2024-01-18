package org.albert.dao;

import org.albert.model.Project;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;
import java.util.List;

/**
 * Class ProjectDAO. Contains Project's CRUD logic.
 */
public class ProjectDAO extends DAOManager implements CRUDInterface<Project, String> {
    //Methods.
    @Override
    public void createEntity(Project entity) {

    }

    @Override
    public void createEntities(List<Project> entities) {

    }

    @Override
    public Project readEntityById(String primaryKey) {
        return null;
    }

    @Override
    public List<Project> readEntitiesById(List<String> primaryKeys) {
        return null;
    }

    @Override
    public List<Project> readAllEntities() {
        return null;
    }

    @Override
    public void updateEntityById(Project entity, String primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Project> entities, List<String> primaryKeys) {

    }

    @Override
    public void deleteEntityById(String primaryKey) {

    }

    @Override
    public void deleteEntitiesById(List<String> primaryKeys) {

    }

    @Override
    public void deleteAllEntities() {

    }

    @Override
    public boolean exists(String primaryKey) {
        return false;
    }
}
