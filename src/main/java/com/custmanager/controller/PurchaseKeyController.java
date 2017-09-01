/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.PurchaseDetailsTableModel;
import com.custmanager.model.BuySellVO;
import com.custmanager.view.PurchaseView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class PurchaseKeyController implements KeyListener {

    private BuySellVO purchaseModel;
    private PurchaseView purchaseView;

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
            total += Double.parseDouble(purchaseView.freightField.getText());
            total += Double.parseDouble(purchaseView.taxField.getText());
            total = purchaseModel.getBillTotal() - total;
            purchaseView.totBillAmt.setText(Double.toString(total));
        }
        if (purchaseView.searchField.getText() == null || purchaseView.searchField.getText().equals("")) {
//            purchaseView.tablePurchaseDetails.setRowSorter(new TableRowSorter(purchaseView.tablePurchaseDetails.getModel()));
        } else {
            PurchaseDetailsTableModel purchaseTableModel = (PurchaseDetailsTableModel) purchaseView.tablePurchaseDetails.getModel();
            TableRowSorter<PurchaseDetailsTableModel> tableRowSorter = new TableRowSorter<PurchaseDetailsTableModel>(purchaseTableModel);
            purchaseView.tablePurchaseDetails.setRowSorter(tableRowSorter);
            String s = purchaseView.searchField.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }

    }

    /**
     * @return the purchaseModel
     */
    public BuySellVO getPurchaseModel() {
        return purchaseModel;
    }

    /**
     * @param purchaseModel the purchaseModel to set
     */
    public void setPurchaseModel(BuySellVO purchaseModel) {
        this.purchaseModel = purchaseModel;
    }

    /**
     * @return the purchaseView
     */
    public PurchaseView getPurchaseView() {
        return purchaseView;
    }

    /**
     * @param purchaseView the purchaseView to set
     */
    public void setPurchaseView(PurchaseView purchaseView) {
        this.purchaseView = purchaseView;
    }

}
