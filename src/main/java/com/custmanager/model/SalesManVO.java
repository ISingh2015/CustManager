package com.custmanager.model;

import com.cust.domain.vo.ElegantSalesMan;
import com.cust.domain.vo.ElegantUser;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class SalesManVO extends Observable {

    private ElegantSalesMan salesMan;
    private ArrayList<ElegantSalesMan> salesManList;
    private ArrayList<ElegantSalesMan> salesManRepList;
    private TableRowSorter<TableModel> tableRowsorter;
    private ArrayList<ElegantUser> managerList;
    private String[] colNames = {"Sales Man ID", "Name", "Manager Id", "Create By", "Create Date", "Frozen"};
    private String[] colNamesManager = {"Manger ID", "Manager Name"};
    private SalesManTableModel salesManModel;
    private UserTableModel managerModel;

    /**
     * @return the salesMan
     */
    public ElegantSalesMan getSalesMan() {
        return salesMan;
    }

    /**
     * @param salesMan the salesMan to set
     */
    public void setSalesMan(ElegantSalesMan salesMan) {
        this.salesMan = salesMan;
        setChanged();
        notifyObservers(this.salesMan);

    }

    /**
     * @return the salesManList
     */
    public ArrayList<ElegantSalesMan> getSalesManList() {
        return salesManList;
    }

    /**
     * @param supList the salesManList to set
     */
    public void setSalesManList(ArrayList<ElegantSalesMan> salesManList) {
        this.salesManList = salesManList;
        setSalesManModel(new SalesManTableModel(colNames, salesManList));
    }

    /**
     * @return the salesManRepList
     */
    public ArrayList<ElegantSalesMan> getSalesManRepList() {
        return salesManRepList;
    }

    /**
     * @param salesManRepList the salesManRepList to set
     */
    public void setSalesManRepList(ArrayList<ElegantSalesMan> salesManRepList) {
        this.salesManRepList = salesManRepList;
    }

    /**
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        return tableRowsorter;
    }

    /**
     * @param tableRowsorter the tableRowsorter to set
     */
    public void setTableRowsorter(TableRowSorter<TableModel> tableRowsorter) {
        this.tableRowsorter = tableRowsorter;
    }

    /**
     * @return the colNames
     */
    public String[] getColNames() {
        return colNames;
    }

    /**
     * @param colNames the colNames to set
     */
    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }

    /**
     * @return the salesManModel
     */
    public SalesManTableModel getSalesManModel() {
        return salesManModel;
    }

    /**
     * @param salesManModel the salesManModel to set
     */
    public void setSalesManModel(SalesManTableModel salesManModel) {
        this.salesManModel = salesManModel;
        setChanged();
        notifyObservers(this.salesManModel);

    }

    /**
     * @return the managerList
     */
    public ArrayList<ElegantUser> getManagerList() {
        return managerList;
    }

    /**
     * @param managerList the managerList to set
     */
    public void setManagerList(ArrayList<ElegantUser> managerList) {
        this.managerList = managerList;
        setManagerModel(new UserTableModel(colNamesManager, managerList));
    }

    /**
     * @return the managerModel
     */
    public UserTableModel getManagerModel() {
        return managerModel;
    }

    /**
     * @param managerModel the managerModel to set
     */
    public void setManagerModel(UserTableModel managerModel) {
        this.managerModel = managerModel;
        setChanged();
        notifyObservers(this.managerModel);

    }

}
