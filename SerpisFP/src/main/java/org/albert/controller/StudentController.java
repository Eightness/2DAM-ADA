package org.albert.controller;

import org.albert.model.Student;
import org.albert.providers.CRUDInterface;
import org.albert.providers.ControllerManager;
import java.util.List;

/**
 * Class StudentController. Student CRUD's Controller.
 */
public class StudentController extends ControllerManager implements CRUDInterface<Student, String> {
    //Methods.
    @Override
    public void createEntity(Student entity) {
        studentDAO.createEntity(entity);
    }

    @Override
    public void createEntities(List<Student> entities) {
        studentDAO.createEntities(entities);
    }

    @Override
    public Student readEntityById(String primaryKey) {
        viewStudent.showEntity(studentDAO.readEntityById(primaryKey));
        return studentDAO.readEntityById(primaryKey);
    }

    @Override
    public List<Student> readEntitiesById(List<String> primaryKeys) {
        viewStudent.showEntities(studentDAO.readEntitiesById(primaryKeys));
        return studentDAO.readEntitiesById(primaryKeys);
    }

    @Override
    public List<Student> readAllEntities() {
        viewStudent.showEntities(studentDAO.readAllEntities());
        return studentDAO.readAllEntities();
    }

    @Override
    public void updateEntityById(Student entity, String primaryKey) {
        studentDAO.updateEntityById(entity, primaryKey);
    }

    @Override
    public void updateEntitiesById(List<Student> entities, List<String> primaryKeys) {
        studentDAO.updateEntitiesById(entities, primaryKeys);
    }

    @Override
    public void deleteEntityById(String primaryKey) {
        studentDAO.deleteEntityById(primaryKey);
    }

    @Override
    public void deleteEntitiesById(List<String> primaryKeys) {
        studentDAO.deleteEntitiesById(primaryKeys);
    }

    @Override
    public void deleteAllEntities() {
        studentDAO.deleteAllEntities();
    }

    @Override
    public boolean exists(String primaryKey) {
        return studentDAO.exists(primaryKey);
    }
}
