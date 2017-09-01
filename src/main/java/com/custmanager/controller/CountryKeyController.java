/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.CountryTableModel;
import com.custmanager.model.CountryVO;
import com.custmanager.view.CountryView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class CountryKeyController implements KeyListener {

    private CountryVO countryModel;
    private CountryView countryView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (getCountryView().searchText.getText() == null || getCountryView().searchText.getText().equals("")) {
            getCountryView().tableCountry.setRowSorter(new TableRowSorter(getCountryView().tableCountry.getModel()));
        } else {
            CountryTableModel countryTableModel = (CountryTableModel) getCountryView().tableCountry.getModel();
            TableRowSorter<CountryTableModel> tableRowSorter = new TableRowSorter<CountryTableModel>(countryTableModel);
            getCountryView().tableCountry.setRowSorter(tableRowSorter);
            String s = getCountryView().searchText.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }
        
    }

    /**
     * @return the countryModel
     */
    public CountryVO getCountryModel() {
        return countryModel;
    }

    /**
     * @param countryModel the countryModel to set
     */
    public void setCountryModel(CountryVO countryModel) {
        this.countryModel = countryModel;
    }

    /**
     * @return the countryView
     */
    public CountryView getCountryView() {
        return countryView;
    }

    /**
     * @param countryView the countryView to set
     */
    public void setCountryView(CountryView countryView) {
        this.countryView = countryView;
    }

}
