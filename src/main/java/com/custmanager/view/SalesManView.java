/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.ElegantSalesMan;
import com.cust.persistance.PersistanceManager;
import com.custmanager.AppManager;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.SalesManTableModel;
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
public class SalesManView extends GradientPanel implements Observer {

    private TableRowSorter<TableModel> tableRowsorter;
    private CustomerTableCellRenderer salesManTableCellRenderer;

    /**
     * Creates new form Sales
     */
    public SalesManView() {
        super(Color.GRAY);
        initComponents();
        createBorders();
    }

    public void addMouseController(MouseListener salesManMouseController) {
        tableSalesMan.addMouseListener(salesManMouseController);
    }

    public void addKeyController(KeyListener salesManKeyController) {
        searchText.addKeyListener(salesManKeyController);
    }

    public void addButtonController(ActionListener saleManController) {
        buttonPanel.newButton.addActionListener(saleManController);
        buttonPanel.saveButton.addActionListener(saleManController);
        buttonPanel.deleteButton.addActionListener(saleManController);
        buttonPanel.printButton.addActionListener(saleManController);
        buttonPanel.resetButton.addActionListener(saleManController);
        managerHelpButton.addActionListener(saleManController);
        refreshButton.addActionListener(saleManController);
    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        tableSalesMan.setEnabled(true);
        CustUtil.setActionButtonIcons(buttonPanel);
        ImageIcon icon = ImagesDir.getImage("refresh.png");
        refreshButton.setIcon(icon);
        if (!AppManager.getInstance().getElegantInventory().menuSalesManRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            statusLabel.setVisible(false);
            statusCheckBox.setVisible(false);
        }
        icon = ImagesDir.getImage("help.png");
        managerHelpButton.setIcon(icon);
    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuSalesManRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuSalesManRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Sales Man Information");
        jPanel1.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Sales Men in Master");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        initTextFields();
        initButtons();
    }

