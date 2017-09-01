/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantCountry;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Inderjit
 */
public class CountryComboModelNew extends DefaultComboBoxModel {

    private static final long serialVersionUID = 1L;


    public CountryComboModelNew(ElegantCountry[] countryList) {
        super(countryList);
    }
    public ElegantCountry getSelectedItem () {
        ElegantCountry country = (ElegantCountry) super.getSelectedItem();
        return country;
    }
    
    public void setSelectedCountryById(long elegantCountryId) {
        for (int cnt=0; cnt<= super.getSize(); cnt ++) {
            ElegantCountry country = (ElegantCountry) super.getElementAt(cnt);
            if (country.getCountryID()==elegantCountryId) {
                super.setSelectedItem(country);
                break;
            }
        }
    }
    public void setSelectedCountryByName(String elegantCountryName) {
        if (elegantCountryName.equals("")) return;
        for (int cnt=0; cnt<= super.getSize(); cnt ++) { 
            ElegantCountry country = (ElegantCountry) super.getElementAt(cnt);
            if (country.getCountryName().equalsIgnoreCase(elegantCountryName)) {
                super.setSelectedItem(country);
                break;
            }
        }
    }
    
}
