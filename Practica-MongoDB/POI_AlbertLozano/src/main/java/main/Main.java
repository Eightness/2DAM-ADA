/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package main;

import view.Menu;

/**
 *
 * @author alblozbla
 */
public class Main {
    //Attributes
    private final static App app = new App();
    private final static MongoDBConnection mongoDBConnection = new MongoDBConnection();
    private final static MySQLConnection mySQLConnection = new MySQLConnection();

    //Main method
    public static void main(String[] args) {
        app.run();
    }
}
