/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.AddressXML;
import com.cust.domain.vo.ElegantCustomer;
import com.cust.persistance.PersistanceManager;
import com.custmanager.AppManager;
import com.custmanager.images.ImagesDir;
import com.custmanager.inputverifiers.CustomerInputVerifier;
import com.custmanager.model.CountryComboModelNew;
import com.custmanager.model.CustTableModel;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.util.CustUtil;
import com.inder.customcomponents.INumberField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class CustomerView extends GradientPanel implements Observer {

    private static final long serialVersionUID = 1L;
    private TableRowSorter<TableModel> tableRowsorter;
    private CustomerTableCellRenderer customerTableCellRenderer;

    /**
     * Creates new form Customer
     *
     * @param color
     */
    public CustomerView() {
        super(Color.GRAY);
        initComponents();
        createBorders();
        TableRowSorter sorter = new TableRowSorter(tableCustomers.getModel());
        setTableRowsorter(sorter);
    }

    public void addMouseController(MouseListener customerMouseController) {
        tableCustomers.addMouseListener(customerMouseController);
    }

    public void addKeyController(KeyListener customerKeyController) {
        searchText.addKeyListener(customerKeyController);
    }

    public void addButtonController(ActionListener customerController) {
        buttonPanel.newButton.addActionListener(customerController);
        buttonPanel.saveButton.addActionListener(customerController);
        buttonPanel.deleteButton.addActionListener(customerController);
        buttonPanel.printButton.addActionListener(customerController);
        buttonPanel.resetButton.addActionListener(customerController);
        refreshButton.addActionListener(customerController);
    }

    /**
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        return tableRowsorter;
    }

    /**
     * @param tableRowsorter the tableRowsorter to set
     */
    public void setTableRowsorter(TableRowSorter<TableModel> tableRowsorter) {
        this.tableRowsorter = tableRowsorter;
    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        CustUtil.setActionButtonIcons(buttonPanel);
        tableCustomers.setEnabled(true);
        ImageIcon icon = ImagesDir.getImage("refresh.png");
        refreshButton.setIcon(icon);
        if (!AppManager.getInstance().getElegantInventory().menuCustomersRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        if (PersistanceManager.getInstance().getElegantUser().getAccountType()!=2)  {
            statusLabel.setVisible(false);
            statusCheckBox.setVisible(false);
        }

    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuCustomersRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        
    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuCustomersRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Customer Information");
        jPanel1.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Customers in Master");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        initTextFields();
        initButtons();
        emailField.setAllowSpaceSymbol(false);
    }

    public void setTableColWidths() {
        TableColumn col = tableCustomers.getColumnModel().getColumn(6);
        col.setPreferredWidth(20);
        col = tableCustomers.getColumnModel().getColumn(0);
        col.setPreferredWidth(20);
        col = tableCustomers.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        tableCustomers.getTableHeader().setReorderingAllowed(false);
        tableCustomers.setColumnSelectionAllowed(false);
        tableCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        tablePanel = new GradientPanel(Color.WHITE);
        customerScrollPane = new javax.swing.JScrollPane();
        tableCustomers = new javax.swing.JTable();
        searchLabel = new javax.swing.JLabel();
        searchText = new com.inder.customcomponents.ITextField(30);
        refreshButton = new com.inder.customcomponents.CustomButton();
        jPanel1 = new GradientPanel(Color.WHITE);
        buttonPanel = new com.inder.customcomponents.ActionPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        addressField = new com.inder.customcomponents.ITextField(100);
        jLabel4 = new javax.swing.JLabel();
        customerIdField = new com.inder.customcomponents.ITextField(10);
        emailField = new com.inder.customcomponents.ITextField(35,false,true,true);
        creditLimitField = new com.inder.customcomponents.INumberField(12,INumberField.DECIMAL);
        countryCombo = new com.inder.customcomponents.CustomComboBox();
        jLabel8 = new javax.swing.JLabel();
        nameField = new com.inder.customcomponents.ITextField(100);
        mobileNoField = new com.inder.customcomponents.INumberField(15);
        telNoField = new com.inder.customcomponents.INumberField(15);
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        websiteField = new com.inder.customcomponents.ITextField(45);
        jLabel10 = new javax.swing.JLabel();
        stateField = new com.inder.customcomponents.ITextField(30);
        creditDaysField = new com.inder.customcomponents.INumberField(3);
        jLabel5 = new javax.swing.JLabel();
        zipField = new com.inder.customcomponents.INumberField(15);
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cityField = new com.inder.customcomponents.ITextField(30);
        jLabel9 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        statusCheckBox = new com.inder.customcomponents.CustomCheckBox();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(750, 750));
        setLayout(new java.awt.GridBagLayout());

        tablePanel.setAutoscrolls(true);
        tablePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        customerScrollPane.setAutoscrolls(true);

        tableCustomers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        customerScrollPane.setViewportView(tableCustomers);

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
        tablePanel.add(customerScrollPane, gridBagConstraints);

        searchLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(102, 102, 255));
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        searchLabel.setIcon(ImagesDir.getImage("search.png"));
        searchLabel.setText("Search");
        searchLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        searchLabel.setPreferredSize(new java.awt.Dimension(82, 20));
        searchLabel.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchText, gridBagConstraints);

        refreshButton.setActionCommand("refreshTable");
        refreshButton.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(refreshButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(tablePanel, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(300, 125));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 265));
        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(buttonPanel, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(2, 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(2, 2));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Credit Limit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel11, gridBagConstraints);

        addressField.setMinimumSize(new java.awt.Dimension(400, 22));
        addressField.setName("addressField"); // NOI18N
        addressField.setPreferredSize(new java.awt.Dimension(400, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(addressField, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mobile No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel4, gridBagConstraints);

        customerIdField.setEditable(false);
        customerIdField.setBackground(new java.awt.Color(204, 255, 204));
        customerIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        customerIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(customerIdField, gridBagConstraints);

        emailField.setText("iTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(emailField, gridBagConstraints);

        creditLimitField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        creditLimitField.setMaximumSize(new java.awt.Dimension(6, 22));
        creditLimitField.setName("crLimitField"); // NOI18N
        creditLimitField.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(creditLimitField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(countryCombo, gridBagConstraints);

        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Customer Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel8, gridBagConstraints);

        nameField.setName("nameField"); // NOI18N
        nameField.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(nameField, gridBagConstraints);

        mobileNoField.setMinimumSize(new java.awt.Dimension(100, 22));
        mobileNoField.setName("mobileNoField"); // NOI18N
        mobileNoField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(mobileNoField, gridBagConstraints);

        telNoField.setMinimumSize(new java.awt.Dimension(200, 22));
        telNoField.setName("telNoField"); // NOI18N
        telNoField.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(telNoField, gridBagConstraints);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Web Site Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel12.setForeground(java.awt.Color.blue);
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Email Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel12, gridBagConstraints);

        websiteField.setName("webSiteField"); // NOI18N
        websiteField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(websiteField, gridBagConstraints);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Credit Days");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel10, gridBagConstraints);

        stateField.setName("stateField"); // NOI18N
        stateField.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(stateField, gridBagConstraints);

        creditDaysField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        creditDaysField.setInputVerifier(new CustomerInputVerifier());
        creditDaysField.setMaximumSize(new java.awt.Dimension(6, 22));
        creditDaysField.setName("crDaysField"); // NOI18N
        creditDaysField.setPreferredSize(new java.awt.Dimension(60, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(creditDaysField, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel5, gridBagConstraints);

        zipField.setInputVerifier(new CustomerInputVerifier());
        zipField.setName("zipField"); // NOI18N
        zipField.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(zipField, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tel No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Customer Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Country");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel7, gridBagConstraints);

        cityField.setName("cityField"); // NOI18N
        cityField.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(cityField, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Zip");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel9, gridBagConstraints);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusLabel, gridBagConstraints);

        statusCheckBox.setText("De-Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField addressField;
    public com.inder.customcomponents.ActionPanel buttonPanel;
    public javax.swing.JTextField cityField;
    public com.inder.customcomponents.CustomComboBox countryCombo;
    public javax.swing.JTextField creditDaysField;
    public javax.swing.JTextField creditLimitField;
    public javax.swing.JTextField customerIdField;
    private javax.swing.JScrollPane customerScrollPane;
    public com.inder.customcomponents.ITextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    public javax.swing.JTextField mobileNoField;
    public javax.swing.JTextField nameField;
    public com.inder.customcomponents.CustomButton refreshButton;
    private javax.swing.JLabel searchLabel;
    public javax.swing.JTextField searchText;
    public javax.swing.JTextField stateField;
    public com.inder.customcomponents.CustomCheckBox statusCheckBox;
    public javax.swing.JLabel statusLabel;
    public javax.swing.JTable tableCustomers;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTextField telNoField;
    public javax.swing.JTextField websiteField;
    public javax.swing.JTextField zipField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
//        System.out.println(arg.getClass());
        if (arg instanceof ElegantCustomer) {
            setValuesIntoFormFields((ElegantCustomer) arg);
        } else if (arg instanceof ComboBoxModel) {
            countryCombo.setModel((CountryComboModelNew) arg);
        } else if (arg instanceof CustTableModel) {
            tableCustomers.setModel((CustTableModel) arg);
            tableCustomers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
    }

    void setValuesIntoFormFields(ElegantCustomer customer) {
        AddressXML addressXML;
        this.customerIdField.setText(new Long(customer.getCustID()).toString());
        this.nameField.setText(customer.getCustName());
        if (customer.getAddressesXML() != null && !customer.getAddressesXML().equals("")) {
            addressXML = (AddressXML) CustUtil.getXStreamInstance().fromXML(customer.getAddressesXML());
            this.addressField.setText(addressXML.getAddress());
            this.telNoField.setText(addressXML.getTelephone().toString());
            this.mobileNoField.setText(addressXML.getMobile().toString());
            this.countryCombo.setSelectedItem(addressXML.getCountry() != null ? addressXML.getCountry() : "");
            this.cityField.setText(addressXML.getCity());
            this.stateField.setText(addressXML.getState());
            this.zipField.setText(addressXML.getPin().toString());
            this.emailField.setText(addressXML.getEmailId());
            this.websiteField.setText(addressXML.getWebSite());
        }
        this.creditDaysField.setText(customer.getPaymentTerms().toString());
        this.creditLimitField.setText(customer.getCreditLimit().toString());
        this.statusCheckBox.setSelected(customer.getFrozen()==1);
    }

    /**
     * @return the customerTableCellRenderer
     */
    public CustomerTableCellRenderer getCustomerTableRenderer() {
        return customerTableCellRenderer;
    }

    /**
     * @param customerTableRenderer the customerTableCellRenderer to set
     */
    public void setCustomerTableRenderer(CustomerTableCellRenderer customerTableRenderer) {
        this.customerTableCellRenderer = customerTableRenderer;
        for (int colCount = 0; colCount < this.tableCustomers.getColumnCount(); colCount++) {
            this.tableCustomers.getColumnModel().getColumn(colCount).setCellRenderer(customerTableRenderer);
        }
    }

}
