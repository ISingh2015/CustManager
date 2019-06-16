/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserAccess;
import com.cust.persistance.PersistanceManager;
import com.custmanager.AppManager;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.CountryComboModelNew;
import com.custmanager.model.UserTableModel1;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.util.CustUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Base64;
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
public class UserAdminView extends GradientPanel implements Observer {

    private TableRowSorter<TableModel> tableRowsorter;
    private CustomerTableCellRenderer userTableCellRenderer;

    /**
     * Creates new form Estimation
     *
     * @param color
     */
    public UserAdminView(Color color) {
        super(color);
        initComponents();
        init();

    }

    public void addMouseController(MouseListener userMouseController) {
        tableUsers.addMouseListener(userMouseController);
    }

    public void addKeyController(KeyListener userKeyController) {
        searchText.addKeyListener(userKeyController);
    }

    public void addButtonController(ActionListener userController) {
        buttonPanel.newButton.addActionListener(userController);
        buttonPanel.saveButton.addActionListener(userController);
        buttonPanel.deleteButton.addActionListener(userController);
        buttonPanel.printButton.addActionListener(userController);
        buttonPanel.resetButton.addActionListener(userController);
        refreshTable.addActionListener(userController);
        selectAllCheckBox.addActionListener(userController);
    }

    void init() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "User Information");
        jPanel1.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "User Rights");
        rightsPanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Users in Master");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "User Division & Role");
        authPanel.setBorder(border);
        
