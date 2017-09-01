
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
import com.custmanager.model.PurchaseRtnDetailsTableModel;
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
public class PurchaseRtnView extends GradientPanel implements Observer {

    private CustomerTableCellRenderer tableCellRenderer;
    private HelpTextField helpTextField;

    /**
     * Creates new form Sales
     */
    public PurchaseRtnView(Color color) {
        super(color);
        initComponents();
        createBorders();
    }

    public void addMouseController(MouseListener purchaseRtnMouseController) {
        tablePurchaseRtnDetails.addMouseListener(purchaseRtnMouseController);
    }

    public void addKeyController(KeyListener purchaseRtnKeyController) {
        freightField.addKeyListener(purchaseRtnKeyController);
        taxField.addKeyListener(purchaseRtnKeyController);
        searchField.addKeyListener(purchaseRtnKeyController);
    }

    public void addButtonController(ActionListener purchaseRtnController) {
        buttonPanel.newButton.addActionListener(purchaseRtnController);
        buttonPanel.saveButton.addActionListener(purchaseRtnController);
        buttonPanel.deleteButton.addActionListener(purchaseRtnController);
        buttonPanel.printButton.addActionListener(purchaseRtnController);
        buttonPanel.resetButton.addActionListener(purchaseRtnController);
        addRowButton.addActionListener(purchaseRtnController);
        removeRowButton.addActionListener(purchaseRtnController);
        purBillHelp.addActionListener(purchaseRtnController);
        supNameHelp.addActionListener(purchaseRtnController);
        salesManNameHelp.addActionListener(purchaseRtnController);
        buttonPanel.printBillButton.addActionListener(purchaseRtnController);
        refreshTable.addActionListener(purchaseRtnController);
        authPanel.authStatCombo.addActionListener(purchaseRtnController);
    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Order RTN Information");
        jPanel2.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Items in Order RTN");
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
        tablePurchaseRtnDetails.setEnabled(true);
        purBillHelp.setEnabled(true);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);

        addRowButton.setEnabled(false);
        removeRowButton.setEnabled(false);
        purOrderNo.requestFocus();
        buttonPanel.printBillButton.setVisible(true);
        ImageIcon icon = ImagesDir.getImage("help.png");
        purBillHelp.setIcon(icon);
        supNameHelp.setIcon(icon);
        salesManNameHelp.setIcon(icon);
        icon = ImagesDir.getImage("add.png");
        addRowButton.setIcon(icon);
        icon = ImagesDir.getImage("cancel.png");
        removeRowButton.setIcon(icon);
        icon = ImagesDir.getImage("refresh.png");
        refreshTable.setIcon(icon);
        CustUtil.setActionButtonIcons(buttonPanel);
        if (!AppManager.getInstance().getElegantInventory().menuPurRtnRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        
    }

    public void setTableColWidths() {
//        if (tablePurchaseRtnDetails.getModel().getRowCount()==0) return;
        tablePurchaseRtnDetails.setRowHeight(30);
        INumberField numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        TableColumn col = tablePurchaseRtnDetails.getColumnModel().getColumn(0);
        col.setPreferredWidth(40);
        col.setCellEditor(new CustomerTableCellEditor(new INumberField(10)));
        col = tablePurchaseRtnDetails.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        col.setCellEditor(new CustomerTableCellEditor(getHelpTextField(), supNameHelp.isEnabled()));
        if (!supNameHelp.isEnabled()) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke("F2");
            InputMap inputMap = tablePurchaseRtnDetails.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            inputMap.remove(keyStroke);
        }

        col = tablePurchaseRtnDetails.getColumnModel().getColumn(2);
        col.setPreferredWidth(250);
        col.setCellEditor(new CustomerTableCellEditor(new ITextField(100)));

