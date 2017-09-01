/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.ProductVO;
import com.custmanager.view.ProductView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Inderjit
 */
public class ProductMouseController implements MouseListener {

    private ProductVO productModel;
    private ProductView productView;

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        if (e.getClickCount() == 2) {
            int row = target.getSelectedRow();
            productModel.setProduct(productModel.getProdList().get(target.convertRowIndexToModel(row)));
            target.setEnabled(false);
            productView.searchText.setEnabled(false);
            productView.initButtonsFordelete();
            productView.catField.requestFocus();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * @return the productModel
     */
    public ProductVO getProductModel() {
        return productModel;
    }

    /**
     * @param customerModel the productModel to set
     */
    public void setProductModel(ProductVO customerModel) {
        this.productModel = customerModel;
    }

    /**
     * @return the productView
     */
    public ProductView getProductView() {
        return productView;
    }

    /**
     * @param customerView the productView to set
     */
    public void setProductView(ProductView customerView) {
        this.productView = customerView;
    }

}
