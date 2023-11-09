/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Pacient;
import dao.PacientDAO;
import java.util.List;
import vista.PacientVista;

/**
 *
 * @author Albert
 */
public class PacientControlador {
    
    //Atributs de la classe PacientControlador.
    private PacientDAO pacientDAO;
    private PacientVista pacientVista;
    
    //Constructor.
    public PacientControlador(PacientDAO pacientDAO, PacientVista pacientVista) {
        this.pacientDAO = pacientDAO;
        this.pacientVista = pacientVista;
    }
    
    //Mètodes relacionats amb la gestió de pacients.
    
    //Mètode per a obtindre i mostrar informació d'un pacient.
    public void mostrarPacient(String idPacient) {
        try {
            Pacient pacient = pacientDAO.obtindrePacientPerId(idPacient);
            pacientVista.mostrarInformacioPacient(pacient);  
        } catch (Exception e) {
            System.out.println("No s'ha encontrat ningún pacient amb eixe DNI.");
        }
    }
    
    //Mètode per a crear un pacient nou.
    public Pacient crearPacient(String dni, String nom, String cognoms) {
        return new Pacient(dni, nom, cognoms);
    }
    
    //Mètode per a insertar pacient en la base de dades.
    public void insertarPacientBaseDades(Pacient pacient) {
        pacientDAO.insertarPacient(pacient);
    }
    
    //Mètode per a eliminar un pacient de la base de dades.
    public void eliminarPacientBaseDades(String dni) {
        pacientDAO.eliminarPacient(dni);
    }
    
    //Mètode per a actualitzar un pacient de la base de dades.
    public void actualitzarPacientBaseDades(String dni, String nom, String cognoms) {
        pacientDAO.actualitzarPacient(dni, nom, cognoms);
    }
    
    //Mètode que, obté els metges de la base de dades i els mostra per pantalla.
    public void mostrarPacients() {
        pacientVista.mostrarInformacioPacients(pacientDAO.obtenirTotsPacients());
    }
}
