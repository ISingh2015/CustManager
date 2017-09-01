/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.ProdTableModel;
import com.custmanager.model.ProductVO;
import com.custmanager.view.ProductView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class ProductKeyController implements KeyListener {

    private ProductVO productModel;
    private ProductView productView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (productView.searchText.getText() == null || productView.searchText.getText().equals("")) {
            productView.tableProducts.setRowSorter(new TableRowSorter(productView.tableProducts.getModel()));
        } else {
            ProdTableModel prodTableModel = (ProdTableModel) productView.tableProducts.getModel();
            TableRowSorter<ProdTableModel> tableRowSorter = new TableRowSorter<ProdTableModel>(prodTableModel);
            productView.tableProducts.setRowSorter(tableRowSorter);
            String s = productView.searchText.getText();
            s = (s != null && s != "" ? "^" : "") + (s != null && s != "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }
        
    }

    /**
     * @return the productModel
     */
    public ProductVO getProductModel() {
        return productModel;
    }

    /**
     * @param productModel the productModel to set
     */
    public void setProductModel(ProductVO productModel) {
        this.productModel = productModel;
    }

    /**
     * @return the productView
     */
    public ProductView getProductView() {
        return productView;
    }

    /**
     * @param productView the productView to set
     */
    public void setProductView(ProductView productView) {
        this.productView = productView;
    }


}
