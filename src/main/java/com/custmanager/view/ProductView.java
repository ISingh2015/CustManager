package com.custmanager.view;

import com.cust.domain.vo.ElegantProduct;
import com.cust.persistance.PersistanceManager;
import com.custmanager.AppManager;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.ProdTableModel;
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
public class ProductView extends GradientPanel implements Observer {

    private TableRowSorter<TableModel> tableRowsorter;
    private CustomerTableCellRenderer customerTableCellRenderer;

    public ProductView() {
        super(Color.GRAY);
        initComponents();
        createBorders();
        setTableRowsorter(new TableRowSorter(tableProducts.getModel()));
    }

    public void addMouseController(MouseListener productMouseController) {
        tableProducts.addMouseListener(productMouseController);
    }

    public void addKeyController(KeyListener productKeyController) {
        searchText.addKeyListener(productKeyController);
    }

    public void addButtonController(ActionListener productController) {
        buttonPanel.newButton.addActionListener(productController);
        buttonPanel.saveButton.addActionListener(productController);
        buttonPanel.deleteButton.addActionListener(productController);
        buttonPanel.printButton.addActionListener(productController);
        buttonPanel.resetButton.addActionListener(productController);
        refreshButton.addActionListener(productController);
    }

    public void initButtons() {
        buttonPanel.newButton.setEnabled(true);
        buttonPanel.saveButton.setEnabled(false);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        tableProducts.setEnabled(true);
        CustUtil.setActionButtonIcons(buttonPanel);
        ImageIcon icon = ImagesDir.getImage("refresh.png");
        refreshButton.setIcon(icon);
        if (!AppManager.getInstance().getElegantInventory().menuProdRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            statusLabel.setVisible(false);
            statusCheckBox.setVisible(false);
        }

    }

