package model;

import org.bson.Document;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
@XmlRootElement(name = "modelPOI")
public class ModelPOI {
    //Attributes
    private int poid;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String description;
    private Date updated;

    //Getters and Setters
    @XmlElement
    public int getPoid() {
        return poid;
    }
    
    public void setPoid(int poid) {
        this.poid = poid;
    }
    @XmlElement
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    @XmlElement
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @XmlElement
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    //Constructor
    public ModelPOI() {
        
    }
    
    public ModelPOI(int poid, double latitude, double longitude, String country, String city, String description, Date updated) {
        setPoid(poid);
        setLatitude(latitude);
        setLongitude(longitude);
        setCountry(country);
        setCity(city);
        setDescription(description);
        setUpdated(updated);
    }

    //Methods  
    @Override
    public String toString() {
        return String. format(
        "Columnes\tValors" +
        "\n" +
        "\nId:\t\t" + getPoid() +
        "\nLatitud:\t" + getLatitude() +
        "\nLongitud:\t" + getLongitude() +
        "\nPaïs:\t\t" + getCountry() +
        "\nCiutat:\t\t" + getCity() +
        "\nDescripció:\t" + getDescription() +
        "\nActualitzat:\t" + getUpdated()
        );
    }

    public Document toDocument() {
        Document document = new Document();

        document.append("poid", this.poid);
        document.append("latitude", this.latitude);
        document.append("longitude", this.longitude);
        document.append("country", this.country);
        document.append("city", this.city);
        document.append("description", this.description);
        document.append("updated", this.updated);

        return document;
    }
}
