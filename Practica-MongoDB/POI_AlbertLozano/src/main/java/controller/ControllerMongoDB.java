/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DAOMongoDB;
import dao.DAOMySQL;
import model.ModelPOI;
import view.ViewPOI;
import java.util.ArrayList;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class ControllerMongoDB {
    //Attributes
    private final DAOMongoDB daoMongoDB = new DAOMongoDB();
    private final ViewPOI viewPOI = new ViewPOI();
    private final DAOMySQL daoMySQL = new DAOMySQL();
    
    //Methods
    public int getCurrentItems() {
        return 0;
    }

    public void insertItem(ModelPOI createdPOI) {

    }

    public void insertVariousItems(ArrayList<ModelPOI> createdPOIs) {

    }

    public void getAllItems() {

    }

    public void getAllItemsOrderedById() {

    }

    public void getItemById(int poid) {

    }

    public void getItemsById(ArrayList<Integer> poids) {

    }

    public void deleteAllItems() {

    }

    public void deleteItemById(int poid) {

    }

    public void deleteItemsById(ArrayList<Integer> poids) {

    }

    public void synchronizeDatabase() {

    }

    public void importItems() {

    }
}
