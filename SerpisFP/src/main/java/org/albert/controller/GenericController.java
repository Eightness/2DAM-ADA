package org.albert.controller;

import org.albert.SerpisFPApplication;
import org.albert.providers.ControllerManager;

import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * Class GenericController. Basic database operations.
 */
public class GenericController extends ControllerManager {
    //Methods.
    public ArrayList<Integer> getItemsCountFromDatabase() {
        return genericDAO.getItemsCountFromDatabase();
    }

    public void deleteAllItemsFromDatabase() {
        genericDAO.deleteAllItemsFromDatabase();
    }

    public void loadDemoData() {
        genericDAO.loadDemoData();
    }

    public boolean existsAtLeastOneEntityOf(String entityName) {
        return genericDAO.existsAtLeastOneEntityOf(entityName);
    }
}