        col = tablePurchaseRtnDetails.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseRtnDetails.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseRtnDetails.getColumnModel().getColumn(5);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseRtnDetails.getColumnModel().getColumn(6);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        col = tablePurchaseRtnDetails.getColumnModel().getColumn(7);
        col.setPreferredWidth(80);
        col.setCellEditor(new CustomerTableCellEditor(new ITextField(15)));
        col = tablePurchaseRtnDetails.getColumnModel().getColumn(8);
        col.setPreferredWidth(100);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));
        col = tablePurchaseRtnDetails.getColumnModel().getColumn(9);
        col.setPreferredWidth(125);
        numField = new INumberField(12, INumberField.DECIMAL);
        numField.setPrecision(3);
        numField.setAllowNegative(false);

        col.setCellEditor(new CustomerTableCellEditor(numField));

        tablePurchaseRtnDetails.getTableHeader().setReorderingAllowed(false);
        tablePurchaseRtnDetails.setColumnSelectionAllowed(false);
        tablePurchaseRtnDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ImageIcon icon = ImagesDir.getImage("help.png");
        if (helpTextField != null) {
            helpTextField.helpButton.setIcon(icon);
            helpTextField.textField.setEditable(false);
        }
        createKeybindings(tablePurchaseRtnDetails);

    }

    private void createKeybindings(JTable table) {
//        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
//        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        ActionMap am = table.getActionMap();
//        im.put(enter, im.get(tab));

        am.put("Action.NextCell", new NextCellAction(table, "purRtn"));
    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        purBillHelp.setEnabled(false);
        addRowButton.setEnabled(true);
        removeRowButton.setEnabled(true);
        purBillHelp.setEnabled(true);
        supNameHelp.setEnabled(true);
        salesManNameHelp.setEnabled(true);
        purOrderNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuPurRtnRep.isEnabled()) {
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
        purBillHelp.setEnabled(false);
        supNameHelp.setEnabled(false);
        salesManNameHelp.setEnabled(false);
        purOrderNo.requestFocus();
        if (!AppManager.getInstance().getElegantInventory().menuPurRtnRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        
    }

    public void initTextFields() {
        Component[] textFieldsList = getComponents();
        for (int intCout = 0; intCout < textFieldsList.length; intCout++) {
            CustUtil.initComponentsInPanel(textFieldsList[intCout]);
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
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        salesManNameField = new com.inder.customcomponents.ITextField(35);
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        purchaseDate = new com.inder.customcomponents.CustomDate();
        freightField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        supIdField = new com.inder.customcomponents.INumberField(10);
        supNameHelp = new com.inder.customcomponents.CustomButton();
        jLabel8 = new javax.swing.JLabel();
        remarks = new com.inder.customcomponents.ITextField(150);
        jPanel3 = new javax.swing.JPanel();
        billIRtndField = new com.inder.customcomponents.ITextField(10);
        purBillHelp = new com.inder.customcomponents.CustomButton();
        totBillAmt = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        supNameField = new com.inder.customcomponents.ITextField(35);
        jPanel4 = new javax.swing.JPanel();
        salesManIdField = new com.inder.customcomponents.INumberField(10);
        salesManNameHelp = new com.inder.customcomponents.CustomButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        taxField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel11 = new javax.swing.JLabel();
        supBillNo = new com.inder.customcomponents.ITextField(10);
        supBillDt = new com.inder.customcomponents.CustomDate();
        purOrderNo = new com.inder.customcomponents.ITextField(15);
        tablePanel = new GradientPanel(Color.WHITE);
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePurchaseRtnDetails = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchField = new com.inder.customcomponents.ITextField(35);
        refreshTable = new com.inder.customcomponents.CustomButton();
        addRowButton = new com.inder.customcomponents.CustomButton();
        removeRowButton = new com.inder.customcomponents.CustomButton();
        authPanel = new com.inder.customcomponents.AuthorisePanel();

        setAutoscrolls(true);
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(75, 750));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(736, 249));
        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 10);
        jPanel2.add(buttonPanel, gridBagConstraints);

        jPanel5.setPreferredSize(new java.awt.Dimension(590, 145));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Supplier Bill Dt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel10, gridBagConstraints);

        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("P.O No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel4, gridBagConstraints);

        salesManNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(salesManNameField, gridBagConstraints);

        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sales Man  Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Supplier Bill No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(purchaseDate, gridBagConstraints);

        freightField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        freightField.setText("iNumberField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(freightField, gridBagConstraints);

        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Supplier Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel2, gridBagConstraints);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Total Bill Amt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel12, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        supIdField.setEditable(false);
        supIdField.setBackground(new java.awt.Color(204, 255, 204));
        supIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        supIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel1.add(supIdField, gridBagConstraints);

        supNameHelp.setActionCommand("supHelp");
        supNameHelp.setMinimumSize(new java.awt.Dimension(20, 20));
        supNameHelp.setPreferredSize(new java.awt.Dimension(20, 20));
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

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Tot Freight  Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(remarks, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        billIRtndField.setEditable(false);
        billIRtndField.setBackground(new java.awt.Color(204, 255, 204));
        billIRtndField.setMinimumSize(new java.awt.Dimension(100, 22));
        billIRtndField.setNextFocusableComponent(purchaseDate);
        billIRtndField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel3.add(billIRtndField, gridBagConstraints);

        purBillHelp.setActionCommand("purBillHelp");
        purBillHelp.setMinimumSize(new java.awt.Dimension(20, 20));
        purBillHelp.setPreferredSize(new java.awt.Dimension(20, 20));
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

        totBillAmt.setEditable(false);
        totBillAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(totBillAmt, gridBagConstraints);

        supNameField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(supNameField, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        salesManIdField.setEditable(false);
        salesManIdField.setBackground(new java.awt.Color(204, 255, 204));
        salesManIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        salesManIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanel4.add(salesManIdField, gridBagConstraints);

        salesManNameHelp.setActionCommand("salesManHelp");
        salesManNameHelp.setMinimumSize(new java.awt.Dimension(20, 20));
        salesManNameHelp.setPreferredSize(new java.awt.Dimension(20, 20));
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("P.O Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel3, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Remarks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel9, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Bill ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel1, gridBagConstraints);

        taxField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        taxField.setMinimumSize(new java.awt.Dimension(100, 22));
        taxField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(taxField, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tot Tax Ded");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(supBillNo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(supBillDt, gridBagConstraints);

        purOrderNo.setMinimumSize(new java.awt.Dimension(100, 22));
        purOrderNo.setName(""); // NOI18N
        purOrderNo.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel5.add(purOrderNo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 11;
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel2, gridBagConstraints);

        tablePanel.setMinimumSize(new java.awt.Dimension(400, 400));
        tablePanel.setName(""); // NOI18N
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tablePurchaseRtnDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablePurchaseRtnDetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablePurchaseRtnDetails.setAutoscrolls(false);
        jScrollPane2.setViewportView(tablePurchaseRtnDetails);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        tablePanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 0, 0);
        tablePanel.add(searchField, gridBagConstraints);

        refreshTable.setActionCommand("refreshTable");
        refreshTable.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshTable.setName("refreshButton"); // NOI18N
        refreshTable.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(refreshTable, gridBagConstraints);

        addRowButton.setActionCommand("addRow");
        addRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        addRowButton.setName("addRow"); // NOI18N
        addRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(addRowButton, gridBagConstraints);

        removeRowButton.setActionCommand("removeRow");
        removeRowButton.setMinimumSize(new java.awt.Dimension(20, 20));
        removeRowButton.setName("removeRow"); // NOI18N
        removeRowButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(removeRowButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridy = 1;
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
    public com.inder.customcomponents.ITextField billIRtndField;
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
    public com.inder.customcomponents.CustomButton refreshTable;
    public com.inder.customcomponents.ITextField remarks;
    private com.inder.customcomponents.CustomButton removeRowButton;
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
    public javax.swing.JTable tablePurchaseRtnDetails;
    public com.inder.customcomponents.INumberField taxField;
    public com.inder.customcomponents.INumberField totBillAmt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PurchaseRtnDetailsTableModel) {
            tablePurchaseRtnDetails.setModel((PurchaseRtnDetailsTableModel) arg);
            setTableCellRenderer(tableCellRenderer);

        } else if (arg instanceof ElegantBuySell) {
            updateValues((ElegantBuySell) arg);

        }
    }

    private void updateValues(ElegantBuySell elegantBuySell) {
        this.billIRtndField.setText(Long.toString(elegantBuySell.getBillID()));
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
            this.authPanel.authTextLabel.setText("Order RTN Status has already been Updated...");
            this.authPanel.authStatCombo.setSelectedIndex(elegantBuySell.getAuthStatus());
            this.authPanel.authDate.setDate(elegantBuySell.getAuthDate());
            this.authPanel.authRemark.setText(elegantBuySell.getAuthRemarks());
            this.authPanel.itemStockPost.setSelected(elegantBuySell.getStockPosted()==1);
            this.authPanel.authStatCombo.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authDate.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authRemark.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.itemStockPost.setEnabled(elegantBuySell.getStockPosted()==0);
        } else {
            this.authPanel.authTextLabel.setText("To Authorised this Order RTN, update the status from the drop down before save.");
            this.authPanel.authStatCombo.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authDate.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.authRemark.setEnabled(elegantBuySell.getAuthStatus() == null);
            this.authPanel.itemStockPost.setEnabled(elegantBuySell.getStockPosted()==0);
            CustUtil.setDateLimitForAuthOrBills(purchaseDate,this.authPanel.authDate,30);
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
        for (int colCount = 0; colCount < this.tablePurchaseRtnDetails.getColumnCount(); colCount++) {
            this.tablePurchaseRtnDetails.getColumnModel().getColumn(colCount).setCellRenderer(tableCellRenderer);
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
