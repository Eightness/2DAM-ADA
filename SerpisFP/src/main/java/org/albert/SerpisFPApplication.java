package org.albert;

import org.albert.view.Input;
import org.albert.view.UIMenu;

import java.util.ArrayList;

/**
 * Class SerpisFPApplication. Contains all switches logic for the menus.
 */
public class SerpisFPApplication {  //Should be extending from AppProvider when connected to the database.
    //Attributes.
    private static boolean running = true;
    private static boolean subMenu = true;
    private static boolean actionMenu = true;
    private static final UIMenu uiMenu = new UIMenu();
    private static final Input input = new Input();
    private static final ArrayList<Integer> itemsCount = new ArrayList<>();

    //Methods.

    //MAIN METHOD.
    //------------------------------------------------------------------------------------------------------------------

    //Start.
    public static void start() {
        //Gathering necessary data.
        getItemsCountFromDatabase();

        //Welcome message.
        System.out.println(uiMenu.coolASCIImage());
        uiMenu.welcomeMessage();

        //Menu's logic.
        while (running) {
            switchEntityMenu();
        }

        //Goodbye message.
        uiMenu.goodbyeMessage();
    }

    //USEFUL METHODS.
    //------------------------------------------------------------------------------------------------------------------

    //Simulates providing database items count.
    private static void getItemsCountFromDatabase() {
        itemsCount.add(26);
        itemsCount.add(8);
        itemsCount.add(2);
        itemsCount.add(3);
        itemsCount.add(7);
        itemsCount.add(6);
    }

    //SWITCHES METHODS.
    //------------------------------------------------------------------------------------------------------------------

    //Entity Menu.
    private static void switchEntityMenu() {
        uiMenu.entityMenu(itemsCount);
        subMenu = true;
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                while (subMenu) {
                    switchCrudMenu("GRUP");
                }
                break;
            case 2:
                while (subMenu) {
                    switchCrudMenu("PROJECTE");
                }
                break;
            case 3:
                while (subMenu) {
                    switchCrudMenu("ALUMNE");
                }
                break;
            case 4:
                while (subMenu) {
                    switchCrudMenu("MÒDUL");
                }
                break;
            case 5:
                while (subMenu) {
                    switchCrudMenu("MATRÍCULA");
                }
                break;
            case 6: running = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Crud Menu.
    private static void switchCrudMenu(String entityName) {
        uiMenu.crudMenu(itemsCount, entityName);
        actionMenu = true;
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                while (actionMenu) {
                    switchCreateMenu(entityName);
                }
                break;
            case 2:
                while (actionMenu) {
                    switchReadMenu(entityName);
                }
                break;
            case 3:
                while (actionMenu) {
                    switchUpdateMenu(entityName);
                }
                break;
            case 4:
                while (actionMenu) {
                    switchDeleteMenu(entityName);
                }
                break;
            case 5: subMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //Create Menu.
    private static void switchCreateMenu(String entityName) {
        uiMenu.createMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                break;
            case 2:
                break;
            case 3: actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Read Menu.
    private static void switchReadMenu(String entityName) {
        uiMenu.readMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                break;
            case 2:
                break;
            case 3: actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Update Menu.
    private static void switchUpdateMenu(String entityName) {
        uiMenu.updateMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                break;
            case 2:
                break;
            case 3: actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Delete Menu.
    private static void switchDeleteMenu(String entityName) {
        uiMenu.deleteMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                break;
            case 2:
                break;
            case 3: actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private static void createActions(String entityName, int option) {
        switch (entityName) {
            case "GRUP":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "PROJECTE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "ALUMNE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MÒDUL":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MATRÍCULA":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
    }

    private static void readActions(String entityName, int option) {
        switch (entityName) {
            case "GRUP":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "PROJECTE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "ALUMNE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MÒDUL":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MATRÍCULA":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
    }

    private static void updateActions(String entityName, int option) {
        switch (entityName) {
            case "GRUP":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "PROJECTE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "ALUMNE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MÒDUL":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MATRÍCULA":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
    }

    private static void deleteActions(String entityName, int option) {
        switch (entityName) {
            case "GRUP":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "PROJECTE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "ALUMNE":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MÒDUL":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            case "MATRÍCULA":
                switch (option) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
    }
}
