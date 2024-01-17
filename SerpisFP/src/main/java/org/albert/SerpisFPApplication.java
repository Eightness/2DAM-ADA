package org.albert;

import org.albert.providers.AppProvider;
import java.util.ArrayList;

/**
 * Class SerpisFPApplication. Contains all switches logic for the menus.
 */
public class SerpisFPApplication extends AppProvider {
    //Attributes.
    private static boolean running = true;
    private static boolean subMenu = true;
    private static boolean actionMenu = true;
    private static ArrayList<Integer> itemsCount;

    public enum Entity {
        GROUP("GRUP"),
        PROJECT("PROJECTE"),
        STUDENT("ALUMNE"),
        SUBJECT("MÒDUL"),
        ENROLLMENT("MATRÍCULA");

        private final String entityName;

        Entity(String entityName) {
            this.entityName = entityName;
        }

        public String getEntityName() {
            return entityName;
        }
    }

    //Methods.

    //MAIN METHOD.
    //------------------------------------------------------------------------------------------------------------------

    //Start.
    public static void start() {
        //Gathering necessary data.
        System.out.println("[!] Conexió a la base de dades realitzada amb èxit.");
        getItemsCountFromDatabase();

        //Welcome message.
        System.out.println(uiMenu.coolASCII());
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
        itemsCount = genericController.getItemsCountFromDatabase();
    }

    //SWITCHES METHODS.
    //------------------------------------------------------------------------------------------------------------------