    public void setTableColWidths() {
        TableColumn col = tableSalesMan.getColumnModel().getColumn(5);
        col.setPreferredWidth(20);
        col = tableSalesMan.getColumnModel().getColumn(0);
        col.setPreferredWidth(20);
        col = tableSalesMan.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        tableSalesMan.getTableHeader().setReorderingAllowed(false);
        tableSalesMan.setColumnSelectionAllowed(false);
        tableSalesMan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSalesMan = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchText = new com.inder.customcomponents.ITextField(100);
        refreshButton = new com.inder.customcomponents.CustomButton();
        jPanel1 = new GradientPanel(Color.WHITE);
        buttonPanel = new com.inder.customcomponents.ActionPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sales2 = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        managerIdField = new com.inder.customcomponents.ITextField(15);
        managerHelpButton = new com.inder.customcomponents.CustomButton();
        nameField = new com.inder.customcomponents.ITextField(35);
        sales1 = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel10 = new javax.swing.JLabel();
        sales4 = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        salesManIdField = new com.inder.customcomponents.ITextField(15);
        sales3 = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        statusCheckBox = new com.inder.customcomponents.CustomCheckBox();

        setLayout(new java.awt.GridBagLayout());

        tablePanel.setFocusable(false);
        tablePanel.setMaximumSize(new java.awt.Dimension(802, 328));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableSalesMan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableSalesMan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tableSalesMan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
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
        gridBagConstraints.insets = new java.awt.Insets(4, 2, 0, 0);
        tablePanel.add(jLabel7, gridBagConstraints);

        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tablePanel.add(searchText, gridBagConstraints);

        refreshButton.setActionCommand("refreshTable");
        refreshButton.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshButton.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        tablePanel.add(refreshButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(tablePanel, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(buttonPanel, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel9.setDisplayedMnemonic('m');
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Sales 3rd Quater");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel9, gridBagConstraints);

        jLabel1.setDisplayedMnemonic('d');
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Sales Man Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 2, 0, 0);
        jPanel3.add(jLabel1, gridBagConstraints);

        sales2.setEditable(false);
        sales2.setBackground(new java.awt.Color(204, 255, 204));
        sales2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(sales2, gridBagConstraints);

        jLabel6.setDisplayedMnemonic('m');
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Sales 1st Quater");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel5.setDisplayedMnemonic('m');
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Manager Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel5, gridBagConstraints);

        jPanel2.setPreferredSize(new java.awt.Dimension(100, 29));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        managerIdField.setEditable(false);
        managerIdField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(managerIdField, gridBagConstraints);

        managerHelpButton.setActionCommand("...");
        managerHelpButton.setPreferredSize(new java.awt.Dimension(20, 20));
        managerHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(managerHelpButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(nameField, gridBagConstraints);

        sales1.setEditable(false);
        sales1.setBackground(new java.awt.Color(204, 255, 204));
        sales1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        sales1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sales1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(sales1, gridBagConstraints);

        jLabel10.setDisplayedMnemonic('m');
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Sales 4th Quater");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel10, gridBagConstraints);

        sales4.setEditable(false);
        sales4.setBackground(new java.awt.Color(204, 255, 204));
        sales4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(sales4, gridBagConstraints);

        salesManIdField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(salesManIdField, gridBagConstraints);

        sales3.setEditable(false);
        sales3.setBackground(new java.awt.Color(204, 255, 204));
        sales3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(sales3, gridBagConstraints);

        jLabel8.setDisplayedMnemonic('m');
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Sales 2nd Quater");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel2.setDisplayedMnemonic('n');
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 2, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(statusLabel, gridBagConstraints);

        statusCheckBox.setText("De-Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(statusCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel3, gridBagConstraints);

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

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    private void managerHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerHelpButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_managerHelpButtonActionPerformed

    private void sales1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sales1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sales1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.ActionPanel buttonPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public com.inder.customcomponents.CustomButton managerHelpButton;
    public javax.swing.JTextField managerIdField;
    public javax.swing.JTextField nameField;
    public com.inder.customcomponents.CustomButton refreshButton;
    public javax.swing.JTextField sales1;
    public javax.swing.JTextField sales2;
    public javax.swing.JTextField sales3;
    public javax.swing.JTextField sales4;
    public javax.swing.JTextField salesManIdField;
    public javax.swing.JTextField searchText;
    public com.inder.customcomponents.CustomCheckBox statusCheckBox;
    public javax.swing.JLabel statusLabel;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tableSalesMan;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ElegantSalesMan) {
            setValuesIntoFormFields((ElegantSalesMan) arg);
        } else if (arg instanceof SalesManTableModel) {
            tableSalesMan.setModel((SalesManTableModel) arg);
            tableSalesMan.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
    }

    void setValuesIntoFormFields(ElegantSalesMan elegantSalesMan) {
        this.salesManIdField.setText(new Long(elegantSalesMan.getSalesManID()).toString());
        this.nameField.setText(elegantSalesMan.getSalesManName());
        this.managerIdField.setText(new Long(elegantSalesMan.getManagerId()).toString());
        this.sales1.setText(elegantSalesMan.getFirstQtr().toString());
        this.sales2.setText(elegantSalesMan.getSecondQtr().toString());
        this.sales3.setText(elegantSalesMan.getThirdQtr().toString());
        this.sales4.setText(elegantSalesMan.getFourthQtr().toString());
        this.statusCheckBox.setSelected(elegantSalesMan.getFrozen()==1);        
//        System.out.println(elegantSalesMan.getFirstQtr().toString() + "" + elegantSalesMan.getSecondQtr().toString() + "" + elegantSalesMan.getThirdQtr().toString() + "" + elegantSalesMan.getFourthQtr().toString());
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
     * @return the salesManTableCellRenderer
     */
    public CustomerTableCellRenderer getSalesManTableCellRenderer() {
        return salesManTableCellRenderer;
    }

    /**
     * @param customerTableCellRenderer the salesManTableCellRenderer to set
     */
    public void setSalesManTableCellRenderer(CustomerTableCellRenderer customerTableCellRenderer) {
        this.salesManTableCellRenderer = customerTableCellRenderer;
        for (int colCount = 0; colCount < this.tableSalesMan.getColumnCount(); colCount++) {
            this.tableSalesMan.getColumnModel().getColumn(colCount).setCellRenderer(customerTableCellRenderer);
        }

    }

}