    public void initButtonsForNew() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(false);
        buttonPanel.printButton.setEnabled(true);
        if (!AppManager.getInstance().getElegantInventory().menuProdRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }

    }

    public void initButtonsFordelete() {
        buttonPanel.newButton.setEnabled(false);
        buttonPanel.saveButton.setEnabled(true);
        buttonPanel.deleteButton.setEnabled(true);
        buttonPanel.printButton.setEnabled(true);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel18.setVisible(false);
        productLineComboBox.setVisible(false);
        dayToManField.setVisible(false);
        styleComboBox.setVisible(false);
        reorderPointField.setAllowNegative(false);
        listPriceField.setAllowNegative(false);
        standardCostField.setAllowNegative(false);
        if (!AppManager.getInstance().getElegantInventory().menuProdRep.isEnabled()) {
            buttonPanel.printButton.setEnabled(false);
        }
        if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            statusLabel.setVisible(false);
            statusCheckBox.setVisible(false);
        }

    }

    public void setTableColWidths() {
        TableColumn col = tableProducts.getColumnModel().getColumn(6);
        col.setPreferredWidth(20);
        col = tableProducts.getColumnModel().getColumn(0);
        col.setPreferredWidth(20);
        col = tableProducts.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        tableProducts.getTableHeader().setReorderingAllowed(false);
        tableProducts.setColumnSelectionAllowed(false);
        tableProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        createKeybindings(tableProducts);
    }

    void createBorders() {
        Border border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Product Information");
        jPanel1.setBorder(border);

        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Products in Master");
        tablePanel.setBorder(border);
        border = new TitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 135)), "Action Panel");
        buttonPanel.setBorder(border);
        initTextFields();
        initButtons();
        initCombos();

    }

    void initCombos() {
        makeFlagComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Purchased Product", "Manufactured Inhouse"}));
        finishedGoodsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Not Saleable", "Saleable"}));
        productLineComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "High", "Medium", "Low"}));
        styleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"", "Men", "Women", "Universal"}));
    }

    public void initTextFields() {
        Component[] textFieldsList = getComponents();
        for (int intCout = 0; intCout < textFieldsList.length; intCout++) {
            CustUtil.initComponentsInPanel(textFieldsList[intCout]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tablePanel = new GradientPanel(Color.WHITE);
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchText = new com.inder.customcomponents.ITextField(25);
        refreshButton = new com.inder.customcomponents.CustomButton();
        jPanel1 = new javax.swing.JPanel();
        buttonPanel = new com.inder.customcomponents.ActionPanel();
        jPanel2 = new javax.swing.JPanel();
        minStockField = new com.inder.customcomponents.INumberField(10,com.inder.customcomponents.INumberField.DECIMAL);
        colorField = new com.inder.customcomponents.ITextField(15);
        jLabel20 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        subCatField = new com.inder.customcomponents.ITextField(10);
        reorderPointField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        catField = new com.inder.customcomponents.ITextField(10);
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productIdField = new com.inder.customcomponents.ITextField(15);
        finishedGoodsComboBox = new com.inder.customcomponents.CustomComboBox();
        listPriceField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        jLabel1 = new javax.swing.JLabel();
        standardCostField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        productLineComboBox = new com.inder.customcomponents.CustomComboBox();
        jLabel18 = new javax.swing.JLabel();
        sizeField = new com.inder.customcomponents.ITextField(15);
        styleComboBox = new com.inder.customcomponents.CustomComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dayToManField = new com.inder.customcomponents.INumberField(3);
        nameField = new com.inder.customcomponents.ITextField(35);
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        weightUnitMeassureField = new com.inder.customcomponents.ITextField(15);
        opStockField = new com.inder.customcomponents.INumberField(10,com.inder.customcomponents.INumberField.DECIMAL);
        sizeUnitMeasureField = new com.inder.customcomponents.ITextField(15);
        makeFlagComboBox = new com.inder.customcomponents.CustomComboBox();
        weightField = new com.inder.customcomponents.INumberField(12,com.inder.customcomponents.INumberField.DECIMAL);
        statusLabel = new javax.swing.JLabel();
        statusCheckBox = new com.inder.customcomponents.CustomCheckBox();

        setLayout(new java.awt.GridBagLayout());

        tablePanel.setFocusable(false);
        tablePanel.setMinimumSize(new java.awt.Dimension(800, 436));
        tablePanel.setPreferredSize(new java.awt.Dimension(800, 430));
        tablePanel.setLayout(new java.awt.GridBagLayout());

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableProducts.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableProducts.getTableHeader().setResizingAllowed(false);
        tableProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableProducts);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 12, 0, 0);
        tablePanel.add(jLabel7, gridBagConstraints);

        searchText.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        tablePanel.add(searchText, gridBagConstraints);

        refreshButton.setActionCommand("refreshTable");
        refreshButton.setMinimumSize(new java.awt.Dimension(20, 20));
        refreshButton.setPreferredSize(new java.awt.Dimension(20, 9));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        tablePanel.add(refreshButton, gridBagConstraints);

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
        add(tablePanel, gridBagConstraints);

        jPanel1.setAutoscrolls(true);
        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(buttonPanel, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        minStockField.setEditable(false);
        minStockField.setBackground(new java.awt.Color(204, 255, 204));
        minStockField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        minStockField.setPreferredSize(new java.awt.Dimension(100, 22));
        minStockField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minStockFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(minStockField, gridBagConstraints);

        colorField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        colorField.setPreferredSize(new java.awt.Dimension(100, 22));
        colorField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(colorField, gridBagConstraints);

        jLabel20.setText("Category Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel20, gridBagConstraints);

        jLabel4.setDisplayedMnemonic('r');
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Finished Goods Flag");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(subCatField, gridBagConstraints);

        reorderPointField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        reorderPointField.setText("iNumberField1");
        reorderPointField.setToolTipText("");
        reorderPointField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reorderPointFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(reorderPointField, gridBagConstraints);

        jLabel13.setText("Weight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel14.setText("Unit Measure Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel14, gridBagConstraints);

        jLabel17.setText("Op Stock");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel17, gridBagConstraints);

        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ReOrder Point");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setForeground(java.awt.Color.blue);
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Standard Cost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(catField, gridBagConstraints);

        jLabel21.setText("Sub Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel21, gridBagConstraints);

        jLabel2.setDisplayedMnemonic('n');
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        productIdField.setEditable(false);
        productIdField.setBackground(new java.awt.Color(204, 255, 204));
        productIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productIdFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(productIdField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(finishedGoodsComboBox, gridBagConstraints);

        listPriceField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        listPriceField.setText("iNumberField1");
        listPriceField.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(listPriceField, gridBagConstraints);

        jLabel1.setDisplayedMnemonic('d');
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Product Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        standardCostField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        standardCostField.setText("iNumberField1");
        standardCostField.setToolTipText("");
        standardCostField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardCostFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(standardCostField, gridBagConstraints);

        productLineComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Medium", "Low" }));
        productLineComboBox.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(productLineComboBox, gridBagConstraints);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Style");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel18, gridBagConstraints);

        sizeField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(sizeField, gridBagConstraints);

        styleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Women", "Men", "Universal" }));
        styleComboBox.setFocusable(false);
        styleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(styleComboBox, gridBagConstraints);

        jLabel16.setText("Product Line");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel16, gridBagConstraints);

        jLabel15.setText("Days to Manufacture");
        jLabel15.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel15AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel15, gridBagConstraints);

        jLabel5.setDisplayedMnemonic('m');
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Color");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel5, gridBagConstraints);

        dayToManField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        dayToManField.setPreferredSize(new java.awt.Dimension(100, 22));
        dayToManField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayToManFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(dayToManField, gridBagConstraints);

        nameField.setMinimumSize(new java.awt.Dimension(400, 22));
        nameField.setPreferredSize(new java.awt.Dimension(400, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(nameField, gridBagConstraints);

        jLabel3.setDisplayedMnemonic('i');
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Make Flag");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Min In-Stock");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel10.setForeground(java.awt.Color.blue);
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("List Price");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel11, gridBagConstraints);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Unit Mesasure");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(jLabel12, gridBagConstraints);

        weightUnitMeassureField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightUnitMeassureFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(weightUnitMeassureField, gridBagConstraints);

        opStockField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        opStockField.setText("iNumberField1");
        opStockField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opStockFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(opStockField, gridBagConstraints);

        sizeUnitMeasureField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeUnitMeasureFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(sizeUnitMeasureField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(makeFlagComboBox, gridBagConstraints);

        weightField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(weightField, gridBagConstraints);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void productIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productIdFieldActionPerformed

    private void minStockFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minStockFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minStockFieldActionPerformed

    private void styleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_styleComboBoxActionPerformed

    private void dayToManFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayToManFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayToManFieldActionPerformed

    private void sizeUnitMeasureFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeUnitMeasureFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeUnitMeasureFieldActionPerformed

    private void reorderPointFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reorderPointFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reorderPointFieldActionPerformed

    private void weightUnitMeassureFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weightUnitMeassureFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weightUnitMeassureFieldActionPerformed

    private void jLabel15AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel15AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15AncestorAdded

    private void colorFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorFieldActionPerformed

    private void standardCostFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standardCostFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_standardCostFieldActionPerformed

    private void opStockFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opStockFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opStockFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.inder.customcomponents.ActionPanel buttonPanel;
    public com.inder.customcomponents.ITextField catField;
    public com.inder.customcomponents.ITextField colorField;
    public com.inder.customcomponents.INumberField dayToManField;
    public com.inder.customcomponents.CustomComboBox finishedGoodsComboBox;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    public com.inder.customcomponents.INumberField listPriceField;
    public com.inder.customcomponents.CustomComboBox makeFlagComboBox;
    public com.inder.customcomponents.INumberField minStockField;
    public com.inder.customcomponents.ITextField nameField;
    public com.inder.customcomponents.INumberField opStockField;
    public com.inder.customcomponents.ITextField productIdField;
    public com.inder.customcomponents.CustomComboBox productLineComboBox;
    public com.inder.customcomponents.CustomButton refreshButton;
    public com.inder.customcomponents.INumberField reorderPointField;
    public com.inder.customcomponents.ITextField searchText;
    public com.inder.customcomponents.ITextField sizeField;
    public com.inder.customcomponents.ITextField sizeUnitMeasureField;
    public com.inder.customcomponents.INumberField standardCostField;
    public com.inder.customcomponents.CustomCheckBox statusCheckBox;
    public javax.swing.JLabel statusLabel;
    public com.inder.customcomponents.CustomComboBox styleComboBox;
    public com.inder.customcomponents.ITextField subCatField;
    private javax.swing.JPanel tablePanel;
    public javax.swing.JTable tableProducts;
    public com.inder.customcomponents.INumberField weightField;
    public com.inder.customcomponents.ITextField weightUnitMeassureField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
//        System.out.println(arg.getClass());
        if (arg instanceof ElegantProduct) {
            setValuesIntoFormFields((ElegantProduct) arg);
        } else if (arg instanceof ProdTableModel) {
            tableProducts.setModel((ProdTableModel) arg);
            tableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }

    }

    void setValuesIntoFormFields(ElegantProduct product) {
        try {
            this.productIdField.setText(Long.toString(product.getProdID()));
            this.nameField.setText(product.getProdName());
            this.catField.setText(product.getCatNo());
            this.subCatField.setText(product.getSubCatNo());
            this.opStockField.setText(product.getOpStock().toString());
            this.minStockField.setText(product.getMinInStock().toString());
            this.reorderPointField.setText(product.getReOrderPoint().toString());
            this.colorField.setText(product.getColor());
            this.standardCostField.setText(product.getStandardCost().toString());
            this.listPriceField.setText(product.getListPrice().toString());
            this.sizeField.setText(product.getSize());
            this.sizeUnitMeasureField.setText(product.getUnitForSize());
            this.weightField.setText(product.getWeight().toString());
            this.weightUnitMeassureField.setText(product.getUnitForWeight());
            this.dayToManField.setText(product.getDaysToManufacture().toString());
            this.makeFlagComboBox.setSelectedIndex(product.getMakeFlag());
            this.finishedGoodsComboBox.setSelectedIndex(product.getFinishedGoodsFlag());
            this.productLineComboBox.setSelectedIndex(product.getProductLine());
            this.styleComboBox.setSelectedIndex(product.getStyle());
            this.catField.requestFocus();
            this.statusCheckBox.setSelected(product.getActive()==1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
     * @return the customerTableCellRenderer
     */
    public CustomerTableCellRenderer getCustomerTableCellRenderer() {
        return customerTableCellRenderer;
    }

    /**
     * @param customerTableCellRenderer the customerTableCellRenderer to set
     */
    public void setCustomerTableCellRenderer(CustomerTableCellRenderer customerTableCellRenderer) {
        this.customerTableCellRenderer = customerTableCellRenderer;
        for (int colCount = 0; colCount < this.tableProducts.getColumnCount(); colCount++) {
            this.tableProducts.getColumnModel().getColumn(colCount).setCellRenderer(customerTableCellRenderer);
        }

    }
}
