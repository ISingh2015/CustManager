/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantCountry;

import com.cust.domain.vo.ElegantUser;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit Singh Sanhotra
 */
public class UserAdminVO extends Observable {

    private ElegantUser elegantUser;
    private ArrayList<ElegantUser> elegantUserList;
    private ArrayList<ElegantUser> elegantUserRepList;
    private ArrayList<ElegantCountry> countryList;
    private CountryComboModelNew countryComboModel;
    private String[] colNames = {"Supplier ID", "Supplier Name", "Payment Terms", "Credit Limit", "Create By", "Create Date", "Frozen"};
    private UserTableModel1 userModel;
    private TableRowSorter<TableModel> tableRowsorter;

    public void initAll() {
        setElegantUser(new ElegantUser());
        setElegantUserList(new ArrayList<>());
        setElegantUserRepList(new ArrayList<>());
    }

    /**
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        if (tableRowsorter == null) {
            setTableRowsorter(new TableRowSorter(getUserModel()));
        }
        return tableRowsorter;

    }

    /**
     * @param tableRowsorter the tableRowsorter to set
     */
    public void setTableRowsorter(TableRowSorter<TableModel> tableRowsorter) {
        this.tableRowsorter = tableRowsorter;
    }

    /**
     * @return the elegantUser
     */
    public ElegantUser getElegantUser() {
        return elegantUser;
    }

    /**
     * @param elegantUser the elegantUser to set
     */
    public void setElegantUser(ElegantUser elegantUser) {
        this.elegantUser = elegantUser;
        setChanged();
        notifyObservers(this.elegantUser);

    }

    /**
     * @return the elegantUserList
     */
    public ArrayList<ElegantUser> getElegantUserList() {
        return elegantUserList;
    }

    /**
     * @param elegantUserList the elegantUserList to set
     */
    public void setElegantUserList(ArrayList<ElegantUser> elegantUserList) {
        this.elegantUserList = elegantUserList;
        setUserModel(new UserTableModel1 (getColNames(), getElegantUserList())); 
    }

    /**
     * @return the elegantUserRepList
     */
    public ArrayList<ElegantUser> getElegantUserRepList() {
        return elegantUserRepList;
    }

    /**
     * @param elegantUserRepList the elegantUserRepList to set
     */
    public void setElegantUserRepList(ArrayList<ElegantUser> elegantUserRepList) {
        this.elegantUserRepList = elegantUserRepList;
        setChanged();
        notifyObservers(this.elegantUserRepList);

    }

    /**
     * @return the countryList
     */
    public ArrayList<ElegantCountry> getCountryList() {
        return countryList;
    }

    /**
     * @param countryList the countryList to set
     */
    public void setCountryList(ArrayList<ElegantCountry> countryList) {
//        this.countryList = countryList;
//        setCountryComboModel(new CountryComboModel());
        this.countryList = countryList;
        if (countryList != null && !countryList.isEmpty()) {
            ElegantCountry[] list = new ElegantCountry[countryList.size()];
            countryList.toArray(list);
            setCountryComboModel(new CountryComboModelNew(list));
        }

        
    }

    /**
     * @return the countryComboModel
     */
    public CountryComboModelNew getCountryComboModel() {
        return countryComboModel;
    }

    /**
     * @param countryComboModel the countryComboModel to set
     */
    public void setCountryComboModel(CountryComboModelNew countryComboModelNew) {
//        this.countryComboModel = countryComboModel;
//        if (getCountryList() != null && !getCountryList().isEmpty()) {
//            String[] countryNamesList = createListForModel(getCountryList());
//            getCountryComboModel().setCountryList(countryNamesList);
//            setChanged();
//            notifyObservers(this.countryComboModel);
//        }
        this.countryComboModel = countryComboModelNew;
        setChanged();
        notifyObservers(this.countryComboModel);

    }

//    String[] createListForModel(ArrayList<ElegantCountry> countryList) {
//        String[] namesList = new String[countryList.size() + 1];
//        int i = 1;
//        namesList[0] = "";
//        for (ElegantCountry country : countryList) {
//            namesList[i] = country.getCountryName();
//            i++;
//        }
//        return namesList;
//    }

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
     * @return the userModel
     */
    public UserTableModel1 getUserModel() {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(UserTableModel1 userModel) {
        this.userModel = userModel;
        setChanged();
        notifyObservers(this.userModel);

    }

}
