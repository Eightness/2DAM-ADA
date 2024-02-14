package org.albert.dao;

import org.albert.model.Enrollment;
import org.albert.model.Student;
import org.albert.model.Subject;
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
 * Class EnrollmentDAO. Contains Enrollment's CRUD logic.
 */
public class EnrollmentDAO extends DAOManager implements CRUDInterface<Enrollment, Integer> {
    //Methods.
    @Override
    public void createEntity(Enrollment entity) {
        if (entity == null) {
            System.out.println("[❌] ERROR! No s'ha creat correctament la matrícula.");
            return;
        }
        if (exists(entity.getId())) {
            System.out.println("[❌] ERROR! Ja existeix una matrícula amb IDMATRICULA " + entity.getId() + ".");
            return;
        }
        if (existsSameEnrollment(entity.getStudent(), entity.getSubject())) {
            System.out.println("[❌] ERROR! L'alumne amb NIA " + entity.getStudent().getNia() + " ja està matriculat en el mòdul amb el codi " + entity.getSubject().getSubjectCode() + ".");
            return;
        }
        try {
            entityTransaction.begin();

            Student student = entity.getStudent();
            Subject subject = entity.getSubject();

            if (student != null) {
                student = entityManager.getReference(student.getClass(), student.getNia());
                entity.setStudent(student);
            }

            if (subject != null) {
                subject = entityManager.getReference(subject.getClass(), subject.getSubjectCode());
                entity.setSubject(subject);
            }

            entityManager.persist(entity);

            if (student != null) {
                student.getEnrollments().add(entity);
                entityManager.merge(student);
            }

            if (subject != null) {
                subject.getEnrollments().add(entity);
                entityManager.merge(subject);
            }

            entityTransaction.commit();
            System.out.println("[✅] S'ha afegit la nova matrícula correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut afegir la nova matrícula correctament.");
        }
    }

    @Override
    public void createEntities(List<Enrollment> entities) {
        for (Enrollment entity : entities) {
            createEntity(entity);
        }
    }

    @Override
    public Enrollment readEntityById(Integer primaryKey) {
        try {
            return entityManager.find(Enrollment.class, primaryKey);
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir la matrícula amb IDMATRICULA " + primaryKey + ".");
            return null;
        }
    }

    @Override
    public List<Enrollment> readEntitiesById(List<Integer> primaryKeys) {
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            for (Integer primaryKey : primaryKeys) {
                Enrollment enrollment = readEntityById(primaryKey);
                if (enrollment != null) {
                    enrollments.add(enrollment);
                }
            }
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir algunes matrícules.");
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> readAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Enrollment> criteriaQuery = criteriaBuilder.createQuery(Enrollment.class);
            Root<Enrollment> root = criteriaQuery.from(Enrollment.class);
            criteriaQuery.select(root);

            TypedQuery<Enrollment> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'han pogut llegir totes les matrícules.");
            return null;
        }
    }

    @Override
    public void updateEntityById(Enrollment entity, Integer primaryKey) {
        try {
            entityTransaction.begin();

            Enrollment existingEnrollment = entityManager.find(Enrollment.class, primaryKey);
            if (existingEnrollment != null) {
                existingEnrollment.setStudent(entity.getStudent());
                existingEnrollment.setSubject(entity.getSubject());
                existingEnrollment.setDescription(entity.getDescription());

                entityTransaction.commit();
                System.out.println("[✅] S'ha actualitzat la matrícula amb IDMATRICULA " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap matrícula amb IDMATRICULA " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut actualitzar la matrícula amb IDMATRICULA " + primaryKey + ".");
        }
    }

    @Override
    public void updateEntitiesById(List<Enrollment> entities, List<Integer> primaryKeys) {
        if (entities.size() != primaryKeys.size()) {
            System.out.println("[❌] ERROR! La llista d'entitats i la llista de claus primàries tenen mides diferents.");
            return;
        }

        for (int i = 0; i < entities.size(); i++) {
            updateEntityById(entities.get(i), primaryKeys.get(i));
        }
    }

    @Override
    public void deleteEntityById(Integer primaryKey) {
        try {
            entityTransaction.begin();

            Enrollment enrollment = entityManager.find(Enrollment.class, primaryKey);
            if (enrollment != null) {
                entityManager.remove(enrollment);
                entityTransaction.commit();
                System.out.println("[✅] S'ha eliminat la matrícula amb IDMATRICULA " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap matrícula amb IDMATRICULA " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut eliminar la matrícula amb IDMATRICULA " + primaryKey + ".");
        }
    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {
        for (Integer primaryKey : primaryKeys) {
            deleteEntityById(primaryKey);
        }
    }

    @Override
    public void deleteAllEntities() {
        try {
            entityTransaction.begin();

            entityManager.createQuery("DELETE FROM Enrollment").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat totes les matrícules.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar totes les matrícules.");
        }
    }

    @Override
    public boolean exists(Integer primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(e) FROM Enrollment e WHERE e.id = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència de la matrícula.");
            return false;
        }
    }

    public boolean existsSameEnrollment(Student student, Subject subject) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(e) FROM Enrollment e WHERE e.student = :student AND e.subject = :subject");
            query.setParameter("student", student);
            query.setParameter("subject", subject);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència de la matrícula.");
            return false;
        }
    }

    @Override
    public boolean existsAtLeastOneEntry() {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(e) FROM Enrollment e");

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència de la matrícula.");
            return false;
        }
    }
}
