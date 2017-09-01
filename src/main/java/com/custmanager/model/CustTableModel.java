/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantCustomer;
import com.custmanager.CustManagerUtil;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class CustTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private List<ElegantCustomer> data;

    public CustTableModel(String[] colNames, List<ElegantCustomer> tableData) {
        this.columnNames = colNames;
        this.data = tableData;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return (getData() != null && !data.isEmpty()) ? getData().size() : 0;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Cust ID";
                break;
            case 1:
                name = "Cust Name";
                break;
            case 2:
                name = "Cust Pay-Terms";
                break;
            case 3:
                name = "Cust Cr-Limit";
                break;
            case 4:
                name = "Create By";
                break;
            case 5:
                name = "Create Date";
                break;
            case 6:
                name = "Frozen";
                break;
                
        }
        return name;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ElegantCustomer customer = getData().get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = customer.getCustID();
                break;
            case 1:
                value = customer.getCustName();
                break;
            case 2:
                if (customer.getPaymentTerms() != null) {
                    value = CustManagerUtil.getCustFormater().format(customer.getPaymentTerms());
                }
                break;
            case 3:
                if (customer.getCreditLimit() != null) {
                    value = CustManagerUtil.getCustFormater().format(customer.getCreditLimit());
                }
                break;
            case 4:
                value = customer.getUserID();
                break;
            case 5:
                value = customer.getCreateDate();
                break;
            case 6:
                value = customer.getFrozen();
                break;

        }
        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = Long.class;
        switch (columnIndex) {
            case 0:
                break;
            case 5:
                break;
            case 1:
                break;
            case 2:
                type = String.class;
                break;
            case 3:
                type = String.class;
                break;
            case 4:
                type = String.class;
                break;
            case 6:
                type = Date.class;                
                break;
            case 7:
                type = Integer.class;                
                break;

        }
        return type;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * @return the data
     */
    public List<ElegantCustomer> getData() {
        return data;
    }

}
