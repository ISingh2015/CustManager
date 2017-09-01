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
import com.custmanager.model.SalesDetailsTableModel;
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
public class SalesView extends GradientPanel implements Observer {

    private CustomerTableCellRenderer tableCellRenderer;
    private HelpTextField helpTextField;

    /**
     * Creates new form Sales
     *
     * @param color
     */
    public SalesView(Color color) {
        super(color);
        initComponents();
        createBorders();
    }

    public void addMouseController(MouseListener purchaseMouseController) {
        tableSalesDetails.addMouseListener(purchaseMouseController);
    }

    public void addKeyController(KeyListener salesKeyController) {
        freightField.addKeyListener(salesKeyController);
        taxField.addKeyListener(salesKeyController);
        searchField.addKeyListener(salesKeyController);
    }

    public void addButtonController(ActionListener salesController) {
        buttonPanel.newButton.addActionListener(salesController);
        buttonPanel.saveButton.addActionListener(salesController);
        buttonPanel.deleteButton.addActionListener(salesController);
        buttonPanel.printButton.addActionListener(salesController);
        buttonPanel.resetButton.addActionListener(salesController);
        addRowButton.addActionListener(salesController);
        removeRowButton.addActionListener(salesController);
        salBillHelp.addActionListener(salesController);
        supNameHelp.addActionListener(salesController);
        salesManNameHelp.addActionListener(salesController);
        buttonPanel.printBillButton.addActionListener(salesController);
        refreshTable.addActionListener(salesController);
        authPanel.authStatCombo.addActionListener(salesController);        
    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Invoice Information");
        jPanel2.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Items in Invoice");
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
        tableSalesDetails.setEnabled(true);

        salBillHelp.setEnabled(true);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);

        addRowButton.setEnabled(false);
        removeRowButton.setEnabled(false);
        saleInvNo.requestFocus();
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
        if (!AppManager.getInstance().getElegantInventory().menuSalesRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void setTableColWidths() {
        tableSalesDetails.setRowHeight(30);
        INumberField numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        TableColumn col = tableSalesDetails.getColumnModel().getColumn(0);
        col.setPreferredWidth(40);
        col.setCellEditor(new CustomerTableCellEditor(new INumberField(10)));
        col = tableSalesDetails.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        col.setCellEditor(new CustomerTableCellEditor(getHelpTextField(), supNameHelp.isEnabled()));
        if (!supNameHelp.isEnabled()) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke("F2");
            InputMap inputMap = tableSalesDetails.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            inputMap.remove(keyStroke);
        }
        col = tableSalesDetails.getColumnModel().getColumn(2);
        col.setPreferredWidth(250);
        col.setCellEditor(new CustomerTableCellEditor(new ITextField(100)));

        col = tableSalesDetails.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tableSalesDetails.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tableSalesDetails.getColumnModel().getColumn(5);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tableSalesDetails.getColumnModel().getColumn(6);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        col = tableSalesDetails.getColumnModel().getColumn(7);
        col.setPreferredWidth(100);

        col.setCellEditor(new CustomerTableCellEditor(new ITextField(15)));
        col = tableSalesDetails.getColumnModel().getColumn(8);
        col.setPreferredWidth(80);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        col = tableSalesDetails.getColumnModel().getColumn(9);
        col.setPreferredWidth(125);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        tableSalesDetails.getTableHeader().setReorderingAllowed(false);
        tableSalesDetails.setColumnSelectionAllowed(false);
        tableSalesDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ImageIcon icon = ImagesDir.getImage("help.png");
        if (helpTextField != null) {
            helpTextField.helpButton.setIcon(icon);
            helpTextField.textField.setEditable(false);
        }
        createKeybindings(tableSalesDetails);

    }

    private void createKeybindings(JTable table) {
//        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
//        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        ActionMap am = table.getActionMap();
//        im.put(enter, im.get(tab));

        am.put("Action.NextCell", new NextCellAction(table, "sal"));
    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        salBillHelp.setEnabled(false);
        addRowButton.setEnabled(true);
        removeRowButton.setEnabled(true);

        salBillHelp.setEnabled(false);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);
        saleInvNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuSalesRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        addRowButton.setEnabled(false);
        removeRowButton.setEnabled(false);

