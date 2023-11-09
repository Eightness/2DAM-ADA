/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Farmacia;
import dao.FarmaciaDAO;
import java.util.List;
import vista.FarmaciaVista;

/**
 *
 * @author Albert
 */
public class FarmaciaControlador {
    
    //Atributs de la classe FarmaciaControlador.
    private FarmaciaDAO farmaciaDAO;
    private FarmaciaVista farmaciaVista;
    
    //Constructor.
    public FarmaciaControlador(FarmaciaDAO farmaciaDAO, FarmaciaVista farmaciaVista) {
        this.farmaciaDAO = farmaciaDAO;
        this.farmaciaVista = farmaciaVista;
    }
    
    //Mètodes relacionats amb la gestió de farmacies.
    
    //Mètode per a obtindre i mostrar informació d'una farmacia.
    public void mostrarFarmacia(String idFarmacia) {
        try {
            Farmacia farmacia = farmaciaDAO.obtindreFarmaciaPerId(idFarmacia);
            farmaciaVista.mostrarInformacioFarmacia(farmacia);
        } catch (Exception e) {
            System.out.println("No s'ha encontrat ninguna farmacia amb eixe CIF.");
        }
    }
    
    //Mètode per a crear una farmacia.
    public Farmacia crearFarmacia(String cif, String adressa) {
        return new Farmacia(cif, adressa);
    }
    
    //Mètode per a insertar una farmacia en la base de dades.
    public void insertarFarmaciaBaseDades(Farmacia farmacia) {
        farmaciaDAO.insertarFarmacia(farmacia);
    }
    
    //Mètode per a eliminar una farmacia de la base de dades.
    public void eliminarFarmaciaBaseDades(String cif) {
        farmaciaDAO.eliminarFarmacia(cif);
    }
    
    //Mètode per a actualitzar una farmacia de la base de dades.
    public void actualitzarFarmaciaBaseDades(String cif, String adressa) {
        farmaciaDAO.actualitzarFarmacia(cif, adressa);
    }
    
    //Mètode que, obté les farmacies de la base de dades i les mostra per pantalla.
    public void mostrarFarmacies() {
        farmaciaVista.mostrarInformacioFarmacies(farmaciaDAO.obtenirTotesFarmacies());
    }
}
