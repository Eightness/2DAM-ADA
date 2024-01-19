package org.albert.dao;

import org.albert.model.Student;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Class StudentDAO. Contains Student's CRUD logic.
 */
public class StudentDAO extends DAOManager implements CRUDInterface<Student, String> {
    //Methods.
    @Override
    public void createEntity(Student entity) {
        if (entity == null) {
            System.out.println("[❌] ERROR! No s'ha creat correctament l'alumne.");
            return;
        }
        if (exists(entity.getNia())) {
            System.out.println("[❌] ERROR! Ja existeix un alumne amb NIA " + entity.getNia() + ".");
            return;
        }
        try {
            entityTransaction.begin();

            entityManager.persist(entity);

            entityTransaction.commit();
            System.out.println("[✅] S'ha afegit el nou alumne correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut afegir el nou alumne correctament. Motiu: " + e.getMessage());
        }
    }

    @Override
    public void createEntities(List<Student> entities) {
        for (Student entity : entities) {
            createEntity(entity);
        }
    }

    @Override
    public Student readEntityById(String primaryKey) {
        try {
            return entityManager.find(Student.class, primaryKey);
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir l'alumne amb NIA " + primaryKey + ". Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> readEntitiesById(List<String> primaryKeys) {
        List<Student> students = new ArrayList<>();
        try {
            for (String primaryKey : primaryKeys) {
                Student student = readEntityById(primaryKey);
                if (student != null) {
                    students.add(student);
                }
            }
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir alguns alumnes. Motiu: " + e.getMessage());
        }
        return students;
    }

    @Override
    public List<Student> readAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);
            criteriaQuery.select(root);

            TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'han pogut llegir tots els alumnes. Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEntityById(Student entity, String primaryKey) {
        try {
            entityTransaction.begin();

            Student existingStudent = entityManager.find(Student.class, primaryKey);
            if (existingStudent != null) {
                existingStudent.setName(entity.getName());
                existingStudent.setSurnames(entity.getSurnames());
                existingStudent.setGroup(entity.getGroup());

                entityTransaction.commit();
                System.out.println("[✅] S'ha actualitzat l'alumne amb NIA " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap alumne amb NIA " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut actualitzar l'alumne amb NIA " + primaryKey + ". Motiu: " + e.getMessage());
        }
    }

    @Override
    public void updateEntitiesById(List<Student> entities, List<String> primaryKeys) {
        if (entities.size() != primaryKeys.size()) {
            System.out.println("[❌] ERROR! La llista d'entitats i la llista de claus primàries tenen mides diferents.");
            return;
        }

        for (int i = 0; i < entities.size(); i++) {
            updateEntityById(entities.get(i), primaryKeys.get(i));
        }
    }

    @Override
    public void deleteEntityById(String primaryKey) {
        try {
            entityTransaction.begin();

            Student student = entityManager.find(Student.class, primaryKey);
            if (student != null) {
                entityManager.remove(student);
                entityTransaction.commit();
                System.out.println("[✅] S'ha eliminat l'alumne amb NIA " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap alumne amb NIA " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut eliminar l'alumne amb NIA " + primaryKey + ". Motiu: " + e.getMessage());
        }
    }

    @Override
    public void deleteEntitiesById(List<String> primaryKeys) {
        for (String primaryKey : primaryKeys) {
            deleteEntityById(primaryKey);
        }
    }

    @Override
    public void deleteAllEntities() {
        try {
            entityTransaction.begin();

            entityManager.createQuery("DELETE FROM Student").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres d'alumnes.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres d'alumnes. Motiu: " + e.getMessage());
        }
    }

    @Override
    public boolean exists(String primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(s) FROM Student s WHERE s.nia = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència de l'alumne. Motiu: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean existsAtLeastOneEntry() {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(s) FROM Student s");

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència de l'alumne. Motiu: " + e.getMessage());
            return false;
        }
    }

    public List<Student> readAllStudentsWithoutAProject() {
        try {
            entityTransaction.begin();

            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.project IS NULL", Student.class);

            List<Student> studentsWithoutGroup = query.getResultList();

            entityTransaction.commit();
            return studentsWithoutGroup;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut llegir els estudiants sense projecte. Motiu: " + e.getMessage());
            return null;
        }
    }

}
