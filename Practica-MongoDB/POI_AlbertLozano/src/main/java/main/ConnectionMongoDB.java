/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
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
    public static void connectToMongoDB() {
        try {
            mongoDBConnection = new MongoClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void disconnectFromMongoDB() {
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

    public static boolean collectionExists(String collectionName, MongoDatabase database) {
        MongoIterable<String> collections = database.listCollectionNames();

        for (String collection : collections) {
            if (collection.equals(collectionName)) return true;
        }
        return false;
    }

    public static void createCollection(String collectionName, MongoDatabase database) {
        try {
            if (collectionExists(collectionName, database)) {
                System.out.println();
                System.out.println("[!] Atenció! Ja existeix la col·lecció " + collectionName);
            } else {
                database.createCollection(collectionName);
                System.out.println();
                System.out.println("[!] La col·lecció " + collectionName + " ha sigut creada.");
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut crear la col·lecció.");
            e.printStackTrace();
        }
    }
    
    public static MongoDatabase getDatabase(String databaseName) {
        try {
            mongoDBDatabase = mongoDBConnection.getDatabase(databaseName);
            return mongoDBDatabase;
        } catch (Exception e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut accedir a la col·lecció.");
        }
        return null;
    }
    
    public static void disableMongoLogging() {
        ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(Level.ERROR);
    }
}
