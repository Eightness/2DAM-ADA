package com.albert.CRUDCar.application.service;

import java.util.List;

/**
 * Interface GenericService. This interface contains methods that define most business' logic.
 */
public interface GenericService<Entity> {
    //Get methods.
    Entity getEntityById(int id);
    List<Entity> getEntitiesByIds(List<Integer> ids);
    List<Entity> getAllEntities(int pageNumber, int pageSize);

    //Post methods.
    Entity addEntity(Entity entity);
    List<Entity> addEntities(List<Entity> entities);

    //Put methods.
    Entity updateEntityById(int id, Entity entity);
    List<Entity> updateEntitiesByIds(List<Integer> ids, List<Entity> entities);

    //Patch methods.
    Entity modifyEntityById(int id, Entity entity);
    List<Entity> modifyEntitiesByIds(List<Integer> ids, List<Entity> entities);

    //Delete methods.
    void deleteEntityById(int id);
    void deleteEntitiesByIds(List<Integer> ids);
    void deleteAllEntities();
}