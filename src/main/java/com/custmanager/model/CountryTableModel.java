/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantCountry;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class CountryTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private List<ElegantCountry> data;

    public CountryTableModel(String[] colNames, List<ElegantCountry> tableData) {
        this.columnNames = colNames;
        this.data = tableData;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return (data != null && !data.isEmpty()) ? data.size() : 0;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Country ID";
                break;
            case 1:
                name = "Country Code";
                break;
            case 2:
                name = "Name";
                break;
            case 3:
                name = "Currency";
                break;
                
            case 4:
                name = "Exch.Rate";
                break;
        }
        return name;
    }

    public Object getValueAt(int row, int col) {
        ElegantCountry country = data.get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = country.getCountryID();
                break;
            case 1:
                value = country.getCountryCd();
                break;
            case 2:
                value = country.getCountryName();
                break;
            case 3:
                value = country.getCurrency();
                break;
            case 4:
                value = country.getExchangeRate();
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
                type = String.class;
                break;
            case 3:
                type = String.class;
                break;
            case 4:
                type = Double.class;
                break;
        }
        return type;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
