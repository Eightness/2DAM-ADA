package model;

import java.sql.Date;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
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
    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
    public String toString() {
        return "ID: " + getPoid() +
        "\nLatitud: " + getLatitude() +
        "\nLongitud: " + getLongitude() +
        "\nPaïs: " + getCountry() +
        "\nCiutat: " + getCity() +
        "\nDescripció: " + getDescription() +
        "\nActualitzat: " + getUpdated();
    }

}
