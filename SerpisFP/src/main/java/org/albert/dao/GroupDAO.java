package org.albert.dao;

import org.albert.model.Group;
import org.albert.model.Student;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Class GroupDAO. Contains Group's CRUD logic.
 */
public class GroupDAO extends DAOManager implements CRUDInterface<Group, Integer> {
    //Methods.
    @Override
    public void createEntity(Group entity) {
        if (entity == null) {
            System.out.println("[❌] ERROR! No s'ha creat correctament el grup.");
            return;
        }
        if (exists(entity.getGroupCode())) {
            System.out.println("[❌] ERROR! Ja existeix un grup amb id (CODGRUPO) " + entity.getGroupCode() + ".");
            return;
        }
        try {
            entityTransaction.begin();

            entityManager.persist(entity);

            entityTransaction.commit();
            System.out.println("[✅] S'ha afegit el nou grup correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut afegir el nou grup correctament.");
        }
    }

    @Override
    public void createEntities(List<Group> entities) {
        for (Group entity : entities) {
            createEntity(entity);
        }
    }

    @Override
    public Group readEntityById(Integer primaryKey) {
        try {
            return entityManager.find(Group.class, primaryKey);
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir el grup amb id (CODGRUPO) " + primaryKey + ".");
            return null;
        }
    }

    @Override
    public List<Group> readEntitiesById(List<Integer> primaryKeys) {
        List<Group> groups = new ArrayList<>();
        try {
            for (Integer primaryKey : primaryKeys) {
                Group group = readEntityById(primaryKey);
                if (group != null) {
                    groups.add(group);
                }
            }
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'ha pogut llegir alguns grups. Motiu: " + e.getMessage());
        }
        return groups;
    }

    @Override
    public List<Group> readAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);
            criteriaQuery.select(root);

            TypedQuery<Group> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("[❌] ERROR! No s'han pogut llegir tots els grups. Motiu: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateEntityById(Group entity, Integer primaryKey) {
        try {
            entityTransaction.begin();

            Group existingGroup = entityManager.find(Group.class, primaryKey);
            if (existingGroup != null) {
                existingGroup.setDescription(entity.getDescription());
                existingGroup.setClassroom(entity.getClassroom());

                entityTransaction.commit();
                System.out.println("[✅] S'ha actualitzat el grup amb id (CODGRUPO) " + primaryKey + " correctament.");
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap grup amb id (CODGRUPO) " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut actualitzar el grup amb id (CODGRUPO) " + primaryKey + ".");
        }
    }

    @Override
    public void updateEntitiesById(List<Group> entities, List<Integer> primaryKeys) {
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

            Group group = entityManager.find(Group.class, primaryKey);

            if (group != null) {
                if (!getStudentsFromThisGroup(group).isEmpty()) {
                    entityTransaction.rollback();
                    System.out.println("[❌] ERROR! No es pot eliminar el grup amb id (CODGRUPO) " + primaryKey + " perquè té alumnes assignats.");
                } else {
                    entityManager.remove(group);
                    entityTransaction.commit();
                    System.out.println("[✅] S'ha eliminat el grup amb id (CODGRUPO) " + primaryKey + " correctament.");
                }
            } else {
                entityTransaction.rollback();
                System.out.println("[❌] ERROR! No s'ha trobat cap grup amb id (CODGRUPO) " + primaryKey + ".");
            }
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'ha pogut eliminar el grup amb id (CODGRUPO) " + primaryKey + ".");
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

            entityManager.createQuery("DELETE FROM Group").executeUpdate();

            entityTransaction.commit();
            System.out.println("[✅] S'han eliminat tots els registres de grups.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[❌] ERROR! No s'han pogut eliminar tots els registres de grups.");
        }
    }

    @Override
    public boolean exists(Integer primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(g) FROM Group g WHERE g.groupCode = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència del grup.");
            return false;
        }
    }

    @Override
    public boolean existsAtLeastOneEntry() {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(g) FROM Group g");

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[❌] No s'ha pogut verificar la existència del grup.");
            return false;
        }
    }

    public List<Student> getStudentsFromThisGroup(Group entity) {
        try {
            Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.group = :group");
            query.setParameter("group", entity);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
