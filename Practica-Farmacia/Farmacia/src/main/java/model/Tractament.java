/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Albert
 */
public class Tractament {
    
    //Atributs de la classe Tractament.
    private int numColegiatMetge;
    private String dniPacient;
    private Date data;
    
    //Constructors de la classe Tractament.
    
    //Constructor amb els identificadors.
    public Tractament(int numColegiatMetge, String dniPacient) {
        this.numColegiatMetge = numColegiatMetge;
        this.dniPacient = dniPacient;
    }
    
    //Constructor amb tots els paràmetres.
    public Tractament(int numColegiatMetge, String dniPacient, Date data) {
        this.numColegiatMetge = numColegiatMetge;
        this.dniPacient = dniPacient;
        this.data = data;
    }
    
    //Getters i Setters de la classe Tracatament.
    public int getNumColegiatMetge() {
        return numColegiatMetge;
    }

    public void setNumColegiatMetge(int numColegiatMetge) {
        this.numColegiatMetge = numColegiatMetge;
    }

    public String getDniPacient() {
        return dniPacient;
    }

    public void setDniPacient(String dniPacient) {
        this.dniPacient = dniPacient;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
