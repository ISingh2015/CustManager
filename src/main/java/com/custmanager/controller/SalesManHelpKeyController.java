/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.SalesManTableModel;
import com.custmanager.model.SalesManVO;
import com.custmanager.view.GeneralBillHelp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class SalesManHelpKeyController implements KeyListener {

    private SalesManVO salesManModel;
    private GeneralBillHelp generalBillelp;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (getGeneralBillelp().searchField.getText() == null || getGeneralBillelp().searchField.getText().equals("")) {
            getGeneralBillelp().tableHelp.setRowSorter(new TableRowSorter(getGeneralBillelp().tableHelp.getModel()));
        } else {
            SalesManTableModel salesManTableModel = (SalesManTableModel) getGeneralBillelp().tableHelp.getModel();
            TableRowSorter<SalesManTableModel> tableRowSorter = new TableRowSorter<SalesManTableModel>(salesManTableModel);
            getGeneralBillelp().tableHelp.setRowSorter(tableRowSorter);
            String s = getGeneralBillelp().searchField.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }
        
    }



    /**
     * @return the salesManModel
     */
    public SalesManVO getSalesManModel() {
        return salesManModel;
    }

    /**
     * @param salesManModel the salesManModel to set
     */
    public void setSalesManModel(SalesManVO salesManModel) {
        this.salesManModel = salesManModel;
    }

    /**
     * @return the generalBillelp
     */
    public GeneralBillHelp getGeneralBillelp() {
        return generalBillelp;
    }

    /**
     * @param generalBillelp the generalBillelp to set
     */
    public void setGeneralBillelp(GeneralBillHelp generalBillelp) {
        this.generalBillelp = generalBillelp;
    }



}
