/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import model.Tractament;
import dao.TractamentDAO;
import vista.TractamentVista;

/**
 *
 * @author Albert
 */
public class TractamentControlador {
    
    //Atributs de la classe TractamentControlador.
    private TractamentDAO tractamentDAO;
    private TractamentVista tractamentVista;
    
    //Constructor.
    public TractamentControlador(TractamentDAO tractamentDAO, TractamentVista tractamentVista) {
        this.tractamentDAO = tractamentDAO;
        this.tractamentVista = tractamentVista;
    }
    
    //Mètodes relacionats amb la gestió de tractaments.
    
    //Mètode per a obtindre i mostrar informació d'un tractament.
    public void mostrarTractament(int idMetge, String idPacient) {
        Tractament tractament = tractamentDAO.obtindreTractamentPerId(idMetge, idPacient);
        tractamentVista.mostrarInformacioTractament(tractament);
    }
    
    //Mètode que, donat el número de col·legiat d’un metge, lliste a tots els pacients que tracta i amb la data que els ha tractat.
    public void llistarPacientsPerMetgeBaseDades(int numColegiat) {  
        tractamentVista.mostrarInformacioTractaments(tractamentDAO.llistarPacientsPerMetge(numColegiat));
    }
}
