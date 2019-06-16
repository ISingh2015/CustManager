package com.custmanager.controller;

import com.custmanager.AppManager;
import com.custmanager.view.PurchaseRtnView;
import com.custmanager.view.PurchaseView;
import com.custmanager.view.SalesRtnView;
import com.custmanager.view.SalesView;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class NextCellAction extends AbstractAction {

    private final JTable table;
    private final String viewName;

    public NextCellAction(JTable table, String viewName) {
        this.table = table;
        this.viewName = viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int col = table.getSelectedColumn();
        int row = table.getSelectedRow();
        int colCount = table.getColumnCount();
        int rowCount = table.getRowCount();
        if (col == 1 && !checkIfValueIsValid(rowCount, col)) {
            return;
        }

        col++;
        if (col >= colCount) {
            col = 0;
            row++;
        }

        if (row >= rowCount) {
            row = 0;
        }
        if (!table.isCellEditable(row, col)) {
            col++;
            if (col >= colCount) {
                col = 0;
                row++;
            }

        }
        table.getSelectionModel().setSelectionInterval(row, row);
        table.getColumnModel().getSelectionModel().setSelectionInterval(col, col);
        table.editCellAt(row, col);
//        System.out.println("Action executed");
    }

    boolean checkIfValueIsValid(int roww, int col) {
        boolean checked = false;
        String currentText = "";
        switch (viewName) {
            case "pur":
                {
                    PurchaseView pv = (PurchaseView) AppManager.getInstance().getViewsList().get("purchaseView");                    
                    currentText = pv.getHelpTextField().textField.getText();
                    break;
                }
            case "purRtn":
                {
                    PurchaseRtnView pv = (PurchaseRtnView) AppManager.getInstance().getViewsList().get("purchaseRtnView");
                    currentText = pv.getHelpTextField().textField.getText();
                    break;
                }
            case "sal":
                {
                    SalesView sv = (SalesView) AppManager.getInstance().getViewsList().get("salesView");
                    currentText = sv.getHelpTextField().textField.getText();
                    break;
                }
            case "salRtn":
                {
                    SalesRtnView sv = (SalesRtnView) AppManager.getInstance().getViewsList().get("salesRtnView");
                    currentText = sv.getHelpTextField().textField.getText();
                    break;
                }
        }
        if (currentText == null || currentText.isEmpty()) {
            return checked;
        }
// disallows duplicate product in other rows
        
        for (int r = 0; r < roww; r++) {
            Object o = table.getValueAt(r, col);
            long v = 0;
            if (o instanceof Long) {
                v = (Long) o;
            }
            if (o instanceof String) {
                String s = (String) o;
                if (!s.equals("")) {
                    v = Long.parseLong((String) o);
                }
            }
            if (v == Long.parseLong(currentText)) {
                checked = true;
                break;
            }
        }
        return checked;
    }
}
