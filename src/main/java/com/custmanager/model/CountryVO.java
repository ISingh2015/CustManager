/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantCountry;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class CountryVO extends Observable {

    private ElegantCountry country;
    private ArrayList<ElegantCountry> countryRepList;
    private ArrayList<ElegantCountry> countryList;
    private TableRowSorter<TableModel> tableRowsorter;
    private String[] colNames = {"Country ID", "Country Code", "Name", "Currency Code", "Exchange Rate"};
    private CountryTableModel countryModel;

    public CountryVO() {
        initAll();
    }

    private void initAll() {
        setCountry(new ElegantCountry());
        setCountryList(new ArrayList<>());
        setCountryRepList(new ArrayList<>());
    }

    /**
     * @return the country
     */
    public ElegantCountry getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(ElegantCountry country) {
        this.country = country;
        setChanged();
        notifyObservers(country);

    }

    /**
     * @return the countryRepList
     */
    public ArrayList<ElegantCountry> getCountryRepList() {
        return countryRepList;
    }

    /**
     * @param countryRepList the countryRepList to set
     */
    public void setCountryRepList(ArrayList<ElegantCountry> countryRepList) {
        this.countryRepList = countryRepList;
        setChanged();
        notifyObservers(countryRepList);

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
        setCountryModel(new CountryTableModel(getColNames(),getCountryList()));
//        setChanged();
//        notifyObservers(countryList);
        
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
     * @return the countryModel
     */
    public CountryTableModel getCountryModel() {
        return countryModel;
    }

    /**
     * @param countryModel the countryModel to set
     */
    public void setCountryModel(CountryTableModel countryModel) {
        this.countryModel = countryModel;
        setChanged();
        notifyObservers(countryModel);
    }

}
