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
public class SupplierListView extends GradientPanel {
    
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form CustList
     */
    public SupplierListView() {
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
        jLabel4 = new javax.swing.JLabel();
        repCrLimit = new com.inder.customcomponents.INumberField(10,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel5 = new javax.swing.JLabel();
        repCrDays = new com.inder.customcomponents.INumberField(3,com.inder.customcomponents.INumberField.NUMERIC);
        frmDtField = new CustomDate();
        toDtField = new CustomDate();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ascDirection = new com.inder.customcomponents.CustomCheckBox();
        idSort = new com.inder.customcomponents.CustomCheckBox();
        nameSort = new com.inder.customcomponents.CustomCheckBox();
        descDirection = new com.inder.customcomponents.CustomCheckBox();
        crLimitSort = new com.inder.customcomponents.CustomCheckBox();
        crDaysSort = new com.inder.customcomponents.CustomCheckBox();
        reportSetup = new com.inder.customcomponents.ReportPageSetup();

        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(550, 350));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setDisplayedMnemonic('c');
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Created Between Dates");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel1, gridBagConstraints);

        jLabel3.setDisplayedMnemonic('s');
        jLabel3.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel3, gridBagConstraints);

        buttonGroup1.add(custActive);
        custActive.setSelected(true);
        custActive.setText("Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(custActive, gridBagConstraints);

        buttonGroup1.add(custFrozen);
        custFrozen.setText("Frozen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(custFrozen, gridBagConstraints);

        jLabel4.setDisplayedMnemonic('L');
        jLabel4.setText("Cr Limit >=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel4, gridBagConstraints);

        repCrLimit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        repCrLimit.setText("0.00");
        repCrLimit.setMinimumSize(new java.awt.Dimension(100, 22));
        repCrLimit.setPreferredSize(new java.awt.Dimension(100, 22));
        repCrLimit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                repCrLimitFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(repCrLimit, gridBagConstraints);

        jLabel5.setDisplayedMnemonic('d');
        jLabel5.setText("Cr Days >=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel5, gridBagConstraints);

        repCrDays.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        repCrDays.setText("0");
        repCrDays.setMinimumSize(new java.awt.Dimension(100, 22));
        repCrDays.setName(""); // NOI18N
        repCrDays.setPreferredSize(new java.awt.Dimension(100, 22));
        repCrDays.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                repCrDaysFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(repCrDays, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(frmDtField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(toDtField, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Supplier Selection Criteria ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 2, 0);
        add(jLabel6, gridBagConstraints);

        jLabel7.setDisplayedMnemonic('r');
        jLabel7.setText("Sort By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel7, gridBagConstraints);

        jLabel8.setDisplayedMnemonic('d');
        jLabel8.setText("Sort Direction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jLabel8, gridBagConstraints);

        buttonGroup3.add(ascDirection);
        ascDirection.setText("Ascending");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(ascDirection, gridBagConstraints);

        buttonGroup2.add(idSort);
        idSort.setSelected(true);
        idSort.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(idSort, gridBagConstraints);

        buttonGroup2.add(nameSort);
        nameSort.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(nameSort, gridBagConstraints);

        buttonGroup3.add(descDirection);
        descDirection.setText("Descending");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(descDirection, gridBagConstraints);

        buttonGroup2.add(crLimitSort);
        crLimitSort.setText("CR.Limit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(crLimitSort, gridBagConstraints);

        buttonGroup2.add(crDaysSort);
        crDaysSort.setText("CR.Days");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(crDaysSort, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(reportSetup, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void repCrDaysFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_repCrDaysFocusLost
        if (repCrDays == null || repCrDays.getText().equals("")) {
            repCrDays.setText("0");
        }
    }//GEN-LAST:event_repCrDaysFocusLost

    private void repCrLimitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_repCrLimitFocusLost
        if (repCrLimit == null || repCrLimit.getText().equals("")) {
            repCrLimit.setText("0.00");
        }
    }//GEN-LAST:event_repCrLimitFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.CustomCheckBox ascDirection;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    public com.inder.customcomponents.CustomCheckBox crDaysSort;
    public com.inder.customcomponents.CustomCheckBox crLimitSort;
    public com.inder.customcomponents.CustomCheckBox custActive;
    public com.inder.customcomponents.CustomCheckBox custFrozen;
    public com.inder.customcomponents.CustomCheckBox descDirection;
    public org.jdesktop.swingx.JXDatePicker frmDtField;
    public com.inder.customcomponents.CustomCheckBox idSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public com.inder.customcomponents.CustomCheckBox nameSort;
    public javax.swing.JTextField repCrDays;
    public javax.swing.JTextField repCrLimit;
    public com.inder.customcomponents.ReportPageSetup reportSetup;
    public org.jdesktop.swingx.JXDatePicker toDtField;
    // End of variables declaration//GEN-END:variables
}
