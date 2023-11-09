/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Albert
 */
public class Medicament {
    
    //Atributs de la clase Medicament.
    private String nomComercial;
    private String formula;
    
    //Constructors de la classe Medicament.
    
    //Constructor amb l'identificador.
    public Medicament(String nomComercial) {
        this.nomComercial = nomComercial;
    }
    
    //Constructor amb tots els paràmetres.
    public Medicament(String nomComercial, String formula) {
        this.nomComercial = nomComercial;
        this.formula = formula;
    }
    
    //Getters i Setters de la classe Medicament.
    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    
}
