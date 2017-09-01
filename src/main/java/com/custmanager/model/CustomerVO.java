/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.AddressXML;
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.vo.ElegantCustomer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class CustomerVO extends Observable {

    private ElegantCustomer customer;
    private AddressXML addressXML;
    private List<ElegantCustomer> custList;
    private ArrayList<ElegantCustomer> custRepList;
    private ArrayList<ElegantCountry> countryList;
    private TableRowSorter<TableModel> tableRowsorter;
    private CountryComboModelNew countryComboModel;
    private String[] colNames = {"Cust ID", "Cust Name", "Payment Terms", "Credit Limit", "Created By", "Create Date", "Frozen"};
    private CustTableModel custModel;

    public CustomerVO() {
        initAll();
    }

    /**
     * @return the customer
     */
    public ElegantCustomer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(ElegantCustomer customer) {
        this.customer = customer;
        setChanged();
        notifyObservers(customer);
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
     * @return the custList
     */
    public List<ElegantCustomer> getCustList() {
        return custList;
    }

    /**
     * @param custList the custList to set
     */
    public void setCustList(List<ElegantCustomer> custList) {
        this.custList = custList;
        setCustModel(new CustTableModel(getColNames(), getCustList()));
//        setChanged();
//        notifyObservers(custList);
    }

    /**
     * @return the custRepList
     */
    public ArrayList<ElegantCustomer> getCustRepList() {
        return custRepList;
    }

    /**
     * @param custRepList the custRepList to set
     */
    public void setCustRepList(ArrayList<ElegantCustomer> custRepList) {
        this.custRepList = custRepList;
        setChanged();
        notifyObservers(custRepList);
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
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        if (tableRowsorter == null) {
            setTableRowsorter(new TableRowSorter(getCustModel()));
        }
        return tableRowsorter;
    }

    /**
     * @param tableRowsorter the tableRowsorter to set
     */
    public void setTableRowsorter(TableRowSorter<TableModel> tableRowsorter) {
        this.tableRowsorter = tableRowsorter;
    }

    public void initAll() {
        setCustomer(new ElegantCustomer());
        setAddressXML(new AddressXML("", "", "", 0l, 0l, "", "", 0l, 0l, 0l));
        setCustList(new LinkedList<>());
        setCustRepList(new ArrayList<>());
//        setCountryComboModel(new CountryComboModel());
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
     * @return the custModel
     */
    public CustTableModel getCustModel() {
        return custModel;
    }

    /**
     * @param custModel the custModel to set
     */
    public void setCustModel(CustTableModel custModel) {
        this.custModel = custModel;
        setChanged();
        notifyObservers(this.custModel);

    }

}
