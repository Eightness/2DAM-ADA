package view;

import java.util.ArrayList;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class Menu {
    //Constructor
    public Menu() {
        
    }
    
    //Methods

    //MESSAGES
    //------------------------------------------------------------------------------------------------------------------
    
    public String welcomeMessage() {
        ArrayList<String> welcomeMessages = new ArrayList<>();
        welcomeMessages.add("Hola! Benvingut/da a Java CRUD! Ara amb dos BBDD diferents!");
        welcomeMessages.add("Benvingut/da al CRUD de MySQL i MongoDB!");
        welcomeMessages.add("Hola caracola! Disfruta gestionant MySQL i MongoDB alhora!");
        int randomNumber = (int) (Math.random() * 3);

        return welcomeMessages.get(randomNumber) + "\nAplicació desenvolupada per Albert Lozano.";
    }
    
    private String currentDatabase(String database, int currentItems) {
        return "Estas emprant la BBDD de " + database + ".\nActualment hi ha " + currentItems + " items." ;
    }

    public String goodbyeMessage() {
        ArrayList<String> goodbyeMessages = new ArrayList<>();
        goodbyeMessages.add("Adéu! Moltes gràcies per emprar la meua aplicació!");
        goodbyeMessages.add("Un plaer! Espere que ens tornem a veure!");
        goodbyeMessages.add("Fins després! Espere que t'haja agradat la meua aplicació!");
        int randomNumber = (int) (Math.random() * 3);

        return goodbyeMessages.get(randomNumber) + "\nAplicació desenvolupada per Albert Lozano.";
    }

    //MAIN MENU
    //------------------------------------------------------------------------------------------------------------------
    
    public void mainMenu(int currentItemsMySQL, int currentItemsMongoDB) {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Items actuals en les BBDD:");
        System.out.println("MySQL: " + currentItemsMySQL);
        System.out.println("MongoDB: " + currentItemsMongoDB);
        System.out.println();
        System.out.println("Amb quina BBDD desitjes treballar?");
        System.out.println();
        System.out.println("1. MySQL.");
        System.out.println("2. MongoDB.");
        System.out.println("3. Eixir.");
    }

    //DATABASE MENU
    //------------------------------------------------------------------------------------------------------------------
    
    public void databaseMenu(int currentItems, boolean database, boolean canSynchronize) {
        String databaseName;
        if (database) {
            databaseName = "MySQL";
        } else {
            databaseName = "MongoDB";
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println(currentDatabase(databaseName, currentItems));
        System.out.println();
        if (canSynchronize) {
            System.out.println("0. Sincronitzar.");
        }
        System.out.println("1. Inserir un element.");
        System.out.println("2. Inserir varios elements.");
        System.out.println("3. Llistar.");
        System.out.println("4. Esborrar.");
        System.out.println("5. Importar.");
        System.out.println("6. Tornar.");
    }

    //SUBMENUS
    //------------------------------------------------------------------------------------------------------------------

    public void readSubmenu(Boolean database, int currentItems) {
        String databaseName;
        if (database) {
            databaseName = "MySQL";
        } else {
            databaseName = "MongoDB";
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println(currentDatabase(databaseName, currentItems));
        System.out.println();
        System.out.println("1. Llistar per ID.");
        System.out.println("2. Llistar per varios ID's.");
        System.out.println("3. Llistar tots, ordenats per ID.");
        System.out.println("4. Tornar.");
    }

    public void deleteSubmenu(Boolean database, int currentItems) {
        String databaseName;
        if (database) {
            databaseName = "MySQL";
        } else {
            databaseName = "MongoDB";
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println(currentDatabase(databaseName, currentItems));
        System.out.println();
        System.out.println("1. Esborrar tots.");
        System.out.println("2. Esborrar per ID.");
        System.out.println("3. Esborrar per varios ID's.");
        System.out.println("4. Tornar.");
    }

}
