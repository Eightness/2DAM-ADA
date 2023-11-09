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
import java.util.Date;
import model.Tractament;

/**
 *
 * @author Albert
 */
public class TractamentDAO {
    
    //Atribut conexió de MetgeDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public TractamentDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de tractaments en la base de dades.
    
    //Mètode per a obtindre un tractament pels seus identificadors des de la base de dades.
    public Tractament obtindreTractamentPerId(int numColegiatMetge, String dniPacient) {
        Tractament tractament = null;
        String consulta = "SELECT * FROM tractament WHERE NumColegiatMetge = ? AND DNIPacient = ?";
        
        try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
            ps.setInt(1, numColegiatMetge);
            ps.setString(2, dniPacient);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                int idMetge = rs.getInt("NumColegiatMetge");
                String idPacient = rs.getString("DNIPacient");
                Date data = rs.getDate("Data");
                
                tractament = new Tractament(idMetge, idPacient, data);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut obtindre un tractament amb eixos IDs.");
        }
        
        return tractament;
    }
    
    //Mètode que, donat el número de col·legiat d’un metge, lliste a tots els pacients que tracta i amb la data que els ha tractat.
    public ArrayList<Tractament> llistarPacientsPerMetge(int numColegiat) {
        ArrayList<Tractament> pacientsTractats = new ArrayList<>();

        try {
            String consulta = "SELECT DNIPacient, Data FROM tractament WHERE NumColegiatMetge = ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setInt(1, numColegiat);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String dniPacient = rs.getString("DNIPacient");
                Date dataTractament = rs.getTimestamp("Data");
                pacientsTractats.add(new Tractament(numColegiat, dniPacient, dataTractament));
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'han pogut obtenir les dades dels pacients tractats pel metge.");
        }

        return pacientsTractats;
    }
    
}
