package org.albert.dao;

import org.albert.model.Student;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;
import java.util.List;

/**
 * Class StudentDAO. Contains Student's CRUD logic.
 */
public class StudentDAO extends DAOManager implements CRUDInterface<Student, String> {
    //Methods.
    @Override
    public void createEntity(Student entity) {

    }

    @Override
    public void createEntities(List<Student> entities) {

    }

    @Override
    public Student readEntityById(String primaryKey) {
        return null;
    }

    @Override
    public List<Student> readEntitiesById(List<String> primaryKeys) {
        return null;
    }

    @Override
    public void updateEntityById(Student entity, String primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Student> entities, List<String> primaryKeys) {

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
