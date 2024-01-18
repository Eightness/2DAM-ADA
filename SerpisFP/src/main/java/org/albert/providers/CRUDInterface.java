package org.albert.providers;

import java.util.List;

/**
 * Interface CRUDInterface. Contains all CRUD methods needed to work with different entities.
 * @param <E> Entity
 * @param <PK> Primary Key
 */
public interface CRUDInterface<E, PK> {
    //Create methods.
    void createEntity(E entity);
    void createEntities(List<E> entities);

    //Read methods.
    E readEntityById(PK primaryKey);
    List<E> readEntitiesById(List<PK> primaryKeys);
    List<E> readAllEntities();

    //Update methods.
    void updateEntityById(E entity, PK primaryKey);
    void updateEntitiesById(List<E> entities, List<PK> primaryKeys);

    //Delete methods.
    void deleteEntityById(PK primaryKey);
    void deleteEntitiesById(List<PK> primaryKeys);
    void deleteAllEntities();

    //Useful methods.
    boolean exists(PK primaryKey);
}
