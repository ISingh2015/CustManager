package com.custmanager.editors;

import com.inder.customcomponents.HelpTextField;
import com.inder.customcomponents.INumberField;
import com.inder.customcomponents.ITextField;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class CustomerTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    JComponent component;
    boolean buttonEnabled = true;

    public CustomerTableCellEditor(JComponent component) {
        this.component = component;
    }

    public CustomerTableCellEditor(JComponent component, boolean buttonEnabled) {
        this.component = component;
        this.buttonEnabled=buttonEnabled;
    }

    @Override
    public Object getCellEditorValue() {
        if ((component instanceof ITextField) || (component instanceof INumberField) || (component instanceof JTextField)) {
            return ((JTextField) component).getText();
        } else if (component instanceof HelpTextField) {
            HelpTextField panel = (HelpTextField) component;
            String text = panel.textField.getText() == null ? "" : panel.textField.getText();
            return text;
        }
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        try {
            if ((component instanceof ITextField) || (component instanceof INumberField) || (component instanceof JTextField)) {
                if (value instanceof String) {
                    ((JTextField) component).setText((String) value);
                }
                if (value instanceof Double) {
                    ((JTextField) component).setText(Double.toString((Double) value));
                }
                if (value instanceof Long) {
                    ((JTextField) component).setText(Long.toString((Long) value));
                }
                if (value instanceof Integer) {
                    ((JTextField) component).setText(Integer.toString((Integer) value));
                }
            } else if (component instanceof HelpTextField) {
                HelpTextField panel = (HelpTextField) component;
                panel.helpButton.setEnabled(buttonEnabled);
                panel.textField.setText(Long.toString((Long) value));
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
        }
        return component;
    }

}
