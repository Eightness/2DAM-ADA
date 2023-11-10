/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.POIDAOMongoDB;
import dao.POIDAOMySQL;
import model.POI;
import view.POIView;

import java.util.ArrayList;

/**
 *
 * @author alblozbla
 */
public class POIControllerMySQL {
    //Attributes
    private final POIDAOMySQL poidaoMySQL = new POIDAOMySQL();
    private final POIView poiView = new POIView();
    
    //Methods
    public int getCurrentItems() {
        return 0;
    }

    public void insertVariousItems(ArrayList<POI> createdPOIs) {
        poidaoMySQL.DAOinsertVariousItems(createdPOIs);
    }

    public void getAllItems() {
        poiView.showPOIs(poidaoMySQL.DAOgetAllItems());
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
