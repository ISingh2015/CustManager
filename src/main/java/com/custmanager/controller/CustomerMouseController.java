/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.domain.vo.AddressXML;
import com.custmanager.model.CustomerVO;
import com.custmanager.util.CustUtil;
import com.custmanager.view.CustomerView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Inderjit
 */
public class CustomerMouseController implements MouseListener {

    private CustomerVO customerModel;
    private CustomerView customerView;

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        if (e.getClickCount() == 2) {
            int row = target.getSelectedRow();
            AddressXML addressXMLVO;
            customerModel.setCustomer(customerModel.getCustList().get(target.convertRowIndexToModel(row)));
            String addressXML = customerModel.getCustomer().getAddressesXML();
            if (addressXML != null)  {
                addressXMLVO = (AddressXML) CustUtil.getXStreamInstance().fromXML(addressXML);
                customerModel.setAddressXML(addressXMLVO);
                customerModel.getCountryComboModel().setSelectedCountryById(addressXMLVO.getCountry());
            } else {
                customerModel.setAddressXML(new AddressXML("", "", "", 0l, 0l, "", "", 0l, 0l, 0l));                
            }
            customerView.countryCombo.setSelectedItem(customerModel.getAddressXML().getCountry());
            target.setEnabled(false);
            customerView.searchText.setEnabled(false);
            customerView.initButtonsFordelete();
            customerView.nameField.requestFocus();
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
     * @return the customerModel
     */
    public CustomerVO getCustomerModel() {
        return customerModel;
    }

    /**
     * @param customerModel the customerModel to set
     */
    public void setCustomerModel(CustomerVO customerModel) {
        this.customerModel = customerModel;
    }

    /**
     * @return the customerView
     */
    public CustomerView getCustomerView() {
        return customerView;
    }

    /**
     * @param customerView the customerView to set
     */
    public void setCustomerView(CustomerView customerView) {
        this.customerView = customerView;
    }

}
