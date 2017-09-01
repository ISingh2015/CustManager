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
import com.custmanager.model.PurchaseDetailsTableModel;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.util.CustUtil;

import com.inder.customcomponents.HelpTextField;
import com.inder.customcomponents.INumberField;
import com.inder.customcomponents.ITextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
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
public class PurchaseView extends GradientPanel implements Observer {

    private CustomerTableCellRenderer tableCellRenderer;
    private HelpTextField helpTextField;

    /**
     * Creates new form Sales
     *
     * @param color
     */
    public PurchaseView(Color color) {
        super(color);
        initComponents();
        createBorders();
    }

    public void addMouseController(MouseListener purchaseMouseController) {
        tablePurchaseDetails.addMouseListener(purchaseMouseController);
    }

    public void addKeyController(KeyListener purchaseKeyController) {
        freightField.addKeyListener(purchaseKeyController);
        taxField.addKeyListener(purchaseKeyController);
        searchField.addKeyListener(purchaseKeyController);
    }

    public void addButtonController(ActionListener purchaseController) {
        buttonPanel.newButton.addActionListener(purchaseController);
        buttonPanel.saveButton.addActionListener(purchaseController);
        buttonPanel.deleteButton.addActionListener(purchaseController);
        buttonPanel.printButton.addActionListener(purchaseController);
        buttonPanel.resetButton.addActionListener(purchaseController);
        addRowButton.addActionListener(purchaseController);
        removeRowButton.addActionListener(purchaseController);
        purBillHelp.addActionListener(purchaseController);
        supNameHelp.addActionListener(purchaseController);
        salesManNameHelp.addActionListener(purchaseController);
        buttonPanel.printBillButton.addActionListener(purchaseController);
        refreshButton.addActionListener(purchaseController);
        authPanel.authStatCombo.addActionListener(purchaseController);
    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Order Information");
        jPanel2.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Items in this Order");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Order Authorization");
        authPanel.setBorder(border);
        initTextFields();
        initButtons();
    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        buttonPanel.printBillButton.setVisible(true);
        tablePurchaseDetails.setEnabled(true);
        purBillHelp.setEnabled(true);
        addRowButton.setEnabled(false);
        removeRowButton.setEnabled(false);
        purBillHelp.setEnabled(true);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);
        ImageIcon icon = ImagesDir.getImage("help.png");
        purBillHelp.setIcon(icon);
        supNameHelp.setIcon(icon);
        salesManNameHelp.setIcon(icon);
        icon = ImagesDir.getImage("add.png");
        addRowButton.setIcon(icon);
        icon = ImagesDir.getImage("cancel.png");
        removeRowButton.setIcon(icon);
        icon = ImagesDir.getImage("refresh.png");
        refreshButton.setIcon(icon);
        CustUtil.setActionButtonIcons(buttonPanel);
        purOrderNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuPurRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        tablePurchaseDetails.setEnabled(true);
        refreshButton.setEnabled(true);
        searchField.setEditable(true);
    }

    public void setTableColWidths() {
        tablePurchaseDetails.setRowHeight(30);
        INumberField numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        TableColumn col = tablePurchaseDetails.getColumnModel().getColumn(0);
        col.setPreferredWidth(40);
        col.setCellEditor(new CustomerTableCellEditor(new INumberField(10)));
        col = tablePurchaseDetails.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        col.setCellEditor(new CustomerTableCellEditor(getHelpTextField(), supNameHelp.isEnabled()));
        if (!supNameHelp.isEnabled()) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke("F2");
            InputMap inputMap = tablePurchaseDetails.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            inputMap.remove(keyStroke);
        }

        col = tablePurchaseDetails.getColumnModel().getColumn(2);
        col.setPreferredWidth(250);
        col.setCellEditor(new CustomerTableCellEditor(new ITextField(100)));
        col = tablePurchaseDetails.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseDetails.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseDetails.getColumnModel().getColumn(5);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseDetails.getColumnModel().getColumn(6);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseDetails.getColumnModel().getColumn(7);
        col.setPreferredWidth(80);
        col.setCellEditor(new CustomerTableCellEditor(new ITextField(15)));
        col = tablePurchaseDetails.getColumnModel().getColumn(8);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        col = tablePurchaseDetails.getColumnModel().getColumn(9);
        col.setPreferredWidth(125);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        tablePurchaseDetails.getTableHeader().setReorderingAllowed(false);
        tablePurchaseDetails.setColumnSelectionAllowed(false);
        tablePurchaseDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ImageIcon icon = ImagesDir.getImage("help.png");
        if (helpTextField != null) {
            helpTextField.helpButton.setIcon(icon);
            helpTextField.textField.setEditable(false);
        }
        createKeybindings(tablePurchaseDetails);

    }

    private void createKeybindings(JTable table) {
//        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
//        InputMap inputMap = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
//        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        ActionMap actionMap = table.getActionMap();
//        im.put(enter, im.get(tab));

        actionMap.put("Action.NextCell", new NextCellAction(table, "pur"));
    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        purBillHelp.setEnabled(false);
        addRowButton.setEnabled(true);
        removeRowButton.setEnabled(true);
        purBillHelp.setEnabled(false);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);
        purOrderNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuPurRep.isEnabled()) {
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
        purBillHelp.setEnabled(false);
        supNameHelp.setEnabled(false);
        salesManNameHelp.setEnabled(false);
        purOrderNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuPurRep.isEnabled()) {
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
        totBillAmt = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        salesManNameField = new com.inder.customcomponents.ITextField(35);
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        remarks = new com.inder.customcomponents.ITextField(150);
        taxField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        supBillNo = new com.inder.customcomponents.ITextField(10);
        purOrderNo = new com.inder.customcomponents.ITextField(15);
        jPanel4 = new javax.swing.JPanel();
        salesManIdField = new com.inder.customcomponents.INumberField(10);
        salesManNameHelp = new com.inder.customcomponents.CustomButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        supIdField = new com.inder.customcomponents.INumberField(10);
        supNameHelp = new com.inder.customcomponents.CustomButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        billIdField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.NUMERIC);
        purBillHelp = new com.inder.customcomponents.CustomButton();
        freightField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        supNameField = new com.inder.customcomponents.ITextField(35);
        supBillDt = new com.inder.customcomponents.CustomDate();
        purchaseDate = new com.inder.customcomponents.CustomDate();
        tablePanel = new GradientPanel(Color.WHITE);
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePurchaseDetails = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchField = new com.inder.customcomponents.ITextField(35);
        addRowButton = new com.inder.customcomponents.CustomButton();
        removeRowButton = new com.inder.customcomponents.CustomButton();
        refreshButton = new com.inder.customcomponents.CustomButton();
        authPanel = new com.inder.customcomponents.AuthorisePanel();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(832, 750));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(500, 125));
        jPanel2.setPreferredSize(new java.awt.Dimension(675, 150));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(buttonPanel, gridBagConstraints);

        jPanel5.setMinimumSize(new java.awt.Dimension(250, 75));
        jPanel5.setPreferredSize(new java.awt.Dimension(590, 145));
        jPanel5.setVerifyInputWhenFocusTarget(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        totBillAmt.setEditable(false);
        totBillAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(totBillAmt, gridBagConstraints);

        salesManNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(salesManNameField, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Supplier Bill No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel6, gridBagConstraints);

        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Supplier Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel2, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tot Tax Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel11, gridBagConstraints);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Total Bill Amt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel12, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("P.O Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel3, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("P.O No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel4, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Remarks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(remarks, gridBagConstraints);

        taxField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxField.setMinimumSize(new java.awt.Dimension(100, 22));
        taxField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(taxField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(supBillNo, gridBagConstraints);

        purOrderNo.setMinimumSize(new java.awt.Dimension(100, 22));
        purOrderNo.setName(""); // NOI18N
        purOrderNo.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(purOrderNo, gridBagConstraints);

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
        salesManNameHelp.setPreferredSize(new java.awt.Dimension(15, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(salesManNameHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel4, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sales Man  Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Supplier Bill Dt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel10, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        supIdField.setEditable(false);
        supIdField.setBackground(new java.awt.Color(204, 255, 204));
        supIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        supIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel1.add(supIdField, gridBagConstraints);

        supNameHelp.setToolTipText("Search for Suppliers");
        supNameHelp.setActionCommand("supHelp");
        supNameHelp.setPreferredSize(new java.awt.Dimension(15, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(supNameHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Purchase Order ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel1, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tot Freight  Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel8, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        billIdField.setEditable(false);
        billIdField.setBackground(new java.awt.Color(204, 255, 204));
        billIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        billIdField.setNextFocusableComponent(purchaseDate);
        billIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel3.add(billIdField, gridBagConstraints);

        purBillHelp.setToolTipText("Search for Purchase Orders");
        purBillHelp.setActionCommand("purBillHelp");
        purBillHelp.setMinimumSize(new java.awt.Dimension(5, 5));
        purBillHelp.setPreferredSize(new java.awt.Dimension(15, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(purBillHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jPanel3, gridBagConstraints);

        freightField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        freightField.setText("iNumberField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(freightField, gridBagConstraints);

        supNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(supNameField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(supBillDt, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(purchaseDate, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel2, gridBagConstraints);

        tablePanel.setAutoscrolls(true);
        tablePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(23, 23));

        tablePurchaseDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablePurchaseDetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablePurchaseDetails.setName("purchaseDetails"); // NOI18N
        jScrollPane2.setViewportView(tablePurchaseDetails);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchField, gridBagConstraints);

        addRowButton.setToolTipText("Add a new Row at Bottom");
        addRowButton.setActionCommand("addRow");
        addRowButton.setFocusable(false);
        addRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        addRowButton.setName("addRow"); // NOI18N
        addRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(addRowButton, gridBagConstraints);

        removeRowButton.setToolTipText("Remove Selected Row");
        removeRowButton.setActionCommand("removeRow");
        removeRowButton.setFocusable(false);
        removeRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        removeRowButton.setName("removeRow"); // NOI18N
        removeRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(removeRowButton, gridBagConstraints);

        refreshButton.setActionCommand("refreshTable");
        refreshButton.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(refreshButton, gridBagConstraints);

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
        authPanel.setPreferredSize(new java.awt.Dimension(45, 130));
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
    public com.inder.customcomponents.INumberField freightField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    public com.inder.customcomponents.CustomButton purBillHelp;
    public com.inder.customcomponents.ITextField purOrderNo;
    public com.inder.customcomponents.CustomDate purchaseDate;
    public com.inder.customcomponents.CustomButton refreshButton;
    public com.inder.customcomponents.ITextField remarks;
    public com.inder.customcomponents.CustomButton removeRowButton;
    public com.inder.customcomponents.INumberField salesManIdField;
    public com.inder.customcomponents.ITextField salesManNameField;
    public com.inder.customcomponents.CustomButton salesManNameHelp;
    public com.inder.customcomponents.ITextField searchField;
    public com.inder.customcomponents.CustomDate supBillDt;
    public com.inder.customcomponents.ITextField supBillNo;
    public com.inder.customcomponents.INumberField supIdField;
    public com.inder.customcomponents.ITextField supNameField;
    public com.inder.customcomponents.CustomButton supNameHelp;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tablePurchaseDetails;
    public com.inder.customcomponents.INumberField taxField;
    public com.inder.customcomponents.INumberField totBillAmt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PurchaseDetailsTableModel) {
            tablePurchaseDetails.setModel((PurchaseDetailsTableModel) arg);
            setTableCellRenderer(tableCellRenderer);

        } else if (arg instanceof ElegantBuySell) {
            updateValues((ElegantBuySell) arg);

        }
    }

    private void updateValues(ElegantBuySell elegantBuySell) {
        this.billIdField.setText(Long.toString(elegantBuySell.getBillID()));
        this.purchaseDate.setDate(elegantBuySell.getBillDt());
        this.purOrderNo.setText(elegantBuySell.getBillNo());
        this.supBillNo.setText(elegantBuySell.getBuyerSellBillNo());
        if (elegantBuySell.getBuyerSellerBillDt() != null) {
            this.supBillDt.setDate(elegantBuySell.getBuyerSellerBillDt());
        }
        this.supIdField.setText(Integer.toString(elegantBuySell.getBuyerSellerCode()));
        this.supNameField.setText(elegantBuySell.getBuyerSellerName());
        this.salesManIdField.setText(Long.toString(elegantBuySell.getSalesManCode()));
        this.salesManNameField.setText(elegantBuySell.getSalesManName());
        this.freightField.setText(Double.toString(elegantBuySell.getFreighTranspDedAmt()));
        this.taxField.setText(Double.toString(elegantBuySell.getTaxDedAmt()));
        this.totBillAmt.setText(Double.toString(elegantBuySell.getFinalBillAmt()));
        this.remarks.setText(elegantBuySell.getRemarks());
        if (elegantBuySell.getAuthStatus() != null && elegantBuySell.getAuthStatus() != 0) {
            this.authPanel.authTextLabel.setText("Order Status has already been Updated...");
            this.authPanel.authStatCombo.setSelectedIndex(elegantBuySell.getAuthStatus());
            this.authPanel.authDate.setDate(elegantBuySell.getAuthDate());
            this.authPanel.authRemark.setText(elegantBuySell.getAuthRemarks());
            this.authPanel.itemStockPost.setSelected(elegantBuySell.getStockPosted()==1);
            this.authPanel.authStatCombo.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authDate.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authRemark.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.itemStockPost.setEnabled(elegantBuySell.getStockPosted()==0);
        } else {
            this.authPanel.authTextLabel.setText("To Authorised this Order, update the status from the drop down before save.");
            this.authPanel.authStatCombo.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authDate.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authRemark.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.itemStockPost.setEnabled(elegantBuySell.getStockPosted()==0);
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
        for (int colCount = 0; colCount < this.tablePurchaseDetails.getColumnCount(); colCount++) {
            this.tablePurchaseDetails.getColumnModel().getColumn(colCount).setCellRenderer(tableCellRenderer);
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
