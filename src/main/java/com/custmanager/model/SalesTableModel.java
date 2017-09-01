/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;


import com.cust.domain.vo.ElegantBuySell;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class SalesTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private ArrayList<ElegantBuySell> data;

    public SalesTableModel(String[] colNames, ArrayList<ElegantBuySell> tableData) {
        this.columnNames = colNames;
        this.data = tableData;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (data != null &&  !data.isEmpty() ) ? data.size() : 0; 
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Bill ID";
                break;
            case 1:
                name = "Bill No";
                break;
            case 2:
                name = "Bill Dt";
                break;
            case 3:
                name = "Customer Name";
                break;
            case 4:
                name = "Bill Value";
                break;
        }
        return name;
    }

    public Object getValueAt(int row, int col) {
        ElegantBuySell buySell = data.get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = buySell.getBillID();
                break;
            case 1:
                value = buySell.getBillNo();
                break;
            case 2:
                value = buySell.getBillDt();
                break;
            case 3:
                value = buySell.getBuyerSellerName();
                break;
            case 4:
                value = buySell.getFinalBillAmt();
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
                type = Date.class;
                break;
            case 3:
                type = String.class;
                break;
            case 4:
                type = String.class;
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
    public ArrayList<ElegantBuySell> getData() {
        return data;
    }

}
