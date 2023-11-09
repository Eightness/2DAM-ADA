/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Metge;
import dao.MetgeDAO;
import java.util.ArrayList;
import model.Tractament;
import vista.MetgeVista;
import vista.TractamentVista;

/**
 *
 * @author Albert
 */
public class MetgeControlador {
    
    //Atributs de la classe MetgeControlador.
    private MetgeDAO metgeDAO;
    private MetgeVista metgeVista;
    
    //Constructor.
    public MetgeControlador(MetgeDAO metgeDAO, MetgeVista metgeVista) {
        this.metgeDAO = metgeDAO;
        this.metgeVista = metgeVista;
    }
    
    //Mètodes relacionats amb la gestió de metges.
    
    //Mètode per a obtindre i mostrar informació d'un metge.
    public void mostrarMetge(int id) {
        try {
            Metge metge = metgeDAO.obtindreMetgePerId(id);
            metgeVista.mostrarInformacioMetge(metge); 
        } catch (Exception e) {
            System.out.println("No s'ha encontrat ningún metge amb eixe nombre de colegiat.");
        } 
    }
    
    //Mètode per a crear un metge nou.
    public Metge crearMetge(int numColegiat, String especialitat, String nom, String cognoms) {
        return new Metge(numColegiat, especialitat, nom, cognoms);
    }
    
    //Mètode per a insertar un metge en la base de dades.
    public void insertarMetgeBaseDades(Metge metge) {
        metgeDAO.insertarMetge(metge);
    }
    
    //Mètode per a eliminar un metge de la base de dades.
    public void eliminarMetgeBaseDades(int numColegiat) {
        metgeDAO.eliminarMetge(numColegiat);
    }
    
    //Mètode per a actualitzar un metge de la base de dades.
    public void actualitzarMetgeBaseDades(int numColegiat, String especialitat, String nom, String cognoms) {
        metgeDAO.actualitzarMetge(numColegiat,especialitat, nom, cognoms);
    }
    
    //Mètode que, obté els metges de la base de dades i els mostra per pantalla.
    public void mostrarMetges() {
        metgeVista.mostrarInformacioMetges(metgeDAO.obtenirTotsMetges());
    }
    
}
