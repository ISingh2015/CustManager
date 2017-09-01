/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.PurchaseTableModel;
//import com.custmanager.model.BuySellVO;
import com.custmanager.model.CustTableModel;
import com.custmanager.model.ProdTableModel;
//import com.custmanager.model.ProductVO;
import com.custmanager.model.PurchaseRtnTableModel;
import com.custmanager.model.SaleRtnTableModel;
import com.custmanager.model.SalesTableModel;
import com.custmanager.model.SupTableModel;
import com.custmanager.model.UserTableModel;
//import com.custmanager.model.SupplierVO;
import com.custmanager.view.GeneralBillHelp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class GeneralHelpKeyController implements KeyListener {

//    private SupplierVO supplierModel;
//    private BuySellVO purchaseModel;
//    private ProductVO productModel;
    private GeneralBillHelp generalBillCodeHelp;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (getGeneralBillCodeHelp().searchField.getText() == null || getGeneralBillCodeHelp().searchField.getText().equals("")) {
            getGeneralBillCodeHelp().tableHelp.setRowSorter(new TableRowSorter(getGeneralBillCodeHelp().tableHelp.getModel()));
        } else {
            if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof SupTableModel) {
                SupTableModel supTableModel = (SupTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<SupTableModel> tableRowSorter = new TableRowSorter<>(supTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof PurchaseTableModel) {
                PurchaseTableModel buySellTableModel = (PurchaseTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<PurchaseTableModel> tableRowSorter = new TableRowSorter<>(buySellTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof PurchaseRtnTableModel) {
                PurchaseRtnTableModel buySellTableModel = (PurchaseRtnTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<PurchaseRtnTableModel> tableRowSorter = new TableRowSorter<>(buySellTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
                
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof ProdTableModel) {
                ProdTableModel prodTableModel = (ProdTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<ProdTableModel> tableRowSorter = new TableRowSorter<>(prodTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof SalesTableModel) {
                SalesTableModel saleTableModel = (SalesTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<SalesTableModel> tableRowSorter = new TableRowSorter<>(saleTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof SaleRtnTableModel) {
                SaleRtnTableModel saleRtnTableModel = (SaleRtnTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<SaleRtnTableModel> tableRowSorter = new TableRowSorter<>(saleRtnTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
                
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof CustTableModel) {
                CustTableModel custTableModel = (CustTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<CustTableModel> tableRowSorter = new TableRowSorter<>(custTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
            } else if (getGeneralBillCodeHelp().tableHelp.getModel() instanceof UserTableModel) {
                UserTableModel userTableModel = (UserTableModel) getGeneralBillCodeHelp().tableHelp.getModel();
                TableRowSorter<UserTableModel> tableRowSorter = new TableRowSorter<>(userTableModel);
                getGeneralBillCodeHelp().tableHelp.setRowSorter(tableRowSorter);
                String s = getGeneralBillCodeHelp().searchField.getText();
                s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
                tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
            }

        }
    }

//    /**
//     * @return the supplierModel
//     */
//    public SupplierVO getSupplierModel() {
//        return supplierModel;
//    }
//
//    /**
//     * @param supplierModel the supplierModel to set
//     */
//    public void setSupplierModel(SupplierVO supplierModel) {
//        this.supplierModel = supplierModel;
//    }
//
//    /**
//     * @return the purchaseModel
//     */
//    public BuySellVO getPurchaseModel() {
//        return purchaseModel;
//    }
//
//    /**
//     * @param purchaseModel the purchaseModel to set
//     */
//    public void setPurchaseModel(BuySellVO purchaseModel) {
//        this.purchaseModel = purchaseModel;
//    }
//
    /**
     * @return the generalBillCodeHelp
     */
    public GeneralBillHelp getGeneralBillCodeHelp() {
        return generalBillCodeHelp;
    }

    /**
     * @param generalBillCodeHelp the generalBillCodeHelp to set
     */
    public void setGeneralBillCodeHelp(GeneralBillHelp generalBillCodeHelp) {
        this.generalBillCodeHelp = generalBillCodeHelp;
    }

//    /**
//     * @return the productModel
//     */
//    public ProductVO getProductModel() {
//        return productModel;
//    }
//
//    /**
//     * @param productModel the productModel to set
//     */
//    public void setProductModel(ProductVO productModel) {
//        this.productModel = productModel;
//    }

}
