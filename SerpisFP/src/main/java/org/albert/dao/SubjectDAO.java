package org.albert.dao;

import org.albert.model.Enrollment;
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
 * Class SubjectDAO. Contains Subject's CRUD logic.
 */
public class SubjectDAO extends DAOManager implements CRUDInterface<Subject, Integer> {
    //Methods.
    @Override
    public void createEntity(Subject entity) {
        if (entity == null) {
            System.out.println("[❌] ERROR! No s'ha creat correctament el mòdul.");
            return;
        }
        if (exists(entity.getSubjectCode())) {
            System.out.println("[❌] ERROR! Ja existeix un mòdul amb CODMODULO " + entity.getSubjectCode() + ".");
            return;
        }
        try {
            entityTransaction.begin();

            entityManager.persist(entity);

            entityTransaction.commit();
            System.out.println("[✅] S'ha afegit el nou mòdul correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut afegir el nou mòdul correctament.");
        }
    }

    @Override
    public void createEntities(List<Subject> entities) {
        for (Subject entity : entities) {
            createEntity(entity);
        }
    }

    @Override
    public Subject readEntityById(Integer primaryKey) {
        try {
            return entityManager.find(Subject.class, primaryKey);
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir el mòdul amb CODMODULO " + primaryKey + ".");
            return null;
        }
    }

    @Override
    public List<Subject> readEntitiesById(List<Integer> primaryKeys) {
        List<Subject> subjects = new ArrayList<>();
        try {
            for (int primaryKey : primaryKeys) {
                Subject subject = readEntityById(primaryKey);
                if (subject != null) {
                    subjects.add(subject);
                }
            }
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir alguns mòduls. Motiu: " + e.getMessage());
        }
        return subjects;
    }

    @Override
    public List<Subject> readAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
            Root<Subject> root = criteriaQuery.from(Subject.class);
            criteriaQuery.select(root);

            TypedQuery<Subject> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'han pogut llegir tots els mòduls.");
            return null;
        }
    }

    @Override
    public void updateEntityById(Subject entity, Integer primaryKey) {
        try {
            entityTransaction.begin();

            Subject existingSubject = entityManager.find(Subject.class, primaryKey);
            if (existingSubject != null) {
                existingSubject.setDescription(entity.getDescription());
                existingSubject.setNumHours(entity.getNumHours());
                // Otros campos que necesites actualizar

                entityTransaction.commit();
                System.out.println("[✅] S'ha actualitzat el mòdul amb CODMODULO " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap mòdul amb CODMODULO " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut actualitzar el mòdul amb CODMODULO " + primaryKey + ".");
        }
    }

    @Override
    public void updateEntitiesById(List<Subject> entities, List<Integer> primaryKeys) {
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

            Subject subject = entityManager.find(Subject.class, primaryKey);

            if (subject != null) {
                List<Enrollment> enrollments = getEnrollmentsFromThisSubject(subject);

                for (Enrollment enrollment : enrollments) {
                    entityManager.remove(enrollment);
                }

                entityManager.remove(subject);
                entityTransaction.commit();
                System.out.println("[✅] S'ha eliminat el mòdul amb CODMODULO " + primaryKey + " correctament.");
                System.out.println("[✅] També s'han eliminat les matrícules associades a aquest.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap mòdul amb CODMODULO " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut eliminar el mòdul amb CODMODULO " + primaryKey + ".");
        }
    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {
        for (int primaryKey : primaryKeys) {
            deleteEntityById(primaryKey);
        }
    }

    @Override
    public void deleteAllEntities() {
        try {
            entityTransaction.begin();

            entityManager.createQuery("DELETE FROM Subject").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres de mòduls.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres de mòduls.");
        }
    }

    @Override
    public boolean exists(Integer primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(s) FROM Subject s WHERE s.subjectCode = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència del mòdul.");
            return false;
        }
    }

    @Override
    public boolean existsAtLeastOneEntry() {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(s) FROM Subject s");

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència del mòdul.");
            return false;
        }
    }

    public List<Enrollment> getEnrollmentsFromThisSubject(Subject subject) {
        try {
            Query query = entityManager.createQuery("SELECT e FROM Enrollment e WHERE e.subject = :subject");
            query.setParameter("subject", subject);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