//        Dimension d = buttonPanel.getPreferredSize();
//        d.setSize(d.getWidth()+20, d.getHeight()+20);
//        buttonPanel.setPreferredSize(d);
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        buttonPanel.resetButton.setEnabled(true);
        divisionCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Logistics Div", "Ordering Div", "Invoiceing Div", "Operations Div", "Marketing Div", "H.R.D Div", "Customer Care Div"}));
        roleCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "General Resource-Staff", "Technical Staff", "Stores Manager", "Orders Manager", "Invoices Manager", "Ords-Invs Manager", "Operations Manager", "Marketing Manager", "Human Resource Development", "Director", "Chief Executive Office", "General Manager", "V.I.P"}));
        roleCombo.setEnabled(false);
        initTextFields();
        initButtons();

    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        tableUsers.setEnabled(true);
        CustUtil.setActionButtonIcons(buttonPanel);
        ImageIcon icon = ImagesDir.getImage("refresh.png");
        refreshTable.setIcon(icon);
        if (!AppManager.getInstance().getElegantInventory().menuUserListing.isEnabled()) {
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
        if (!AppManager.getInstance().getElegantInventory().menuUserListing.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuUserListing.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void initTextFields() {
        Component[] textFieldsList = getComponents();
        for (Component textFieldsList1 : textFieldsList) {
            CustUtil.initComponentsInPanel(textFieldsList1);
        }
    }

    public void setTableColWidths() {
        TableColumn col = tableUsers.getColumnModel().getColumn(6);
        col.setPreferredWidth(20);
        col = tableUsers.getColumnModel().getColumn(0);
        col.setPreferredWidth(20);
        col = tableUsers.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        tableUsers.getTableHeader().setReorderingAllowed(false);
        tableUsers.setColumnSelectionAllowed(false);
        tableUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        customComboBox1 = new com.inder.customcomponents.CustomComboBox();
        tablePanel = new GradientPanel(Color.WHITE);
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchText = new com.inder.customcomponents.ITextField();
        refreshTable = new com.inder.customcomponents.CustomButton();
        jPanel1 = new GradientPanel(Color.WHITE);
        rightsPanel = new GradientPanel(Color.WHITE);
        countryMasCheck = new com.inder.customcomponents.CustomCheckBox();
        productMasCheck = new com.inder.customcomponents.CustomCheckBox();
        salesManMasCheck = new com.inder.customcomponents.CustomCheckBox();
        purCheck = new com.inder.customcomponents.CustomCheckBox();
        purRTNCheck = new com.inder.customcomponents.CustomCheckBox();
        customerMasCheck = new com.inder.customcomponents.CustomCheckBox();
        supplierMasCheck = new com.inder.customcomponents.CustomCheckBox();
        salesCheck = new com.inder.customcomponents.CustomCheckBox();
        salesRTNCheck = new com.inder.customcomponents.CustomCheckBox();
        purchRepCheck = new com.inder.customcomponents.CustomCheckBox();
        purchRTNRepCheck = new com.inder.customcomponents.CustomCheckBox();
        salesRepCheck = new com.inder.customcomponents.CustomCheckBox();
        salesRTNRepCheck = new com.inder.customcomponents.CustomCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        countryMasRepCheck = new com.inder.customcomponents.CustomCheckBox();
        productMasRepCheck = new com.inder.customcomponents.CustomCheckBox();
        salesManMasRepCheck = new com.inder.customcomponents.CustomCheckBox();
        custMasRepCheck = new com.inder.customcomponents.CustomCheckBox();
        supMasRepCheck = new com.inder.customcomponents.CustomCheckBox();
        pendOrderRep = new com.inder.customcomponents.CustomCheckBox();
        pendSalesRep = new com.inder.customcomponents.CustomCheckBox();
        ordervsSaleRep = new com.inder.customcomponents.CustomCheckBox();
        adminUserList = new com.inder.customcomponents.CustomCheckBox();
        selectAllCheckBox = new com.inder.customcomponents.CustomCheckBox();
        elegantDoc = new com.inder.customcomponents.CustomCheckBox();
        elegantChat = new com.inder.customcomponents.CustomCheckBox();
        menuAdmin = new com.inder.customcomponents.CustomCheckBox();
        jPanel2 = new javax.swing.JPanel();
        userIdField = new com.inder.customcomponents.ITextField(10);
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        userConfirmPassword = new com.inder.customcomponents.CustomPasswordField();
        mobileNoField = new com.inder.customcomponents.INumberField(15);
        jLabel13 = new javax.swing.JLabel();
        addressField = new com.inder.customcomponents.ITextField(100);
        jLabel1 = new javax.swing.JLabel();
        telNoField = new com.inder.customcomponents.INumberField(15);
        emailField = new com.inder.customcomponents.ITextField(35,false,true,true);
        websiteField = new com.inder.customcomponents.ITextField(45);
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cityField = new com.inder.customcomponents.ITextField(30);
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nameField = new com.inder.customcomponents.ITextField(100);
        zipField = new com.inder.customcomponents.INumberField(15);
        jLabel14 = new javax.swing.JLabel();
        userPassword = new com.inder.customcomponents.CustomPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        userLogin = new com.inder.customcomponents.ITextField();
        stateField = new com.inder.customcomponents.ITextField(30);
        countryCombo = new com.inder.customcomponents.CustomComboBox();
        rolesLabel = new javax.swing.JLabel();
        authPanel = new javax.swing.JPanel();
        roleCombo = new com.inder.customcomponents.CustomComboBox();
        jLabel22 = new javax.swing.JLabel();
        divisionCombo = new com.inder.customcomponents.CustomComboBox();
        jLabel21 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        statusCheckBox = new com.inder.customcomponents.CustomCheckBox();
        buttonPanel = new com.inder.customcomponents.ActionPanel();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(700, 750));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        tablePanel.setMinimumSize(new java.awt.Dimension(200, 200));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setRequestFocusEnabled(false);
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableUsers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableUsers.setAutoscrolls(false);
        jScrollPane2.setViewportView(tableUsers);

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
        jLabel7.setToolTipText("");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchText, gridBagConstraints);

        refreshTable.setActionCommand("refreshTable");
        refreshTable.setMinimumSize(new java.awt.Dimension(20, 20));
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
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(tablePanel, gridBagConstraints);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 375));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        rightsPanel.setLayout(new java.awt.GridBagLayout());

        countryMasCheck.setText("Country Mas");
        countryMasCheck.setActionCommand("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(countryMasCheck, gridBagConstraints);

        productMasCheck.setText("Product Mas");
        productMasCheck.setActionCommand("2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(productMasCheck, gridBagConstraints);

        salesManMasCheck.setText("SalesMan Mas");
        salesManMasCheck.setActionCommand("3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(salesManMasCheck, gridBagConstraints);

        purCheck.setText("Order");
        purCheck.setActionCommand("7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(purCheck, gridBagConstraints);

        purRTNCheck.setText("Order Rtn");
        purRTNCheck.setActionCommand("8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(purRTNCheck, gridBagConstraints);

        customerMasCheck.setText("Customers Mas");
        customerMasCheck.setActionCommand("4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(customerMasCheck, gridBagConstraints);

        supplierMasCheck.setText("Supplier Mas");
        supplierMasCheck.setActionCommand("5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(supplierMasCheck, gridBagConstraints);

        salesCheck.setText("Invoice");
        salesCheck.setActionCommand("9");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(salesCheck, gridBagConstraints);

        salesRTNCheck.setText("Invoice Rtn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(salesRTNCheck, gridBagConstraints);

        purchRepCheck.setText("Order Rep");
        purchRepCheck.setActionCommand("12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(purchRepCheck, gridBagConstraints);

        purchRTNRepCheck.setText("Order RTN Rep");
        purchRTNRepCheck.setActionCommand("13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(purchRTNRepCheck, gridBagConstraints);

        salesRepCheck.setText("Invoice Rep");
        salesRepCheck.setActionCommand("14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(salesRepCheck, gridBagConstraints);

        salesRTNRepCheck.setText("Invoice RTN Rep");
        salesRTNRepCheck.setActionCommand("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(salesRTNRepCheck, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Data Entry & Reports");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("M.I.S Reporting");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Masters");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(jLabel18, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Data ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(jLabel19, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Reports");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(jLabel20, gridBagConstraints);

        countryMasRepCheck.setText("Country Mas Rep");
        countryMasRepCheck.setActionCommand("16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(countryMasRepCheck, gridBagConstraints);

        productMasRepCheck.setText("Product Mas Rep");
        productMasRepCheck.setActionCommand("17");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(productMasRepCheck, gridBagConstraints);

        salesManMasRepCheck.setText("Sales Man Mas Rep");
        salesManMasRepCheck.setActionCommand("18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(salesManMasRepCheck, gridBagConstraints);

        custMasRepCheck.setText("Customer Mas Rep");
        custMasRepCheck.setActionCommand("19");
        custMasRepCheck.setName("19"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(custMasRepCheck, gridBagConstraints);

        supMasRepCheck.setText("Supplier Mas Rep");
        supMasRepCheck.setActionCommand("20");
        supMasRepCheck.setName("20"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(supMasRepCheck, gridBagConstraints);

        pendOrderRep.setText("Pending Order");
        pendOrderRep.setActionCommand("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(pendOrderRep, gridBagConstraints);

        pendSalesRep.setText("Pending Invoice");
        pendSalesRep.setActionCommand("32");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(pendSalesRep, gridBagConstraints);

        ordervsSaleRep.setText("Order Vs Sale");
        ordervsSaleRep.setActionCommand("31");
        ordervsSaleRep.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(ordervsSaleRep, gridBagConstraints);

        adminUserList.setText("Admin User");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(adminUserList, gridBagConstraints);

        selectAllCheckBox.setText("Select All");
        selectAllCheckBox.setActionCommand("selectUnSelectAll");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(selectAllCheckBox, gridBagConstraints);

        elegantDoc.setText("Docs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(elegantDoc, gridBagConstraints);

        elegantChat.setText("Chat");
        elegantChat.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(elegantChat, gridBagConstraints);

        menuAdmin.setText("Admin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        rightsPanel.add(menuAdmin, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(rightsPanel, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        userIdField.setEditable(false);
        userIdField.setBackground(new java.awt.Color(204, 255, 204));
        userIdField.setFocusTraversalPolicyProvider(true);
        userIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        userIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(userIdField, gridBagConstraints);

        jLabel11.setForeground(new java.awt.Color(51, 51, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Login ID");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel11, gridBagConstraints);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("User Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel10, gridBagConstraints);

        userConfirmPassword.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(userConfirmPassword, gridBagConstraints);

        mobileNoField.setMinimumSize(new java.awt.Dimension(100, 22));
        mobileNoField.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(mobileNoField, gridBagConstraints);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Web Site Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel13, gridBagConstraints);

        addressField.setPreferredSize(new java.awt.Dimension(400, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(addressField, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("User Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        telNoField.setMinimumSize(new java.awt.Dimension(200, 22));
        telNoField.setName(""); // NOI18N
        telNoField.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(telNoField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(emailField, gridBagConstraints);

        websiteField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(websiteField, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Country");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel12.setForeground(java.awt.Color.blue);
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Email Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel12, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel15.setForeground(new java.awt.Color(51, 51, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Confirm Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(cityField, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel6, gridBagConstraints);

        nameField.setMinimumSize(new java.awt.Dimension(400, 22));
        nameField.setPreferredSize(new java.awt.Dimension(400, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(nameField, gridBagConstraints);

        zipField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(zipField, gridBagConstraints);

        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Password");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel14, gridBagConstraints);

        userPassword.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(userPassword, gridBagConstraints);

        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mobile No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tel No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Zip");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel9, gridBagConstraints);

        userLogin.setText("iTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(userLogin, gridBagConstraints);

        stateField.setMinimumSize(new java.awt.Dimension(100, 22));
        stateField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(stateField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(countryCombo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(rolesLabel, gridBagConstraints);

        authPanel.setLayout(new java.awt.GridBagLayout());

        roleCombo.setPreferredSize(new java.awt.Dimension(175, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        authPanel.add(roleCombo, gridBagConstraints);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("User Org Role");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        authPanel.add(jLabel22, gridBagConstraints);

        divisionCombo.setToolTipText("Allow, Set Authorization Level");
        divisionCombo.setPreferredSize(new java.awt.Dimension(150, 25));
        divisionCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionComboActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        authPanel.add(divisionCombo, gridBagConstraints);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Org Divsion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        authPanel.add(jLabel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        jPanel2.add(authPanel, gridBagConstraints);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusLabel, gridBagConstraints);

        statusCheckBox.setText("De-Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 24;
        gridBagConstraints.gridheight = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(40, 2, 2, 2);
        jPanel1.add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 25;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(buttonPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        if (!AppManager.getInstance().getElegantInventory().menuUserListing.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }//GEN-LAST:event_formFocusGained

    private void divisionComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionComboActionPerformed
        if (divisionCombo.getSelectedIndex() > 0) {
            roleCombo.setEnabled(true);
        } else {
            roleCombo.setEnabled(false);
        }
    }//GEN-LAST:event_divisionComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.ITextField addressField;
    public com.inder.customcomponents.CustomCheckBox adminUserList;
    private javax.swing.JPanel authPanel;
    public com.inder.customcomponents.ActionPanel buttonPanel;
    public com.inder.customcomponents.ITextField cityField;
    public com.inder.customcomponents.CustomComboBox countryCombo;
    public com.inder.customcomponents.CustomCheckBox countryMasCheck;
    public com.inder.customcomponents.CustomCheckBox countryMasRepCheck;
    public com.inder.customcomponents.CustomCheckBox custMasRepCheck;
    private com.inder.customcomponents.CustomComboBox customComboBox1;
    public com.inder.customcomponents.CustomCheckBox customerMasCheck;
    public com.inder.customcomponents.CustomComboBox divisionCombo;
    public com.inder.customcomponents.CustomCheckBox elegantChat;
    public com.inder.customcomponents.CustomCheckBox elegantDoc;
    public com.inder.customcomponents.ITextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public com.inder.customcomponents.CustomCheckBox menuAdmin;
    public com.inder.customcomponents.INumberField mobileNoField;
    public com.inder.customcomponents.ITextField nameField;
    public com.inder.customcomponents.CustomCheckBox ordervsSaleRep;
    public com.inder.customcomponents.CustomCheckBox pendOrderRep;
    public com.inder.customcomponents.CustomCheckBox pendSalesRep;
    public com.inder.customcomponents.CustomCheckBox productMasCheck;
    public com.inder.customcomponents.CustomCheckBox productMasRepCheck;
    public com.inder.customcomponents.CustomCheckBox purCheck;
    public com.inder.customcomponents.CustomCheckBox purRTNCheck;
    public com.inder.customcomponents.CustomCheckBox purchRTNRepCheck;
    public com.inder.customcomponents.CustomCheckBox purchRepCheck;
    public com.inder.customcomponents.CustomButton refreshTable;
    public javax.swing.JPanel rightsPanel;
    public com.inder.customcomponents.CustomComboBox roleCombo;
    public javax.swing.JLabel rolesLabel;
    public com.inder.customcomponents.CustomCheckBox salesCheck;
    public com.inder.customcomponents.CustomCheckBox salesManMasCheck;
    public com.inder.customcomponents.CustomCheckBox salesManMasRepCheck;
    public com.inder.customcomponents.CustomCheckBox salesRTNCheck;
    public com.inder.customcomponents.CustomCheckBox salesRTNRepCheck;
    public com.inder.customcomponents.CustomCheckBox salesRepCheck;
    public com.inder.customcomponents.ITextField searchText;
    public com.inder.customcomponents.CustomCheckBox selectAllCheckBox;
    public com.inder.customcomponents.ITextField stateField;
    public com.inder.customcomponents.CustomCheckBox statusCheckBox;
    public javax.swing.JLabel statusLabel;
    public com.inder.customcomponents.CustomCheckBox supMasRepCheck;
    public com.inder.customcomponents.CustomCheckBox supplierMasCheck;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tableUsers;
    public com.inder.customcomponents.INumberField telNoField;
    public com.inder.customcomponents.CustomPasswordField userConfirmPassword;
    private com.inder.customcomponents.ITextField userIdField;
    public com.inder.customcomponents.ITextField userLogin;
    public com.inder.customcomponents.CustomPasswordField userPassword;
    public com.inder.customcomponents.ITextField websiteField;
    public com.inder.customcomponents.INumberField zipField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ElegantUser) {
            setValuesIntoFormFields((ElegantUser) arg);
        } else if (arg instanceof ComboBoxModel) {
            countryCombo.setModel((CountryComboModelNew) arg);
        } else if (arg instanceof UserTableModel1) {
            tableUsers.setModel((UserTableModel1) arg);
            tableUsers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
    }

    void setValuesIntoFormFields(ElegantUser user) {
//        String [] menuNames = {"countryMasCheck","productMasCheck","salesManMasCheck","supplierMasCheck","customerMasCheck","estCheck","purCheck","purRTNCheck","salesCheck","salesRTNCheck"};            
        String decodedPwd = "";
        if (user.getUserLoginPwd() != null && !user.getUserLoginPwd().equals("")) {
            decodedPwd = new String(Base64.getDecoder().decode(user.getUserLoginPwd().getBytes()));
        }
        this.userIdField.setText(new Long(user.getUserID()).toString());
        this.nameField.setText(user.getUserName());
        this.userLogin.setText(user.getUserLoginName());
        this.userPassword.setText(decodedPwd);
        this.userConfirmPassword.setText(decodedPwd);
        this.addressField.setText(user.getUserAddress());
        this.telNoField.setText(user.getTelephoneNo());
        this.mobileNoField.setText(user.getMobileNo());
        this.emailField.setText(user.getEmailId());
        this.stateField.setText(user.getState());
        this.zipField.setText(Long.toString(user.getPinCode() == null ? 0 : user.getPinCode()));
        this.countryCombo.setSelectedItem(user.getCountry());
        this.cityField.setText(user.getCity());
        this.websiteField.setText(user.getWebSite());
        this.divisionCombo.setSelectedIndex(user.getDivision() == null ? 0 : user.getDivision());
        this.roleCombo.setSelectedIndex(user.getRole() == null ? 0 : user.getRole()== "ROLE_ADMIN" ? 1: 0);
        this.statusCheckBox.setSelected(user.getAccountStatus()==1);
        if (user.getElegantUserAccessList() == null) {
            return;
        }
        for (ElegantUserAccess elegantUserAccess : user.getElegantUserAccessList()) {
//            for (int i = 1, j = 2000; i <= 40; i++) {
//                if (elegantUserAccess.getMenuId() == i && elegantUserAccess.getUserAccessId() < j) {
//
//                }
//            }
//
            if (elegantUserAccess.getMenuId() == 1 && elegantUserAccess.getUserAccessId() < 2000) {
                this.countryMasCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 2 && elegantUserAccess.getUserAccessId() < 2000) {
                this.productMasCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 3 && elegantUserAccess.getUserAccessId() < 2000) {
                this.salesManMasCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 4 && elegantUserAccess.getUserAccessId() < 2000) {
                this.supplierMasCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 5 && elegantUserAccess.getUserAccessId() < 2000) {
                this.customerMasCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 7 && elegantUserAccess.getUserAccessId() < 2000) {
                this.purCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 10 && elegantUserAccess.getUserAccessId() < 2000) {
                this.purRTNCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 9 && elegantUserAccess.getUserAccessId() < 2000) {
                this.salesCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 11 && elegantUserAccess.getUserAccessId() < 2000) {
                this.salesRTNCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 12 && elegantUserAccess.getUserAccessId() < 2000) {
                this.menuAdmin.setSelected(elegantUserAccess.getMenuAllowed());

            } else if (elegantUserAccess.getMenuId() == 15 && elegantUserAccess.getUserAccessId() < 2000) {
                this.countryMasRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 16 && elegantUserAccess.getUserAccessId() < 2000) {
                this.salesManMasRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 17 && elegantUserAccess.getUserAccessId() < 2000) {
                this.productMasRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 18 && elegantUserAccess.getUserAccessId() < 2000) {
                this.supMasRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 19 && elegantUserAccess.getUserAccessId() < 2000) {
                this.custMasRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 22 && elegantUserAccess.getUserAccessId() < 2000) {
                this.purchRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 23 && elegantUserAccess.getUserAccessId() < 2000) {
                this.purchRTNRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 24 && elegantUserAccess.getUserAccessId() < 2000) {
                this.salesRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 25 && elegantUserAccess.getUserAccessId() < 2000) {
                this.salesRTNRepCheck.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 30 && elegantUserAccess.getUserAccessId() < 2000) {
                this.ordervsSaleRep.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 31 && elegantUserAccess.getUserAccessId() < 2000) {
                this.pendOrderRep.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 32 && elegantUserAccess.getUserAccessId() < 2000) {
                this.pendSalesRep.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 33 && elegantUserAccess.getUserAccessId() < 2000) {
                this.adminUserList.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 39 && elegantUserAccess.getUserAccessId() < 2000) {
                this.elegantDoc.setSelected(elegantUserAccess.getMenuAllowed());
            } else if (elegantUserAccess.getMenuId() == 40 && elegantUserAccess.getUserAccessId() < 2000) {
                this.elegantChat.setSelected(elegantUserAccess.getMenuAllowed());
            }

        }
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

    /**
     * @return the userTableCellRenderer
     */
    public CustomerTableCellRenderer getUserTableCellRenderer() {
        return userTableCellRenderer;
    }

    /**
     * @param userTableCellRenderer the userTableCellRenderer to set
     */
    public void setUserTableCellRenderer(CustomerTableCellRenderer userTableCellRenderer) {
        this.userTableCellRenderer = userTableCellRenderer;
        for (int colCount = 0; colCount < this.tableUsers.getColumnCount(); colCount++) {
            this.tableUsers.getColumnModel().getColumn(colCount).setCellRenderer(userTableCellRenderer);
        }

    }

}