    //Entity Menu.
    private static void switchEntityMenu() {
        uiMenu.entityMenu(itemsCount);
        subMenu = true;
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 0:
                genericController.deleteAllItemsFromDatabase();
                break;
            case 1:
                processEntity(Entity.GROUP);
                break;
            case 2:
                processEntity(Entity.PROJECT);
                break;
            case 3:
                processEntity(Entity.STUDENT);
                break;
            case 4:
                processEntity(Entity.SUBJECT);
                break;
            case 5:
                processEntity(Entity.ENROLLMENT);
                break;
            case 6:
                running = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Process Entity.
    private static void processEntity(Entity entityName) {
        while (subMenu) {
            switchCrudMenu(entityName);
        }
    }

    //Crud Menu.
    private static void switchCrudMenu(Entity entityName) {
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
            case 5:
                subMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //Create Menu.
    private static void switchCreateMenu(Entity entityName) {
        uiMenu.createMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                createActions(entityName, 1);
                break;
            case 2:
                createActions(entityName, 2);
                break;
            case 3:
                actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Read Menu.
    private static void switchReadMenu(Entity entityName) {
        uiMenu.readMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                readActions(entityName, 1);
                break;
            case 2:
                readActions(entityName, 2);
                break;
            case 3:
                actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Update Menu.
    private static void switchUpdateMenu(Entity entityName) {
        uiMenu.updateMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                updateActions(entityName, 1);
                break;
            case 2:
                updateActions(entityName, 2);
                break;
            case 3:
                actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Delete Menu.
    private static void switchDeleteMenu(Entity entityName) {
        uiMenu.deleteMenu(itemsCount, entityName);
        switch (input.getInt("Per favor, selecciona una opció: ")) {
            case 1:
                deleteActions(entityName, 1);
                break;
            case 2:
                deleteActions(entityName, 2);
                break;
            case 3:
                actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //Create Actions.
    private static void createActions(Entity entityName, int option) {
        actionMenu = false;
        switch (entityName) {
            case GROUP:
                switch (option) {
                    case 1:
                        groupController.createEntity(input.createGroup());
                        break;
                    case 2:
                        groupController.createEntities(input.createGroups());
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case PROJECT:
                switch (option) {
                    case 1:
                        projectController.createEntity(input.createProject());
                        break;
                    case 2:
                        projectController.createEntities(input.createProjects());
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case STUDENT:
                switch (option) {
                    case 1:
                        studentController.createEntity(input.createStudent());
                        break;
                    case 2:
                        studentController.createEntities(input.createStudents());
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case SUBJECT:
                switch (option) {
                    case 1:
                        subjectController.createEntity(input.createSubject());
                        break;
                    case 2:
                        subjectController.createEntities(input.createSubjects());
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case ENROLLMENT:
                switch (option) {
                    case 1:
                        enrollmentController.createEntity(input.createEnrollment());
                        break;
                    case 2:
                        enrollmentController.createEntities(input.createEnrollments());
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
        getItemsCountFromDatabase();
    }

    //Read Actions.
    private static void readActions(Entity entityName, int option) {
        actionMenu = false;
        switch (entityName) {
            case GROUP:
                switch (option) {
                    case 1:
                        groupController.readEntityById(input.getInt("Introdueix un id (CODGRUPO): "));
                        break;
                    case 2:
                        groupController.readEntitiesById(input.getInts("Introdueix un id (CODGRUPO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case PROJECT:
                switch (option) {
                    case 1:
                        projectController.readEntityById(input.getString("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 2:
                        projectController.readEntitiesById(input.getStrings("Introdueix un id (CODPROYECTO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case STUDENT:
                switch (option) {
                    case 1:
                        studentController.readEntityById(input.getString("Introdueix un id (NIA): "));
                        break;
                    case 2:
                        studentController.readEntitiesById(input.getStrings("Introdueix un id (NIA): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case SUBJECT:
                switch (option) {
                    case 1:
                        subjectController.readEntityById(input.getString("Introdueix un id (CODMODULO): "));
                        break;
                    case 2:
                        subjectController.readEntitiesById(input.getStrings("Introdueix un id (CODMODULO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case ENROLLMENT:
                switch (option) {
                    case 1:
                        enrollmentController.readEntityById(input.getInt("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 2:
                        enrollmentController.readEntitiesById(input.getInts("Introdueix un id (IDMATRICULA): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
    }

    //Update Actions.
    private static void updateActions(Entity entityName, int option) {
        actionMenu = false;
        switch (entityName) {
            case GROUP:
                switch (option) {
                    case 1:
                        groupController.updateEntityById(input.createGroup(), input.getInt("Introdueix un id (CODGRUPO): "));
                        break;
                    case 2:
                        groupController.updateEntitiesById(input.createGroups(), input.getInts("Introdueix un id (CODGRUPO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case PROJECT:
                switch (option) {
                    case 1:
                        projectController.updateEntityById(input.createProject(), input.getString("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 2:
                        projectController.updateEntitiesById(input.createProjects(), input.getStrings("Introdueix un id (CODPROYECTO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case STUDENT:
                switch (option) {
                    case 1:
                        studentController.updateEntityById(input.createStudent(), input.getString("Introdueix un id (NIA): "));
                        break;
                    case 2:
                        studentController.updateEntitiesById(input.createStudents(), input.getStrings("Introdueix un id (NIA): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case SUBJECT:
                switch (option) {
                    case 1:
                        subjectController.updateEntityById(input.createSubject(), input.getString("Introdueix un id (CODMODULO): "));
                        break;
                    case 2:
                        subjectController.updateEntitiesById(input.createSubjects(), input.getStrings("Introdueix un id (CODMODULO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case ENROLLMENT:
                switch (option) {
                    case 1:
                        enrollmentController.updateEntityById(input.createEnrollment(), input.getInt("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 2:
                        enrollmentController.updateEntitiesById(input.createEnrollments(), input.getInts("Introdueix un id (IDMATRICULA): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            default:
                System.out.println("Algo ha fallat.");
        }
    }

    //Delete Actions.
    private static void deleteActions(Entity entityName, int option) {
        actionMenu = false;
        switch (entityName) {
            case GROUP : {
                switch (option) {
                    case 1 : groupController.deleteEntityById(input.getInt("Introdueix un id (CODGRUPO): ")); break;
                    case 2 : groupController.deleteEntitiesById(input.getInts("Introdueix un id (CODGRUPO): ")); break;
                    default : System.out.println("Opció introduïda invàlida.");
                }
                break;
            }
            case PROJECT : {
                switch (option) {
                    case 1 : projectController.deleteEntityById(input.getString("Introdueix un id (CODPROYECTO): ")); break;
                    case 2 : projectController.deleteEntitiesById(input.getStrings("Introdueix un id (CODPROYECTO): ")); break;
                    default : System.out.println("Opció introduïda invàlida.");
                }
                break;
            }
            case STUDENT : {
                switch (option) {
                    case 1 : studentController.deleteEntityById(input.getString("Introdueix un id (NIA): ")); break;
                    case 2 : studentController.deleteEntitiesById(input.getStrings("Introdueix un id (NIA): ")); break;
                    default : System.out.println("Opció introduïda invàlida.");
                }
                break;
            }
            case SUBJECT : {
                switch (option) {
                    case 1 : subjectController.deleteEntityById(input.getString("Introdueix un id (CODMODULO): ")); break;
                    case 2 : subjectController.deleteEntitiesById(input.getStrings("Introdueix un id (CODMODULO): ")); break;
                    default : System.out.println("Opció introduïda invàlida.");
                }
                break;
            }
            case ENROLLMENT : {
                switch (option) {
                    case 1 : enrollmentController.deleteEntityById(input.getInt("Introdueix un id (IDMATRICULA): ")); break;
                    case 2 : enrollmentController.deleteEntitiesById(input.getInts("Introdueix un id (IDMATRICULA): ")); break;
                    default : System.out.println("Opció introduïda invàlida.");
                }
                break;
            }
            default : System.out.println("Algo ha fallat.");
        }
        getItemsCountFromDatabase();
    }

}
