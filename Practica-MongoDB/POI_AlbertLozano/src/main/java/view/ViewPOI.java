package view;

import model.ModelPOI;
import java.util.ArrayList;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class ViewPOI {
    //Attributes
    private final Input input = new Input();

    //Methods
    public void showPOI(ModelPOI poi) {
        if (poi == null) {
            return;
        }
        System.out.println();
        System.out.println(poi);
    }

    public void showPOIs(ArrayList<ModelPOI> pois) {
        for (int i = 0; i < pois.size(); i++) {
            if (i % 4 == 0 && i > 0) {
                input.getString("Presiona 'Intro' per a continuar... ");
            }
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Punt d'interés " + (i + 1) + ":");
            showPOI(pois.get(i));
        }
    }
}
