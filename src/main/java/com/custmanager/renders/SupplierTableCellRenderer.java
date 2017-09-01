/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.renders;

import com.custmanager.util.CustUtil;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Inderjit
 */
public class SupplierTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    SimpleDateFormat sdf = new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            if (value instanceof Date) {
                value = sdf.format(value);
            } else {
                value = value.toString();
            }
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
