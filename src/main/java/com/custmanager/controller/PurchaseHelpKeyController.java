/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.PurchaseTableModel;
import com.custmanager.model.BuySellVO;
import com.custmanager.view.GeneralBillHelp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class PurchaseHelpKeyController implements KeyListener {

    private BuySellVO purchaseModel;
    private GeneralBillHelp purchaseCodeHelp;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (getPurchaseCodeHelp().searchField.getText() == null || getPurchaseCodeHelp().searchField.getText().equals("")) {
//            getPurchaseCodeHelp().tableHelp.setRowSorter(new TableRowSorter(getPurchaseCodeHelp().tableHelp.getModel()));
        } else {
            PurchaseTableModel purchaseTableModel = (PurchaseTableModel) getPurchaseCodeHelp().tableHelp.getModel();
            TableRowSorter<PurchaseTableModel> tableRowSorter = new TableRowSorter<PurchaseTableModel>(purchaseTableModel);
            getPurchaseCodeHelp().tableHelp.setRowSorter(tableRowSorter);
            String s = getPurchaseCodeHelp().searchField.getText();
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
     * @return the purchaseCodeHelp
     */
    public GeneralBillHelp getPurchaseCodeHelp() {
        return purchaseCodeHelp;
    }

    /**
     * @param purchaseCodeHelp the purchaseCodeHelp to set
     */
    public void setPurchaseCodeHelp(GeneralBillHelp purchaseCodeHelp) {
        this.purchaseCodeHelp = purchaseCodeHelp;
    }


}
