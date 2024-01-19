package org.albert.dao;

import org.albert.model.Project;
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
 * Class ProjectDAO. Contains Project's CRUD logic.
 */
public class ProjectDAO extends DAOManager implements CRUDInterface<Project, String> {
    //Methods.
    @Override
    public void createEntity(Project entity) {
        if (entity == null) {
            System.out.println("[❌] ERROR! No s'ha creat correctament el projecte.");
            return;
        }
        if (exists(entity.getId())) {
            System.out.println("[❌] ERROR! Ja existeix un projecte amb CODPROYECTO " + entity.getId() + ".");
            return;
        }
        try {
            entityTransaction.begin();

            entityManager.persist(entity);

            entityTransaction.commit();
            System.out.println("[✅] S'ha afegit el nou projecte correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut afegir el nou projecte correctament. Motiu: " + e.getMessage());
        }
    }

    @Override
    public void createEntities(List<Project> entities) {
        for (Project entity : entities) {
            createEntity(entity);
        }
    }

    @Override
    public Project readEntityById(String primaryKey) {
        try {
            return entityManager.find(Project.class, primaryKey);
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir el projecte amb CODPROYECTO " + primaryKey + ". Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Project> readEntitiesById(List<String> primaryKeys) {
        List<Project> projects = new ArrayList<>();
        try {
            for (String primaryKey : primaryKeys) {
                Project project = readEntityById(primaryKey);
                if (project != null) {
                    projects.add(project);
                }
            }
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir alguns projectes. Motiu: " + e.getMessage());
        }
        return projects;
    }

    @Override
    public List<Project> readAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = criteriaQuery.from(Project.class);
            criteriaQuery.select(root);

            TypedQuery<Project> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'han pogut llegir tots els projectes. Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEntityById(Project entity, String primaryKey) {
        try {
            entityTransaction.begin();

            Project existingProject = entityManager.find(Project.class, primaryKey);
            if (existingProject != null) {
                existingProject.setTitle(entity.getTitle());
                existingProject.setStudent(entity.getStudent());

                entityTransaction.commit();
                System.out.println("[✅] S'ha actualitzat el projecte amb CODPROYECTO " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap projecte amb CODPROYECTO " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut actualitzar el projecte amb CODPROYECTO " + primaryKey + ". Motiu: " + e.getMessage());
        }
    }

    @Override
    public void updateEntitiesById(List<Project> entities, List<String> primaryKeys) {
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

            Project project = entityManager.find(Project.class, primaryKey);
            if (project != null) {
                entityManager.remove(project);
                entityTransaction.commit();
                System.out.println("[✅] S'ha eliminat el projecte amb CODPROYECTO " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap projecte amb CODPROYECTO " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut eliminar el projecte amb CODPROYECTO " + primaryKey + ". Motiu: " + e.getMessage());
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

            entityManager.createQuery("DELETE FROM Project").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres de projectes.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres de projectes. Motiu: " + e.getMessage());
        }
    }

    @Override
    public boolean exists(String primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(p) FROM Project p WHERE p.id = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència del projecte. Motiu: " + e.getMessage());
            return false;
        }
    }
}
