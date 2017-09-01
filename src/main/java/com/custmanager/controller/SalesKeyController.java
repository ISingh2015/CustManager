/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.BuySellVO;
import com.custmanager.model.SalesRtnDetailsTableModel;
import com.custmanager.view.SalesView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class SalesKeyController implements KeyListener {

    private BuySellVO salesModel;
    private SalesView salesView;

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
            total += Double.parseDouble(getSalesView().freightField.getText());
            total += Double.parseDouble(getSalesView().taxField.getText());
            total = getSalesModel().getInvTotal() - total;
            getSalesView().totBillAmt.setText(Double.toString(total));
        }

        if (getSalesView().searchField.getText() == null || getSalesView().searchField.getText().equals("")) {
            getSalesView().tableSalesDetails.setRowSorter(new TableRowSorter(getSalesView().tableSalesDetails.getModel()));
            return;
        }
        SalesRtnDetailsTableModel salesTableModel = (SalesRtnDetailsTableModel) getSalesView().tableSalesDetails.getModel();
        salesTableModel = (SalesRtnDetailsTableModel) getSalesView().tableSalesDetails.getModel();
        TableRowSorter<SalesRtnDetailsTableModel> tableRowSorter = new TableRowSorter<>(salesTableModel);
        getSalesView().tableSalesDetails.setRowSorter(tableRowSorter);
        String s = getSalesView().searchField.getText();
        s = (s != null && !s.equals("") ? "^"+s : "");
        tableRowSorter.setRowFilter(RowFilter.regexFilter(s));

    }

    /**
     * @return the salesModel
     */
    public BuySellVO getSalesModel() {
        return salesModel;
    }

    /**
     * @param salesModel the salesModel to set
     */
    public void setSalesModel(BuySellVO salesModel) {
        this.salesModel = salesModel;
    }

    /**
     * @return the salesView
     */
    public SalesView getSalesView() {
        return salesView;
    }

    /**
     * @param salesView the salesView to set
     */
    public void setSalesView(SalesView salesView) {
        this.salesView = salesView;
    }

}
