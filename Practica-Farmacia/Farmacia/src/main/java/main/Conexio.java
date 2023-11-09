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
 * @author Albert
 */
public class Conexio {
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String database = "farmacia";
    public static String hostname = "localhost";
    public static String port = "3306";
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static String username = "root";
    public static String password = "root";
    public static Connection conn = null;

    public static Connection conectarMySQL() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("[!] T'has conectat a la base de dades correctament!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("[!] No s'ha pogut conectar a la base de dades. ");
        }
        return conn;
    }

    public static void desconectarMySQL() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("[!] Estas desconectat de la base de dades.");
            }
        } catch (SQLException e) {
            System.out.println("[!] No s'ha pogut desconectar de la base de dadees.");
        }
    }

}
