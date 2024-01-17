package org.albert.dao;

import org.albert.model.Group;
import org.albert.providers.CRUDInterface;
import org.albert.providers.DAOManager;

import javax.persistence.Query;
import java.util.List;

/**
 * Class GroupDAO. Contains Group's CRUD logic.
 */
public class GroupDAO extends DAOManager implements CRUDInterface<Group, Integer> {
    //Methods.
    @Override
    public void createEntity(Group entity) {
        if (exists(entity.getGroupCode())) {
            System.out.println("[!] ERROR! Ja existeix un grup amb eixe id (CODGRUPO)!");
            return;
        }
        try {
            entityTransaction.begin();

            entityManager.persist(entity);

            entityTransaction.commit();
            System.out.println("[!] S'ha afegit el nou grup correctament.");
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            System.out.println("[!] ERROR! No s'ha pogut afegir el nou grup correctament.");
        }
    }

    @Override
    public void createEntities(List<Group> entities) {

    }

    @Override
    public Group readEntityById(Integer primaryKey) {
        return null;
    }

    @Override
    public List<Group> readEntitiesById(List<Integer> primaryKeys) {
        return null;
    }

    @Override
    public void updateEntityById(Group entity, Integer primaryKey) {

    }

    @Override
    public void updateEntitiesById(List<Group> entities, List<Integer> primaryKeys) {

    }

    @Override
    public void deleteEntityById(Integer primaryKey) {

    }

    @Override
    public void deleteEntitiesById(List<Integer> primaryKeys) {

    }

    @Override
    public boolean exists(Integer primaryKey) {
        try {
            Query query = entityManager.createQuery("SELECT COUNT(g) FROM Group g WHERE g.groupCode = :primaryKey");
            query.setParameter("primaryKey", primaryKey);

            Long count = (Long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {
            System.out.println("[!] No s'ha pogut verificar la existència del grup.");
            return false;
        }
    }
}
