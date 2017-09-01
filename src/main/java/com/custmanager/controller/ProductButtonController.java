/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.AppManager;
import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.ElegantProduct;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.ProductManager;
import com.custmanager.util.CustUtil;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.model.ProductVO;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.ProdListReport;
import com.custmanager.view.ProdListView;
import com.custmanager.view.ProductView;
import com.custmanager.model.ProdTableModel;
import com.custmanager.view.GeneralBillHelp;
import com.custmanager.view.PurchaseRtnView;
import com.custmanager.view.PurchaseView;
import com.custmanager.view.SalesRtnView;
import com.custmanager.view.SalesView;
import com.inder.customcomponents.HelpTextField;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Inderjit
 */
public class ProductButtonController implements ActionListener {

    private ProductVO productModel;
    private ProductView productView;
    private HelpTextField helpTextFieldPur, helpTextFieldSal, helpTextFieldPurRTN, helpTextFieldSalRTN;
    private ProdListView prodListPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            productView.initTextFields();
            productView.initButtonsForNew();
            productModel.setProduct(new ElegantProduct());
            productView.tableProducts.setRowSorter(new TableRowSorter(productView.tableProducts.getModel()));
//            sortTableData();
        } else if (e.getActionCommand().equalsIgnoreCase("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(productView, "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields();
                productView.tableProducts.setRowSorter(new TableRowSorter(productView.tableProducts.getModel()));
            }

        } else if (e.getActionCommand().equalsIgnoreCase("PrintList")) {
            showDialogProdList();
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            if (saveUpdate()) {
                productView.initTextFields();
                productView.initButtons();
                productView.tableProducts.setRowSorter(new TableRowSorter(productView.tableProducts.getModel()));
//                sortTableData();

            }
        } else if (e.getActionCommand().equalsIgnoreCase("Delete")) {
            if (CustUtil.confirmDelete(productView)) {
                if (delete()) {
                    productView.initTextFields();
                    productView.initButtons();
                    productView.tableProducts.setRowSorter(new TableRowSorter(productView.tableProducts.getModel()));
//                    sortTableData();

                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Discard")) {
            productView.initTextFields();
            productView.initButtons();
            productView.tableProducts.setRowSorter(new TableRowSorter(productView.tableProducts.getModel()));
//            sortTableData();
            productView.searchText.setEnabled(true);
        } else if (e.getActionCommand().equalsIgnoreCase("helpButtonPur")) {
            showProductHelpScreen("pur");
        } else if (e.getActionCommand().equalsIgnoreCase("helpButtonSal")) {
            showProductHelpScreen("sal");
        } else if (e.getActionCommand().equalsIgnoreCase("helpButtonPurRTN")) {
            showProductHelpScreen("purRTN");
        } else if (e.getActionCommand().equalsIgnoreCase("helpButtonSalRTN")) {
            showProductHelpScreen("salRTN");
        }

        productView.setTableColWidths();
//        if (productView.tableProducts.getRowCount() != 0) {
//            sortTableData();
//        }

    }

    void showProductHelpScreen(String screenName) {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        GeneralBillHelp productHelpScreen = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("productHelp");
        CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
        productHelpScreen.setTableCellRenderer(customerTableCellRenderer);

        OKCancelDialogPrint repDialog = new OKCancelDialogPrint("Select Product", productHelpScreen);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            int row = productHelpScreen.tableHelp.getSelectedRow();
            JTable target = productHelpScreen.tableHelp;
            if (row >= 0) {
                ElegantProduct elegantProduct = ((ProdTableModel) productHelpScreen.tableHelp.getModel()).getData().get(target.convertRowIndexToModel(row));
                long prodCode = elegantProduct.getProdID();
                String prodName = elegantProduct.getProdName();
                double inStock = elegantProduct.getMinInStock(), saleRate = 0d;
                if (screenName.equals("pur")) {
                    PurchaseView purchaseView = (PurchaseView) AppManager.getInstance().getViewsList().get("purchaseView");
                    if (duplicateProdinTable(purchaseView.tablePurchaseDetails, prodCode)) {
                        return;
                    }
                    saleRate = elegantProduct.getStandardCost();
                    getHelpTextFieldPur().textField.setText(Long.toString(prodCode));

                    int purchaseRowSelected = purchaseView.tablePurchaseDetails.getSelectedRow();
                    if (purchaseRowSelected >= 0) {
                        purchaseView.tablePurchaseDetails.getModel().setValueAt(prodCode, purchaseRowSelected, 1);
                        purchaseView.tablePurchaseDetails.getModel().setValueAt(prodName, purchaseRowSelected, 2);
                        if (purchaseView.tablePurchaseDetails.getModel().getValueAt(purchaseRowSelected, 3) != null) {
                            purchaseView.tablePurchaseDetails.getModel().setValueAt(inStock, purchaseRowSelected, 3);
                        }
                        if (purchaseView.tablePurchaseDetails.getModel().getValueAt(purchaseRowSelected, 6) != null) {
                            double rate = (double) purchaseView.tablePurchaseDetails.getModel().getValueAt(purchaseRowSelected, 6);
//                            System.out.println("Purchase " + purchRate + "-" + rate + "-" + (rate != purchRate) );                            
                            if (rate != saleRate) {
                                purchaseView.tablePurchaseDetails.getModel().setValueAt(saleRate, purchaseRowSelected, 6);
                            }
                        }
                    }
                } else if (screenName.equals("sal")) {
                    SalesView saleView = (SalesView) AppManager.getInstance().getViewsList().get("salesView");
                    if (duplicateProdinTable(saleView.tableSalesDetails, prodCode)) {
                        return;
                    }

                    saleRate = elegantProduct.getListPrice();
                    getHelpTextFieldSal().textField.setText(Long.toString(prodCode));
                    int saleRowSelected = saleView.tableSalesDetails.getSelectedRow();
                    if (saleRowSelected >= 0) {
                        saleView.tableSalesDetails.getModel().setValueAt(prodCode, saleRowSelected, 1);
                        saleView.tableSalesDetails.getModel().setValueAt(prodName, saleRowSelected, 2);
                        if (saleView.tableSalesDetails.getModel().getValueAt(saleRowSelected, 3) != null) {
                            saleView.tableSalesDetails.getModel().setValueAt(inStock, saleRowSelected, 3);
                        }
                        if (saleView.tableSalesDetails.getModel().getValueAt(saleRowSelected, 6) != null) {
                            double rate = (double) saleView.tableSalesDetails.getModel().getValueAt(saleRowSelected, 6);
//                            System.out.println("Sales " + purchRate + "-" + rate + "-" + (rate <= purchRate) );                            
                            if (rate <= saleRate) {
                                saleView.tableSalesDetails.getModel().setValueAt(saleRate, saleRowSelected, 6);
                            }
                        }
                    }
                } else if (screenName.equals("purRTN")) {
                    PurchaseRtnView purchaseRtnView = (PurchaseRtnView) AppManager.getInstance().getViewsList().get("purchaseRtnView");
                    if (duplicateProdinTable(purchaseRtnView.tablePurchaseRtnDetails, prodCode)) {
                        return;
                    }

                    saleRate = elegantProduct.getListPrice();
                    getHelpTextFieldPurRTN().textField.setText(Long.toString(prodCode));
                    int purRTNRowSelected = purchaseRtnView.tablePurchaseRtnDetails.getSelectedRow();
                    if (purRTNRowSelected >= 0) {
                        purchaseRtnView.tablePurchaseRtnDetails.getModel().setValueAt(prodCode, purRTNRowSelected, 1);
                        purchaseRtnView.tablePurchaseRtnDetails.getModel().setValueAt(prodName, purRTNRowSelected, 2);
                        if (purchaseRtnView.tablePurchaseRtnDetails.getModel().getValueAt(purRTNRowSelected, 3) != null) {
                            purchaseRtnView.tablePurchaseRtnDetails.getModel().setValueAt(inStock, purRTNRowSelected, 3);
                        }
                        if (purchaseRtnView.tablePurchaseRtnDetails.getModel().getValueAt(purRTNRowSelected, 6) != null) {
                            double rate = (double) purchaseRtnView.tablePurchaseRtnDetails.getModel().getValueAt(purRTNRowSelected, 6);
//                            System.out.println("Sales " + purchRate + "-" + rate + "-" + (rate <= purchRate) );                            
                            if (rate <= saleRate) {
                                purchaseRtnView.tablePurchaseRtnDetails.getModel().setValueAt(saleRate, purRTNRowSelected, 6);
                            }
                        }
                    }
                } else if (screenName.equals("salRTN")) {
                    SalesRtnView saleRtnView = (SalesRtnView) AppManager.getInstance().getViewsList().get("salesRtnView");
                    if (duplicateProdinTable(saleRtnView.tableSalesRtnDetails, prodCode)) {
                        return;
                    }

                    saleRate = elegantProduct.getListPrice();
                    getHelpTextFieldSalRTN().textField.setText(Long.toString(prodCode));
                    int salRTNRowSelected = saleRtnView.tableSalesRtnDetails.getSelectedRow();
                    if (salRTNRowSelected >= 0) {
                        saleRtnView.tableSalesRtnDetails.getModel().setValueAt(prodCode, salRTNRowSelected, 1);
                        saleRtnView.tableSalesRtnDetails.getModel().setValueAt(prodName, salRTNRowSelected, 2);
                        if (saleRtnView.tableSalesRtnDetails.getModel().getValueAt(salRTNRowSelected, 3) != null) {
                            saleRtnView.tableSalesRtnDetails.getModel().setValueAt(inStock, salRTNRowSelected, 3);
                        }
                        if (saleRtnView.tableSalesRtnDetails.getModel().getValueAt(salRTNRowSelected, 6) != null) {
                            double rate = (double) saleRtnView.tableSalesRtnDetails.getModel().getValueAt(salRTNRowSelected, 6);
//                            System.out.println("Sales " + purchRate + "-" + rate + "-" + (rate <= purchRate) );                            
                            if (rate <= saleRate) {
                                saleRtnView.tableSalesRtnDetails.getModel().setValueAt(saleRate, salRTNRowSelected, 6);
                            }
                        }
                    }
                }
            }

        }
    }

    private void sortTableData() {
        DefaultRowSorter sorter = ((DefaultRowSorter) productView.tableProducts.getRowSorter());
        if (sorter != null && sorter.getModelRowCount() > 0) {
            ArrayList list = new ArrayList();
            list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
            sorter.setSortKeys(list);
            sorter.sort();
        }
    }

    private boolean duplicateProdinTable(JTable table, long selectedProdCode) {
        boolean duplicates = false;
        if (table.getSelectedRow() == 0) {
            return duplicates;
        }
        for (int cntr = 0; cntr < table.getRowCount(); cntr++) {
            Long code = (Long) table.getModel().getValueAt(cntr, 1);
            if (cntr != table.getSelectedRow() && Objects.equals(code, selectedProdCode)) {
                duplicates = true;
                break;
            }
        }
        return duplicates;
    }

    public void initAllFields() {
        QueryCriteria queryCriteria = null;
        if (PersistanceManager.getInstance().getServiceControl().getQueryCriteria() == null) {
            queryCriteria = new QueryCriteria();
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
        }

        List<SortCriteria> sortCriteriaList = null;
        List<FilterCriteria> filterCriteriaList = null;
        if (PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getSortCriteria() != null && PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            sortCriteriaList = PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getSortCriteria();
        }
        if (PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getFilterCriteria() != null && PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            filterCriteriaList = PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getFilterCriteria();
        }

        if (filterCriteriaList == null) {
            filterCriteriaList = new ArrayList<>();
        }
        if (sortCriteriaList == null) {
            sortCriteriaList = new ArrayList<>();
        }
        FilterCriteria filterCriteria = null;
        ProductManager prodManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
        try {
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("active");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.add(filterCriteria);
            }

            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("prodID");
            sortCriteria.setSortDirection("DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);

            ArrayList<ElegantProduct> elegantProductList = prodManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
            this.productModel.setProdList(elegantProductList);
            this.productView.setTableColWidths();
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.productView.setCustomerTableCellRenderer(customerTableCellRenderer);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);

        } catch (Exception e) {
            System.out.println("Init All Fields " + e.getMessage());
        }
    }

    public boolean saveUpdate() {
        boolean saved = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return saved;
        }

        if (!validateData()) {
            return saved;
        }
        this.productView.searchText.setText("");
        ArrayList<ElegantProduct> prodList = new ArrayList<ElegantProduct>();
        ProductManager prodManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
        try {
            ElegantProduct prod = updateProduct(productModel.getProduct());
            prodList.add(prod);
            prodManager.saveOrUpdateProduct(prodList);

            ArrayList<ElegantProduct> elegantProductList = prodManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
            this.productModel.setProdList(elegantProductList);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.productView.setCustomerTableCellRenderer(customerTableCellRenderer);
            saved = true;
        } catch (Exception e) {
            System.out.println("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    boolean validateData() {
        boolean validated = false;
        double minStockField = Double.parseDouble(productView.minStockField.getText());
        double reorderPointField = Double.parseDouble(productView.reorderPointField.getText());
        double costField = Double.parseDouble(productView.standardCostField.getText());
        double listPrice = Double.parseDouble(productView.listPriceField.getText());
        if (minStockField == 0 || reorderPointField == 0 || costField == 0 || listPrice == 0) {
            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
            return validated;

        }
        if (minStockField <= reorderPointField) {
            CustUtil.showMessageDialogue("Minimium In Stock should be Different and greater than Re Order Point");
            return validated;
        }
        if (costField > listPrice) {
            CustUtil.showMessageDialogue("Standard Cost is above List Price.");
            return validated;
        }
        validated = true;
        return validated;
    }

    public boolean delete() {
        boolean deleted = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return deleted;
        }
        ProductManager prodManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
        try {
            ElegantProduct prod = productModel.getProduct();
            if (prodManager.deleteProduct(prod)) {
                productModel.getProdList().remove(productModel.getProduct());
                deleted = true;
            } else {
                CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Product. Transactions Exist ");
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private ElegantProduct updateProduct(ElegantProduct prod) {
        prod.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
        prod.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
        prod.setProdID(Long.parseLong((productView.productIdField.getText()).equals("") ? "0" : productView.productIdField.getText()));
        prod.setProdName(productView.nameField.getText());
        prod.setCatNo(productView.catField.getText());
        prod.setSubCatNo(productView.subCatField.getText());
        prod.setColor(productView.colorField.getText());
        prod.setMakeFlag(productView.makeFlagComboBox.getSelectedIndex());
        prod.setFinishedGoodsFlag(productView.finishedGoodsComboBox.getSelectedIndex());
        prod.setOpStock(Double.parseDouble(productView.opStockField.getText()));
        prod.setMinInStock(Double.parseDouble(productView.minStockField.getText()));
        prod.setReOrderPoint(Double.parseDouble(productView.reorderPointField.getText()));
        prod.setStandardCost(Double.parseDouble(productView.standardCostField.getText()));
        prod.setListPrice(Double.parseDouble(productView.listPriceField.getText()));
        prod.setSize(productView.sizeField.getText());
        prod.setUnitForSize(productView.sizeUnitMeasureField.getText());
        prod.setWeight(Double.parseDouble(productView.weightField.getText()));
        prod.setUnitForWeight(productView.weightUnitMeassureField.getText());
        prod.setDaysToManufacture(Integer.parseInt(productView.dayToManField.getText()));
        prod.setProductLine(productView.productLineComboBox.getSelectedIndex());
        prod.setStyle(productView.styleComboBox.getSelectedIndex());
        prod.setActive(productView.statusCheckBox.isSelected() ? 1 : 0);
        if (prod.getCreateDate() == null) {
            prod.setCreateDate(new Date());
        }
        prod.setActive(0);

        return prod;
    }

    private ArrayList<ElegantProduct> getAllProductsForReport(Date frDt, Date toDt, boolean isActive, Double minstock, Double reorder, Double standardPrice, Double listPrice, int sortField, int sortDir) {
        ArrayList<ElegantProduct> elegantProdRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        ProductManager custManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("createDate");
            filterCriteria.setFilterCondition(">=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(frDt));
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("createDate");
            filterCriteria.setFilterCondition("<=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(toDt));
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("active");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(isActive ? "0" : "1");
            filterCriteriaList.add(filterCriteria);
            if (minstock != null && minstock > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("minInStock");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(minstock.toString());
                filterCriteriaList.add(filterCriteria);
            }
            if (reorder != null && reorder > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("reOrderPoint");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(reorder.toString());
                filterCriteriaList.add(filterCriteria);
            }
            if (standardPrice != null && standardPrice > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("standardCost");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(standardPrice.toString());
                filterCriteriaList.add(filterCriteria);
            }
            if (listPrice != null && listPrice > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("listPrice");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(listPrice.toString());
                filterCriteriaList.add(filterCriteria);
            }

            queryCriteria.setFilterCriteria(filterCriteriaList);
            SortCriteria sortCriteria = new SortCriteria();
            if (sortField == 1) {
                sortCriteria.setSortFieldName("prodID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("prodName");
            }
            if (sortField == 3) {
                sortCriteria.setSortFieldName("minInStock");
            }
            if (sortField == 4) {
                sortCriteria.setSortFieldName("reOrderPoint");
            }
            sortCriteria.setSortDirection(sortDir == 1 ? "ASC" : "DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);

            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
//            ServiceControl sc = custManager.getServiceControl();
//            sc.setQueryCriteria(queryCriteria);

            elegantProdRepList = custManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
            productModel.setProdRepList(elegantProdRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllProductsForReport " + e.getMessage());
        }

        return elegantProdRepList;
    }

    void showDialogProdList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (prodListPanel == null) {
            prodListPanel = new ProdListView();
        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, prodListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = prodListPanel.frmDtField.getDate();
                Date toDt = prodListPanel.toDtField.getDate();
                Double minstock = Double.parseDouble(prodListPanel.minStockField.getText());
                Double reorder = Double.parseDouble(prodListPanel.reorderField.getText());
                Double listPrice = Double.parseDouble(prodListPanel.listpriceField.getText());
                Double standardPrice = Double.parseDouble(prodListPanel.standarcostField.getText());
                int rowHeight = new Integer(prodListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(prodListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(prodListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(prodListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(prodListPanel.reportSetup.rightMarginField.getText());
                boolean createBorder = prodListPanel.reportSetup.createBorder.isSelected();
                boolean custActive = (prodListPanel.custActive.isSelected() ? prodListPanel.custActive.isSelected() : false);
                int sortField = (prodListPanel.idSort.isSelected() ? 1 : (prodListPanel.nameSort.isSelected() ? 2 : (prodListPanel.instockSort.isSelected() ? 3 : 4)));
                int sortDir = (prodListPanel.ascDirection.isSelected() ? 1 : 2);

                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(prodListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    prodListPanel = null;
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                productView.setCursor(hourglassCursor);

                getAllProductsForReport(frDt, toDt, custActive, minstock, reorder, standardPrice, listPrice, sortField, sortDir);
                if (productModel.getProdRepList() == null || productModel.getProdRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(prodListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
//                    System.out.println(productModel.getProdRepList().size());
                    ProdListReport prodListReport = new ProdListReport(productModel.getProdRepList(), frDt, toDt, custActive, createBorder, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = prodListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                productView.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the productModel
     */
    public ProductVO getProductModel() {
        return productModel;
    }

    /**
     * @param productModel the productModel to set
     */
    public void setProductModel(ProductVO productModel) {
        this.productModel = productModel;
    }

    /**
     * @return the productView
     */
    public ProductView getProductView() {
        return productView;
    }

    /**
     * @param productView the productView to set
     */
    public void setProductView(ProductView productView) {
        this.productView = productView;
    }

    /**
     * @return the helpTextField
     */
    public HelpTextField getHelpTextFieldPur() {
        return helpTextFieldPur;
    }

    /**
     * @param helpTextField the helpTextField to set
     */
    public void setHelpTextFieldPur(HelpTextField helpTextField) {
        this.helpTextFieldPur = helpTextField;
    }

    /**
     * @return the helpTextFieldSal
     */
    public HelpTextField getHelpTextFieldSal() {
        return helpTextFieldSal;
    }

    /**
     * @param helpTextFieldSal the helpTextFieldSal to set
     */
    public void setHelpTextFieldSal(HelpTextField helpTextFieldSal) {
        this.helpTextFieldSal = helpTextFieldSal;
    }

    /**
     * @return the helpTextFieldPurRTN
     */
    public HelpTextField getHelpTextFieldPurRTN() {
        return helpTextFieldPurRTN;
    }

    /**
     * @param helpTextFieldPurRTN the helpTextFieldPurRTN to set
     */
    public void setHelpTextFieldPurRTN(HelpTextField helpTextFieldPurRTN) {
        this.helpTextFieldPurRTN = helpTextFieldPurRTN;
    }

    /**
     * @return the helpTextFieldSalRTN
     */
    public HelpTextField getHelpTextFieldSalRTN() {
        return helpTextFieldSalRTN;
    }

    /**
     * @param helpTextFieldSalRTN the helpTextFieldSalRTN to set
     */
    public void setHelpTextFieldSalRTN(HelpTextField helpTextFieldSalRTN) {
        this.helpTextFieldSalRTN = helpTextFieldSalRTN;
    }

}
