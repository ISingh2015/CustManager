/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.PurchaseDetailsTableModel;
import com.custmanager.model.BuySellVO;
import com.custmanager.model.PurchaseRtnDetailsTableModel;
import com.custmanager.view.PurchaseRtnView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class PurchaseRtnKeyController implements KeyListener {

    private BuySellVO purchaseRtnModel;
    private PurchaseRtnView purchaseRtnView;

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
            total += Double.parseDouble(purchaseRtnView.freightField.getText());
            total += Double.parseDouble(purchaseRtnView.taxField.getText());
            total = purchaseRtnModel.getBillTotal() - total;
            purchaseRtnView.totBillAmt.setText(Double.toString(total));
        }
        if (purchaseRtnView.searchField.getText() == null || purchaseRtnView.searchField.getText().equals("")) {
//            purchaseView.tablePurchaseDetails.setRowSorter(new TableRowSorter(purchaseView.tablePurchaseDetails.getModel()));
        } else {
            PurchaseRtnDetailsTableModel purchaseRtnDetailsTableModel = (PurchaseRtnDetailsTableModel) purchaseRtnView.tablePurchaseRtnDetails.getModel();
            TableRowSorter<PurchaseRtnDetailsTableModel> tableRowSorter = new TableRowSorter<PurchaseRtnDetailsTableModel>(purchaseRtnDetailsTableModel);
            purchaseRtnView.tablePurchaseRtnDetails.setRowSorter(tableRowSorter);
            String s = purchaseRtnView.searchField.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }

    }

    /**
     * @return the purchaseModel
     */
    public BuySellVO getPurchaseRtnModel() {
        return purchaseRtnModel;
    }

    /**
     * @param purchaseModel the purchaseModel to set
     */
    public void setPurchaseRtnModel(BuySellVO purchaseRtnModel) {
        this.purchaseRtnModel = purchaseRtnModel;
    }

    /**
     * @return the purchaseView
     */
    public PurchaseRtnView getPurchaseRtnView() {
        return purchaseRtnView;
    }

    /**
     * @param purchaseView the purchaseView to set
     */
    public void setPurchaseRtnView(PurchaseRtnView purchaseRtnView) {
        this.purchaseRtnView = purchaseRtnView;
    }

}
