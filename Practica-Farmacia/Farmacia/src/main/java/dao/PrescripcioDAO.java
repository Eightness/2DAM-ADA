/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import model.Prescripcio;

/**
 *
 * @author Albert
 */
public class PrescripcioDAO {
    
    //Atribut conexió de PrescripcioDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public PrescripcioDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de prescripcions en la base de dades.
    
    //Mètode per a obtindre una prescripció pels seus identificadors des de la base de dades.
    public Prescripcio obtindrePrescripcioPerId(int numColegiatMetge, String dniPacient, String nomComercialMedicament) {
        Prescripcio prescripcio = null;
        String consulta = "SELECT * FROM prescripcio WHERE NumColegiatMetge = ? AND DNIPacient LIKE '?' AND NomComercialMedicament LIKE '?'";
        
        try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
            ps.setInt(1, numColegiatMetge);
            ps.setString(2, dniPacient);
            ps.setString(3, nomComercialMedicament);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int idMetge = rs.getInt("NumColegiatMetge");
                String idPacient = rs.getString("DNIPacient");
                String idMedicament = rs.getString("NomComercialMedicament");
                Date data = rs.getDate("Data");
                int quantitat = rs.getInt("Quantitat");
                
                prescripcio = new Prescripcio(idMetge, idPacient, idMedicament, data, quantitat);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut obtindre una prescripció amb eixos IDs.");
        }
        
        return prescripcio;
    }
    
    //Mètode per a comprobar si existeix una prescripció en la base de dades, donat un nombre de colegiat, un dni de pacient i un nom comercial de medicament.
    public boolean existeixPrescripcio(int numColegiatMetge, String dniPacient, String nomComercialMedicament) {
        try {
            String consulta = "SELECT COUNT(*) FROM prescripcio WHERE NumColegiatMetge = ? AND DNIPacient = ? AND NomComercialMedicament = ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setInt(1, numColegiatMetge);
            ps.setString(2, dniPacient);
            ps.setString(3, nomComercialMedicament);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut comprovar si existeix la prescripció.");
        }

        return false;
    }
    
    //Mètode per a insertar una prescripció d'un medicament per a un pacient determinat.
    public void insertarPrescripcio(int numColegiatMetge, String dniPacient, String nomComercialMedicament, Date data, int quantitat) {
        if (!existeixPrescripcio(numColegiatMetge, dniPacient, nomComercialMedicament)) {
            try {
                String consulta = "INSERT INTO prescripcio (NumColegiatMetge, DNIPacient, NomComercialMedicament, Data, Quantitat) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = conexio.prepareStatement(consulta);

                ps.setInt(1, numColegiatMetge);
                ps.setString(2, dniPacient);
                ps.setString(3, nomComercialMedicament);
                ps.setTimestamp(4, new Timestamp(data.getTime()));
                ps.setInt(5, quantitat);
                ps.executeUpdate();
                
                System.out.println("");
                System.out.println("S'ha insertat la prescripció amb èxit.");
            } catch (SQLException e) {
                System.out.println("ERROR! No s'ha pogut insertar la prescripció.");
            }
        } else {
            System.out.println("Ja existeix una prescripció del mateix medicament, realitzada pel mateix metge, al mateix pacient");
            System.out.println("En esta farmacia no es permet que un mateix metge realitze la prescripció d'un medicament a un mateix pacient més d'una vegada.");
        }
        
    }

    
}
