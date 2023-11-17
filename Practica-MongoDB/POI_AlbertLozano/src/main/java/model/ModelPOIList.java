package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modelPOIs")
public class ModelPOIList {
    private List<ModelPOI> pois;

    @XmlElement(name = "modelPOI")
    public List<ModelPOI> getPois() {
        return pois;
    }

    public void setPois(List<ModelPOI> pois) {
        this.pois = pois;
    }
    
    public ArrayList<ModelPOI> getPoisToArrayList() {
        return new ArrayList<>(pois);
    }
}
