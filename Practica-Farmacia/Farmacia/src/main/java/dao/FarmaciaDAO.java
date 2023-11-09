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
import model.Farmacia;

/**
 *
 * @author Albert
 */
public class FarmaciaDAO {
    
    //Atribut conexió de FarmaciaDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public FarmaciaDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de farmacies en la base de dades.
    
    //Mètode per a obtindre una farmacia pel seu identificador des de la base de dades.
    public Farmacia obtindreFarmaciaPerId(String cif) {
        Farmacia farmacia = null;
        String consulta = "SELECT * FROM farmacia WHERE CIF LIKE ?";
        
        try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
            ps.setString(1, cif);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String idFarmacia = rs.getString("CIF");
                String adressa = rs.getString("Adreça");
                
                farmacia = new Farmacia(idFarmacia, adressa);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut obtindre una farmacia amb eixe ID.");
        }
        
        return farmacia;
    }
    
    //Mètode boolean que ens torna vertader o fals si ja existeix una farmacia amb un cif determinat.
    public boolean existeixFarmacia(String cif) {
        try {
            String consulta = "SELECT CIF FROM farmacia WHERE CIF LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setString(1, cif);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    //Mètode per a insertar una farmacia a la base de dades.
    public void insertarFarmacia(Farmacia farmacia) {
        if (!existeixFarmacia(farmacia.getCif())) {
            try {
                String consulta = "INSERT INTO farmacia (CIF, Adreça) VALUES (?, ?)";
                PreparedStatement ps = conexio.prepareStatement(consulta);
            
                ps.setString(1, farmacia.getCif());
                ps.setString(2, farmacia.getAdressa());
                ps.executeUpdate();
                
                System.out.println("");
                System.out.println("S'ha insertat la nova farmacia amb èxit.");
            } catch (SQLException e) {
                System.out.println("ERROR! No s'ha pogut insertar la nova farmacia.");
            }
            
        } else {
            System.out.println("Ja existeix una farmacia amb eixe CIF.");
        }
    }
    
    //Mètode per a eliminar una farmacia de la base de dades.
    public void eliminarFarmacia(String cif) {
        try {
            String consulta = "DELETE FROM farmacia WHERE CIF LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            
            ps.setString(1, cif);
            ps.executeUpdate();
            
            System.out.println("");
            System.out.println("S'ha eliminat la farmacia amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut eliminar la farmacia.");
        }
    }
    
    //Mètode per a actualitzar una farmacia de la base de dades.
    public void actualitzarFarmacia(String cif, String adressa) {
        try {
            String consulta = "UPDATE farmacia SET Adreça = ? WHERE CIF LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);

            ps.setString(1, adressa);
            ps.setString(2, cif);
            ps.executeUpdate();
            System.out.println("");
            System.out.println("S'ha actualitzat la farmacia amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut actualitzar la farmacia.");
        }
    }
    
    //Mètode que obté totes les farmacies de la base de dades i els retorna en una colecció de Java.
    public ArrayList<Farmacia> obtenirTotesFarmacies() {
        ArrayList<Farmacia> farmacies = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM farmacia";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cifFarmacia = rs.getString("CIF");
                String adressa = rs.getString("Adreça");

                Farmacia farmacia = new Farmacia(cifFarmacia, adressa);
                farmacies.add(farmacia);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'han pogut obtenir les farmacies.");
        }
        return farmacies;
    }
    
}
