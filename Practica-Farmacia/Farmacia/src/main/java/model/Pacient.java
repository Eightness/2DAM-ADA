/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Albert
 */
public class Pacient {
    
    //Atributs de la classe Pacient.
    private String dni;
    private String nom;
    private String cognoms;
    
    //Constructors de la classe Pacient.
    
    //Constructor amb l'identificador.
    public Pacient(String dni) {
        this.dni = dni;
    }
    
    //Constructor amb tots els paràmetres.
    public Pacient(String dni, String nom, String cognoms) {
        this.dni = dni;
        this.nom = nom;
        this.cognoms = cognoms;
    }
    
    //Getters i Setters de la classe Pacient.
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }
    
}
