/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;


import com.cust.domain.vo.ElegantProduct;
import com.custmanager.CustManagerUtil;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class ProdTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private ArrayList<ElegantProduct> data;

    public ProdTableModel(String[] colNames, ArrayList<ElegantProduct> tableData) {
        this.columnNames = colNames;
        this.data = tableData;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (getData() != null &&  !data.isEmpty() ) ? getData().size() : 0; 
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Product ID";
                break;
            case 1:
                name = "Product Name";
                break;
            case 2:
                name = "In-Stock";
                break;
            case 3:
                name = "Re-Order Point";
                break;                
            case 4:
                name = "Standard Cost";
                break;
            case 5:
                name = "List Price";
                break;
            case 6:
                name = "Color";
                break;
                
        }
        return name;
    }

    public Object getValueAt(int row, int col) {
        ElegantProduct product = getData().get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = product.getProdID();
                break;
            case 1:
                value = product.getProdName();
                break;
            case 2:
                value = CustManagerUtil.getCustFormater().format(product.getOpStock()+product.getMinInStock());
                break;
            case 3:
                value = product.getReOrderPoint();
                break;
            case 4:
                value = product.getStandardCost();
                break;
            case 5:
                value = product.getListPrice();
                break;
            case 6:
                value = product.getColor();
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
                type = Integer.class;
                break;
            case 3:
                type = Double.class;
                break;
            case 4:
                type = Double.class;
                break;
            case 5:
                type = Double.class;
                break;
            case 6:
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
    public ArrayList<ElegantProduct> getData() {
        return data;
    }

}
