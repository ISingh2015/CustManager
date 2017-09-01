/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.CountryVO;
import com.custmanager.util.CustUtil;
import com.custmanager.view.CountryView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Inderjit
 */
public class CountryMouseController implements MouseListener {

    private CountryVO countryModel;
    private CountryView countryView;

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            JTable target = (JTable) e.getSource();
            if (e.getClickCount() == 2) {
                int row = target.getSelectedRow();
                getCountryModel().setCountry(getCountryModel().getCountryList().get(target.convertRowIndexToModel(row)));
                target.setEnabled(false);
                getCountryView().searchText.setEnabled(false);
                getCountryView().initButtonsFordelete();
                getCountryView().countryCdField.requestFocus();
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            CustUtil.showErrorDialogue(ae.getMessage());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
