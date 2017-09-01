/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.domain.vo.AddressXML;
import com.custmanager.model.SupplierVO;
import com.custmanager.util.CustUtil;
import com.custmanager.view.SupplierView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Inderjit
 */
public class SupplierMouseController implements MouseListener {

    private SupplierVO supplierModel;
    private SupplierView supplierView;

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        if (e.getClickCount() == 2) {
            int row = target.getSelectedRow();
            AddressXML addressXMLVO;
            supplierModel.setSupplier(supplierModel.getSupList().get(target.convertRowIndexToModel(row)));
            String addressXML = supplierModel.getSupplier().getAddressesXML();
            if (addressXML != null) {
                addressXMLVO = (AddressXML) CustUtil.getXStreamInstance().fromXML(addressXML);
                supplierModel.setAddressXML(addressXMLVO);
                supplierModel.getCountryComboModel().setSelectedCountryById(supplierModel.getAddressXML().getCountry());
            } else {
                supplierModel.setAddressXML(new AddressXML("", "", "", 0l, 0l, "", "", 0l, 0l, 0l));
            }
            target.setEnabled(false);
            supplierView.searchText.setEnabled(false);
            supplierView.initButtonsFordelete();
            supplierView.nameField.requestFocus();
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
     * @return the supplierModel
     */
    public SupplierVO getSupplierModel() {
        return supplierModel;
    }

    /**
     * @param customerModel the supplierModel to set
     */
    public void setSupplierModel(SupplierVO customerModel) {
        this.supplierModel = customerModel;
    }

    /**
     * @return the supplierView
     */
    public SupplierView getSupplierView() {
        return supplierView;
    }

    /**
     * @param customerView the supplierView to set
     */
    public void setSupplierView(SupplierView customerView) {
        this.supplierView = customerView;
    }

}
