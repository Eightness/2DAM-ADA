/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.ModelPOIList;

/**
 *
 * @author Albert
 */
public class XMLReader {
    
    public static ModelPOIList readXML(String filePath) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(ModelPOIList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ModelPOIList modelPOIList = (ModelPOIList) jaxbUnmarshaller.unmarshal(file);

            System.out.println();
            System.out.println("[!] S'ha llegit l'arxiu XML.");
            return modelPOIList;
        } catch (JAXBException e) {
            System.out.println();
            System.out.println("[!] Atenció! No s'ha pogut llegir l'arxiu XML correctament.");
            e.printStackTrace();
            return null;
        }
    }
    
}
