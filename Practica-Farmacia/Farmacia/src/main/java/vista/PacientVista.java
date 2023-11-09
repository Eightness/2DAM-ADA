/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.ArrayList;
import model.Pacient;

/**
 *
 * @author Albert
 */
public class PacientVista {
    
    //Mètode per a mostrar informació d'un pacient en la consola.
    public void mostrarInformacioPacient(Pacient pacient) {
        System.out.println("");
        System.out.println("DNI: " + pacient.getDni());
        System.out.println("Nom: " + pacient.getNom());
        System.out.println("Cognoms: " + pacient.getCognoms());
        System.out.println("");
    }
    
    //Mètode per a mostrar informació de diversos pacients en la consola.
    public void mostrarInformacioPacients(ArrayList<Pacient> pacients) {
        if (pacients.isEmpty()) {
            System.out.println("No s'ha encontrat cap coincidència.");
            return;
        }
        for (int i = 0; i < pacients.size(); i++) {
            System.out.println("-----------------------------------");
            System.out.println("Pacient " + (i + 1) + ":");
            mostrarInformacioPacient(pacients.get(i));
        }
    }
    
}
