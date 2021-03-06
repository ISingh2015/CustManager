/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.custmanager.util.CustUtil;
import com.inder.customcomponents.CustomDate;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Inderjit
 */
public class ProdListView extends GradientPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form CustList
     */
    public ProdListView() {
        super(Color.GRAY);
        initComponents();
        init();

    }

    void init() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Filter Criteria");
        this.setBorder(border);

        SimpleDateFormat format = new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT);
        frmDtField.setDate(CustUtil.addDays(new Date(), -30));
        frmDtField.setFormats(format);
        toDtField.setDate(CustUtil.addDays(new Date(), 0));
        toDtField.setFormats(format);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        custActive = new com.inder.customcomponents.CustomCheckBox();
        custFrozen = new com.inder.customcomponents.CustomCheckBox();
        frmDtField = new CustomDate();
        toDtField = new CustomDate();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        minStockField = new com.inder.customcomponents.INumberField();
        jLabel6 = new javax.swing.JLabel();
        reorderField = new com.inder.customcomponents.INumberField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        listpriceField = new com.inder.customcomponents.INumberField();
        standarcostField = new com.inder.customcomponents.INumberField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ascDirection = new com.inder.customcomponents.CustomCheckBox();
        idSort = new com.inder.customcomponents.CustomCheckBox();
        nameSort = new com.inder.customcomponents.CustomCheckBox();
        descDirection = new com.inder.customcomponents.CustomCheckBox();
        instockSort = new com.inder.customcomponents.CustomCheckBox();
        reorderSort = new com.inder.customcomponents.CustomCheckBox();
        reportSetup = new com.inder.customcomponents.ReportPageSetup();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(550, 350));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setDisplayedMnemonic('c');
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Created Between Dates");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel1, gridBagConstraints);

        jLabel3.setDisplayedMnemonic('s');
        jLabel3.setText("Product Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel3, gridBagConstraints);

        buttonGroup1.add(custActive);
        custActive.setSelected(true);
        custActive.setText("Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(custActive, gridBagConstraints);

        buttonGroup1.add(custFrozen);
        custFrozen.setText("Frozen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(custFrozen, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(frmDtField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(toDtField, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Product Selection Criteria ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 2, 0);
        add(jLabel4, gridBagConstraints);

        jLabel5.setText("Min in Stock >=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel5, gridBagConstraints);

        minStockField.setHorizontalAlignment(com.inder.customcomponents.INumberField.RIGHT);
        minStockField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(minStockField, gridBagConstraints);

        jLabel6.setText("Re Order Point >=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel6, gridBagConstraints);

        reorderField.setHorizontalAlignment(com.inder.customcomponents.INumberField.RIGHT);
        reorderField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(reorderField, gridBagConstraints);

        jLabel7.setText("Standar Cost >=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel7, gridBagConstraints);

        jLabel8.setText("List Price >=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel8, gridBagConstraints);

        listpriceField.setHorizontalAlignment(com.inder.customcomponents.INumberField.RIGHT);
        listpriceField.setText("0");
        listpriceField.setToolTipText("");
        listpriceField.setMinimumSize(new java.awt.Dimension(100, 22));
        listpriceField.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(listpriceField, gridBagConstraints);

        standarcostField.setHorizontalAlignment(com.inder.customcomponents.INumberField.RIGHT);
        standarcostField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(standarcostField, gridBagConstraints);

        jLabel9.setText("Sort By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel9, gridBagConstraints);

        jLabel10.setText("Sort Direction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel10, gridBagConstraints);

        buttonGroup3.add(ascDirection);
        ascDirection.setText("Ascending");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(ascDirection, gridBagConstraints);

        buttonGroup2.add(idSort);
        idSort.setSelected(true);
        idSort.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(idSort, gridBagConstraints);

        buttonGroup2.add(nameSort);
        nameSort.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(nameSort, gridBagConstraints);

        buttonGroup3.add(descDirection);
        descDirection.setText("Descending");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(descDirection, gridBagConstraints);

        buttonGroup2.add(instockSort);
        instockSort.setText("In-Stock");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(instockSort, gridBagConstraints);

        buttonGroup2.add(reorderSort);
        reorderSort.setText("Re-Order Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(reorderSort, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(reportSetup, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.CustomCheckBox ascDirection;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    public com.inder.customcomponents.CustomCheckBox custActive;
    public com.inder.customcomponents.CustomCheckBox custFrozen;
    public com.inder.customcomponents.CustomCheckBox descDirection;
    public org.jdesktop.swingx.JXDatePicker frmDtField;
    public com.inder.customcomponents.CustomCheckBox idSort;
    public com.inder.customcomponents.CustomCheckBox instockSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public com.inder.customcomponents.INumberField listpriceField;
    public com.inder.customcomponents.INumberField minStockField;
    public com.inder.customcomponents.CustomCheckBox nameSort;
    public com.inder.customcomponents.INumberField reorderField;
    public com.inder.customcomponents.CustomCheckBox reorderSort;
    public com.inder.customcomponents.ReportPageSetup reportSetup;
    public com.inder.customcomponents.INumberField standarcostField;
    public org.jdesktop.swingx.JXDatePicker toDtField;
    // End of variables declaration//GEN-END:variables
}
