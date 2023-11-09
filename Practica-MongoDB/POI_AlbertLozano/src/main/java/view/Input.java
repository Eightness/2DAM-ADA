/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.POI;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alblozbla
 */
public class Input {
    //Attributes
    private final Scanner scanner = new Scanner(System.in);
    
    //Methods
    public int getInt(String message) {
        int num = 0;
        boolean validated = false;
        while(!validated) {
            System.out.print(message);
            try {
                num = scanner.nextInt();
                validated = true;
            } catch (Exception e) {
                System.out.println("No s'ha introduït un nombre enter.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return num;
    }

    public double getDouble(String message) {

        return 0.0;
    }

    public String getString(String message) {

        return "";
    }

    public POI createPOI() {

        return null;
    }

    public ArrayList<POI> createPOIs() {

        return null;
    }
    
}
