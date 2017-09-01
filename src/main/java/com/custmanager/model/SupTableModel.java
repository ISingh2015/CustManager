/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantSupplier;
import com.custmanager.CustManagerUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class SupTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private ArrayList<ElegantSupplier> data;

    public SupTableModel(String[] colNames, ArrayList<ElegantSupplier> tableData) {
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
                name = "Sup ID";
                break;
            case 1:
                name = "Sup Name";
                break;
            case 2:
                name = "Sup Pay-Terms";
                break;
            case 3:
                name = "Sup Cr-Limit";
                break;
            case 4:
                name = "Created By";
                break;
            case 5:
                name = "Created Date";
                break;
            case 6:
                name = "Frozen";
                break;

        }
        return name;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ElegantSupplier supplier = getData().get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = supplier.getSupID();
                break;
            case 1:
                value = supplier.getSupName();
                break;
            case 2:
                if (supplier.getPaymentTerms() != null) {
                    value = CustManagerUtil.getCustFormater().format(supplier.getPaymentTerms());
                }
                break;
            case 3:
                if (supplier.getCreditLimit() != null) {
                    value = CustManagerUtil.getCustFormater().format(supplier.getCreditLimit());
                }
                break;
            case 4:
                value = supplier.getUserID();
                break;
            case 5:
                value = supplier.getCreateDate();
                break;
            case 6:
                value = supplier.getFrozen();
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
            case 4:
                break;
            case 1:
                type = String.class;
                break;
            case 2:
                type = String.class;
                break;
            case 3:
                type = String.class;
                break;
            case 5:
                type = Date.class;
                break;
            case 6:
                type = Integer.class;
                break;

        }
        return type;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * @return the data
     */
    public ArrayList<ElegantSupplier> getData() {
        return data;
    }

}
