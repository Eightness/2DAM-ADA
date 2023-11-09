package main;

import controller.POIControllerMongoDB;
import controller.POIControllerMySQL;
import view.Input;
import view.Menu;

public class App {
    //Instances
    private final POIControllerMongoDB controllerMongoDB = new POIControllerMongoDB();
    private final POIControllerMySQL controllerMySQL = new POIControllerMySQL();
    private final MongoDBConnection mongoDBConnection = new MongoDBConnection();
    private final MySQLConnection mySQLConnection = new MySQLConnection();
    private final Menu menu = new Menu();
    private final Input input = new Input();

    //Attributes
    private Boolean isMySQL;   //Boolean to see in which database are we in (true = MySQL, false = MongoDB)
    private Boolean running = true;    //Boolean to check if the app is still running
    private Boolean submenu = false; //Boolean to see if we are in a database submenu
    private Boolean crudSubmenu = false;  //Boolean to see if we are in a specific CRUD selection

    //Getters and Setters
    public POIControllerMongoDB getControllerMongoDB() {
        return controllerMongoDB;
    }

    public POIControllerMySQL getControllerMySQL() {
        return controllerMySQL;
    }

    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }

    public MySQLConnection getMySQLConnection() {
        return mySQLConnection;
    }

    public Menu getMenu() {
        return menu;
    }

    public Input getInput() {
        return input;
    }

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public Boolean getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Boolean submenu) {
        this.submenu = submenu;
    }

    public Boolean getCrudSubmenu() {
        return crudSubmenu;
    }

    public void setCrudSubmenu(Boolean crudSubmenu) {
        this.crudSubmenu = crudSubmenu;
    }

    public Boolean getIsMySQL() {
        return isMySQL;
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
        } else {
            System.out.println();
            System.err.println("[!] No s'ha pogut conectar a les BBDD correctament. :(");

            System.out.println();
            System.err.println("...");

            System.out.println();
            System.err.println("Finalitzant l'aplicació...");
        }
    }

    //CHECK CONNECTION
    //------------------------------------------------------------------------------------------------------------------

    public boolean checkConnection() {
        return mySQLConnection.getConnection() && mongoDBConnection.getConnection();
    }

    //SWITCHES
    //------------------------------------------------------------------------------------------------------------------

    public void mainSwitch() {
        menu.mainMenu();
        switch(input.getInt("Elegeix una opció: ")) {
            case 1: //MongoDB
                setSubmenu(true);
                setIsMySQL(false); //Setting database to MongoDB
                while (submenu) {
                    menu.databaseMenu(controllerMongoDB.getCurrentItems(), isMySQL);
                    databaseSwitch();
                }
                break;
            case 2: //MySQL
                setSubmenu(true);
                setIsMySQL(true);  //Setting database to MySQL
                while (submenu) {
                    menu.databaseMenu(controllerMySQL.getCurrentItems(), isMySQL);
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
                if (controllerMySQL.getCurrentItems() != controllerMongoDB.getCurrentItems()) {
                    if (isMySQL) {

                    } else {

                    }
                } else {
                    break;
                }
                break;
            case 1: //MongoDB: Create one       / MySQL: Create various
                if (isMySQL) {

                } else {

                }
                break;
            case 2: //MongoDB: Create various   / MySQL: Read
                if (isMySQL) {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        readSwitch();
                    }
                } else {

                }
                break;
            case 3: //MongoDB: Read             / MySQL: Delete
                if (isMySQL) {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.deleteSubmenu(isMySQL, controllerMySQL.getCurrentItems());
                        deleteSwitch();
                    }
                } else {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.readSubmenu(isMySQL, controllerMongoDB.getCurrentItems());
                        readSwitch();
                    }
                }
                break;
            case 4: //MongoDB: Delete           / MySQL: Import
                if (isMySQL) {

                } else {
                    setCrudSubmenu(true);
                    while(crudSubmenu) {
                        menu.deleteSubmenu(isMySQL, controllerMongoDB.getCurrentItems());
                        deleteSwitch();
                    }
                }
                break;
            case 5: //MongoDB: Import           / MySQL: Go back
                if (isMySQL) {
                    setSubmenu(false);
                } else {

                }
                break;
            case 6: //MongoDB: Go back          / MySQL: Nothing
                if (isMySQL) {

                } else {
                    setSubmenu(false);
                }
                break;
            default:
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }

    public void readSwitch() {
        switch(input.getInt("Elegeix una opció: ")) {
            case 1: //Read by ID
                if (isMySQL) {

                } else {

                }
                break;
            case 2: //Read by various IDs
                if (isMySQL) {

                } else {

                }
                break;
            case 3: //Read all, ordered by ID
                if (isMySQL) {

                } else {

                }
                break;
            case 4: //Go back
                setCrudSubmenu(false);
                break;
            default:
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }

    public void deleteSwitch() {
        switch(input.getInt("Elegeix una opció: ")) {
            case 1: //Delete all
                if (isMySQL) {

                } else {

                }
                break;
            case 2: //Delete by ID
                if (isMySQL) {

                } else {

                }
                break;
            case 3: //Delete by various IDs
                if (isMySQL) {

                } else {

                }
                break;
            case 4: //Go back
                setCrudSubmenu(false);
                break;
            default:
                System.out.println("No s'ha introduït una opció vàlida.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
