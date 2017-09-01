/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Inderjit
 */
public class CountryComboModel extends AbstractListModel implements ComboBoxModel {

    private static final long serialVersionUID = 1L;
    private String countryList[] = {""};
    String selectedCountry = null;

    public CountryComboModel() {
    }

    public int getSize() {
        return (countryList == null ? 0 : countryList.length);
    }

    public Object getElementAt(int index) {
        return countryList[index];
    }

    public void setSelectedItem(Object anItem) {
        selectedCountry = (String) anItem;
    }

    public Object getSelectedItem() {
        return selectedCountry;
    }

    /**
     * @param countryList the countryList to set
     */
    public void setCountryList(String[] countryList) {
        this.countryList = countryList;
    }

}
