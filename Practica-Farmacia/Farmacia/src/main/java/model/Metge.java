/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Albert
 */
public class Metge {
    
    //Atributs de la classe Metge.
    private int numColegiat;
    private String especialitat;
    private String nom;
    private String cognoms;
    
    //Constructors de la classe Metge.
    
    //Constructor amb l'identificador.
    public Metge(int numColegiat) {
        this.numColegiat = numColegiat;
    }
    
    //Constructor amb tots els paràmetres.
    public Metge(int numColegiat, String especialitat, String nom, String cognoms) {
        this.numColegiat = numColegiat;
        this.especialitat = especialitat;
        this.nom = nom;
        this.cognoms = cognoms;
    }
    
    //Getters i Setters de la classe Metge.
    public int getNumColegiat() {
        return numColegiat;
    }

    public void setNumColegiat(int numColegiat) {
        this.numColegiat = numColegiat;
    }

    public String getEspecialitat() {
        return especialitat;
    }

    public void setEspecialitat(String especialitat) {
        this.especialitat = especialitat;
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
