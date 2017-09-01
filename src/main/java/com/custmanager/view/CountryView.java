/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.ElegantCountry;
import com.cust.persistance.PersistanceManager;
import com.custmanager.AppManager;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.CountryTableModel;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.util.CustUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
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
public class CountryView extends GradientPanel implements Observer {

    private TableRowSorter<TableModel> tableRowsorter;
    private CustomerTableCellRenderer customerTableCellRenderer;

    /**
     * Creates new form CountryView
     */
    public CountryView() {
        super(Color.GRAY);
        initComponents();
        createBorders();
        TableRowSorter sorter = new TableRowSorter(tableCountry.getModel());
        setTableRowsorter(sorter);

    }

    public void addKeyController(KeyListener countryKeyController) {
        searchText.addKeyListener(countryKeyController);
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

    public void addMouseController(MouseListener customerMouseController) {
        tableCountry.addMouseListener(customerMouseController);
    }

    public void addButtonController(ActionListener countryController) {
        buttonPanel.newButton.addActionListener(countryController);
        buttonPanel.saveButton.addActionListener(countryController);
        buttonPanel.deleteButton.addActionListener(countryController);
        buttonPanel.printButton.addActionListener(countryController);
        buttonPanel.resetButton.addActionListener(countryController);
        refreshTable.addActionListener(countryController);
    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Country Information");
        jPanel1.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Country List");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        initTextFields();
        initButtons();
//        com.custmanager.util.CustUtil.matchSize(jPanel1);
    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        tableCountry.setEnabled(true);
        CustUtil.setActionButtonIcons(buttonPanel);
        ImageIcon icon = ImagesDir.getImage("refresh.png");
        refreshTable.setIcon(icon);
        if (!AppManager.getInstance().getElegantInventory().menuCountryRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        if (PersistanceManager.getInstance().getElegantUser().getAccountType()!=2)  {
            statusLabel.setVisible(false);
            statusCheckBox.setVisible(false);
        }
    }

    public void initTextFields() {
        Component[] textFieldsList = getComponents();
        for (Component textFieldsList1 : textFieldsList) {
            CustUtil.initComponentsInPanel(textFieldsList1);
        }
    }

    public void setTableColWidths() {
        TableColumn col = tableCountry.getColumnModel().getColumn(0);
        col.setPreferredWidth(20);
        col = tableCountry.getColumnModel().getColumn(1);
        col.setPreferredWidth(20);
        col = tableCountry.getColumnModel().getColumn(2);
        col.setPreferredWidth(150);
        tableCountry.getTableHeader().setReorderingAllowed(false);
        tableCountry.setColumnSelectionAllowed(false);
        tableCountry.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuCountryRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        
    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuCountryRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
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
        tableCountry = new javax.swing.JTable();
        searchLabel = new javax.swing.JLabel();
        searchText = new com.inder.customcomponents.ITextField(30);
        refreshTable = new com.inder.customcomponents.CustomButton();
        jPanel1 = new GradientPanel(Color.WHITE);
        buttonPanel = new com.inder.customcomponents.ActionPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        exchangeRate = new com.inder.customcomponents.INumberField(8,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel1 = new javax.swing.JLabel();
        countryIdField = new com.inder.customcomponents.ITextField(10);
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        countryCdField = new com.inder.customcomponents.ITextField(5);
        jLabel8 = new javax.swing.JLabel();
        nameField = new com.inder.customcomponents.ITextField(30);
        currencyField = new com.inder.customcomponents.ITextField(5);
        statusLabel = new javax.swing.JLabel();
        statusCheckBox = new com.inder.customcomponents.CustomCheckBox();

        setAutoscrolls(true);
        setName(""); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        tablePanel.setMaximumSize(new java.awt.Dimension(802, 328));
        tablePanel.setPreferredSize(new java.awt.Dimension(400, 400));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableCountry.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        customerScrollPane.setViewportView(tableCountry);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(tablePanel, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 2, 10);
        jPanel1.add(buttonPanel, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Country Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        exchangeRate.setMinimumSize(new java.awt.Dimension(100, 22));
        exchangeRate.setName("exchangeRate"); // NOI18N
        exchangeRate.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 200);
        jPanel2.add(exchangeRate, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Country Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        countryIdField.setEditable(false);
        countryIdField.setBackground(new java.awt.Color(204, 255, 204));
        countryIdField.setMinimumSize(new java.awt.Dimension(100, 22));
        countryIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(countryIdField, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Currency");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Exchange Rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel4, gridBagConstraints);

        countryCdField.setName("countryCdField"); // NOI18N
        countryCdField.setPreferredSize(new java.awt.Dimension(50, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 200);
        jPanel2.add(countryCdField, gridBagConstraints);

        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Country Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel8, gridBagConstraints);

        nameField.setMinimumSize(new java.awt.Dimension(200, 22));
        nameField.setName("nameField"); // NOI18N
        nameField.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(nameField, gridBagConstraints);

        currencyField.setMinimumSize(new java.awt.Dimension(100, 22));
        currencyField.setName("currencyField"); // NOI18N
        currencyField.setPreferredSize(new java.awt.Dimension(50, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 200);
        jPanel2.add(currencyField, gridBagConstraints);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusLabel, gridBagConstraints);

        statusCheckBox.setText("De-Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(statusCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
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
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.ActionPanel buttonPanel;
    public javax.swing.JTextField countryCdField;
    public javax.swing.JTextField countryIdField;
    public javax.swing.JTextField currencyField;
    private javax.swing.JScrollPane customerScrollPane;
    public javax.swing.JTextField exchangeRate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTextField nameField;
    public com.inder.customcomponents.CustomButton refreshTable;
    private javax.swing.JLabel searchLabel;
    public javax.swing.JTextField searchText;
    public com.inder.customcomponents.CustomCheckBox statusCheckBox;
    public javax.swing.JLabel statusLabel;
    public javax.swing.JTable tableCountry;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ElegantCountry) {
            setValuesIntoFormFields((ElegantCountry) arg);
        } else if (arg instanceof CountryTableModel) {
            tableCountry.setModel((CountryTableModel) arg);
            tableCountry.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }

    }

    void setValuesIntoFormFields(ElegantCountry country) {
        if (country == null) {
            return;
        }
        this.countryIdField.setText(Long.toString(country.getCountryID()));
        this.countryCdField.setText(country.getCountryCd());
        this.nameField.setText(country.getCountryName());
        this.currencyField.setText(country.getCurrency());
        this.exchangeRate.setText(country.getExchangeRate().toString());
        this.statusCheckBox.setSelected(country.getDisabled()==1);
    }

    public void setCountryTableRenderer(CustomerTableCellRenderer customerTableRenderer) {
        this.customerTableCellRenderer = customerTableRenderer;
        for (int colCount = 0; colCount < this.tableCountry.getColumnCount(); colCount++) {
            this.tableCountry.getColumnModel().getColumn(colCount).setCellRenderer(this.customerTableCellRenderer);
        }
    }

}
