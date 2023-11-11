/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class ConnectionMySQL {
    //Attributes
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String database = "poidb";
    public static String hostname = "localhost";
    public static String port = "3306";
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static String username = "root";
    public static String password = "root";
    public static Connection mySQLConnection = null;

    //Methods
    public static Connection connectToMySQL() {
        try {
            Class.forName(driver);
            mySQLConnection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return mySQLConnection;
    }
    
    public static void disconnectFromMySQL() {
        try {
            if (mySQLConnection != null) {
                mySQLConnection.close();
                System.out.println();
                System.out.println("[!] T'has desconectat de la base de dades de MySQL.");
            }
        } catch (SQLException e) {
            System.out.println();
            System.err.println("[!] No s'ha pogut desconectar de la base de dades de MySQL.");
        }
    }
    
}
