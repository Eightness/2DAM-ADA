/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.POIDAOMongoDB;
import model.POI;
import view.POIView;

import java.util.ArrayList;

/**
 *
 * @author alblozbla
 */
public class POIControllerMongoDB {
    //Attributes
    private final POIDAOMongoDB poidaoMongoDB = new POIDAOMongoDB();
    private final POIView poiView = new POIView();
    
    //Getters and Setters
    
    //Constructor
    
    //Methods
    public int getCurrentItems() {
        return 0;
    }

    public void insertItem(POI createdPOI) {

    }

    public void insertVariousItems(ArrayList<POI> createdPOIs) {

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
