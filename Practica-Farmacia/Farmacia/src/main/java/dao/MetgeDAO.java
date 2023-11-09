/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Metge;

/**
 *
 * @author Albert
 */
public class MetgeDAO {
    
    //Atribut conexió de MetgeDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public MetgeDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de metges en la base de dades.
    
    //Mètode per a obtindre un metge pel seu identificador des de la base de dades.
    public Metge obtindreMetgePerId(int numColegiat) {
        Metge metge = null;
        String consulta = "SELECT * FROM metge WHERE NumColegiat = ?";
        
        try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
            ps.setInt(1, numColegiat);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                int idMetge = rs.getInt("NumColegiat");
                String especialitat = rs.getString("Especialitat");
                String nom = rs.getString("Nom");
                String cognoms = rs.getString("Cognoms");
                
                metge = new Metge(idMetge, especialitat, nom, cognoms);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut obtindre un metge amb eixe ID.");
        }
        
        return metge;
    }
    
    //Mètode boolean que ens torna vertader o fals si ja existeix un metge amb un nombre de colegiat determinat.
    public boolean existeixMetge(int numColegiat) {
        try {
            String consulta = "SELECT NumColegiat FORM metge WHERE NumColegiat = ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setInt(1, numColegiat);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    //Mètode per a insertar un metge a la base de dades.
    public void insertarMetge(Metge metge) {
        if (!existeixMetge(metge.getNumColegiat())) {
            try {
                String consulta = "INSERT INTO metge (NumColegiat, Especialitat, Nom, Cognoms) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexio.prepareStatement(consulta);

                ps.setInt(1, metge.getNumColegiat());
                ps.setString(2, metge.getEspecialitat());
                ps.setString(3, metge.getNom());
                ps.setString(4, metge.getCognoms());
                ps.executeUpdate();
                
                System.out.println("");
                System.out.println("S'ha insertat el nou metge amb èxit.");
            } catch (SQLException e) {
                System.out.println("ERROR! No s'ha pogut insertar el nou metge.");
            }
        } else {
            System.out.println("Ja existeix un metge amb eixe nombre de colegiat.");
        }
    }
    
    //Mètode per a eliminar un metge de la base de dades.
    public void eliminarMetge(int numColegiat) {
        try {
            String consulta = "DELETE FROM metge WHERE NumColegiat = ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            
            ps.setInt(1, numColegiat);
            ps.executeUpdate();
            
            System.out.println("");
            System.out.println("S'ha eliminat el metge amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut eliminar el metge.");
        }
    }
    
    //Mètode per a actualitzar un metge de la base de dades.
    public void actualitzarMetge(int numColegiat, String especialitat, String nom, String cognoms) {
        try {
            String consulta = "UPDATE metge SET Especialitat = ?, Nom = ?, Cognoms = ? WHERE NumColegiat = ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);

            ps.setString(1, especialitat);
            ps.setString(2, nom);
            ps.setString(3, cognoms);
            ps.setInt(4, numColegiat);
            ps.executeUpdate();
            System.out.println("");
            System.out.println("S'ha actualitzat el metge amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut actualitzar el metge.");
        }
    }
    
    //Mètode que obté tots els metges de la base de dades i els retorna en una colecció de Java.
    public ArrayList<Metge> obtenirTotsMetges() {
        ArrayList<Metge> metges = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM metge";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int numColegiat = rs.getInt("NumColegiat");
                String especialitat = rs.getString("Especialitat");
                String nom = rs.getString("Nom");
                String cognoms = rs.getString("Cognoms");

                Metge metge = new Metge(numColegiat, especialitat, nom, cognoms);
                metges.add(metge);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'han pogut obtenir els metges.");
        }
        return metges;
    }

}
