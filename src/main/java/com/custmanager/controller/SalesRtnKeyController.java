/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.BuySellVO;
import com.custmanager.model.SalesRtnDetailsTableModel;
import com.custmanager.view.SalesRtnView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class SalesRtnKeyController implements KeyListener {

    private BuySellVO salesRtnModel;
    private SalesRtnView salesRtnView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println(e.getSource().getClass().getName());
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
            double total = 0d;
            total += Double.parseDouble(getSalesRtnView().freightField.getText());
            total += Double.parseDouble(getSalesRtnView().taxField.getText());
            total = getSalesRtnModel().getSaleRtnTotal()- total;
            getSalesRtnView().totBillAmt.setText(Double.toString(total));  
        }

        if (getSalesRtnView().searchField.getText() == null || getSalesRtnView().searchField.getText().equals("")) {
//            getSalesRtnView().tableSalesDetails.setRowSorter(new TableRowSorter(getSalesRtnView().tableSalesDetails.getModel()));
        } else {
            SalesRtnDetailsTableModel salesRtnTableModel = (SalesRtnDetailsTableModel) getSalesRtnView().tableSalesRtnDetails.getModel();
            salesRtnTableModel = (SalesRtnDetailsTableModel) getSalesRtnView().tableSalesRtnDetails.getModel();
            TableRowSorter<SalesRtnDetailsTableModel> tableRowSorter = new TableRowSorter<SalesRtnDetailsTableModel>(salesRtnTableModel);
            getSalesRtnView().tableSalesRtnDetails.setRowSorter(tableRowSorter);
            String s = getSalesRtnView().searchField.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }

    }

    /**
     * @return the salesModel
     */
    public BuySellVO getSalesRtnModel() {
        return salesRtnModel;
    }

    /**
     * @param salesModel the salesModel to set
     */
    public void setSalesRtnModel(BuySellVO salesRtnModel) {
        this.salesRtnModel = salesRtnModel;
    }

    /**
     * @return the salesView
     */
    public SalesRtnView getSalesRtnView() {
        return salesRtnView;
    }

    /**
     * @param salesView the salesView to set
     */
    public void setSalesRtnView(SalesRtnView salesRtnView) {
        this.salesRtnView = salesRtnView;
    }

}
