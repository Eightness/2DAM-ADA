/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.ArrayList;
import model.Metge;

/**
 *
 * @author Albert
 */
public class MetgeVista {
    
    //Mètode per a mostrar informació d'un metge en la consola.
    public void mostrarInformacioMetge(Metge metge) {
        System.out.println("");
        System.out.println("Nombre de colegiat: " + metge.getNumColegiat());
        System.out.println("Especialitat: " + metge.getEspecialitat());
        System.out.println("Nom: " + metge.getNom());
        System.out.println("Cognoms: " + metge.getCognoms());
        System.out.println("");
    }
    
    //Mètode per a mostrar informació de diversos metges en la consola.
    public void mostrarInformacioMetges(ArrayList<Metge> metges) {
        if (metges.isEmpty()) {
            System.out.println("No s'ha encontrat cap coincidència.");
            return;
        }
        for (int i = 0; i < metges.size(); i++) {
            System.out.println("-----------------------------------");
            System.out.println("Metge " + (i + 1) + ":");
            mostrarInformacioMetge(metges.get(i));
        }
    }
    
}
