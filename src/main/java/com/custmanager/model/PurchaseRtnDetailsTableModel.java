/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantBuySellDetails;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.ProductManager;
import com.custmanager.AppManager;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Inderjit
 */
public class PurchaseRtnDetailsTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private String[] columnNames;
    private ArrayList<ElegantBuySellDetails> data;

    public PurchaseRtnDetailsTableModel(String[] colNames, ArrayList<ElegantBuySellDetails> tableData) {
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
                name = "Srl";
                break;
            case 1:
                name = "Item Code";
                break;
            case 2:
                name = "Item Description";
                break;
            case 3:
                name = "In-Stock";
                break;

            case 4:
                name = "Pur Qty";
                break;
            case 5:
                name = "Pur Rtn";
                break;
            case 6:
                name = "Rate";
                break;
            case 7:
                name = "Unit";
                break;
            case 8:
                name = "Discount";
                break;
            case 9:
                name = "Value";
                break;

        }
        return name;
    }

    public void setValueAt(Object val, int row, int col) {
        ElegantBuySellDetails buySell = getData().get(row);
        boolean done = true;
        switch (col) {
            case 1:
                if (val instanceof String) {
                    buySell.setProductId(Long.parseLong((String) val));
                } else {
                    buySell.setProductId((Long) val);
                }
                double prodStock = getStockForProduct(buySell.getBillID(), buySell.getProductId());
                buySell.setInStock(prodStock);
                
                break;
            case 2:
                buySell.setProductName((String) val);
                break;
            case 3:
                if (val instanceof String) {
                    buySell.setInStock(Double.parseDouble((String) val));
                } else if (val instanceof Double) {
                    buySell.setInStock((Double) val);
                }
                break;
            case 4:
                if (val instanceof String) {
                    double value = Double.parseDouble((String) val);
                    if (value<=buySell.getInStock()){
                        buySell.setPurchQty(value);
                    } else {
                        done=false;
                    }
                }
                buySell.setUnitAmt((buySell.getPurchRtnQty() * buySell.getPurchRate()) - buySell.getUnitDiscount());
                break;
            case 5:
                if (val instanceof String) {
                    buySell.setPurchRtnQty(Double.parseDouble((String) val));
                }
                break;
            case 6:
                if (val instanceof String) {
                    buySell.setPurchRate(Double.parseDouble((String) val));
//                    System.out.println("setting rate" + buySell.getPurchRate());
                } else if (val instanceof Double) {
                    buySell.setPurchRate((Double) val);
//                    System.out.println("setting rate" + buySell.getPurchRate());
                    
                }
                buySell.setUnitAmt((buySell.getPurchRtnQty() * buySell.getPurchRate()) - buySell.getUnitDiscount());
                break;
            case 7:
                buySell.setUnitPackaging((String) val);
                break;
            case 8:
                if (val instanceof String) {
                    buySell.setUnitDiscount(Double.parseDouble((String) val));
                }
                buySell.setUnitAmt((buySell.getPurchRtnQty() * buySell.getPurchRate()) - buySell.getUnitDiscount());
                break;
            case 9:
                if (val instanceof String) {
                    String s = (String) val;
                    if (!s.equals("")) {
                        buySell.setUnitAmt(0d);
                    } else {
                        buySell.setUnitAmt(Double.parseDouble(s));
                    }
                }
                break;

        }
        if (done) fireTableRowsUpdated(row, row);
    }

    public Object getValueAt(int row, int col) {
        ElegantBuySellDetails buySell = getData().get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = buySell.getSrl();
                break;
            case 1:
                value = buySell.getProductId();
                break;
            case 2:
                value = buySell.getProductName();
                break;
            case 3:    
                value = buySell.getInStock();
                break;
                
            case 4:
                value = buySell.getPurchQty();
                break;
            case 5:
                value = buySell.getPurchRtnQty();
                break;
            case 6:
                value = buySell.getPurchRate();
                break;
            case 7:
                value = buySell.getUnitPackaging();
                break;
            case 8:
                value = buySell.getUnitDiscount();
                break;
            case 9:
                value = buySell.getUnitAmt();
                break;

        }
        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = Double.class;
        switch (columnIndex) {
            case 0:
                type = Long.class;
                break;
            case 1:
                type = Long.class;
                break;
            case 2:
                type = String.class;
                break;
            case 7:
                type = String.class;
                break;
        }
        return type;
    }

    public boolean isCellEditable(int row, int col) {
        if (col == 0 || col == 2 || col == 3 || col == 4 || col == 9) {
            return false;
        }
        return true;
    }

    /**
     * @return the data
     */
    public ArrayList<ElegantBuySellDetails> getData() {
        return data;
    }
    private double getStockForProduct(long currBill, long prodId) {
        double prodStock = 0d;
        ProductManager prodManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
        try {
            prodStock = prodManager.getProductStockById(PersistanceManager.getInstance().getElegantUser(), prodId, currBill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return prodStock;
    }

}
