/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Medicament;
import java.util.ArrayList;

/**
 *
 * @author Albert
 */
public class MedicamentDAO {
    
    //Atribut conexió de MedicamentDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public MedicamentDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de medicaments de metges en la base de dades.
    
    //Mètode per a obtindre un medicament pel seu identificador des de la base de dades.
    public Medicament obtindreMedicamentPerId(String nomComercial) {
        Medicament medicament = null;
        String consulta = "SELECT * FROM medicament WHERE NomComercial LIKE ";
        
        try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
            ps.setString(1, nomComercial);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String idMedicament = rs.getString("NomComercial");
                String formula = rs.getString("Formula");
                
                medicament = new Medicament(idMedicament, formula);
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut obtindre un medicament amb eixe ID.");
        }
        
        return medicament;
    }
    
    //Mètode boolean que ens torna vertader o fals si ja existeix un medicament amb un nom determinat.
    public boolean existeixMedicament(String nom) {
        try {
            String consulta = "SELECT NomComercial FROM medicament WHERE NomComercial LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    //Mètode per a insertar un medicament a la base de dades.
    public void insertarMedicament(Medicament medicament) {
        if (!existeixMedicament(medicament.getNomComercial())) {
            try {
                String consulta = "INSERT INTO medicament (NomComercial, Formula) VALUES (?, ?)";
                PreparedStatement ps = conexio.prepareStatement(consulta);
            
                ps.setString(1, medicament.getNomComercial());
                ps.setString(2, medicament.getFormula());
                ps.executeUpdate();
                
                System.out.println("");
                System.out.println("S'ha insertat el nou medicament amb èxit.");
            } catch (SQLException e) {
                System.out.println("ERROR! No s'ha pogut insertar el nou medicament.");
            }
            
        } else {
            System.out.println("Ja existeix un medicament amb eixe nom comercial.");
        }
    }
    
    //Mètode per a eliminar un medicament de la base de dades.
    public void eliminarMedicament(String nomComercial) {
        try {
            String consulta = "DELETE FROM medicament WHERE NomComercial LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            
            ps.setString(1, nomComercial);
            ps.executeUpdate();
            
            System.out.println("");
            System.out.println("S'ha eliminat el medicament amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut eliminar el medicament.");
        }
    }
    
    //Mètode per a actualitzar un medicament de la base de dades.
    public void actualitzarMedicament(String nomComercial, String formula) {
        try {
            String consulta = "UPDATE medicament SET Formula = ? WHERE NomComercial LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);

            ps.setString(1, formula);
            ps.setString(2, nomComercial);
            ps.executeUpdate();
            System.out.println("");
            System.out.println("S'ha actualitzat el medicament amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut actualitzar el medicament.");
        }
    }
    
    //Mètode que obté tots els meidcaments de la base de dades i els retorna en una colecció de Java.
    public ArrayList<Medicament> obtenirTotsMedicaments() {
        ArrayList<Medicament> medicaments = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM medicament";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nomComercial = rs.getString("NomComercial");
                String formula = rs.getString("Formula");

                Medicament medicament = new Medicament(nomComercial, formula);
                medicaments.add(medicament);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'han pogut obtenir els medicaments.");
        }
        return medicaments;
    }
}
