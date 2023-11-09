/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pacient;
import java.util.ArrayList;

/**
 *
 * @author Albert
 */
public class PacientDAO {
    
    //Atribut conexió de PacientDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public PacientDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de pacients en la base de dades.
    
    //Mètode per a obtindre un pacient pel seu identificador des de la base de dades.
    public Pacient obtindrePacientPerId(String dniPacient) {
    Pacient pacient = null;
    String consulta = "SELECT * FROM pacient WHERE dni LIKE ?";
    
    try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
        ps.setString(1, dniPacient);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            String idPacient = rs.getString("DNI");
            String nom = rs.getString("Nom");
            String cognoms = rs.getString("Cognoms");
            
            pacient = new Pacient(idPacient, nom, cognoms);
        }
    } catch (SQLException e) {
        System.out.println("ERROR! No s'ha pogut obtindre un pacient amb eixe ID.");
    }
    
    return pacient;
}
    
    //Mètode boolean que ens torna vertader o fals si ja existeix un pacient amb un dni determinat.
    public boolean existeixPacient(String dni) {
        try {
            String consulta = "SELECT DNI FORM pacient WHERE DNI LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    //Mètode per a insertar un pacient a la base de dades.
    public void insertarPacient(Pacient pacient) {
        if (!existeixPacient(pacient.getDni())) {
            try {
                String consulta = "INSERT INTO pacient (DNI, Nom, Cognoms) VALUES (?, ?, ?)";
                PreparedStatement ps = conexio.prepareStatement(consulta);

                ps.setString(1, pacient.getDni());
                ps.setString(2, pacient.getNom());
                ps.setString(3, pacient.getCognoms());
                ps.executeUpdate();
                
                System.out.println("");
                System.out.println("S'ha insertat el nou pacient amb èxit.");
            } catch (SQLException e) {
                System.out.println("ERROR! No s'ha pogut insertar el nou pacient.");
            }
        } else {
            System.out.println("Ja existeix un pacient amb eixe DNI.");
        }
    }
    
    //Mètode per a eliminar un pacient de la base de dades.
    public void eliminarPacient(String dni) {
        try {
            String consulta = "DELETE FROM pacient WHERE DNI LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            
            ps.setString(1, dni);
            ps.executeUpdate();
            
            System.out.println("");
            System.out.println("S'ha eliminat el pacient amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut eliminar el pacient.");
        }
    }
    
    //Mètode per a actualitzar un pacient de la base de dades.
    public void actualitzarPacient(String dni, String nom, String cognoms) {
        try {
            String consulta = "UPDATE pacient SET Nom = ?, Cognoms = ? WHERE DNI LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);

            ps.setString(1, nom);
            ps.setString(2, cognoms);
            ps.setString(3, dni);
            ps.executeUpdate();
            System.out.println(""); 
            System.out.println("S'ha actualitzat el pacient amb èxit.");
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut actualitzar el pacient.");
        }
    }
    
    //Mètode que obté tots els pacients de la base de dades i els retorna en una colecció de Java.
    public ArrayList<Pacient> obtenirTotsPacients() {
        ArrayList<Pacient> pacients = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM pacient";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String dniPacient = rs.getString("DNI");
                String nom = rs.getString("Nom");
                String cognoms = rs.getString("Cognoms");

                Pacient pacient = new Pacient(dniPacient, nom, cognoms);
                pacients.add(pacient);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'han pogut obtenir els pacients.");
        }
        return pacients;
    }
}
