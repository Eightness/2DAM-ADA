package org.albert.view;

import org.albert.SerpisFPApplication;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Menu. Contains all terminal menus (UI).
 */
public class UIMenu {
    //Attributes.
    private final String[] welcomeMessages = {
            "Hola! Benvingut/da a la meva aplicació JPA del SerpisFP!",
            "Ara podràs gestionar la base de dades del centre educatiu IES Serpis de manera fàcil i eficient amb JPA.",
            "Benvingut/da al teu espai de control per a la gestió de dades acadèmiques amb JPA!",
            "Amb JPA, podràs afegir, llegir, actualitzar i esborrar registres de manera senzilla. Comencem!",
            "Gràcies per utilitzar la meva app. Si tens algun dubte, estic aquí per ajudar-te!"
    };
    private final String[] goodbyeMessages = {
            "Gràcies per utilitzar SerpisFP! Espere veure't prompte.",
            "Que tingues un bon dia! Fins prompte!",
            "Si tens més tasques per gestionar, recorda que SerpisFP està ací per ajudar-te.",
            "Fins la pròxima vegada! No dubtes en tornar si necessites alguna cosa.",
            "Espere haver estat d'ajuda. Fins prompte!"
    };

    //Methods.
    public String coolASCII() {
        return "\n" +
                "    ___    __    ____  __________  ______\n" +
                "   /   |  / /   / __ )/ ____/ __ \\/_  __/\n" +
                "  / /| | / /   / __  / __/ / /_/ / / /   \n" +
                " / ___ |/ /___/ /_/ / /___/ _, _/ / /    \n" +
                "/_/  |_/_____/_____/_____/_/ |_| /_/     \n";

    }

    public void welcomeMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(welcomeMessages.length);

