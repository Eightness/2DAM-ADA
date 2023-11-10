/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.POI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static main.MySQLConnection.mySQLConnection;

/**
 *
 * @author alblozbla
 */
public class POIDAOMySQL {
    //Attributes
    
    //Methods
    private boolean existsPOI(int poid) {
        try {
            String consulta = "SELECT poid FORM pois_al15 WHERE poid = ?";
            PreparedStatement ps = mySQLConnection.prepareStatement(consulta);
            ps.setInt(1, poid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public int DAOgetCurrentItems() {
        return 0;
    }

    public void DAOinsertVariousItems(ArrayList<POI> createdPOIs) {
        int numInserted = 0;
        for (int i = 0; i < createdPOIs.size(); i++) {
            if (!existsPOI(createdPOIs.get(i).getPoid())) {
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
            } else {
                System.out.println("Ja existeix un punt d'interés amb el poid " + createdPOIs.get(i).getPoid());
            }
        }
        System.out.println();
        System.out.println("S'ha/n insertat " + numInserted + " punt/s d'interés.");
    }

    public void DAOgetAllItems() {

    }

    public void DAOgetAllItemsOrderedById() {

    }

    public void DAOgetItemById(int poid) {

    }

    public void DAOgetItemsById(ArrayList<Integer> poids) {

    }

    public void DAOdeleteAllItems() {

    }

    public void DAOdeleteItemById(int poid) {

    }

    public void DAOdeleteItemsById(ArrayList<Integer> poids) {

    }

    public void DAOsynchronizeDatabase() {

    }
}
