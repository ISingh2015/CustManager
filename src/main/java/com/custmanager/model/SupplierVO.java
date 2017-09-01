/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.AddressXML;
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.vo.ElegantSupplier;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class SupplierVO extends Observable {

    private ElegantSupplier supplier;
    private AddressXML addressXML;
    private ArrayList<ElegantSupplier> supList;
    private ArrayList<ElegantSupplier> supRepList;
    private TableRowSorter<TableModel> tableRowsorter;
    private ArrayList<ElegantCountry> countryList;
    private CountryComboModelNew countryComboModel;
    private String[] colNames = {"Supplier ID", "Supplier Name", "Payment Terms", "Credit Limit", "Create By", "Create Date", "Frozen"};
    private SupTableModel supModel;

    public SupplierVO() {
        initAll();
    }

    public void initAll() {
        setSupplier(new ElegantSupplier());
        setSupList(new ArrayList<>());
        setSupRepList(new ArrayList<>());
    }

    /**
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        if (tableRowsorter == null) {
            setTableRowsorter(new TableRowSorter(getSupModel()));
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
     * @return the supplier
     */
    public ElegantSupplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(ElegantSupplier supplier) {
        this.supplier = supplier;
        setChanged();
        notifyObservers(this.supplier);

    }

    /**
     * @return the supList
     */
    public ArrayList<ElegantSupplier> getSupList() {
        return this.supList;
    }

    /**
     * @param supList the supList to set
     */
    public void setSupList(ArrayList<ElegantSupplier> supList) {
        this.supList = supList;
        setSupModel(new SupTableModel(getColNames(), getSupList()));
    }

    /**
     * @return the supRepList
     */
    public ArrayList<ElegantSupplier> getSupRepList() {
        return supRepList;
    }

    /**
     * @param supRepList the supRepList to set
     */
    public void setSupRepList(ArrayList<ElegantSupplier> supRepList) {
        this.supRepList = supRepList;
        setChanged();
        notifyObservers(supRepList);

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
     * @return the supModel
     */
    public SupTableModel getSupModel() {
        return supModel;
    }

    /**
     * @param supModel the supModel to set
     */
    public void setSupModel(SupTableModel supModel) {
        this.supModel = supModel;
        setChanged();
        notifyObservers(this.supModel);
    }

    /**
     * @return the addressXML
     */
    public AddressXML getAddressXML() {
        return addressXML;
    }

    /**
     * @param addressXML the addressXML to set
     */
    public void setAddressXML(AddressXML addressXML) {
        this.addressXML = addressXML;
        setChanged();
        notifyObservers(addressXML);
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
        this.countryList = countryList;
        if (countryList != null && !countryList.isEmpty()) {
            ElegantCountry[] list = new ElegantCountry[countryList.size()];
            countryList.toArray(list);
            setCountryComboModel(new CountryComboModelNew(list));
        }
//        setChanged();
//        notifyObservers(countryList);
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
    public void setCountryComboModel(CountryComboModelNew countryComboModel) {
        this.countryComboModel = countryComboModel;
//        if (getCountryList() != null && !getCountryList().isEmpty()) {
//            String[] countryNamesList = createListForModel(getCountryList());
//            getCountryComboModel().setCountryList(countryNamesList);
//        }
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
}
