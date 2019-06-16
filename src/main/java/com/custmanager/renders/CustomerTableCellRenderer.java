package com.custmanager.renders;

import com.custmanager.util.CustUtil;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class CustomerTableCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;
    SimpleDateFormat sdf = new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            if (value instanceof Date) {
                value = sdf.format(value);
            }
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
