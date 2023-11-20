package main;

import controller.ControllerMongoDB;
import controller.ControllerMySQL;
import java.util.ArrayList;
import java.util.List;
import model.ModelPOI;
import view.Input;
import view.Menu;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class App {
    //Own instances
    private final ControllerMongoDB controllerMongoDB = new ControllerMongoDB();
    private final ControllerMySQL controllerMySQL = new ControllerMySQL();
    private final Menu menu = new Menu();
    private final Input input = new Input();

    //Attributes
    private boolean isMySQL;                //Boolean to see in which database are we in (true = MySQL, false = MongoDB)
    private boolean running = true;         //Boolean to check if the app is still running
    private boolean submenu = false;        //Boolean to see if we are in a database submenu
    private boolean crudSubmenu = false;    //Boolean to see if we are in a specific CRUD selection

    //Getters and Setters
    public void setRunning(Boolean running) {
        this.running = running;
    }

    public void setSubmenu(Boolean submenu) {
        this.submenu = submenu;
    }

    public void setCrudSubmenu(Boolean crudSubmenu) {
        this.crudSubmenu = crudSubmenu;
    }

    public void setIsMySQL(Boolean isMySQL) {
        this.isMySQL = isMySQL;
    }

    //Constructor
    public App() {

    }

    //Methods

    //MAIN METHOD
    //------------------------------------------------------------------------------------------------------------------
    
    public void run() {
        //Disabling MongoDB log messages
        ConnectionMongoDB.disableMongoLogging();
        
        if (checkConnection()) {
            System.out.println();
            System.out.println("[!] T'has conectat a les BBDD correctament. :)");

            System.out.println();
            System.out.println(menu.welcomeMessage());

            while (running) {
                mainSwitch();
            }

            System.out.println();
            System.out.println(menu.goodbyeMessage());
            
            ConnectionMySQL.disconnectFromMySQL();
            ConnectionMongoDB.disconnectFromMongoDB();
        } else {
            if (ConnectionMySQL.mySQLConnection == null) {
                System.out.println();
                System.out.println("[!] No s'ha pogut conectar a la BBDD de MySQL correctament. :(");
            }
            
            if (ConnectionMongoDB.mongoDBConnection == null) {
                System.out.println();
                System.out.println("[!] No s'ha pogut conectar a la BBDD de MongoDB correctament. :(");
            }

            System.out.println();
            System.out.println("...");

            System.out.println();
            System.out.println("Finalitzant l'aplicació...");
        }
    }

    //UTILITY
    //------------------------------------------------------------------------------------------------------------------

    public boolean checkConnection() {
        //MySQL
        ConnectionMySQL.connectToMySQL();
        //MongoDB
        ConnectionMongoDB.connectToMongoDB();
        ConnectionMongoDB.getDatabase("poidb");
        ConnectionMongoDB.getCollection("pois_al15");
        
        return ConnectionMySQL.mySQLConnection != null && ConnectionMongoDB.mongoDBConnection != null;
    }
    
    public boolean isSynchronizable() {
        return controllerMySQL.getCurrentItems() != controllerMongoDB.getCurrentItems();
    }
    
    public void pressToContinue() {
        input.getString("Presiona 'Intro' per a finalitzar i tornar al menú anterior...");
    }

    //SWITCHES
    //------------------------------------------------------------------------------------------------------------------

    public void mainSwitch() {
        menu.mainMenu(controllerMySQL.getCurrentItems(), controllerMongoDB.getCurrentItems());
        switch(input.getInt("Elegeix una opció: ")) {
            case 1: //MySQL
                setSubmenu(true);
                setIsMySQL(true); //Setting database to MySQL
                while (submenu) {
                    menu.databaseMenu(controllerMySQL.getCurrentItems(), isMySQL, isSynchronizable());
                    databaseSwitch();
                }
                break;
            case 2: //MongoDB
                setSubmenu(true);
                setIsMySQL(false);  //Setting database to MongoDB
                while (submenu) {
                    menu.databaseMenu(controllerMongoDB.getCurrentItems(), isMySQL, isSynchronizable());
                    databaseSwitch();
                }
                break;
            case 3: //Exit
                setRunning(false);
                break;
            default:
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }

    public void databaseSwitch() {
        switch(input.getInt("Elegeix una opció: ")) {
            case 0: //Synchronize
                if (isSynchronizable()) {
                    int decision = input.getInt("[!] Atenció! Vas a sincronitzar les dades amb l'altra BBDD. Es perdrà aquella informació exclusiva de l'altra BBDD.\nContinuar? [Sí 1]/[No 0]: ", 1, 0);
                    if (decision == 1) {    
                        if (isMySQL) {
                            controllerMySQL.synchronizeDatabase();
                            pressToContinue();
                        } else {
                            controllerMongoDB.synchronizeDatabase();
                            pressToContinue();
                        }
                    }
                } else {
                    System.out.println();
                    System.out.println("No s'ha introduït una opció vàlida.");
                }
                break;
            case 1: //Insert
                if (isMySQL) {
                    controllerMySQL.insertItem(input.createPOI());
                    pressToContinue();
                } else {
                    controllerMongoDB.insertItem(input.createPOI());
                    pressToContinue();
                }
                break;
            case 2: //Insert various
                if (isMySQL) {
                    controllerMySQL.insertVariousItems(input.createPOIs());
                    pressToContinue();
                } else {
                    controllerMongoDB.insertVariousItems(input.createPOIs());
                    pressToContinue();
                }
                break;
            case 3: //Read
                if (isMySQL) {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.readSubmenu(isMySQL, controllerMySQL.getCurrentItems());
                        readSwitch();
                    }
                } else {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.readSubmenu(isMySQL, controllerMongoDB.getCurrentItems());
                        readSwitch();
                    }
                }
                break;
            case 4: //Delete
                if (isMySQL) {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.deleteSubmenu(isMySQL, controllerMySQL.getCurrentItems());
                        deleteSwitch();
                    }
                } else {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.deleteSubmenu(isMySQL, controllerMongoDB.getCurrentItems());
                        deleteSwitch();
                    }
                }
                break;
            case 5: //Import
                if (isMySQL) {
                    ArrayList<ModelPOI> poisFromXML = XMLReader.readXML();
                    controllerMySQL.importItems(poisFromXML);
                    pressToContinue();
                } else {
                    ArrayList<ModelPOI> poisFromXML = XMLReader.readXML();
                    controllerMongoDB.importItems(poisFromXML);
                    pressToContinue();
                }
                break;
            case 6: //Go back
                setSubmenu(false);
                break;
            case 10:
                if (isMySQL) {
                    controllerMySQL.insertDefaultRows();
                } else {
                    controllerMongoDB.insertDefaultCollection();
                }
                break;
            default:
                System.out.println();
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }

    //SUBMENU SWITCHES
    //------------------------------------------------------------------------------------------------------------------

    public void readSwitch() {
        switch(input.getInt("Elegeix una opció: ")) {
            case 1: //Read by ID
                if (isMySQL) {
                    controllerMySQL.getItemById(input.getInt("Introdueix un Id: "));
                    pressToContinue();
                } else {
                    controllerMongoDB.getItemById(input.getInt("Introdueix un Id: "));
                    pressToContinue();
                }
                setCrudSubmenu(false);
                break;
            case 2: //Read by various IDs
                if (isMySQL) {
                    controllerMySQL.getItemsById(input.getInts("Introdueix un Id: "));
                    pressToContinue();
                } else {
                    controllerMongoDB.getItemsById(input.getInts("Introdueix un Id: "));
                    pressToContinue();
                }
                setCrudSubmenu(false);
                break;
            case 3: //Read all, ordered by ID
                if (isMySQL) {
                    controllerMySQL.getAllItems(true);
                    pressToContinue();
                } else {
                    controllerMongoDB.getAllItems(true);
                    pressToContinue();
                }
                setCrudSubmenu(false);
                break;
            case 4: //Go back
                setCrudSubmenu(false);
                break;
            default:
                System.out.println();
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }

    public void deleteSwitch() {
        switch(input.getInt("Elegeix una opció: ")) {
            case 1: //Delete all
                int decision = input.getInt("[!] Atenció! Vas a esborrar tots els items de la base de dades.\nContinuar? [Sí 1]/[No 0]: ", 1, 0);
                if (decision == 1) {
                    if (isMySQL) {
                        controllerMySQL.deleteAllItems();
                        pressToContinue();
                    } else {
                        controllerMongoDB.deleteAllItems();
                        pressToContinue();
                    }
                }
                setCrudSubmenu(false);
                break;
            case 2: //Delete by ID
                if (isMySQL) {
                    controllerMySQL.deleteItemById(input.getInt("Introdueix un Id: "));
                    pressToContinue();
                } else {
                    controllerMongoDB.deleteItemById(input.getInt("Introdueix un Id: "));
                    pressToContinue();
                }
                setCrudSubmenu(false);
                break;
            case 3: //Delete by various IDs
                if (isMySQL) {
                    controllerMySQL.deleteItemsById(input.getInts("Introdueix un Id: "));
                    pressToContinue();
                } else {
                    controllerMongoDB.deleteItemsById(input.getInts("Introdueix un Id: "));
                    pressToContinue();
                }
                setCrudSubmenu(false);
                break;
            case 4: //Go back
                setCrudSubmenu(false);
                break;
            default:
                System.out.println();
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }
}
