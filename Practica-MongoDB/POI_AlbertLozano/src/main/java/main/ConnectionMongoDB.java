/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class ConnectionMongoDB {
    //Attributes
    public static MongoClient mongoDBConnection = null;
    public static MongoDatabase mongoDBDatabase = null;
    
    //Methods
    public static MongoClient connectToMongoDB() {
        try {
            mongoDBConnection = new MongoClient();
            return mongoDBConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void disconnectFromMongoDB() {
        try {
            if (mongoDBConnection != null) {
                mongoDBConnection.close();
                System.out.println();
                System.out.println("[!] T'has desconectat de la base de dades de MongoDB.");
            }
        } catch (Exception ex) {
            System.out.println();
            System.err.println("[!] No s'ha pogut desconectar de la base de dades de MongoDB.");
        }
    }
    
    public static MongoDatabase getDatabase(String databaseName) {
        try {
            mongoDBDatabase = mongoDBConnection.getDatabase(databaseName);
            return mongoDBDatabase;
        } catch (Exception e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut accedir a la colecció.");
        }
        return null;
    }
    
    public static void disableMongoLogging() {
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
    }
}
