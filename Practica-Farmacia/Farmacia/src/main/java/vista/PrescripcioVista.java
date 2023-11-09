/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import model.Prescripcio;

/**
 *
 * @author Albert
 */
public class PrescripcioVista {
    
    //Mètode per a mostrar informació d'una prescripcio en la consola.
    public void mostrarInformacioPrescripcio(Prescripcio prescripcio) {
        System.out.println("");
        System.out.println("Nombre de colegiat del metge: " + prescripcio.getNumColegiatMetge());
        System.out.println("DNI del pacient: " + prescripcio.getDniPacient());
        System.out.println("Nom comercial del medicament: " + prescripcio.getNomComercialMedicament());
        System.out.println("Data: " + prescripcio.getData());
        System.out.println("Quantitat: " + prescripcio.getQuantitat());
        System.out.println("");
    }
    
}
