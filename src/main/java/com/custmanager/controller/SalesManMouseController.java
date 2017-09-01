/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.SalesManVO;
import com.custmanager.view.SalesManView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Inderjit
 */
public class SalesManMouseController implements MouseListener {

    private SalesManVO salesManModel;
    private SalesManView salesManView;

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        if (e.getClickCount() == 2) {
            int row = target.getSelectedRow();
            getSalesManModel().setSalesMan(getSalesManModel().getSalesManList().get(target.convertRowIndexToModel(row)));
            target.setEnabled(false);
            getSalesManView().searchText.setEnabled(false);
            getSalesManView().initButtonsFordelete();
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
