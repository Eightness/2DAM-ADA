/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import model.Venta;

/**
 *
 * @author Albert
 */
public class VentaVista {
    
    //Mètode per a mostrar informació d'una venta en la consola.
    public void mostrarInformacioVenta(Venta venta) {
        System.out.println("");
        System.out.println("Nom comercial del medicament: " + venta.getNomComercialMedicament());
        System.out.println("CIF de la farmacia: " + venta.getCifFarmacia());
        System.out.println("Data: " + venta.getData());
        System.out.println("Preu: " + venta.getPreu());
        System.out.println("");
    }
    
}
