/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Prescripcio;
import dao.PrescripcioDAO;
import java.util.Date;
import vista.PrescripcioVista;

/**
 *
 * @author Albert
 */
public class PrescripcioControlador {
    
    //Atributs de la classe PrescripcioControlador.
    private PrescripcioDAO prescripcioDAO;
    private PrescripcioVista prescripcioVista;
    
    //Constructor.
    public PrescripcioControlador(PrescripcioDAO prescripcioDAO, PrescripcioVista prescripcioVista) {
        this.prescripcioDAO = prescripcioDAO;
        this.prescripcioVista = prescripcioVista;
    }
    
    //Mètodes relacionats amb la gestió de prescripcions.
    
    //Mètode per a obtindre i mostrar informació d'una prescripció.
    public void mostrarPrescripcio(int idMetge, String idPacient, String idMedicament) {
        Prescripcio prescripcio = prescripcioDAO.obtindrePrescripcioPerId(idMetge, idPacient, idMedicament);
        prescripcioVista.mostrarInformacioPrescripcio(prescripcio);
    }
    
    //Mètode per a insertar una prescripció a la base de dades.
    public void insertarPrescripcioBaseDades(int numColegiatMetge, String dniPacient, String nomComercialMedicament, Date data, int quantitat) {
        prescripcioDAO.insertarPrescripcio(numColegiatMetge, dniPacient, nomComercialMedicament, data, quantitat);
    }
    
}
