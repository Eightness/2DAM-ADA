/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.ModelPOI;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 *
 * @author alblozbla
 */
public class ViewPOI {
    //Attributes
    private final Input input = new Input();

    //Methods

    public void showPOI(ModelPOI poi) {
        System.out.println();
        System.out.println(poi.toString());
        System.out.println();
        System.out.println();
    }

    public void showPOIs(ArrayList<ModelPOI> pois) {
        for (int i = 0; i < pois.size(); i++) {
            if (i % 3 == 0) {
                System.out.println("El següent missatge es repetirà cada 3 items per a millorar la lectura des de la terminal:");
                input.getString("Presiona 'Intro' per a continuar veient dades... ");
            }
            System.out.println("Punt d'interés " + (i + 1) + ":");
            showPOI(pois.get(i));
        }
        input.getString("Presiona 'Intro' per a finalitzar i tornar al menú anterior...");
    }
    
}
