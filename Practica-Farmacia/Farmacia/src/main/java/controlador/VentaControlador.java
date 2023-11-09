/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Venta;
import dao.VentaDAO;
import vista.VentaVista;

/**
 *
 * @author Albert
 */
public class VentaControlador {
    
    //Atributs de la classe VentaControlador.
    private VentaDAO ventaDAO;
    private VentaVista ventaVista;
    
    //Constructor.
    public VentaControlador(VentaDAO ventaDAO, VentaVista ventaVista) {
        this.ventaDAO = ventaDAO;
        this.ventaVista = ventaVista;
    }
    
    //Mètodes relacionats amb la gestió de ventes.
    
    //Mètode per a obtindre i mostrar informació d'una venta.
    public void mostrarVenta(String idMedicament, String idFarmacia) {
        Venta venta = ventaDAO.obtindreVentaPerId(idMedicament, idFarmacia);
        ventaVista.mostrarInformacioVenta(venta);
    }
    
    //Mètode per a eliminar les ventes de medicaments de una determinada farmacia.
    public void eliminarVentesPerFarmacia(String cifFarmacia) {
        ventaDAO.eliminarVentesPerFarmacia(cifFarmacia);
    }
    
}
