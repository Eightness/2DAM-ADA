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
public class Venta {
    
    //Atributs de la classe Venta.
    private String nomComercialMedicament;
    private String cifFarmacia;
    private Date data;
    private Double preu;
    
    //Constructors.
    
    //Constructor amb els identificadors.
    public Venta(String nomComercialMedicament, String cifFarmacia) {
        this.nomComercialMedicament = nomComercialMedicament;
        this.cifFarmacia = cifFarmacia;
    }
    
    //Constructor amb tots els paràmetres.
    public Venta(String nomComercialMedicament, String cifFarmacia, Date data, Double preu) {
        this.nomComercialMedicament = nomComercialMedicament;
        this.cifFarmacia = cifFarmacia;
        this.data = data;
        this.preu = preu;
    }
    
    //Getters i Setters de la classe Venta.
    public String getNomComercialMedicament() {
        return nomComercialMedicament;
    }

    public void setNomComercialMedicament(String nomComercialMedicament) {
        this.nomComercialMedicament = nomComercialMedicament;
    }

    public String getCifFarmacia() {
        return cifFarmacia;
    }

    public void setCifFarmacia(String cifFarmacia) {
        this.cifFarmacia = cifFarmacia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double preu) {
        this.preu = preu;
    }
    
}
