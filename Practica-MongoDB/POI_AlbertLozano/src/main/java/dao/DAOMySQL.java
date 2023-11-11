/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import model.ModelPOI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static main.ConnectionMySQL.mySQLConnection;

/**
 *
 * @author alblozbla
 */
public class DAOMySQL {
    //Attributes
    
    //Methods
    private boolean DAOexistsPOI(int poid) {
        try {
            String query = "SELECT * FROM pois_al15 WHERE poid = ?";
            PreparedStatement ps = mySQLConnection.prepareStatement(query);
            ps.setInt(1, poid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public int DAOgetCurrentItems() {
        int currentItems = 0;
        String query = "SELECT COUNT(*) AS total FROM pois_al15";
        
        try {
            PreparedStatement ps = mySQLConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                currentItems = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("[!] No s'ha pogut aconseguir el nombre total d'items actuals.");
        }
        
        return currentItems;
    }

    public void DAOinsertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        int numInserted = 0;
        
        for (int i = 0; i < createdPOIs.size(); i++) {
            if (DAOexistsPOI(createdPOIs.get(i).getPoid())) {
                System.out.println("Ja existeix un punt d'interés amb el poid " + createdPOIs.get(i).getPoid());
                continue;
            }
            try {
                String query = "INSERT INTO pois_al15 (poid, latitude, longitude, country, city, description, updated) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = mySQLConnection.prepareStatement(query);

                ps.setInt(1, createdPOIs.get(i).getPoid());
                ps.setDouble(2, createdPOIs.get(i).getLatitude());
                ps.setDouble(3, createdPOIs.get(i).getLongitude());
                ps.setString(4, createdPOIs.get(i).getCountry());
                ps.setString(5, createdPOIs.get(i).getCity());
                ps.setString(6, createdPOIs.get(i).getDescription());
                ps.setDate(7, createdPOIs.get(i).getUpdated());
                ps.executeUpdate();

                numInserted++;
            } catch (SQLException e) {
                System.out.println();
                System.out.println("[!] Ja existeix un punt d'interés amb el poid " + createdPOIs.get(i).getPoid() + ".");
            }
        }
        
        System.out.println();
        System.out.println("S'ha/n insertat " + numInserted + " punt/s d'interés.");
    }

    public ArrayList<ModelPOI> DAOgetAllItems() {
        ArrayList<ModelPOI> allItems = new ArrayList<>();
        String query = "SELECT * FROM pois_al15";
        
        try {
            PreparedStatement ps = mySQLConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
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
            System.out.println("[!] No s'han pogut aconseguir els items de la base de dades.");
        }
        
        return allItems;
    }

    public void DAOgetAllItemsOrderedById() {

    }

    public void DAOgetItemById(int poid) {

    }

    public void DAOgetItemsById(ArrayList<Integer> poids) {
        
    }

    public void DAOdeleteAllItems() {
        String query = "DELETE FROM pois_al15";
        
        if (DAOgetCurrentItems() == 0) {
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return;
        }
        
        try {
            PreparedStatement ps = mySQLConnection.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[!] No s'han pogut esborrar els items de la base de dades.");
        }
    }

    public void DAOdeleteItemById(int poid) {

    }

    public void DAOdeleteItemsById(ArrayList<Integer> poids) {

    }

    public void DAOsynchronizeDatabase() {

    }
}