        salBillHelp.setEnabled(false);
        supNameHelp.setEnabled(false);
        salesManNameHelp.setEnabled(false);
        saleInvNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuSalesRep.isEnabled()) {
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
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        salesManNameField = new com.inder.customcomponents.ITextField(35);
        custNameField = new com.inder.customcomponents.ITextField(35);
        saleInvNo = new com.inder.customcomponents.ITextField(15);
        taxField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jPanel4 = new javax.swing.JPanel();
        salesManIdField = new com.inder.customcomponents.INumberField(10);
        salesManNameHelp = new com.inder.customcomponents.CustomButton();
        remarks = new com.inder.customcomponents.ITextField(150);
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        billIdField = new com.inder.customcomponents.INumberField(10,com.inder.customcomponents.INumberField.NUMERIC);
        salBillHelp = new com.inder.customcomponents.CustomButton();
        jLabel2 = new javax.swing.JLabel();
        freightField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jPanel1 = new javax.swing.JPanel();
        custIdField = new com.inder.customcomponents.INumberField(10);
        supNameHelp = new com.inder.customcomponents.CustomButton();
        jLabel9 = new javax.swing.JLabel();
        totBillAmt = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        invDate = new com.inder.customcomponents.CustomDate();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tablePanel = new GradientPanel(Color.WHITE);
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSalesDetails = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchField = new com.inder.customcomponents.ITextField(35);
        addRowButton = new com.inder.customcomponents.CustomButton();
        removeRowButton = new com.inder.customcomponents.CustomButton();
        refreshTable = new com.inder.customcomponents.CustomButton();
        authPanel = new com.inder.customcomponents.AuthorisePanel();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(700, 750));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(736, 247));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(buttonPanel, gridBagConstraints);

        jPanel5.setPreferredSize(new java.awt.Dimension(590, 145));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Invoice ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel1, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tot Tax Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel11, gridBagConstraints);

        salesManNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(salesManNameField, gridBagConstraints);

        custNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(custNameField, gridBagConstraints);

        saleInvNo.setMinimumSize(new java.awt.Dimension(100, 22));
        saleInvNo.setName(""); // NOI18N
        saleInvNo.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(saleInvNo, gridBagConstraints);

        taxField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxField.setMinimumSize(new java.awt.Dimension(100, 22));
        taxField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(taxField, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(remarks, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Invoice Dt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
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
        salBillHelp.setActionCommand("salBillHelp");
        salBillHelp.setFocusable(false);
        salBillHelp.setPreferredSize(new java.awt.Dimension(20, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(salBillHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel3, gridBagConstraints);

        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Customer Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel2, gridBagConstraints);

        freightField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        freightField.setText("iNumberField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(freightField, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel1, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Remarks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel9, gridBagConstraints);

        totBillAmt.setEditable(false);
        totBillAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(totBillAmt, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Invoice No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel4, gridBagConstraints);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Total Bill Amt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel12, gridBagConstraints);

        invDate.setBackground(new java.awt.Color(204, 255, 204));
        invDate.setForeground(new java.awt.Color(204, 255, 204));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(invDate, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tot Freight  Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel8, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sales Man  Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel2, gridBagConstraints);

        tablePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableSalesDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableSalesDetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tableSalesDetails);

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

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setIcon(ImagesDir.getImage("search.png"));
        jLabel7.setText("Search");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchField, gridBagConstraints);

        addRowButton.setToolTipText("Add a new Row at Bottom");
        addRowButton.setActionCommand("addRow");
        addRowButton.setFocusable(false);
        addRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        addRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(addRowButton, gridBagConstraints);

        removeRowButton.setToolTipText("Remove Selected Row");
        removeRowButton.setActionCommand("removeRow");
        removeRowButton.setFocusable(false);
        removeRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        removeRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(removeRowButton, gridBagConstraints);

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
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
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
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
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
    public com.inder.customcomponents.ITextField saleInvNo;
    public com.inder.customcomponents.INumberField salesManIdField;
    public com.inder.customcomponents.ITextField salesManNameField;
    public com.inder.customcomponents.CustomButton salesManNameHelp;
    public com.inder.customcomponents.ITextField searchField;
    public com.inder.customcomponents.CustomButton supNameHelp;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tableSalesDetails;
    public com.inder.customcomponents.INumberField taxField;
    public com.inder.customcomponents.INumberField totBillAmt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof SalesDetailsTableModel) {
            tableSalesDetails.setModel((SalesDetailsTableModel) arg);
            setTableCellRenderer(tableCellRenderer);

        } else if (arg instanceof ElegantBuySell) {
            updateValues((ElegantBuySell) arg);
        }
    }

    private void updateValues(ElegantBuySell elegantBuySell) {
        this.billIdField.setText(Long.toString(elegantBuySell.getBillID()));
        this.invDate.setDate(elegantBuySell.getBillDt());
        this.saleInvNo.setText(elegantBuySell.getBillNo());
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
        for (int colCount = 0; colCount < this.tableSalesDetails.getColumnCount(); colCount++) {
            this.tableSalesDetails.getColumnModel().getColumn(colCount).setCellRenderer(tableCellRenderer);
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
