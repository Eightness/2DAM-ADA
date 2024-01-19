package org.albert.dao;

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
public class SubjectDAO extends DAOManager implements CRUDInterface<Subject, String> {
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
            System.out.println("[❌] ERROR! No s'ha pogut afegir el nou mòdul correctament. Motiu: " + e.getMessage());
        }
    }

    @Override
    public void createEntities(List<Subject> entities) {
        for (Subject entity : entities) {
            createEntity(entity);
        }
    }

    @Override
    public Subject readEntityById(String primaryKey) {
        try {
            return entityManager.find(Subject.class, primaryKey);
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir el mòdul amb CODMODULO " + primaryKey + ". Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Subject> readEntitiesById(List<String> primaryKeys) {
        List<Subject> subjects = new ArrayList<>();
        try {
            for (String primaryKey : primaryKeys) {
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
            System.out.println("[❌] ERROR! No s'han pogut llegir tots els mòduls. Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEntityById(Subject entity, String primaryKey) {
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
            System.out.println("[❌] ERROR! No s'ha pogut actualitzar el mòdul amb CODMODULO " + primaryKey + ". Motiu: " + e.getMessage());
        }
    }

    @Override
    public void updateEntitiesById(List<Subject> entities, List<String> primaryKeys) {
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

            Subject subject = entityManager.find(Subject.class, primaryKey);
            if (subject != null) {
                entityManager.remove(subject);
                entityTransaction.commit();
                System.out.println("[✅] S'ha eliminat el mòdul amb CODMODULO " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap mòdul amb CODMODULO " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut eliminar el mòdul amb CODMODULO " + primaryKey + ". Motiu: " + e.getMessage());
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

            entityManager.createQuery("DELETE FROM Subject").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres de mòduls.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres de mòduls. Motiu: " + e.getMessage());
        }
    }

    @Override
    public boolean exists(String primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(s) FROM Subject s WHERE s.subjectCode = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència del mòdul. Motiu: " + e.getMessage());
            return false;
        }
    }
}
