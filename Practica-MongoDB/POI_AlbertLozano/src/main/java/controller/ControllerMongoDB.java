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
        return daoMongoDB.DAOgetCurrentItems();
    }

    public void insertItem(ModelPOI createdPOI) {
        daoMongoDB.DAOinsertItem(createdPOI);
    }

    public void insertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        daoMongoDB.DAOinsertVariousItems(createdPOIs);
    }

    public void getAllItems(Boolean ordered) {
        viewPOI.showPOIs(daoMongoDB.DAOgetAllItems(ordered));
    }

    public void getItemById(int poid) {
        viewPOI.showPOI(daoMongoDB.DAOgetItemById(poid));
    }

    public void getItemsById(ArrayList<Integer> poids) {
        viewPOI.showPOIs(daoMongoDB.DAOgetItemsById(poids));
    }

    public void deleteAllItems() {
        daoMongoDB.DAOdeleteAllItems();
    }

    public void deleteItemById(int poid) {
        daoMongoDB.DAOdeleteItemById(poid);
    }

    public void deleteItemsById(ArrayList<Integer> poids) {
        daoMongoDB.DAOdeleteItemsById(poids);
    }

    public void synchronizeDatabase() {
        daoMySQL.DAOsynchronizeDatabase(daoMongoDB.DAOgetAllItems(true));
    }

    public void importItems() {

    }

    public void insertDefaultCollection() {
        daoMongoDB.DAOinsertDefaultCollection();
    }
}
