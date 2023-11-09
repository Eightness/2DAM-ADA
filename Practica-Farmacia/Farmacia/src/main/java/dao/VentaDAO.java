/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Venta;

/**
 *
 * @author Albert
 */
public class VentaDAO {
    
    //Atribut conexió de VentaDAO.
    private Connection conexio;
    
    //Constructor que rep la conexió a la base de dades.
    public VentaDAO(Connection conexio) {
        this.conexio = conexio;
    }
    
    //Mètodes relacionats amb la gestió de ventes en la base de dades.
    
    //Mètode per a obtindre una venta pels seus identificadors des de la base de dades.
    public Venta obtindreVentaPerId(String nomComercialMedicament, String cifFarmacia) {
        Venta venta = null;
        String consulta = "SELECT * FROM venta WHERE NomComercialMedicament LIKE '?' AND CIFFarmacia LIKE '?'";
        
        try (PreparedStatement ps = conexio.prepareStatement(consulta)) {
            ps.setString(1, nomComercialMedicament);
            ps.setString(2, cifFarmacia);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String idMedicament = rs.getString("NomComercialMedicament");
                String idFarmacia = rs.getString("CIFFarmacia");
                Date data = rs.getDate("Data");
                Double preu = rs.getDouble("Preu");
                
                venta = new Venta(idMedicament, idFarmacia, data, preu);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'ha pogut obtindre una venta amb eixos IDs.");
        }
        
        return venta;
    }
    
    //Mètode per a eliminar tots els medicaments venuts per auna farmàcia determinada.
    public void eliminarVentesPerFarmacia(String cifFarmacia) {
        try {
            String consulta = "DELETE FROM venta WHERE CIFFarmacia LIKE ?";
            PreparedStatement ps = conexio.prepareStatement(consulta);
            ps.setString(1, cifFarmacia);

            int filasAfectades = ps.executeUpdate();

            if (filasAfectades > 0) {
                System.out.println("Eliminat/s " + filasAfectades + " registre/s de venta per a la farmàcia amb CIF: " + cifFarmacia);
            } else {
                System.out.println("No s'han trobat registres de venta per a la farmàcia amb CIF: " + cifFarmacia);
            }
        } catch (SQLException e) {
            System.out.println("ERROR! No s'han pogut eliminar els registres de venda.");
        }
    }
}
