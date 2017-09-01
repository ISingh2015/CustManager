/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.CustTableModel;
import com.custmanager.model.CustomerVO;
import com.custmanager.view.CustomerView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class CustomerKeyController implements KeyListener {

    private CustomerVO customerModel;
    private CustomerView customerView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (customerView.searchText.getText() == null || customerView.searchText.getText().equals("")) {
            customerView.tableCustomers.setRowSorter(new TableRowSorter(customerView.tableCustomers.getModel()));            
        } else {
            CustTableModel custTableModel = (CustTableModel) customerView.tableCustomers.getModel();
            TableRowSorter<CustTableModel> tableRowSorter = new TableRowSorter<CustTableModel>(custTableModel);
            customerView.tableCustomers.setRowSorter(tableRowSorter);
            String s = customerView.searchText.getText();
            s = (s!=null && s!= "" ? "^" : "")  + (s!=null && s!= "" ? s : "");
            tableRowSorter.setRowFilter(RowFilter.regexFilter(s));
        }
        
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
