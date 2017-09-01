/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.SalesManTableModel;
import com.custmanager.model.SalesManVO;
import com.custmanager.view.SalesManView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class SalesManKeyController implements KeyListener {

    private SalesManVO salesManModel;
    private SalesManView salesManView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (salesManView.searchText.getText() == null || salesManView.searchText.getText().equals("")) {
            salesManView.tableSalesMan.setRowSorter(new TableRowSorter(salesManView.tableSalesMan.getModel()));
        } else {
            SalesManTableModel salesManTableModel = (SalesManTableModel) salesManView.tableSalesMan.getModel();
            TableRowSorter<SalesManTableModel> tableRowSorter = new TableRowSorter<SalesManTableModel>(salesManTableModel);
            salesManView.tableSalesMan.setRowSorter(tableRowSorter);
            String s = salesManView.searchText.getText();
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
     * @return the salesManView
     */
    public SalesManView getSalesManView() {
        return salesManView;
    }

    /**
     * @param salesManView the salesManView to set
     */
    public void setSalesManView(SalesManView salesManView) {
        this.salesManView = salesManView;
    }


}
