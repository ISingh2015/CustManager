/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.AddressXML;
import com.cust.domain.vo.ElegantSupplier;
import com.cust.persistance.PersistanceManager;
import com.custmanager.AppManager;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.CountryComboModelNew;
import com.custmanager.model.SupTableModel;
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
public class SupplierView extends GradientPanel implements Observer {

    private static final long serialVersionUID = 1L;
    private TableRowSorter<TableModel> tableRowsorter;
    private CustomerTableCellRenderer supplierTableCellRenderer;

    /**
     * Creates new form Customer
     *
     * @param color
     */
    public SupplierView() {
        super(Color.GRAY);
        initComponents();
        createBorders();
        setTableRowsorter(new TableRowSorter(tableSuppliers.getModel()));
    }

    public void addMouseController(MouseListener supplierMouseController) {
        tableSuppliers.addMouseListener(supplierMouseController);
    }

    public void addKeyController(KeyListener supplierKeyController) {
        searchText.addKeyListener(supplierKeyController);
    }

    public void addButtonController(ActionListener supplierController) {
        buttonPanel.newButton.addActionListener(supplierController);
        buttonPanel.saveButton.addActionListener(supplierController);
        buttonPanel.deleteButton.addActionListener(supplierController);
        buttonPanel.printButton.addActionListener(supplierController);
        buttonPanel.resetButton.addActionListener(supplierController);
        refreshTable.addActionListener(supplierController);
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
        tableSuppliers.setEnabled(true);
        CustUtil.setActionButtonIcons(buttonPanel);
        ImageIcon icon = ImagesDir.getImage("refresh.png");
        refreshTable.setIcon(icon);
        if (!AppManager.getInstance().getElegantInventory().menuSupRep.isEnabled()) {
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
        if (!AppManager.getInstance().getElegantInventory().menuSupRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuSupRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Supplier Information");
        jPanel1.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Suppliers in Master");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        initTextFields();
        initButtons();
        emailField.setAllowSpaceSymbol(false);
    }

    public void setTableColWidths() {
        TableColumn col = tableSuppliers.getColumnModel().getColumn(6);
        col.setPreferredWidth(20);
        col = tableSuppliers.getColumnModel().getColumn(0);
        col.setPreferredWidth(20);
        col = tableSuppliers.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        tableSuppliers.getTableHeader().setReorderingAllowed(false);
        tableSuppliers.setColumnSelectionAllowed(false);
        tableSuppliers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        scrolltable = new javax.swing.JScrollPane();
        tableSuppliers = new javax.swing.JTable();
        searchLabel = new javax.swing.JLabel();
        searchText = new com.inder.customcomponents.ITextField(30);
        refreshTable = new com.inder.customcomponents.CustomButton();
        jPanel1 = new GradientPanel(Color.WHITE);
        buttonPanel = new com.inder.customcomponents.ActionPanel();
        jPanel2 = new javax.swing.JPanel();
        stateField = new com.inder.customcomponents.ITextField(30);
        supplierIdField = new com.inder.customcomponents.ITextField(10);
        nameField = new com.inder.customcomponents.ITextField(100);
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cityField = new com.inder.customcomponents.ITextField(30);
        emailField = new com.inder.customcomponents.ITextField(35,false,true,true);
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        mobileNoField = new com.inder.customcomponents.INumberField(15);
        creditLimitField = new com.inder.customcomponents.INumberField(12,INumberField.DECIMAL);
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        websiteField = new com.inder.customcomponents.ITextField(45);
        zipField = new com.inder.customcomponents.INumberField(15);
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        countryCombo = new com.inder.customcomponents.CustomComboBox();
        addressField = new com.inder.customcomponents.ITextField(100);
        jLabel3 = new javax.swing.JLabel();
        telNoField = new com.inder.customcomponents.INumberField(15);
        creditDaysField = new com.inder.customcomponents.INumberField(3);
        statusLabel = new javax.swing.JLabel();
        statusCheckBox = new com.inder.customcomponents.CustomCheckBox();

        setMinimumSize(new java.awt.Dimension(300, 125));
        setPreferredSize(new java.awt.Dimension(700, 750));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        tablePanel.setAutoscrolls(true);
        tablePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableSuppliers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrolltable.setViewportView(tableSuppliers);

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
        tablePanel.add(scrolltable, gridBagConstraints);

        searchLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
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

        refreshTable.setActionCommand("refreshTable");
        refreshTable.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshTable.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(refreshTable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(tablePanel, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(300, 125));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 265));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 10);
        jPanel1.add(buttonPanel, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        stateField.setMinimumSize(new java.awt.Dimension(100, 22));
        stateField.setPreferredSize(new java.awt.Dimension(100, 22));
        stateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(stateField, gridBagConstraints);

        supplierIdField.setEditable(false);
        supplierIdField.setFocusTraversalPolicyProvider(true);
        supplierIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        supplierIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(supplierIdField, gridBagConstraints);

        nameField.setMinimumSize(new java.awt.Dimension(400, 22));
        nameField.setPreferredSize(new java.awt.Dimension(400, 22));
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(nameField, gridBagConstraints);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Web Site Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Country");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Supplier Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        cityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(cityField, gridBagConstraints);

        emailField.setText("iTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(emailField, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Supplier Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel8, gridBagConstraints);

        mobileNoField.setMinimumSize(new java.awt.Dimension(100, 22));
        mobileNoField.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(mobileNoField, gridBagConstraints);

        creditLimitField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        creditLimitField.setPreferredSize(new java.awt.Dimension(120, 22));
        creditLimitField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditLimitFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(creditLimitField, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mobile No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel12.setForeground(java.awt.Color.blue);
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Email Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel12, gridBagConstraints);

        websiteField.setPreferredSize(new java.awt.Dimension(100, 22));
        websiteField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                websiteFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(websiteField, gridBagConstraints);

        zipField.setPreferredSize(new java.awt.Dimension(100, 22));
        zipField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(zipField, gridBagConstraints);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Credit Days");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Zip");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Credit Limit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(countryCombo, gridBagConstraints);

        addressField.setPreferredSize(new java.awt.Dimension(400, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(addressField, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tel No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        telNoField.setMinimumSize(new java.awt.Dimension(200, 22));
        telNoField.setName(""); // NOI18N
        telNoField.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(telNoField, gridBagConstraints);

        creditDaysField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        creditDaysField.setPreferredSize(new java.awt.Dimension(60, 22));
        creditDaysField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditDaysFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 80);
        jPanel2.add(creditDaysField, gridBagConstraints);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusLabel, gridBagConstraints);

        statusCheckBox.setText("De-Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityFieldActionPerformed

    private void stateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stateFieldActionPerformed

    private void zipFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zipFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zipFieldActionPerformed

    private void creditDaysFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditDaysFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditDaysFieldActionPerformed

    private void creditLimitFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditLimitFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditLimitFieldActionPerformed

    private void websiteFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_websiteFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_websiteFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField addressField;
    public com.inder.customcomponents.ActionPanel buttonPanel;
    public javax.swing.JTextField cityField;
    public com.inder.customcomponents.CustomComboBox countryCombo;
    public javax.swing.JTextField creditDaysField;
    public javax.swing.JTextField creditLimitField;
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
    public com.inder.customcomponents.CustomButton refreshTable;
    private javax.swing.JScrollPane scrolltable;
    private javax.swing.JLabel searchLabel;
    public javax.swing.JTextField searchText;
    public javax.swing.JTextField stateField;
    public com.inder.customcomponents.CustomCheckBox statusCheckBox;
    public javax.swing.JLabel statusLabel;
    public javax.swing.JTextField supplierIdField;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tableSuppliers;
    public javax.swing.JTextField telNoField;
    public javax.swing.JTextField websiteField;
    public javax.swing.JTextField zipField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ElegantSupplier) {
            setValuesIntoFormFields((ElegantSupplier) arg);
        } else if (arg instanceof ComboBoxModel) {
            countryCombo.setModel((CountryComboModelNew) arg);
        } else if (arg instanceof SupTableModel) {
            tableSuppliers.setModel((SupTableModel) arg);
            tableSuppliers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
    }

    void setValuesIntoFormFields(ElegantSupplier supplier) {
        AddressXML addressXML;
        this.supplierIdField.setText(new Long(supplier.getSupID()).toString());
        this.nameField.setText(supplier.getSupName());
        if (supplier.getAddressesXML() != null && !supplier.getAddressesXML().equals("")) {
            addressXML = (AddressXML) CustUtil.getXStreamInstance().fromXML(supplier.getAddressesXML());
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
        this.creditDaysField.setText(supplier.getPaymentTerms().toString());
        this.creditLimitField.setText(supplier.getCreditLimit().toString());
        this.statusCheckBox.setSelected(supplier.getFrozen()==1);
    }

    /**
     * @return the supplierTableCellRenderer
     */
    public CustomerTableCellRenderer getSupplierTableCellRenderer() {
        return supplierTableCellRenderer;
    }

    /**
     * @param customerTableCellRenderer the supplierTableCellRenderer to set
     */
    public void setSupplierTableCellRenderer(CustomerTableCellRenderer supplierTableCellRenderer) {
        this.supplierTableCellRenderer = supplierTableCellRenderer;
        for (int colCount = 0; colCount < this.tableSuppliers.getColumnCount(); colCount++) {
            this.tableSuppliers.getColumnModel().getColumn(colCount).setCellRenderer(supplierTableCellRenderer);
        }

    }

}
