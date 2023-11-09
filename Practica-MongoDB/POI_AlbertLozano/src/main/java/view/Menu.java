/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.POIControllerMongoDB;
import controller.POIControllerMySQL;
import java.util.ArrayList;

/**
 *
 * @author alblozbla
 */
public class Menu {
    //Attributes
    private Input input;
    private POIControllerMongoDB controllerMongoDB = new POIControllerMongoDB();
    private POIControllerMySQL controllerMySQL = new POIControllerMySQL();
    private boolean firstBack = false;
    private boolean secondBack = false;
    private boolean thirdBack = false;
    private boolean fourthBack = false;
    private boolean run = true;
    private String database;
    
    //Getters and Setters
    public boolean isFirstBack() {
        return firstBack;
    }

    public void setFirstBack(boolean firstBack) {
        this.firstBack = firstBack;
    }

    public boolean isSecondBack() {
        return secondBack;
    }

    public void setSecondBack(boolean secondBack) {
        this.secondBack = secondBack;
    }

    public boolean isThirdBack() {
        return thirdBack;
    }

    public void setThirdBack(boolean thirdBack) {
        this.thirdBack = thirdBack;
    }

    public boolean isFourthBack() {
        return fourthBack;
    }

    public void setFourthBack(boolean fourthBack) {
        this.fourthBack = fourthBack;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    
    //Constructor
    public Menu() {
        
    }
    
    //Methods
    public void startMenu() {
        System.out.println();
        System.out.println(welcomeMessage());
        System.out.println();
        
    }
    
    private String welcomeMessage() {
        ArrayList<String> welcomeMessages = new ArrayList<>();
        welcomeMessages.add("Hola! Benvingut/da a l'aplicació de Java CRUD! Ara amb dos BBDD diferents!");
        welcomeMessages.add("Benvingut/da al CRUD de MySQL i MongoDB!");
        welcomeMessages.add("Hola caracola! Disfruta gestionant MySQL i MongoDB alhora!");
        
        int randomNumber = (int) (Math.random() * 3);

        return welcomeMessages.get(randomNumber) + "\nDesenvolupada per Albert Lozano.";
    }
    
    private String currentDatabase(String database, int currentItems) {
        return "Estas emprant la BBDD de " + database + ".\nActualment hi ha " + currentItems + " items." ;
    }
    
    private int currentItemsMongoDB() {
        return controllerMongoDB.getCurrentItems();
    }
    
    private int currentItemsMySQL() {
        return controllerMySQL.getCurrentItems();
    }
    
    private void mainMenu() {
        System.out.println();
        System.out.println("Amb quina BBDD desitjes treballar?");
        System.out.println();
        System.out.println("1. MongoDB.");
        System.out.println("2. MySQL.");
        System.out.println("3. Eixir.");
        System.out.println();
    }
    
    private void mongoDBMenu() {
        System.out.println();
        System.out.println(currentDatabase("MongoDB", currentItemsMongoDB()));
        System.out.println();
        System.out.println("1. Inserir un element.");
        System.out.println("2. Inserir varios elements.");
        System.out.println("3. Llistar.");
        System.out.println("4. Esborrar.");
        System.out.println("5. Sincronitzar.");
        System.out.println("6. Tornar.");
        System.out.println();
    }
    
    private void mySQLMenu() {
        
    }
}
