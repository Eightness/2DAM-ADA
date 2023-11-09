/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.ArrayList;
import model.Farmacia;

/**
 *
 * @author Albert
 */
public class FarmaciaVista {
    
    //Mètode per a mostrar informació d'una farmacia en la consola.
    public void mostrarInformacioFarmacia(Farmacia farmacia) {
        System.out.println("");
        System.out.println("CIF: " + farmacia.getCif());
        System.out.println("Adreça: " + farmacia.getAdressa());
        System.out.println("");
    }
    
    //Mètode per a mostrar informació de diverses farmacies en la consola.
    public void mostrarInformacioFarmacies(ArrayList<Farmacia> farmacies) {
        if (farmacies.isEmpty()) {
            System.out.println("No s'ha encontrat cap coincidència.");
            return;
        }
        for (int i = 0; i < farmacies.size(); i++) {
            System.out.println("-----------------------------------");
            System.out.println("Farmacia " + (i + 1) + ":");
            mostrarInformacioFarmacia(farmacies.get(i));
        }
    }
}
