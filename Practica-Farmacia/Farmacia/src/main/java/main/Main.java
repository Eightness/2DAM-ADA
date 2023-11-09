/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import model.*;
import vista.*;
import dao.*;
import controlador.*;
import java.sql.Connection;
import java.util.*;
import static main.Conexio.conectarMySQL;
import static main.Conexio.desconectarMySQL;

/**
 *
 * @author Albert
 */
public class Main {
    
    //Enum entitats.
    public static enum entitat{
        METGE, PACIENT, FARMACIA, MEDICAMENT;
    }
    
    //Conexió a la base de dades.
    private static final Connection conexio = conectarMySQL();
    
    //Atributs de la classe Main.
    private static final Scanner entrada = new Scanner(System.in);
    private static entitat entitatActual;
    private static final MetgeControlador metgeControlador = new MetgeControlador(new MetgeDAO(conexio), new MetgeVista());
    private static final PacientControlador pacientControlador = new PacientControlador(new PacientDAO(conexio), new PacientVista());
    private static final MedicamentControlador medicamentControlador = new MedicamentControlador(new MedicamentDAO(conexio), new MedicamentVista());
    private static final FarmaciaControlador farmaciaControlador = new FarmaciaControlador(new FarmaciaDAO(conexio), new FarmaciaVista());
    private static final PrescripcioControlador prescripcioControlador = new PrescripcioControlador(new PrescripcioDAO(conexio), new PrescripcioVista());
    private static final TractamentControlador tractamentControlador = new TractamentControlador(new TractamentDAO(conexio), new TractamentVista());
    private static final VentaControlador ventaControlador = new VentaControlador(new VentaDAO(conexio), new VentaVista());
    
    //Mètodes per a l'aplicació.
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //Mètodes per a rebre entrada de l'usuari.
    
    //Mètode per a rebre un nombre enter de l'usuari.
    public static int obtindreInt(String missatge) {
        int num = 0;
        boolean validat = false;
        while(!validat) {
            System.out.print(missatge);
            try {
                num = entrada.nextInt();
                validat = true;
            } catch (Exception e) {
                System.out.println("No s'ha introduït un nombre enter.");
                entrada.nextLine();
            }
        }
        entrada.nextLine();
        return num;
    }
    
    //Mètode per a rebre un nombre decimal de l'usuari.
    public static Double obtindreDouble(String missatge) {
        while(true) {
            System.out.print(missatge);
            try {
                return entrada.nextDouble();
            } catch (Exception e) {
                System.out.println("No s'ha introduït un nombre vàlid.");
            }
        }
    }
    
