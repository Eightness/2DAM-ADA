package org.albert.view;

import java.util.Random;

/**
 * Class Menu. Contains all terminal menus (UI).
 */
public class UIMenu {
    //Attributes.
    private final String databaseName = "SerpisFP";
    private final String authorName = "Albert Lozano";
    private final String[] welcomeMessages = {
            "Hola! Benvingut/da a la meva aplicació JPA del SerpisFP!",
            "Ara podràs gestionar la base de dades del centre educatiu IES Serpis de manera fàcil i eficient amb JPA.",
            "Benvingut/da al teu espai de control per a la gestió de dades acadèmiques amb JPA!",
            "Amb JPA, podràs afegir, modificar i eliminar registres de manera senzilla. Comencem!",
            "Gràcies per utilitzar la meva app. Si tens algun dubte, estic aquí per ajudar-te!"
    };
    private final String[] goodbyeMessages = {
            "Gràcies per utilitzar SerpisFP! Espere veure't aviat.",
            "Que tinguis un bon dia! Fins aviat!",
            "Si tens més tasques per gestionar, recorda que SerpisFP està aquí per ajudar-te.",
            "Fins la propera vegada! No dubtis en tornar si necessites alguna cosa.",
            "Espere haver estat d'ajuda. Fins aviat!"
    };

    //Methods.
    public String coolASCIImage() {
        return """

                  ___   _     ______  _____ ______  _____\s
                 / _ \\ | |    | ___ \\|  ___|| ___ \\|_   _|
                / /_\\ \\| |    | |_/ /| |__  | |_/ /  | | \s
                |  _  || |    | ___ \\|  __| |    /   | | \s
                | | | || |____| |_/ /| |___ | |\\ \\   | | \s
                \\_| |_/\\_____/\\____/ \\____/ \\_| \\_|  \\_/ \s
                                                         \s
                                                         \s
                """;
    }

    public void welcomeMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(welcomeMessages.length);

        System.out.println();
        System.out.println(welcomeMessages[randomIndex]);
        System.out.println("Aplicació desenvolupada per " + authorName + ".");
    }

    public void goodbyeMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(goodbyeMessages.length);

        System.out.println();
        System.out.println(goodbyeMessages[randomIndex]);
        System.out.println("Aplicació desenvolupada per " + authorName + ".");
    }

    public void databaseItemsCounter(
            int databaseItemsCount,
            int groupItemsCount,
            int projectItemsCount,
            int studentItemsCount,
            int subjectItemsCount
    ) {
        System.out.println();
        System.out.println("Actualment hi ha un total de " + databaseItemsCount + " ítems en la base de dades '" + databaseName + "'.");
        System.out.println("Grups: " + groupItemsCount + ".");
        System.out.println("Projectes: " + projectItemsCount + ".");
        System.out.println("Alumnes: " + studentItemsCount + ".");
        System.out.println("Mòduls: " + subjectItemsCount + ".");
    }

    public void entityMenu() {
        System.out.println();
        System.out.println("Amb quina entitat vols treballar?");
        System.out.println("1. Grup.");
        System.out.println("2. Projecte.");
        System.out.println("3. Alumne.");
        System.out.println("4. Mòdul.");
        System.out.println("5. Matrícula.");
        System.out.println("6. Eixir de l'aplicació.");
        System.out.println();
        System.out.print("Per favor, selecciona una opció: ");
    }

    public void crudMenu() {
        System.out.println();
        System.out.println("Quina acció vols realitzar?");
        System.out.println("1. (Create) Afegir.");
        System.out.println("2. (Read) Llegir.");
        System.out.println("3. (Update) Actualitzar.");
        System.out.println("4. (Delete) Esborrar.");
        System.out.println("5. Tornar.");
        System.out.println();
        System.out.print("Per favor, selecciona una opció: ");
    }

    public void createMenu() {
        System.out.println();
        System.out.println("De quina manera vols afegir?");
        System.out.println("1. Afegir un element.");
        System.out.println("2. Afegir varios elements.");
        System.out.println("3. Tornar.");
        System.out.println();
        System.out.print("Per favor, selecciona una opció: ");
    }

    public void readMenu() {
        System.out.println();
        System.out.println("De quina manera vols llegir?");
        System.out.println("1. Llegir un element.");
        System.out.println("2. Llegir varios elements.");
        System.out.println("3. Tornar.");
        System.out.println();
        System.out.print("Per favor, selecciona una opció: ");
    }

    public void updateMenu() {
        System.out.println();
        System.out.println("De quina manera vols actualitzar?");
        System.out.println("1. Actualitzar un element.");
        System.out.println("2. Actualitzar varios elements.");
        System.out.println("3. Tornar.");
        System.out.println();
        System.out.print("Per favor, selecciona una opció: ");
    }

    public void deleteMenu() {
        System.out.println();
        System.out.println("De quina manera vols esborrar?");
        System.out.println("1. Esborrar un element.");
        System.out.println("2. Esborrar varios elements.");
        System.out.println("3. Tornar.");
        System.out.println();
        System.out.print("Per favor, selecciona una opció: ");
    }
}