        System.out.println();
        System.out.println(welcomeMessages[randomIndex]);
        System.out.println("Aplicació desenvolupada per Albert Lozano.");
    }

    public void goodbyeMessage() {
        Random random = new Random();
        int randomIndex = random.nextInt(goodbyeMessages.length);

        System.out.println();
        System.out.println(goodbyeMessages[randomIndex]);
        System.out.println("Aplicació desenvolupada per Albert Lozano.");
    }

    public void entityMenu(
            ArrayList<Integer> itemsCount
    ) {
        System.out.println();
        System.out.println("Menú principal:");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Base de Dades     | Amb quina entitat vols treballar? |");
        System.out.println("| 'SerpisFP'        |                                   |");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Total ítems:\t" + itemsCount.get(0) + "\t| 1. Grup.\t\t\t\t\t\t\t|");
        System.out.println("| Grups:\t\t" + itemsCount.get(1) + "\t| 2. Projecte.\t\t\t\t\t\t|");
        System.out.println("| Projectes:\t" + itemsCount.get(2) + "\t| 3. Alumne.\t\t\t\t\t\t|");
        System.out.println("| Alumnes:\t\t" + itemsCount.get(3) + "\t| 4. Mòdul.\t\t\t\t\t\t\t|");
        System.out.println("| Mòduls:\t\t" + itemsCount.get(4) + "\t| 5. Matrícula.\t\t\t\t\t\t|");
        System.out.println("| Matrícules:\t" + itemsCount.get(5) + "\t| 6. Eixir de l'aplicació.\t\t\t|");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("[!] Si vols esborrar totes les dades de la BBDD, polsa 0.");
    }

    public void crudMenu(
            ArrayList<Integer> itemsCount,
            SerpisFPApplication.Entity entity
    ) {
        System.out.println();
        System.out.println("Treballant amb l'entitat " + entity.getEntityName() + "...");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Base de Dades     | Quina acció vols realitzar?       |");
        System.out.println("| 'SerpisFP'        |                                   |");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Total ítems:\t" + itemsCount.get(0) + "\t| 1. Afegir.\t\t\t\t\t\t|");
        System.out.println("| Grups:\t\t" + itemsCount.get(1) + "\t| 2. Llegir.\t\t\t\t\t\t|");
        System.out.println("| Projectes:\t" + itemsCount.get(2) + "\t| 3. Actualitzar.\t\t\t\t\t|");
        System.out.println("| Alumnes:\t\t" + itemsCount.get(3) + "\t| 4. Esborrar.\t\t\t\t\t\t|");
        System.out.println("| Mòduls:\t\t" + itemsCount.get(4) + "\t| 5. Tornar.\t\t\t\t\t\t|");
        System.out.println("| Matrícules:\t" + itemsCount.get(5) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------+");
    }

    public void createMenu(
            ArrayList<Integer> itemsCount,
            SerpisFPApplication.Entity entity
    ) {
        System.out.println();
        System.out.println("Afegint l'entitat " + entity.getEntityName() + "...");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Base de Dades     | De quina manera vols afegir?      |");
        System.out.println("| 'SerpisFP'        |                                   |");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Total ítems:\t" + itemsCount.get(0) + "\t| 1. Afegir un element.\t\t\t\t|");
        System.out.println("| Grups:\t\t" + itemsCount.get(1) + "\t| 2. Afegir varios elements.\t\t|");
        System.out.println("| Projectes:\t" + itemsCount.get(2) + "\t| 3. Tornar.\t\t\t\t\t\t|");
        System.out.println("| Alumnes:\t\t" + itemsCount.get(3) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Mòduls:\t\t" + itemsCount.get(4) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Matrícules:\t" + itemsCount.get(5) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------+");
    }

    public void readMenu(
            ArrayList<Integer> itemsCount,
            SerpisFPApplication.Entity entity
    ) {
        System.out.println();
        System.out.println("Llegint l'entitat " + entity.getEntityName() + "...");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Base de Dades     | De quina manera vols llegir?      |");
        System.out.println("| 'SerpisFP'        |                                   |");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Total ítems:\t" + itemsCount.get(0) + "\t| 1. Llegir un element.\t\t\t\t|");
        System.out.println("| Grups:\t\t" + itemsCount.get(1) + "\t| 2. Llegir varios elements.\t\t|");
        System.out.println("| Projectes:\t" + itemsCount.get(2) + "\t| 3. Tornar.\t\t\t\t\t\t|");
        System.out.println("| Alumnes:\t\t" + itemsCount.get(3) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Mòduls:\t\t" + itemsCount.get(4) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Matrícules:\t" + itemsCount.get(5) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------+");
    }

    public void updateMenu(
            ArrayList<Integer> itemsCount,
            SerpisFPApplication.Entity entity
    ) {
        System.out.println();
        System.out.println("Actualitzant l'entitat " + entity.getEntityName() + "...");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Base de Dades     | De quina manera vols actualitzar? |");
        System.out.println("| 'SerpisFP'        |                                   |");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Total ítems:\t" + itemsCount.get(0) + "\t| 1. Actualitzar un element.\t\t|");
        System.out.println("| Grups:\t\t" + itemsCount.get(1) + "\t| 2. Actualitzar varios elements.\t|");
        System.out.println("| Projectes:\t" + itemsCount.get(2) + "\t| 3. Tornar.\t\t\t\t\t\t|");
        System.out.println("| Alumnes:\t\t" + itemsCount.get(3) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Mòduls:\t\t" + itemsCount.get(4) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Matrícules:\t" + itemsCount.get(5) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------+");
    }

    public void deleteMenu(
            ArrayList<Integer> itemsCount,
            SerpisFPApplication.Entity entity
    ) {
        System.out.println();
        System.out.println("Esborrant l'entitat " + entity.getEntityName() + "...");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Base de Dades     | De quina manera vols esborrar?    |");
        System.out.println("| 'SerpisFP'        |                                   |");
        System.out.println("+-------------------+-----------------------------------+");
        System.out.println("| Total ítems:\t" + itemsCount.get(0) + "\t| 1. Esborrar un element.\t\t\t|");
        System.out.println("| Grups:\t\t" + itemsCount.get(1) + "\t| 2. Esborrar varios elements.\t\t|");
        System.out.println("| Projectes:\t" + itemsCount.get(2) + "\t| 3. Tornar.\t\t\t\t\t\t|");
        System.out.println("| Alumnes:\t\t" + itemsCount.get(3) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Mòduls:\t\t" + itemsCount.get(4) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("| Matrícules:\t" + itemsCount.get(5) + "\t|\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------+");
    }
}
