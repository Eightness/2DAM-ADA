package controller;

import dao.DAOMongoDB;
import dao.DAOMySQL;
import model.ModelPOI;
import view.ViewPOI;
import java.util.ArrayList;

/**
 *
 * @author Albert Lozano Blasco
 * @version 1.0
 */
public class ControllerMySQL {
    //Attributes
    private final DAOMySQL daoMySQL = new DAOMySQL();
    private final ViewPOI viewPOI = new ViewPOI();
    private final DAOMongoDB daoMongoDB = new DAOMongoDB();
    
    //Methods
    public int getCurrentItems() {
        return daoMySQL.DAOgetCurrentItems();
    }
    
    public void insertItem(ModelPOI createdPOI) {
        daoMySQL.DAOinsertItem(createdPOI);
    }

    public void insertVariousItems(ArrayList<ModelPOI> createdPOIs) {
        daoMySQL.DAOinsertVariousItems(createdPOIs);
    }

    public void getAllItems(Boolean ordered) {
        viewPOI.showPOIs(daoMySQL.DAOgetAllItems(ordered));
    }

    public void getItemById(int poid) {
        viewPOI.showPOI(daoMySQL.DAOgetItemById(poid));
    }

    public void getItemsById(ArrayList<Integer> poids) {
        viewPOI.showPOIs(daoMySQL.DAOgetItemsById(poids));
    }

    public void deleteAllItems() {
        daoMySQL.DAOdeleteAllItems();
    }

    public void deleteItemById(int poid) {
        daoMySQL.DAOdeleteItemById(poid);
    }

    public void deleteItemsById(ArrayList<Integer> poids) {
        daoMySQL.DAOdeleteItemsById(poids);
    }
    
    public void synchronizeDatabase() {
        daoMongoDB.DAOsynchronizeDatabase(daoMySQL.DAOgetAllItems(true));
    }

    public void importItems() {

    }
    
    public void insertDefaultRows() {
        daoMySQL.DAOdefaultRows();
    }
}