    //Mètode per a rebre una cadena de text de l'usuari.
    public static String obtindreString(String missatge) {
        System.out.print(missatge);
        return entrada.nextLine();
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //Mètodes per als menús de l'aplicació.
    
    //Mètode per a mostrar el menú principal d'entitats.
    public static int menuEntitats() {
        System.out.println("");
        System.out.println("Amb quina entitat desitges treballar?");
        System.out.println("1. METGE.");
        System.out.println("2. PACIENT.");
        System.out.println("3. FARMACIA.");
        System.out.println("4. MEDICAMENT.");
        System.out.println("5. FUNCIONS ESPECIALS.");
        System.out.println("6. Eixir.");
        System.out.println("");
        return obtindreInt("Elegeix una opció: ");
    }
    
    //Mètode per a mostrar el submenú CRUD.
    public static int menuCRUD() {
        System.out.println("");
        System.out.println("Què desitges fer?");
        System.out.println("0. VEURE DADES.");
        System.out.println("1. SELECT.");
        System.out.println("2. INSERT.");
        System.out.println("3. DELETE.");
        System.out.println("4. UPDATE.");
        System.out.println("5. Tornar.");
        System.out.println("");
        return obtindreInt("Elegeix una opció: ");
    }
    
    //Mètode per a mostrar el submenú de les funcions especials.
    public static int menuFuncionsEspecials() {
        System.out.println("");
        System.out.println("1. Donat el nombre de colegiat, llistar a tots els pacients que tracta.");
        System.out.println("2. Insertar prescripció d'un medicament determinat per un pacient determinat.");
        System.out.println("3. Eiminar tots els medicaments venuts per una farmàcia determinada.");
        System.out.println("4. Tornar.");
        System.out.println("");
        return obtindreInt("Elegeix una opció: ");
    }
    
    //Mètode per al switch del menú principal.
    public static void switchMenuPrincipal(int opcio) {
        switch(opcio) {
            case 1 -> {
                entitatActual = entitat.METGE;
                switchSubmenu(menuCRUD());
            }
            case 2 -> {
                entitatActual = entitat.PACIENT;
                switchSubmenu(menuCRUD());
            }
            case 3 -> {
                entitatActual = entitat.FARMACIA;
                switchSubmenu(menuCRUD());
            }
            case 4 -> {
                entitatActual = entitat.MEDICAMENT;
                switchSubmenu(menuCRUD());
            }
            case 5 -> {
                switchFuncionsEspecials(menuFuncionsEspecials());
            }
            case 6 -> {
                System.out.println("");
                desconectarMySQL();
                System.out.println("Gràcies per utilitzar la meua aplicació!");
                System.exit(0);
            }
            default -> System.out.println("Opció elegida invàlida.");
        }
    }
    
    //Mètode per al switch del submenú.
    public static void switchSubmenu(int opcio) {
        switch(opcio) {
            case 0 -> {
                switch (entitatActual) {
                    case METGE -> {
                        System.out.println("");
                        metgeControlador.mostrarMetges();
                        }
                    case PACIENT -> {
                        System.out.println("");
                        pacientControlador.mostrarPacients();
                        }
                    case FARMACIA -> {
                        System.out.println("");
                        farmaciaControlador.mostrarFarmacies();
                        }
                    case MEDICAMENT -> {
                        System.out.println("");
                        medicamentControlador.mostrarMedicaments();
                        }
                    default -> {
                        System.out.println("Alguna cosa ha fallat.");
                        }
                }
            }
            case 1 -> {
                switch (entitatActual) {
                    case METGE -> {
                        System.out.println("");
                        metgeControlador.mostrarMetge(obtindreInt("Introdueix el nombre de colegiat que vulgues visualitzar: "));
                        }
                    case PACIENT -> {
                        System.out.println("");
                        pacientControlador.mostrarPacient(obtindreString("Introdueix el DNI del pacient que vulgues visualitzar: "));
                        }
                    case FARMACIA -> {
                        System.out.println("");
                        farmaciaControlador.mostrarFarmacia(obtindreString("Introdueix el CIF de la farmacia que vulgues visualitzar: "));
                        }
                    case MEDICAMENT -> {
                        System.out.println("");
                        medicamentControlador.mostrarMedicament(obtindreString("Introdueix el nom del medicament que vulgues visualitzar: "));
                        }
                    default -> {
                        System.out.println("Alguna cosa ha fallat.");
                        }
                }
            }
            case 2 -> {
                switch (entitatActual) {
                    case METGE -> {
                        System.out.println("");
                        System.out.println("Per a insertar un nou metge, és necessari crear un nou.");
                        int numColegiat = obtindreInt("Introdueix un nombre de colegiat: ");
                        String especialitat = obtindreString("Introdueix una especialitat: ");
                        String nom = obtindreString("Introdueix un nom: ");
                        String cognoms = obtindreString("Introdueix cognoms: ");
                        
                        Metge metge = metgeControlador.crearMetge(numColegiat, especialitat, nom, cognoms);
                        metgeControlador.insertarMetgeBaseDades(metge);
                        }
                    case PACIENT -> {
                        System.out.println("");
                        System.out.println("Per a insertar un nou pacient, és necessari crear un nou.");
                        String dni = obtindreString("Introdueix un DNI: ");
                        String nom = obtindreString("Introdueix un nom: ");
                        String cognoms = obtindreString("Introdueix cognoms: ");
                        
                        Pacient pacient = pacientControlador.crearPacient(dni, nom, cognoms);
                        pacientControlador.insertarPacientBaseDades(pacient);
                        }
                    case FARMACIA -> {
                        System.out.println("");
                        System.out.println("Per a insertar una nova farmacia, és necessari crear una nova.");
                        String cif = obtindreString("Introdueix el CIF: ");
                        String adressa = obtindreString("Introdueix adreça: ");
                        
                        Farmacia farmacia = farmaciaControlador.crearFarmacia(cif, adressa);
                        farmaciaControlador.insertarFarmaciaBaseDades(farmacia);
                        }
                    case MEDICAMENT -> {
                        System.out.println("");
                        System.out.println("Per a insertar un nou medicament, és necessari crear un nou.");
                        String nomComercial = obtindreString("Introdueix un nom comercial: ");
                        String formula = obtindreString("Introdueix la fòrmula del medicament: ");
                        
                        Medicament medicament = medicamentControlador.crearMedicament(nomComercial, formula);
                        medicamentControlador.insertarMedicamentBaseDades(medicament);
                        }
                    default -> {
                        System.out.println("Alguna cosa ha fallat.");
                        }
                }
            }
            case 3 -> {
                switch (entitatActual) {
                    case METGE -> {
                        System.out.println("");
                        int numColegiat = obtindreInt("Introdueix el nombre de colegiat que desitges eliminar: ");
                        metgeControlador.eliminarMetgeBaseDades(numColegiat);
                        }
                    case PACIENT -> {
                        System.out.println("");
                        String dni = obtindreString("Introdueix el DNI del pacient que desitges eliminar: ");
                        pacientControlador.eliminarPacientBaseDades(dni);
                        }
                    case FARMACIA -> {
                        System.out.println("");
                        String cif = obtindreString("Introdueix el CIF de la farmacia que desitges eliminar: ");
                        farmaciaControlador.eliminarFarmaciaBaseDades(cif);
                        }
                    case MEDICAMENT -> {
                        System.out.println("");
                        String nomComercial = obtindreString("Introdueix el nom comercial del medicament que desitges eliminar: ");
                        medicamentControlador.eliminarMedicamentBaseDades(nomComercial);
                        }
                    default -> {
                        System.out.println("Alguna cosa ha fallat.");
                        }
                }
            }
            case 4 -> {
                switch (entitatActual) {
                    case METGE -> {
                        System.out.println("");
                        int numColegiat = obtindreInt("Introdueix nombre de colegiat del metge que vols modificar dades: ");
                        String especialitat = obtindreString("Introdueix una nova especialitat: ");
                        String nom = obtindreString("Introdueix nou nom: ");
                        String cognoms = obtindreString("Introdueix nous cognoms: ");
                        
                        metgeControlador.actualitzarMetgeBaseDades(numColegiat, especialitat, nom, cognoms);
                        }
                    case PACIENT -> {
                        System.out.println("");
                        String dni = obtindreString("Introdueix el DNI del pacient del que vols modificar dades: ");
                        String nom = obtindreString("Introdueix nou nom: ");
                        String cognoms = obtindreString("Introdueix nous cognoms: ");
                        
                        pacientControlador.actualitzarPacientBaseDades(dni, nom, cognoms);
                        }
                    case FARMACIA -> {
                        System.out.println("");
                        String cif = obtindreString("Introdueix el CIF: ");
                        String adressa = obtindreString("Introdueix adreça: ");
                        
                        farmaciaControlador.actualitzarFarmaciaBaseDades(cif, adressa);
                        }
                    case MEDICAMENT -> {
                        System.out.println("");
                        String nomComercial = obtindreString("Introdueix el nom comercial del medicament que vols modificar dades: ");
                        String formula = obtindreString("Introdueix la nova fòrmula del medicament: ");
                        
                        medicamentControlador.actualitzarMedicamentBaseDades(nomComercial, formula);
                        }
                    default -> {
                        System.out.println("Alguna cosa ha fallat.");
                        }
                }
            }
            case 5 -> {
                System.out.println("");
                System.out.println("Tornant arrere.");
                break;
            }
            default -> System.out.println("Opció elegida invàlida.");
        }
    }
    
    //Mètode per al switch de les funcions especials.
    public static void switchFuncionsEspecials(int opcio) {
        switch(opcio) {
            case 1 -> {
                System.out.println("");
                int numColegiat = obtindreInt("Introdueix nombre de colegiat: ");
                tractamentControlador.llistarPacientsPerMetgeBaseDades(numColegiat);
            }
            case 2 -> {
                System.out.println("");
                System.out.println("A l'hora d'insertar una prescripció, s'ha de tenir en compte dues coses: ");
                System.out.println("1. El metge, pacient i medicament, han d'existir en la base de dades.");
                System.out.println("2. No poden hi haure dues prescripcions iguals (que coincideixen el metge, pacient i medicament).");
                System.out.println("");
                int numColegiat = obtindreInt("Introdueix el nombre de colegiat del metge: ");
                String dniPacient = obtindreString("Introdueix el DNI del pacient: ");
                String nomComercialMedicament = obtindreString("Introdueix el nom del medicament: ");
                int quanitat = obtindreInt("Introdueix la quantitat: ");
                Date data = new Date();
                prescripcioControlador.insertarPrescripcioBaseDades(numColegiat, dniPacient, nomComercialMedicament, data, quanitat);
            }
            case 3 -> {
                System.out.println("");
                String cifFarmacia = obtindreString("Introdueix el CIF de la farmacia: ");
                ventaControlador.eliminarVentesPerFarmacia(cifFarmacia);
            }
            case 4 -> {
                break;
            }
            default -> {
                System.out.println("Opció invàlida.");
            }
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //Mètode main per a executar l'aplicació CRUD.
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("Benvinguts a l'aplicació CRUD de la base de dades 'Farmacia'.");
        System.out.println("Treball realitzat per Albert Lozano.");
                
        while (true) {
            switchMenuPrincipal(menuEntitats());
        }
        
    }
}
