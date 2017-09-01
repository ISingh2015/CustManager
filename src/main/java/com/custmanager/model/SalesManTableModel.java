/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantSalesMan;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class SalesManTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private List<ElegantSalesMan> data;

    public SalesManTableModel(String[] colNames, List<ElegantSalesMan> tableData) {
        this.columnNames = colNames;
        this.data = tableData;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (getData() != null && !data.isEmpty()) ? getData().size() : 0;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "SalesMan ID";
                break;
            case 1:
                name = "Name";
                break;
            case 2:
                name = "Manager ID";
                break;
            case 3:
                name = "Create By";
                break;
            case 4:
                name = "Create Date";
                break;
            case 5:
                name = "Frozen";
                break;
                
        }
        return name;
    }

    public Object getValueAt(int row, int col) {
        ElegantSalesMan salesMan = getData().get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = salesMan.getSalesManID();
                break;
            case 1:
                value = salesMan.getSalesManName();
                break;
            case 2:
                value = salesMan.getManagerId();
                break;
            case 3:
                value = salesMan.getUserID();
                break;
            case 4:
                value = salesMan.getCreateDate();
                break;
            case 5:
                value = salesMan.getFrozen();
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
            case 1:
                type = String.class;
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                type = Date.class;                
                break;
            case 5:
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
    public List<ElegantSalesMan> getData() {
        return data;
    }

}
