package org.albert.controller;

import org.albert.model.Student;
import org.albert.providers.ControllerManager;

import java.util.ArrayList;
import java.util.List;

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
        return genericDAO.existsAtLeastOneEntity(entityName);
    }
}
