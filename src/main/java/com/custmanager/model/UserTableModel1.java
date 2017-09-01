/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantUser;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class UserTableModel1 extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private List<ElegantUser> data;

    public UserTableModel1(String[] colNames, List<ElegantUser> tableData) {
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
                name = "User ID";
                break;
            case 1:
                name = "User Name";
                break;
            case 2:
                name = "Login ID";
                break;
            case 3:
                name = "Tel No";
                break;
            case 4:
                name = "Mob No";
                break;
            case 5:
                name = "Email";
                break;
            case 6:
                name = "Status";
                break;
                
        }
        return name;
    }

    public Object getValueAt(int row, int col) {
        ElegantUser user = data.get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = user.getUserID();
                break;
            case 1:
                value = user.getUserName();
                break;
            case 2:
                value = user.getUserLoginName();
                break;
            case 3:
                value = user.getTelephoneNo();
                break;
            case 4:
                value = user.getMobileNo();
                break;
            case 5:
                value = user.getEmailId();
                break;
            case 6:
                value = user.getAccountStatus();
                break;

        }
        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = Long.class;
        switch (columnIndex) {
            case 1:
                type = String.class;                
                break;
        }
        return type;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
