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
 * @author alblozbla
 */
public class ControllerMySQL {
    //Attributes
    private final DAOMySQL poiDAOMySQL = new DAOMySQL();
    private final ViewPOI poiView = new ViewPOI();
    
    //Methods
    public int getCurrentItems() {
        return poiDAOMySQL.DAOgetCurrentItems();
    }

    public void insertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        poiDAOMySQL.DAOinsertVariousItems(createdPOIs);
    }

    public void getAllItems() {
        poiView.showPOIs(poiDAOMySQL.DAOgetAllItems());
    }

    public void getAllItemsOrderedById() {

    }

    public void getItemById(int poid) {

    }

    public void getItemsById(ArrayList<Integer> poids) {
          
    }

    public void deleteAllItems() {
        poiDAOMySQL.DAOdeleteAllItems();
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
