/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.ArrayList;
import model.Tractament;

/**
 *
 * @author Albert
 */
public class TractamentVista {
    
    //Mètode per a mostrar informació d'un tractament en la consola.
    public void mostrarInformacioTractament(Tractament tractament) {
        System.out.println("");
        System.out.println("Nombre de colegiat del metge: " + tractament.getNumColegiatMetge());
        System.out.println("DNI del pacient: " + tractament.getDniPacient());
        System.out.println("Data: " + tractament.getData());
        System.out.println("");
    }
    
    //Mètode per a mostrar informació de diversos tractaments en la consola.
    public void mostrarInformacioTractaments(ArrayList<Tractament> tractaments) {
        if (tractaments.isEmpty()) {
            System.out.println("No s'ha encontrat cap coincidència.");
            return;
        }
        for (int i = 0; i < tractaments.size(); i++) {
            System.out.println("-----------------------------------");
            System.out.println("Tractament " + (i + 1) + ":");
            mostrarInformacioTractament(tractaments.get(i));
        }
    }
     
}
