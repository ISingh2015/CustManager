/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.SupTableModel;
import com.custmanager.model.SupplierVO;
import com.custmanager.view.SupplierView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class SupplierKeyController implements KeyListener {

    private SupplierVO supplierModel;
    private SupplierView supplierView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (supplierView.searchText.getText() == null || supplierView.searchText.getText().equals("")) {
            supplierView.tableSuppliers.setRowSorter(new TableRowSorter(supplierView.tableSuppliers.getModel()));
        } else {
            SupTableModel supTableModel = (SupTableModel) supplierView.tableSuppliers.getModel();
            TableRowSorter<SupTableModel> tableRowSorter = new TableRowSorter<SupTableModel>(supTableModel);
            supplierView.tableSuppliers.setRowSorter(tableRowSorter);
            String s = supplierView.searchText.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }
        
    }

    /**
     * @return the supplierModel
     */
    public SupplierVO getSupplierModel() {
        return supplierModel;
    }

    /**
     * @param supplierModel the supplierModel to set
     */
    public void setSupplierModel(SupplierVO supplierModel) {
        this.supplierModel = supplierModel;
    }

    /**
     * @return the supplierView
     */
    public SupplierView getSupplierView() {
        return supplierView;
    }

    /**
     * @param supplierView the supplierView to set
     */
    public void setSupplierView(SupplierView supplierView) {
        this.supplierView = supplierView;
    }

}
