/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.ModelPOI;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author alblozbla
 */
public class Input {
    //Attributes
    private final Scanner scanner = new Scanner(System.in);
    
    //Methods

    //GET INPUT
    //------------------------------------------------------------------------------------------------------------------

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

    public ArrayList<Integer> getInts(String message) {
        ArrayList<Integer> nums = new ArrayList<>();
        boolean keepAsking = true;

        System.out.println("Quan vulgues parar, introdueix un -1.");
        while(keepAsking) {
            int num = getInt(message);
            if (num == -1) {
                keepAsking = false;
            } else {
                nums.add(num);
            }
        }

        return nums;
    }

    public double getDouble(String message) {
        double num = 0.0;
        boolean validated = false;
        while(!validated) {
            System.out.print(message);
            try {
                num = scanner.nextDouble();
                validated = true;
            } catch (Exception e) {
                System.out.println("No s'ha introduït un nombre.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return num;
    }

    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    //METHODS THAT REQUIRE USER'S INPUT
    //------------------------------------------------------------------------------------------------------------------

    public ModelPOI createPOI() {
        System.out.println();
        System.out.println("Creant un punt d'interés: ");
        int poid = getInt("Introdueix el ID: ");
        if (poid == 0) {
            return null;
        }
        double latitude = getDouble("Introdueix la latitud: ");
        double longitude = getDouble("Introdueix la longitud: ");
        String country = getString("Introdueix el païs: ");
        String city = getString("Introdueix la ciutat: ");
        String description = getString("Introdueix una descripció: ");
        java.util.Date utilDate = new java.util.Date();
        Date updated = new Date(utilDate.getTime());

        return new ModelPOI(poid, latitude, longitude, country, city, description, updated);
    }

    public ArrayList<ModelPOI> createPOIs() {
        ArrayList<ModelPOI> createdPOIs = new ArrayList<>();
        boolean keepCreating = true;

        System.out.println();
        System.out.println("Quan vulgues parar, introdueix un 0 en el poid.");
        while(keepCreating) {
            ModelPOI newPOI = createPOI();
            if (newPOI == null) {
                keepCreating = false;
            } else {
                createdPOIs.add(newPOI);
            }
        }

        return createdPOIs;
    }
    
}
