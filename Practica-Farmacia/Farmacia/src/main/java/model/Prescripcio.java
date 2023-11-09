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
public class Prescripcio {
    
    //Atributs de la classe Prescripcio.
    private int numColegiatMetge;
    private String dniPacient;
    private String nomComercialMedicament;
    private Date data;
    private int quantitat;
    
    //Constructors de la classe Prescripcio.
    
    //Constructor amb els identificadors.
    public Prescripcio(int numColegiatMetge, String dniPacient, String nomComercialMedicament) {
        this.numColegiatMetge = numColegiatMetge;
        this.dniPacient = dniPacient;
        this.nomComercialMedicament = nomComercialMedicament;
    }
    
    //Constructor amb tots els paràmetres.
    public Prescripcio(int numColegiatMetge, String dniPacient, String nomComercialMedicament, Date data, int quantitat) {
        this.numColegiatMetge = numColegiatMetge;
        this.dniPacient = dniPacient;
        this.nomComercialMedicament = nomComercialMedicament;
        this.data = data;
        this.quantitat = quantitat;
    }
    
    //Getters i Setters de la classe Prescripcio.
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

    public String getNomComercialMedicament() {
        return nomComercialMedicament;
    }

    public void setNomComercialMedicament(String nomComercialMedicament) {
        this.nomComercialMedicament = nomComercialMedicament;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }
    
}
