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
    public static String password = "serpis"; //root/serpis
    public static Connection mySQLConnection = null;

    //Methods
    public static void connectToMySQL() {
        try {
            Class.forName(driver);
            mySQLConnection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
