/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Albert
 */
public class Farmacia {
    
    //Atributs de la classe Farmacia.
    private String cif;
    private String adressa;
    
    //Constructor amb l'identificador.
    public Farmacia(String cif) {
        this.cif = cif;
    }
    
    //Constructor amb tots els paràmetres.
    public Farmacia(String cif, String adressa) {
        this.cif = cif;
        this.adressa = adressa;
    }
    
    //Getters i Setters de la classe Farmacia.
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAdressa() {
        return adressa;
    }

    public void setAdressa(String adressa) {
        this.adressa = adressa;
    }
    
}
