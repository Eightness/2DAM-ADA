/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Medicament;
import dao.MedicamentDAO;
import java.util.List;
import vista.MedicamentVista;

/**
 *
 * @author Albert
 */
public class MedicamentControlador {
    
    //Atributs de la classe MedicamentControlador.
    private MedicamentDAO medicamentDAO;
    private MedicamentVista medicamentVista;
    
    //Constructor.
    public MedicamentControlador(MedicamentDAO medicamentDAO, MedicamentVista medicamentVista) {
        this.medicamentDAO = medicamentDAO;
        this.medicamentVista = medicamentVista;
    }
    
    //Mètodes relacionats amb la gestió de medicaments.
    
    //Mètode per a obtindre i mostrar informació d'un medicament.
    public void mostrarMedicament(String idMedicament) {
        try {
           Medicament medicament = medicamentDAO.obtindreMedicamentPerId(idMedicament);
            medicamentVista.mostrarInformacioMedicament(medicament); 
        } catch (Exception e) {
            System.out.println("No s'ha encontrat ningun medicament amb eixe nom comercial.");
        } 
    }
    
    //Mètode per a crear un medicament.
    public Medicament crearMedicament(String nomComercial, String formula) {
        return new Medicament(nomComercial, formula);
    }
    
    //Mètode per a insertar un metge en la base de dades.
    public void insertarMedicamentBaseDades(Medicament medicament) {
        medicamentDAO.insertarMedicament(medicament);
    }
    
    //Mètode per a eliminar un medicament de la base de dades.
    public void eliminarMedicamentBaseDades(String nomComercial) {
        medicamentDAO.eliminarMedicament(nomComercial);
    }
    
    //Mètode per a actualitzar un medicament de la base de dades.
    public void actualitzarMedicamentBaseDades(String nomComercial, String formula) {
        medicamentDAO.actualitzarMedicament(nomComercial, formula);
    }
    
    //Mètode que, obté els medicaments de la base de dades i els mostra per pantalla.
    public void mostrarMedicaments() {
        medicamentVista.mostrarInformacioMedicaments(medicamentDAO.obtenirTotsMedicaments());
    }
}
