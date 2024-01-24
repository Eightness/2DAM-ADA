package org.albert;

import lombok.Getter;
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

    @Getter
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
    }

    //Methods.

    //MAIN METHOD.
    //------------------------------------------------------------------------------------------------------------------

    //Start.
    public static void start() {
        //Gathering necessary data.
        System.out.println("[✅] Conexió a la base de dades realitzada amb èxit.");
        genericController.deleteAllItemsFromDatabase(); //Delete this line if you want to save changes into the DB.
        genericController.loadDemoData();
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
                String election = input.getStringNotNull("[❗] Estas segur/a de voler esborrar totes les dades? 'OK' per confirmar o qualsevol altra cosa per a cancelar: ");
                if (election.equalsIgnoreCase("OK")) {
                    genericController.deleteAllItemsFromDatabase();
                    getItemsCountFromDatabase();
                }
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
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 2:
                createActions(entityName, 2);
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
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
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 2:
                readActions(entityName, 2);
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 3:
                readActions(entityName, 3);
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 4:
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
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 2:
                updateActions(entityName, 2);
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
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
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 2:
                deleteActions(entityName, 2);
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 3:
                deleteActions(entityName, 3);
                input.getStringNullable("Presiona qualsevol tecla per a continuar... ");
                break;
            case 4:
                actionMenu = false;
                break;
            default:
                System.out.println("Opció introduïda invàlida.");
        }
    }

    //Actions.
    //------------------------------------------------------------------------------------------------------------------

    //Create Actions.
    private static void createActions(Entity entityName, int option) {
        actionMenu = false;
        System.out.println();
        System.out.println("[❔] Recorda que l'ordre de creació per defecte (quan la BBDD està buida) és:");
        System.out.println("1.GRUP/MÒDUL > 2.ALUMNE > 3.PROJECTE/MATRÍCULA.");
        switch (entityName) {
            case GROUP:
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Creant nou grup...");
                        groupController.createEntity(input.createGroup());
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Creant nous grups...");
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
                        System.out.println();
                        System.out.println("[❕] Creant nou projecte...");
                        projectController.createEntity(input.createProject());
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Creant nous projectes...");
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
                        System.out.println();
                        System.out.println("[❕] Creant nou alumne...");
                        studentController.createEntity(input.createStudent());
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Creant nous alumnes...");
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
                        System.out.println();
                        System.out.println("[❕] Creant nou mòdul...");
                        subjectController.createEntity(input.createSubject());
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Creant nous mòduls...");
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
                        System.out.println();
                        System.out.println("[❕] Creant nova matrícula...");
                        enrollmentController.createEntity(input.createEnrollment());
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Creant noves matrícules...");
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
                        System.out.println();
                        System.out.println("[❕] Llegint un grup...");
                        groupController.readEntityById(input.getInt("Introdueix un id (CODGRUPO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Llegint varios grups...");
                        groupController.readEntitiesById(input.getInts("Introdueix un id (CODGRUPO): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Llegint tots els grups...");
                        groupController.readAllEntities();
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case PROJECT:
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Llegint un projecte...");
                        projectController.readEntityById(input.getStringNotNull("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Llegint varios projectes...");
                        projectController.readEntitiesById(input.getStrings("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Llegint tots els projectes...");
                        projectController.readAllEntities();
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case STUDENT:
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Llegint un alumne...");
                        studentController.readEntityById(input.getStringNotNull("Introdueix un id (NIA): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Llegint varios alumnes...");
                        studentController.readEntitiesById(input.getStrings("Introdueix un id (NIA): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Llegint tots els alumnes...");
                        studentController.readAllEntities();
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case SUBJECT:
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Llegint un mòdul...");
                        subjectController.readEntityById(input.getInt("Introdueix un id (CODMODULO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Llegint varios mòduls...");
                        subjectController.readEntitiesById(input.getInts("Introdueix un id (CODMODULO): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Llegint tots els mòduls...");
                        subjectController.readAllEntities();
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case ENROLLMENT:
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Llegint una matrícula...");
                        enrollmentController.readEntityById(input.getInt("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Llegint varies matrícules...");
                        enrollmentController.readEntitiesById(input.getInts("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Llegint totes les matrícules...");
                        enrollmentController.readAllEntities();
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
                        System.out.println();
                        System.out.println("[❕] Actualitzant un grup...");
                        groupController.updateEntityById(input.createGroup(), input.getInt("Introdueix un id (CODGRUPO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Actualitzant varios grups...");
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
                        System.out.println();
                        System.out.println("[❕] Actualitzant un projecte...");
                        projectController.updateEntityById(input.createProject(), input.getStringNotNull("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Actualitzant varios projectes...");
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
                        System.out.println();
                        System.out.println("[❕] Actualitzant un alumne...");
                        studentController.updateEntityById(input.createStudent(), input.getStringNotNull("Introdueix un id (NIA): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Actualitzant varios alumnes...");
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
                        System.out.println();
                        System.out.println("[❕] Actualitzant un mòdul...");
                        subjectController.updateEntityById(input.createSubject(), input.getInt("Introdueix un id (CODMODULO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Actualitzant varios mòduls...");
                        subjectController.updateEntitiesById(input.createSubjects(), input.getInts("Introdueix un id (CODMODULO): "));
                        break;
                    default:
                        System.out.println("Algo ha fallat.");
                        break;
                }
                break;
            case ENROLLMENT:
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Actualitzant una matrícula...");
                        enrollmentController.updateEntityById(input.createEnrollment(), input.getInt("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Actualitzant varies matrícules...");
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
            case GROUP: {
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Esborrant un grup...");
                        groupController.deleteEntityById(input.getInt("Introdueix un id (CODGRUPO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Esborrant varios grups...");
                        groupController.deleteEntitiesById(input.getInts("Introdueix un id (CODGRUPO): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Esborrant tots els grups...");
                        groupController.deleteAllEntities();
                        break;
                    default:
                        System.out.println("Opció introduïda invàlida.");
                        break;
                }
                break;
            }
            case PROJECT: {
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Esborrant un projecte...");
                        projectController.deleteEntityById(input.getStringNotNull("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Esborrant varios projectes...");
                        projectController.deleteEntitiesById(input.getStrings("Introdueix un id (CODPROYECTO): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Esborrant tots els projectes...");
                        projectController.deleteAllEntities();
                        break;
                    default:
                        System.out.println("Opció introduïda invàlida.");
                        break;
                }
                break;
            }
            case STUDENT: {
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Esborrant un alumne...");
                        studentController.deleteEntityById(input.getStringNotNull("Introdueix un id (NIA): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Esborrant varios alumnes...");
                        studentController.deleteEntitiesById(input.getStrings("Introdueix un id (NIA): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Esborrant tots els alumnes...");
                        studentController.deleteAllEntities();
                        break;
                    default:
                        System.out.println("Opció introduïda invàlida.");
                        break;
                }
                break;
            }
            case SUBJECT: {
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Esborrant un mòdul...");
                        subjectController.deleteEntityById(input.getInt("Introdueix un id (CODMODULO): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Esborrant varios mòduls...");
                        subjectController.deleteEntitiesById(input.getInts("Introdueix un id (CODMODULO): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Esborrant tots els mòduls...");
                        subjectController.deleteAllEntities();
                        break;
                    default:
                        System.out.println("Opció introduïda invàlida.");
                        break;
                }
                break;
            }
            case ENROLLMENT: {
                switch (option) {
                    case 1:
                        System.out.println();
                        System.out.println("[❕] Esborrant una matrícula...");
                        enrollmentController.deleteEntityById(input.getInt("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("[❕] Esborrant varies matrícules...");
                        enrollmentController.deleteEntitiesById(input.getInts("Introdueix un id (IDMATRICULA): "));
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("[❕] Esborrant totes les matrícules...");
                        enrollmentController.deleteAllEntities();
                        break;
                    default:
                        System.out.println("Opció introduïda invàlida.");
                        break;
                }
                break;
            }
            default:
                System.out.println("Algo ha fallat.");
                break;
        }
        getItemsCountFromDatabase();
    }

}
