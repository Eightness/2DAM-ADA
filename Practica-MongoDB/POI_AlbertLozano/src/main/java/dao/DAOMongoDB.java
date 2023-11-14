/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.ModelPOI;
import static main.ConnectionMongoDB.mongoDBConnection;
import static main.ConnectionMongoDB.mongoDBDatabase;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class DAOMongoDB {
    //Attributes
    
    //Getters and Setters
    
    //Methods
    
    //CHECKS
    //--------------------------------------------------------------------------
    private boolean DAOexistsPOI(int poid) {
        return false;
    }

    public int DAOgetCurrentItems() {
        return 0;
    }
    
    //CRUD
    //--------------------------------------------------------------------------
    
    //CREATE
    //--------------------------------------------------------------------------
    
    public boolean DAOinsertItem(ModelPOI createdPOI) {
        return true;
    }

    public void DAOinsertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        
    }
    
    //READ
    //--------------------------------------------------------------------------

    public ArrayList<ModelPOI> DAOgetAllItems(Boolean ordered) {
        ArrayList<ModelPOI> allItems = new ArrayList<>();

        return allItems;
    }

    public ModelPOI DAOgetItemById(int poid) {
        return null;
    }

    public ArrayList<ModelPOI> DAOgetItemsById(ArrayList<Integer> poids) {
        ArrayList<ModelPOI> itemsById = new ArrayList<>();

        return itemsById;
    }
    
    //DELETE
    //--------------------------------------------------------------------------

    public void DAOdeleteAllItems() {
        
    }

    public boolean DAOdeleteItemById(int poid) {
        return true;
    }

    public void DAOdeleteItemsById(ArrayList<Integer> poids) {
        
    }
    
    //SYNCHRONIZE
    //--------------------------------------------------------------------------

    public void DAOsynchronizeDatabase(ArrayList<ModelPOI> mySQLPOIs) {
        
    }
    
    //DEFAULT
    //--------------------------------------------------------------------------
    
    public void DAOdefaultCollection() {
        
    }
}
