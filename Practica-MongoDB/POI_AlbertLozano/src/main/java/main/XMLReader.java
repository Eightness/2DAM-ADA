/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.ModelPOI;
/**
 *
 * @author Albert
 */
public class XMLReader {
    
    //Change filePath if needed
    private static final String filePath = "/home/alblozbla/Escriptori/2DAM-ADA/Practica-MongoDB/POI_AlbertLozano/src/main/resources/items.xml";  
    
    //Method to get an array of ModelPOIs
    public static ArrayList<ModelPOI> readXML() {
        //Instantiate the ModelPOI ArrayList
        ArrayList<ModelPOI> poisFromXML = new ArrayList<>();
        //Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            //Parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filePath));
            
            //Optional, but recommended (normalization in dom parsing)
            doc.getDocumentElement().normalize();
            
            //get a list of <modelPOI>
            NodeList list = doc.getElementsByTagName("modelPOI");
            
            for (int i = 0; i < list.getLength(); i++) {
                //get a single node of <modelPOI>
                Node node = list.item(i); 
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Get an element / node
                    Element element = (Element) node;
                    
                    //Get attributes
                    int poid = Integer.parseInt(element.getElementsByTagName("poid").item(0).getTextContent());
                    double latitude = Double.parseDouble(element.getElementsByTagName("latitude").item(0).getTextContent());
                    double longitude = Double.parseDouble(element.getElementsByTagName("longitude").item(0).getTextContent());
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    String city = element.getElementsByTagName("city").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    //Set new date format
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date updated = dateFormat.parse(element.getElementsByTagName("updated").item(0).getTextContent());
                    
                    poisFromXML.add(new ModelPOI(poid, latitude, longitude, country, city, description, updated));
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            System.out.println();
            System.out.println("[!] Atenció! No s'ha pogut llegir l'arxiu XML.");
            e.printStackTrace();
        }
        
        return poisFromXML;
    }
}
