package org.albert.providers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Class DAOManager. Contains all manager's and resources needed to work with DAOs.
 */
public abstract class DAOManager {
    //Attributes.
    protected final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SerpisFP");
    protected final EntityManager entityManager = entityManagerFactory.createEntityManager();
    protected final EntityTransaction entityTransaction = entityManager.getTransaction();
}
