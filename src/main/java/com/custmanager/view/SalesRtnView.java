/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.ElegantBuySell;
import com.custmanager.AppManager;
import com.custmanager.controller.NextCellAction;
import com.custmanager.editors.CustomerTableCellEditor;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.SaleRtnDetailsTableModel;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.util.CustUtil;
import com.inder.customcomponents.HelpTextField;
import com.inder.customcomponents.INumberField;
import com.inder.customcomponents.ITextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

/**
 *
 * @author Inderjit
 */
public class SalesRtnView extends GradientPanel implements Observer {

    private CustomerTableCellRenderer tableCellRenderer;
    private HelpTextField helpTextField;

    /**
     * Creates new form Sales
     *
     * @param color
     */
    public SalesRtnView(Color color) {
        super(color);
        initComponents();
        createBorders();
    }

    public void addMouseController(MouseListener purchaseMouseController) {
        tableSalesRtnDetails.addMouseListener(purchaseMouseController);
    }

    public void addKeyController(KeyListener salesKeyController) {
        freightField.addKeyListener(salesKeyController);
        taxField.addKeyListener(salesKeyController);
        searchField.addKeyListener(salesKeyController);
    }

    public void addButtonController(ActionListener salesRtnController) {
        buttonPanel.newButton.addActionListener(salesRtnController);
        buttonPanel.saveButton.addActionListener(salesRtnController);
        buttonPanel.deleteButton.addActionListener(salesRtnController);
        buttonPanel.printButton.addActionListener(salesRtnController);
        buttonPanel.resetButton.addActionListener(salesRtnController);
        addRowButton.addActionListener(salesRtnController);
        removeRowButton.addActionListener(salesRtnController);
        salBillHelp.addActionListener(salesRtnController);
        supNameHelp.addActionListener(salesRtnController);
        salesManNameHelp.addActionListener(salesRtnController);
        buttonPanel.printBillButton.addActionListener(salesRtnController);
        refreshTable.addActionListener(salesRtnController);
        authPanel.authStatCombo.addActionListener(salesRtnController);
    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Invoice RTN Information");
        jPanel2.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Items in Invoice RTN");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);

        initTextFields();
        initButtons();
    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        tableSalesRtnDetails.setEnabled(true);
        salBillHelp.setEnabled(true);

        addRowButton.setEnabled(false);
        removeRowButton.setEnabled(false);
        saleRtnNo.requestFocus();
        buttonPanel.printBillButton.setVisible(true);
        ImageIcon icon = ImagesDir.getImage("help.png");
        salBillHelp.setIcon(icon);
        supNameHelp.setIcon(icon);
        salesManNameHelp.setIcon(icon);
        icon = ImagesDir.getImage("add.png");
        addRowButton.setIcon(icon);
        icon = ImagesDir.getImage("cancel.png");
        removeRowButton.setIcon(icon);
        icon = ImagesDir.getImage("refresh.png");
        refreshTable.setIcon(icon);
        CustUtil.setActionButtonIcons(buttonPanel);
        if (!AppManager.getInstance().getElegantInventory().menuSalesRtnRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void setTableColWidths() {
        tableSalesRtnDetails.setRowHeight(30);
        INumberField numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        TableColumn col = tableSalesRtnDetails.getColumnModel().getColumn(0);
        col.setPreferredWidth(40);
        col.setCellEditor(new CustomerTableCellEditor(new INumberField(10)));
        col = tableSalesRtnDetails.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        col.setCellEditor(new CustomerTableCellEditor(getHelpTextField()));
        col = tableSalesRtnDetails.getColumnModel().getColumn(2);
        col.setPreferredWidth(250);
        col.setCellEditor(new CustomerTableCellEditor(new ITextField(100)));

        col = tableSalesRtnDetails.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tableSalesRtnDetails.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tableSalesRtnDetails.getColumnModel().getColumn(5);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tableSalesRtnDetails.getColumnModel().getColumn(6);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        col = tableSalesRtnDetails.getColumnModel().getColumn(7);
        col.setPreferredWidth(100);

        col.setCellEditor(new CustomerTableCellEditor(new ITextField(15)));
        col = tableSalesRtnDetails.getColumnModel().getColumn(8);
        col.setPreferredWidth(80);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        col = tableSalesRtnDetails.getColumnModel().getColumn(9);
        col.setPreferredWidth(125);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        tableSalesRtnDetails.getTableHeader().setReorderingAllowed(false);
        tableSalesRtnDetails.setColumnSelectionAllowed(false);
        tableSalesRtnDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ImageIcon icon = ImagesDir.getImage("help.png");
        if (helpTextField != null) {
            helpTextField.helpButton.setIcon(icon);
            helpTextField.textField.setEditable(false);
        }
        createKeybindings(tableSalesRtnDetails);

    }

    private void createKeybindings(JTable table) {
        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        ActionMap am = table.getActionMap();
//        im.put(enter, im.get(tab));

        am.put("Action.NextCell", new NextCellAction(table, "salRtn"));
    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        addRowButton.setEnabled(true);
        removeRowButton.setEnabled(true);

        salBillHelp.setEnabled(false);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);
        saleRtnNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuSalesRtnRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        
    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        addRowButton.setEnabled(true);
        removeRowButton.setEnabled(true);

        salBillHelp.setEnabled(false);
        supNameHelp.setEnabled(false);
        salesManNameHelp.setEnabled(false);
        saleRtnNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuSalesRtnRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        
    }

    public void initTextFields() {
        Component[] textFieldsList = getComponents();
        for (Component textFieldsList1 : textFieldsList) {
            CustUtil.initComponentsInPanel(textFieldsList1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new GradientPanel(Color.WHITE);
        buttonPanel = new com.inder.customcomponents.ActionPanel();
        jPanel5 = new javax.swing.JPanel();
        taxField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel8 = new javax.swing.JLabel();
        totBillAmt = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        remarks = new com.inder.customcomponents.ITextField(150);
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        salesManNameField = new com.inder.customcomponents.ITextField(35);
        jLabel4 = new javax.swing.JLabel();
        invDate = new com.inder.customcomponents.CustomDate();
        jPanel4 = new javax.swing.JPanel();
        salesManIdField = new com.inder.customcomponents.INumberField(10);
        salesManNameHelp = new com.inder.customcomponents.CustomButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        custIdField = new com.inder.customcomponents.INumberField(10);
        supNameHelp = new com.inder.customcomponents.CustomButton();
        freightField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        billIdField = new com.inder.customcomponents.INumberField(10,com.inder.customcomponents.INumberField.NUMERIC);
        salBillHelp = new com.inder.customcomponents.CustomButton();
        custNameField = new com.inder.customcomponents.ITextField(35);
        jLabel11 = new javax.swing.JLabel();
        saleRtnNo = new com.inder.customcomponents.ITextField(15);
        tablePanel = new GradientPanel(Color.WHITE);
        removeRowButton = new com.inder.customcomponents.CustomButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSalesRtnDetails = new javax.swing.JTable();
        addRowButton = new com.inder.customcomponents.CustomButton();
        jLabel7 = new javax.swing.JLabel();
        searchField = new com.inder.customcomponents.ITextField(35);
        refreshTable = new com.inder.customcomponents.CustomButton();
        authPanel = new com.inder.customcomponents.AuthorisePanel();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(700, 750));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(736, 247));
        jPanel2.setPreferredSize(new java.awt.Dimension(736, 247));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(buttonPanel, gridBagConstraints);

        jPanel5.setPreferredSize(new java.awt.Dimension(590, 145));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        taxField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxField.setMinimumSize(new java.awt.Dimension(100, 22));
        taxField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(taxField, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tot Freight  Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel8, gridBagConstraints);

        totBillAmt.setEditable(false);
        totBillAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(totBillAmt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 2);
        jPanel5.add(remarks, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sales Man  Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Customer Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Sale RTN ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel1, gridBagConstraints);

        salesManNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(salesManNameField, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Sale RTN No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(invDate, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        salesManIdField.setEditable(false);
        salesManIdField.setBackground(new java.awt.Color(204, 255, 204));
        salesManIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        salesManIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel4.add(salesManIdField, gridBagConstraints);

        salesManNameHelp.setToolTipText("Search for Sales Men");
        salesManNameHelp.setActionCommand("salesManHelp");
        salesManNameHelp.setFocusable(false);
        salesManNameHelp.setPreferredSize(new java.awt.Dimension(20, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(salesManNameHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 2);
        jPanel5.add(jPanel4, gridBagConstraints);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Total Bill Amt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel12, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Remarks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel9, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        custIdField.setEditable(false);
        custIdField.setBackground(new java.awt.Color(204, 255, 204));
        custIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        custIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel1.add(custIdField, gridBagConstraints);

        supNameHelp.setToolTipText("Search for Suppliers");
        supNameHelp.setActionCommand("custHelp");
        supNameHelp.setFocusable(false);
        supNameHelp.setPreferredSize(new java.awt.Dimension(20, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(supNameHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel1, gridBagConstraints);

        freightField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        freightField.setText("iNumberField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(freightField, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Invoice Dt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel3, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        billIdField.setEditable(false);
        billIdField.setBackground(new java.awt.Color(204, 255, 204));
        billIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        billIdField.setNextFocusableComponent(invDate);
        billIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel3.add(billIdField, gridBagConstraints);

        salBillHelp.setToolTipText("Search for Purchase Orders");
        salBillHelp.setActionCommand("salRtnBillHelp");
        salBillHelp.setFocusable(false);
        salBillHelp.setPreferredSize(new java.awt.Dimension(20, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(salBillHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel3, gridBagConstraints);

        custNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(custNameField, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tot Tax Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel11, gridBagConstraints);

        saleRtnNo.setMinimumSize(new java.awt.Dimension(100, 22));
        saleRtnNo.setName(""); // NOI18N
        saleRtnNo.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(saleRtnNo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel2, gridBagConstraints);

        tablePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        removeRowButton.setToolTipText("Remove Selected Row");
        removeRowButton.setActionCommand("removeRow");
        removeRowButton.setFocusable(false);
        removeRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        removeRowButton.setName("removeRow"); // NOI18N
        removeRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(removeRowButton, gridBagConstraints);

        tableSalesRtnDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableSalesRtnDetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tableSalesRtnDetails);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(jScrollPane2, gridBagConstraints);

        addRowButton.setToolTipText("Add a new Row at Bottom");
        addRowButton.setActionCommand("addRow");
        addRowButton.setFocusable(false);
        addRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        addRowButton.setName("addRow"); // NOI18N
        addRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(addRowButton, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setIcon(ImagesDir.getImage("search.png"));
        jLabel7.setText("Search");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        tablePanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchField, gridBagConstraints);

        refreshTable.setActionCommand("refreshTable");
        refreshTable.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshTable.setName("refreshButton"); // NOI18N
        refreshTable.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(refreshTable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(tablePanel, gridBagConstraints);

        authPanel.setName("authPanel"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(authPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.CustomButton addRowButton;
    public com.inder.customcomponents.AuthorisePanel authPanel;
    public com.inder.customcomponents.INumberField billIdField;
    public com.inder.customcomponents.ActionPanel buttonPanel;
    public com.inder.customcomponents.INumberField custIdField;
    public com.inder.customcomponents.ITextField custNameField;
    public com.inder.customcomponents.INumberField freightField;
    public com.inder.customcomponents.CustomDate invDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    public com.inder.customcomponents.CustomButton refreshTable;
    public com.inder.customcomponents.ITextField remarks;
    public com.inder.customcomponents.CustomButton removeRowButton;
    public com.inder.customcomponents.CustomButton salBillHelp;
    public com.inder.customcomponents.ITextField saleRtnNo;
    public com.inder.customcomponents.INumberField salesManIdField;
    public com.inder.customcomponents.ITextField salesManNameField;
    public com.inder.customcomponents.CustomButton salesManNameHelp;
    public com.inder.customcomponents.ITextField searchField;
    public com.inder.customcomponents.CustomButton supNameHelp;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tableSalesRtnDetails;
    public com.inder.customcomponents.INumberField taxField;
    public com.inder.customcomponents.INumberField totBillAmt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof SaleRtnDetailsTableModel) {
            tableSalesRtnDetails.setModel((SaleRtnDetailsTableModel) arg);
            setTableCellRenderer(tableCellRenderer);

        } else if (arg instanceof ElegantBuySell) {
            updateValues((ElegantBuySell) arg);
        }
    }

    private void updateValues(ElegantBuySell elegantBuySell) {
        this.billIdField.setText(Long.toString(elegantBuySell.getBillID()));
        this.invDate.setDate(elegantBuySell.getBillDt());
        this.saleRtnNo.setText(elegantBuySell.getBillNo());
//        this.supBillNo.setText(elegantBuySell.getBuyerSellBillNo());
//        this.supBillDt.setDate(elegantBuySell.getBuyerSellerBillDt());
        this.custIdField.setText(Integer.toString(elegantBuySell.getBuyerSellerCode()));
        this.custNameField.setText(elegantBuySell.getBuyerSellerName());
        this.salesManIdField.setText(Long.toString(elegantBuySell.getSalesManCode()));
        this.salesManNameField.setText(elegantBuySell.getSalesManName());
        this.freightField.setText(Double.toString(elegantBuySell.getFreighTranspDedAmt()));
        this.taxField.setText(Double.toString(elegantBuySell.getTaxDedAmt()));
        this.totBillAmt.setText(Double.toString(elegantBuySell.getFinalBillAmt()));
        this.remarks.setText(elegantBuySell.getRemarks());
        if (elegantBuySell.getAuthStatus() != null && elegantBuySell.getAuthStatus() != 0) {
            this.authPanel.authTextLabel.setText("Invoice Status has already been Updated...");
            this.authPanel.authStatCombo.setSelectedIndex(elegantBuySell.getAuthStatus());
            this.authPanel.authDate.setDate(elegantBuySell.getAuthDate());
            this.authPanel.authRemark.setText(elegantBuySell.getAuthRemarks());
            this.authPanel.itemStockPost.setSelected(elegantBuySell.getStockPosted() == 1);
            this.authPanel.authStatCombo.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authDate.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authRemark.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.itemStockPost.setEnabled(elegantBuySell.getStockPosted() == 0);
        } else {
            this.authPanel.authTextLabel.setText("To Authorised this Invoice, update the status from the drop down before save.");
            this.authPanel.authStatCombo.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authDate.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authRemark.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.itemStockPost.setEnabled(elegantBuySell.getStockPosted() == 0);
            CustUtil.setDateLimitForAuthOrBills(invDate,this.authPanel.authDate,30);
        }

    }

    public CustomerTableCellRenderer getTableCellRenderer() {
        return tableCellRenderer;
    }

    /**
     * @param tableCellRenderer the tableCellRenderer to set
     */
    public void setTableCellRenderer(CustomerTableCellRenderer tableCellRenderer) {
        this.tableCellRenderer = tableCellRenderer;
        for (int colCount = 0; colCount < this.tableSalesRtnDetails.getColumnCount(); colCount++) {
            this.tableSalesRtnDetails.getColumnModel().getColumn(colCount).setCellRenderer(tableCellRenderer);
        }

    }

    /**
     * @return the helpTextField
     */
    public HelpTextField getHelpTextField() {
        return helpTextField;
    }

    /**
     * @param helpTextField the helpTextField to set
     */
    public void setHelpTextField(HelpTextField helpTextField) {
        this.helpTextField = helpTextField;
    }

}
