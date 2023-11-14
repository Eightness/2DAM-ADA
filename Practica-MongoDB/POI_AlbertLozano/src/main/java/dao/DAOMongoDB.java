package dao;

import java.util.ArrayList;
import java.util.Date;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import main.ConnectionMongoDB;
import model.ModelPOI;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class DAOMongoDB {
    //Attributes
    private final MongoDatabase database = ConnectionMongoDB.getDatabase("db");
    private final MongoCollection<Document> collection;
    private Document query;
    {
        assert database != null;
        String collectionName = "pois_al15";
        //Create collection (if not exists)
        ConnectionMongoDB.createCollection(collectionName, database);
        //Get collection
        collection = database.getCollection(collectionName);
    }

    //Setters and Getters
    public void setQuery(String key, Object value) {
        this.query = new Document(key, value);
    }


    //Methods
    
    //CHECKS AND UTILITY
    //--------------------------------------------------------------------------
    private boolean DAOexistsPOI(int poid) {
        setQuery("poid", poid);
        long count = collection.countDocuments(query);
        return count > 0;
    }

    public int DAOgetCurrentItems() {
        return (int) collection.countDocuments();
    }

    private ModelPOI documentToModelPOI(Document document) {
        if (document == null) {
            return null;
        }

        int poid = document.getInteger("poid");
        double latitude = document.getDouble("latitude");
        double longitude = document.getDouble("longitude");
        String country = document.getString("country");
        String city = document.getString("city");
        String description = document.getString("description");
        Date updated = document.getDate("updated");

        return new ModelPOI(poid, latitude, longitude, country, city, description, (java.sql.Date) updated);
    }
    
    //CRUD
    //--------------------------------------------------------------------------
    
    //CREATE
    //--------------------------------------------------------------------------
    
    public boolean DAOinsertItem(ModelPOI createdPOI) {
        if (DAOexistsPOI(createdPOI.getPoid())) {
            System.out.println();
            System.out.println("[!] Ja existeix un punt d'interés amb el poid " + createdPOI.getPoid() + ".");
            return false;
        }
        try {
            collection.insertOne(createdPOI.toDocument());

            System.out.println();
            System.out.println("[!] S'ha inserit el nou item amb èxit.");
            return true;
        } catch (MongoWriteException e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut inserir l'item.");
            return false;
        }
    }

    public void DAOinsertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        int numInserted = 0;

        for (ModelPOI poi: createdPOIs) {
            if (DAOinsertItem(poi)) {
                numInserted++;
            }
        }

        System.out.println();
        System.out.println("[!] S'ha/n insertat " + numInserted + " punt/s d'interés.");
    }
    
    //READ
    //--------------------------------------------------------------------------

    public ArrayList<ModelPOI> DAOgetAllItems(Boolean ordered) {
        ArrayList<ModelPOI> allItems = new ArrayList<>();
        FindIterable<Document> cursor;

        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return allItems;
        }

        if (ordered) {
            cursor = collection.find().sort(Sorts.ascending("poid"));
        } else {
            cursor = collection.find();
        }

        try (MongoCursor<Document> iterator = cursor.iterator()){
            while (iterator.hasNext()) {
                Document document = iterator.next();
                ModelPOI poi = documentToModelPOI(document);
                allItems.add(poi);
            }
        }

        return allItems;
    }

    public ModelPOI DAOgetItemById(int poid) {
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return null;
        } else if (!DAOexistsPOI(poid)) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha cap item amb Id " + poid + ".");
            return null;
        }

        Bson filter = new Document("poid", poid);
        FindIterable<Document> result = collection.find(filter).limit(1);

        return documentToModelPOI(result.first());
    }

    public ArrayList<ModelPOI> DAOgetItemsById(ArrayList<Integer> poids) {
        ArrayList<ModelPOI> itemsById = new ArrayList<>();

        for (Integer poid : poids) {
            ModelPOI poi = DAOgetItemById(poid);
            if (poi != null) {
                itemsById.add(poi);
            }
        }

        return itemsById;
    }
    
    //DELETE
    //--------------------------------------------------------------------------

    public void DAOdeleteAllItems() {
        int numDeleted = DAOgetCurrentItems();

        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return;
        }

        try {
            collection.deleteMany(new Document());
            System.out.println();
            System.out.println("[!] S'ha/n esborrat " + numDeleted + " item/s de la base de dades.");
        } catch(MongoException e) {
            System.out.println();
            System.out.println("[!] No s'han pogut esborrar els items de la base de dades.");
        }
    }

    public boolean DAOdeleteItemById(int poid) {
        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return false;
        } else if (!DAOexistsPOI(poid)) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha cap item amb Id " + poid + ".");
            return false;
        }

        try {
            collection.deleteOne(eq("poid", poid));

            System.out.println();
            System.out.println("[!] S'ha esborrat l'item amb Id " + poid + ".");
            return true;
        } catch (MongoException e) {
            System.out.println();
            System.out.println("[!] No s'ha pogut esborrar l'item de la base de dades amb Id " + poid + ".");
            return false;
        }
    }

    public void DAOdeleteItemsById(ArrayList<Integer> poids) {
        int numDeleted = 0;

        if (DAOgetCurrentItems() == 0) {
            System.out.println();
            System.out.println("[!] Atenció! No hi ha items en la base de dades.");
            return;
        }

        for (Integer poid : poids) {
            if (DAOexistsPOI(poid) && DAOdeleteItemById(poid)) {
                numDeleted++;
            }
        }

        System.out.println();
        System.out.println("[!] S'han esborrat un total de " + numDeleted + " d'items.");
    }
    
    //SYNCHRONIZE
    //--------------------------------------------------------------------------

    public void DAOsynchronizeDatabase(ArrayList<ModelPOI> mySQLPOIs) {
        DAOdeleteAllItems();
        DAOinsertVariousItems(mySQLPOIs);
    }
    
    //DEFAULT
    //--------------------------------------------------------------------------
    
    public void DAOinsertDefaultCollection() {
        ArrayList<ModelPOI> pois = new ArrayList<>();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date updated = new java.sql.Date(utilDate.getTime());

        pois.add(new ModelPOI(1, 37.7749, -122.4194, "Estats Units", "San Francisco", "Golden Gate Bridge", updated));
        pois.add(new ModelPOI(2, -22.9083, -43.1964, "Brasil", "Río de Janeiro", "Cristo Redentor", updated));
        pois.add(new ModelPOI(3, 55.7558, 37.6176, "Rússia", "Moscou", "Catedral de São Basílio", updated));
        pois.add(new ModelPOI(4, -12.0464, -77.0428, "Perú", "Lima", "Plaza de Armas", updated));
        pois.add(new ModelPOI(5, 52.5200, 13.4050, "Alemanya", "Berlín", "Porta de Brandenburg", updated));
        pois.add(new ModelPOI(6, 40.7128, -74.0060, "Estats Units", "Nova York", "Central Park", updated));
        pois.add(new ModelPOI(7, -33.8688, 151.2093, "Austràlia", "Sídney", "Opera House", updated));
        pois.add(new ModelPOI(8, 41.9028, 12.4964, "Itàlia", "Roma", "Colosseu", updated));
        pois.add(new ModelPOI(9, 35.6895, 139.6917, "Japó", "Tòquio", "Palau Imperial", updated));
        pois.add(new ModelPOI(10, 30.0444, 31.2357, "Egipte", "Caire", "Piràmides de Giza", updated));

        DAOinsertVariousItems(pois);
    }
}
