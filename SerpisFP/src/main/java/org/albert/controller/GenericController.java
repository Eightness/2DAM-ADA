package org.albert.controller;

import org.albert.providers.ControllerManager;

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
}
