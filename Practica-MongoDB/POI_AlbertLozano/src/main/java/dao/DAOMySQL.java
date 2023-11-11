/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ModelPOI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static main.ConnectionMySQL.mySQLConnection;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class DAOMySQL {
    //Attributes
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;
    
    //Getters and Setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    //Methods
    
    //CHECKS
    //--------------------------------------------------------------------------
    private boolean DAOexistsPOI(int poid) {
        try {
            setQuery("SELECT * FROM pois_al15 WHERE poid = ?");
            ps = mySQLConnection.prepareStatement(query);
            ps.setInt(1, poid);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public int DAOgetCurrentItems() {
        int currentItems = 0;
        setQuery("SELECT COUNT(*) AS total FROM pois_al15");
        
        try {
            ps = mySQLConnection.prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                currentItems = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("[!] No s'ha pogut aconseguir el nombre total d'items actuals.");
        }
        
        return currentItems;
    }
    
    //CRUD
    //--------------------------------------------------------------------------
    
    //CREATE
    //--------------------------------------------------------------------------
    
    public boolean DAOinsertItem(ModelPOI createdPOI) {
        if (DAOexistsPOI(createdPOI.getPoid())) {
            System.out.println();
            System.out.println("Ja existeix un punt d'interés amb el poid " + createdPOI.getPoid());
            return false;
        }
        try {
            setQuery("INSERT INTO pois_al15 (poid, latitude, longitude, country, city, description, updated) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps = mySQLConnection.prepareStatement(query);

            ps.setInt(1, createdPOI.getPoid());
            ps.setDouble(2, createdPOI.getLatitude());
            ps.setDouble(3, createdPOI.getLongitude());
            ps.setString(4, createdPOI.getCountry());
            ps.setString(5, createdPOI.getCity());
            ps.setString(6, createdPOI.getDescription());
            ps.setDate(7, createdPOI.getUpdated());
            
            ps.executeUpdate();
            
            System.out.println();
            System.out.println("S'ha inserit el nou item amb èxit.");
            
            return true;
        } catch (SQLException e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut inserir l'item.");
            return false;
        }
    }

    public void DAOinsertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        int numInserted = 0;
        
        for (int i = 0; i < createdPOIs.size(); i++) {
            if(DAOinsertItem(createdPOIs.get(i))) {
                numInserted++;
            }
        }
        
        System.out.println();
        System.out.println("S'ha/n insertat " + numInserted + " punt/s d'interés.");
    }
    
    //READ
    //--------------------------------------------------------------------------

    public ArrayList<ModelPOI> DAOgetAllItems(Boolean ordered) {
        ArrayList<ModelPOI> allItems = new ArrayList<>();
        
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return allItems;
        }
        
        try {
            if (ordered) {
                setQuery("SELECT * FROM pois_al15 ORDER BY poid");
            } else {
                setQuery("SELECT * FROM pois_al15");
            }
            ps = mySQLConnection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                ModelPOI poi = new ModelPOI();
                poi.setPoid(rs.getInt("poid"));
                poi.setLatitude(rs.getDouble("latitude"));
                poi.setLongitude(rs.getDouble("latitude"));
                poi.setCountry(rs.getString("country"));
                poi.setCity(rs.getString("city"));
                poi.setDescription(rs.getString("description"));
                poi.setUpdated(rs.getDate("updated"));
                
                allItems.add(poi);
            }
            
        } catch (SQLException e) {
            System.out.println();
            System.out.println("[!] No s'han pogut aconseguir els items de la base de dades.");
        }
        
        return allItems;
    }

    public ModelPOI DAOgetItemById(int poid) {
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return null;
        } else if (!DAOexistsPOI(poid)) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha cap item amb Id " + poid + ".");
            return null;
        }
        
        try {
            ModelPOI poi = new ModelPOI();
            setQuery("SELECT * FROM pois_al15 WHERE poid = ?");
            ps = mySQLConnection.prepareStatement(query);
            ps.setInt(1, poid);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                poi.setPoid(rs.getInt("poid"));
                poi.setLatitude(rs.getDouble("latitude"));
                poi.setLongitude(rs.getDouble("longitude"));
                poi.setCountry(rs.getString("country"));
                poi.setCity(rs.getString("city"));
                poi.setDescription(rs.getString("description"));
                poi.setUpdated(rs.getDate("updated"));
            }
            
            return poi;
        } catch (SQLException e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut aconseguir l'item de la base de dades.");
        }
        
        return null;
    }

    public ArrayList<ModelPOI> DAOgetItemsById(ArrayList<Integer> poids) {
        ArrayList<ModelPOI> itemsById = new ArrayList<>();
        
        for (int i = 0; i < poids.size(); i++) {
            ModelPOI poi = DAOgetItemById(poids.get(i));
            if (poi != null) {
                itemsById.add(poi);
            }
        }
        
        return itemsById;
    }
    
    //DELETE
    //--------------------------------------------------------------------------

    public void DAOdeleteAllItems() {
        int numDeleted = DAOgetCurrentItems();
        
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return;
        }
        
        try {
            setQuery("DELETE FROM pois_a15");
            ps = mySQLConnection.prepareStatement(query);
            ps.executeUpdate();
            System.out.println();
            System.out.println("S'ha/n esborrat " + numDeleted + " item/s de la base de dades.");
        } catch (SQLException e) {
            System.out.println();
            System.out.println("[!] No s'han pogut esborrar els items de la base de dades.");
        }
    }

    public boolean DAOdeleteItemById(int poid) {
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return false;
        } else if (!DAOexistsPOI(poid)) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha cap item amb Id " + poid + ".");
            return false;
        }
        
        try {
            setQuery("DELETE FROM pois_al15 WHERE poid = ?");
            ps = mySQLConnection.prepareStatement(query);
            ps.setInt(1, poid);
            ps.executeUpdate();
            
            System.out.println();
            System.out.println("S'ha esborrat l'item amb Id " + poid + ".");
            return true;
        } catch (SQLException e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut esborrar l'item de la base de dades amb Id " + poid + "." );
            return false;
        }
    }

    public void DAOdeleteItemsById(ArrayList<Integer> poids) {
        int numDeleted = poids.size();
        
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return;
        }
        
        for (int i = 0; i < poids.size(); i++) {
            if(!DAOdeleteItemById(poids.get(i))) {
                numDeleted--;
            }
        }
        
        System.out.println();
        System.out.println("S'han esborrat un total de " + numDeleted + " d'items.");
    }
    
    //SYNCHRONIZE
    //--------------------------------------------------------------------------

    public void DAOsynchronizeDatabase() {
        
    }
}
