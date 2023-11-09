/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.ArrayList;
import model.Medicament;

/**
 *
 * @author Albert
 */
public class MedicamentVista {
    
    //Mètode per a mostrar informació d'un medicament en la consola.
    public void mostrarInformacioMedicament(Medicament medicament) {
        System.out.println("");
        System.out.println("Nom comercial: " + medicament.getNomComercial());
        System.out.println("Fòrmula: " + medicament.getFormula());
        System.out.println("");
    }
    
    //Mètode per a mostrar informació de diversos medicaments en la consola.
    public void mostrarInformacioMedicaments(ArrayList<Medicament> medicaments) {
        if (medicaments.isEmpty()) {
            System.out.println("No s'ha encontrat cap coincidència.");
            return;
        }
        for (int i = 0; i < medicaments.size(); i++) {
            System.out.println("-----------------------------------");
            System.out.println("Medicament " + (i + 1) + ":");
            mostrarInformacioMedicament(medicaments.get(i));
        }
    }
}
