/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.domain.vo.ElegantBuySellConsolidation;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.BuySellManager;
import com.custmanager.AppManager;
import com.custmanager.ElegantInventory;

import com.custmanager.images.ImagesDir;
import com.custmanager.images.ImagesDir;
import com.custmanager.model.BuySellVO;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.PendingOrderListReport;
import com.custmanager.util.CustUtil;
import com.custmanager.view.GeneralBillHelp;
import com.custmanager.view.OKCancelDialogHelp;

import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.view.PendingBuySellListView;
import com.inder.customcomponents.HelpTextField;
import com.inder.customcomponents.ITitlePanel;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Inderjit Singh Sanhotra
 */
public class ElegantMenuController implements ActionListener {

    private PendingBuySellListView pendingOrderPanel, pendingSaleListPanel, orderVsSaleListPanel;
    private PendingOrderListReport pendingSellListReport, pendingOrdersReport;
    private GeneralBillHelp supHelpPanel, salesManHelpPanel, prodHelpPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int c = 0; c < AppManager.getInstance().getElegantInventory().elegantTabsPanel.getTabCount(); c++) {
            Component comp = (Component) AppManager.getInstance().getElegantInventory().elegantTabsPanel.getComponent(c);
            ITitlePanel panel = null;
            if (comp instanceof ITitlePanel) {
                panel = (ITitlePanel) comp;
            }
            if (panel == null) {
                continue;
            }
            if (e.getActionCommand().equals("Country") && panel.getName().equals("1")
                    || e.getActionCommand().equals("SalesMan") && panel.getName().equals("3")
                    || e.getActionCommand().equals("Product") && panel.getName().equals("2")
                    || e.getActionCommand().equals("Supplier") && panel.getName().equals("4")
                    || e.getActionCommand().equals("Customer") && panel.getName().equals("5")
                    || e.getActionCommand().equals("PurchaseOrder") && panel.getName().equals("7")
                    || e.getActionCommand().equals("SalesInvoice") && panel.getName().equals("9")
                    || e.getActionCommand().equals("PurchaseRTN") && panel.getName().equals("10")
                    || e.getActionCommand().equals("SalesRTN") && panel.getName().equals("11")
                    || e.getActionCommand().equals("UserAdmin") && panel.getName().equals("12")) {
                AppManager.getInstance().getElegantInventory().elegantTabsPanel.setSelectedComponent(comp);
                break;
            }
        }
        if (e.getActionCommand().equals("userlisting")) {
            UserAdminButtonController userAdminButtonController = (UserAdminButtonController) AppManager.getInstance().getViewControllerList().get("userAdminController");
            if (userAdminButtonController != null) {
                userAdminButtonController.showDialogUserList();
            }
        } else if (e.getActionCommand().equals("docs")) {
        } else if (e.getActionCommand().equals("chat")) {
        } else if (e.getActionCommand().equalsIgnoreCase("Countries")) {
            CountryButtonController buttonController = (CountryButtonController) AppManager.getInstance().getViewControllerList().get("countryController");
            if (buttonController != null) {
                buttonController.showDialogCountryList();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("CustomersRep")) {
            CustomerButtonController buttonController = (CustomerButtonController) AppManager.getInstance().getViewControllerList().get("custController");
            if (buttonController != null) {
                buttonController.showDialogCustList();
            }
        } else if (e.getActionCommand().equals("SalesManRep")) {
            SalesManButtonController buttonController = (SalesManButtonController) AppManager.getInstance().getViewControllerList().get("salesManController");
            if (buttonController != null) {
                buttonController.showDialogSalesManList();
            }
        } else if (e.getActionCommand().equals("SuppliersRep")) {
            SupplierButtonController buttonController = (SupplierButtonController) AppManager.getInstance().getViewControllerList().get("suppController");
            if (buttonController != null) {
                buttonController.showDialogSuppList();
            }
        } else if (e.getActionCommand().equals("ProductsRep")) {
            ProductButtonController buttonController = (ProductButtonController) AppManager.getInstance().getViewControllerList().get("productController");
            if (buttonController != null) {
                buttonController.showDialogProdList();
            }
        } else if (e.getActionCommand().equals("OrderVsSale")) {
            showOrderVsSaleList();
        } else if (e.getActionCommand().equals("PendingOrders")) {
            showPendingOrdersList();
        } else if (e.getActionCommand().equals("PendingSales")) {
            showPendingSalesList();
        } else if (e.getActionCommand().equals("PurchaseRep")) {
            PurchaseButtonController buttonController = (PurchaseButtonController) AppManager.getInstance().getViewControllerList().get("purchaseController");
            if (buttonController != null) {
                buttonController.showDialogPurchaseList();
            }
        } else if (e.getActionCommand().equals("PurchaseRTNRep")) {
            PurchaseRtnButtonController buttonController = (PurchaseRtnButtonController) AppManager.getInstance().getViewControllerList().get("purchaseRtnController");
            if (buttonController != null) {
                buttonController.showDialogPurchaseRtnList();
            }

        } else if (e.getActionCommand().equals("SalesRep")) {
            SaleButtonController buttonController = (SaleButtonController) AppManager.getInstance().getViewControllerList().get("saleController");
            if (buttonController != null) {
                buttonController.showDialogSalesList();
            }
        } else if (e.getActionCommand().equals("SalesRTNRep")) {
            SalesRtnButtonController buttonController = (SalesRtnButtonController) AppManager.getInstance().getViewControllerList().get("saleRtnController");
            if (buttonController != null) {
                buttonController.showDialogSalesRtnList();
            }
        } else if (e.getActionCommand().equals("Exit")) {
            int optionSelected = ElegantInventory.confirmExit();
            if (optionSelected == JOptionPane.YES_OPTION) {
                AppManager appManager = AppManager.getInstance();
                appManager.getViewList().clear();
                appManager = null;
                System.exit(0);
            }
        } else if (e.getActionCommand().equals("supHelpFieldPendRep")) {
            if (this.getSupHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSupHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getSupHelpPanel().tableHelp.getSelectedRow();
                    this.pendingOrderPanel.supCodeField.textField.setText(Long.toString((Long) this.getSupHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equals("salesManHelpFieldPendRep")) {
            if (this.getSalesManHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalesManHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getSalesManHelpPanel().tableHelp.getSelectedRow();
                    this.pendingOrderPanel.salesManCodeField.textField.setText(Long.toString((Long) this.getSalesManHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equals("productHelpFieldRep")) {
            if (this.getProdHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getProdHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getProdHelpPanel().tableHelp.getSelectedRow();
                    this.pendingOrderPanel.productCodeField.textField.setText(Long.toString((Long) this.getProdHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equals("custHelpFieldPendRep")) {
            if (this.supHelpPanel != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.supHelpPanel);
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.supHelpPanel.tableHelp.getSelectedRow();
                    this.pendingSaleListPanel.supCodeField.textField.setText(Long.toString((Long) this.supHelpPanel.tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equals("salesManHelpFieldPendSaleRep")) {
            if (this.getSalesManHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalesManHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getSalesManHelpPanel().tableHelp.getSelectedRow();
                    this.pendingSaleListPanel.salesManCodeField.textField.setText(Long.toString((Long) this.getSalesManHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equals("productHelpFieldSaleRep")) {
            if (this.getProdHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getProdHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getProdHelpPanel().tableHelp.getSelectedRow();
                    this.pendingSaleListPanel.productCodeField.textField.setText(Long.toString((Long) this.getProdHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        }
    }

    private ArrayList<ElegantBuySellConsolidation> getAllPendingOrdersForReport(String salesMan, String supCode, String prodCode, Date frDt, Date toDt, String billType) {
        ArrayList<ElegantBuySellConsolidation> elegantBuySellRepList = null;
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
        try {
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
            elegantBuySellRepList = purManager.getAllPendingOrders(PersistanceManager.getInstance().getElegantUser(), supCode, salesMan, prodCode, frDt, toDt, CustUtil.PENDINGPURCHASEORDERS);
            BuySellVO purchaseModel = (BuySellVO) AppManager.getInstance().getViewModelList().get("purchaseModel");
            purchaseModel.setPendingBuyRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllPendingOrdersForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    void showPendingOrdersList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.pendingOrderPanel == null) {
            this.pendingOrderPanel = new PendingBuySellListView();
            this.pendingOrderPanel.labelHeader.setText("Enter Pending Order Selection Criteria Below");
            GeneralBillHelp suphelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("supplierCodeHelp");
            GeneralBillHelp salesmanHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            GeneralBillHelp productHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("productHelp");

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.setSupHelpPanel(suphelpPanel);
            this.getSupHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getSupHelpPanel().setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            this.setSalesManHelpPanel(salesmanHelpPanel);
            this.getSalesManHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getSalesManHelpPanel().setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            this.setProdHelpPanel(productHelpPanel);
            this.getProdHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getProdHelpPanel().setTableColWidths();

            HelpTextField supHelpTextField = this.pendingOrderPanel.supCodeField;
            supHelpTextField.helpButton.setActionCommand("supHelpFieldPendRep");
            supHelpTextField.addButtonController(this);

            HelpTextField salesManHelpTextField = this.pendingOrderPanel.salesManCodeField;
            salesManHelpTextField.helpButton.setActionCommand("salesManHelpFieldPendRep");
            salesManHelpTextField.addButtonController(this);

            HelpTextField productHelpTextField = this.pendingOrderPanel.productCodeField;
            productHelpTextField.helpButton.setActionCommand("productHelpFieldRep");
            productHelpTextField.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, pendingOrderPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = pendingOrderPanel.frmDtField.getDate();
                Date toDt = pendingOrderPanel.toDtField.getDate();
                String supCode = pendingOrderPanel.supCodeField.textField.getText();
                String salesManCode = pendingOrderPanel.salesManCodeField.textField.getText();
                String productCode = pendingOrderPanel.productCodeField.textField.getText();
                int rowHeight = new Integer(pendingOrderPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(pendingOrderPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(pendingOrderPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(pendingOrderPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(pendingOrderPanel.reportSetup.rightMarginField.getText());

                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(pendingOrderPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                pendingOrderPanel.setCursor(hourglassCursor);
                getAllPendingOrdersForReport(salesManCode, supCode, productCode, frDt, toDt, CustUtil.PENDINGPURCHASEORDERS);
                BuySellVO purchaseModel = (BuySellVO) AppManager.getInstance().getViewModelList().get("purchaseModel");
                if (purchaseModel == null) {
                    JOptionPane.showMessageDialog(pendingOrderPanel.getRootPane().getContentPane(), "You do not have enough Access to View this Report.", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else if (purchaseModel == null || purchaseModel.getPendingBuyRepList() == null || purchaseModel.getPendingBuyRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(pendingOrderPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);

                } else {
                    pendingOrdersReport = new PendingOrderListReport(purchaseModel.getPendingBuyRepList(), frDt, toDt, supCode, salesManCode, productCode, CustUtil.PENDINGPURCHASEORDERS, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = pendingOrdersReport.getReport();
                    JasperViewer jasperViewer = new JasperViewer(jp, false);
                    jasperViewer.setVisible(true);
                    jasperViewer.setAlwaysOnTop(false);
                    jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
                    jasperViewer.setTitle(CustUtil.APPNAME);
                    jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
                    jasperViewer.setTitle(CustUtil.APPNAME);

                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                pendingOrderPanel.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the supHelpPanel
     */
    public GeneralBillHelp getSupHelpPanel() {
        return supHelpPanel;
    }

    /**
     * @param supHelpPanel the supHelpPanel to set
     */
    public void setSupHelpPanel(GeneralBillHelp supHelpPanel) {
        this.supHelpPanel = supHelpPanel;
    }

    /**
     * @return the salesManHelpPanel
     */
    public GeneralBillHelp getSalesManHelpPanel() {
        return salesManHelpPanel;
    }

    /**
     * @param salesManHelpPanel the salesManHelpPanel to set
     */
    public void setSalesManHelpPanel(GeneralBillHelp salesManHelpPanel) {
        this.salesManHelpPanel = salesManHelpPanel;
    }

    /**
     * @return the prodHelpPanel
     */
    public GeneralBillHelp getProdHelpPanel() {
        return prodHelpPanel;
    }

    /**
     * @param prodHelpPanel the prodHelpPanel to set
     */
    public void setProdHelpPanel(GeneralBillHelp prodHelpPanel) {
        this.prodHelpPanel = prodHelpPanel;
    }

    private ArrayList<ElegantBuySellConsolidation> getAllPendingSalesForReport(String salesMan, String supCode, String prodCode, Date frDt, Date toDt, String billType) {
        ArrayList<ElegantBuySellConsolidation> elegantBuySellRepList = null;
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
        try {
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
            elegantBuySellRepList = purManager.getAllPendingOrders(PersistanceManager.getInstance().getElegantUser(), supCode, salesMan, prodCode, frDt, toDt, CustUtil.PENDINGSALESORDERS);
            BuySellVO saleModel = (BuySellVO) AppManager.getInstance().getViewModelList().get("saleModel");
            saleModel.setPendingBuyRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllPendingSalesForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    public void showPendingSalesList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.pendingSaleListPanel == null) {
            this.pendingSaleListPanel = new PendingBuySellListView();
            this.pendingSaleListPanel.labelHeader.setText("Enter Pending Sales Selection Criteria Below");
            this.pendingSaleListPanel.labelSupCust.setText("Cust Code");
            GeneralBillHelp custhelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("custCodeHelp");
            GeneralBillHelp salesmanHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            GeneralBillHelp productHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("productHelp");
            this.setSupHelpPanel(custhelpPanel);
            this.setSalesManHelpPanel(salesmanHelpPanel);
            this.setProdHelpPanel(productHelpPanel);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.supHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            this.supHelpPanel.setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            this.getSalesManHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getSalesManHelpPanel().setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            this.getProdHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getProdHelpPanel().setTableColWidths();

            HelpTextField supHelpTextField = this.pendingSaleListPanel.supCodeField;
            supHelpTextField.helpButton.setActionCommand("custHelpFieldPendRep");
            supHelpTextField.addButtonController(this);

            HelpTextField salesManHelpTextField = this.pendingSaleListPanel.salesManCodeField;
            salesManHelpTextField.helpButton.setActionCommand("salesManHelpFieldPendSaleRep");
            salesManHelpTextField.addButtonController(this);

            HelpTextField productHelpTextField = this.pendingSaleListPanel.productCodeField;
            productHelpTextField.helpButton.setActionCommand("productHelpFieldSaleRep");
            productHelpTextField.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, pendingSaleListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = pendingSaleListPanel.frmDtField.getDate();
                Date toDt = pendingSaleListPanel.toDtField.getDate();
                String supCode = pendingSaleListPanel.supCodeField.textField.getText();
                String salesManCode = pendingSaleListPanel.salesManCodeField.textField.getText();
                String productCode = pendingSaleListPanel.productCodeField.textField.getText();
                int rowHeight = new Integer(pendingSaleListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(pendingSaleListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(pendingSaleListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(pendingSaleListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(pendingSaleListPanel.reportSetup.rightMarginField.getText());

                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(pendingSaleListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                this.pendingSaleListPanel.setCursor(hourglassCursor);
                getAllPendingSalesForReport(salesManCode, supCode, productCode, frDt, toDt, CustUtil.PENDINGSALESORDERS);
                BuySellVO saleModel = (BuySellVO) AppManager.getInstance().getViewModelList().get("saleModel");
                if (saleModel == null) {
                    JOptionPane.showMessageDialog(pendingSaleListPanel.getRootPane().getContentPane(), "You do not have enough Access to View this Report.", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else if (saleModel.getPendingBuyRepList() == null || saleModel.getPendingBuyRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(pendingSaleListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    pendingSellListReport = new PendingOrderListReport(saleModel.getPendingBuyRepList(), frDt, toDt, supCode, salesManCode, productCode, CustUtil.PENDINGSALESORDERS, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = pendingSellListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                this.pendingSaleListPanel.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private ArrayList<ElegantBuySellConsolidation> getAllOrderVsSaleForReport(String salesMan, String supCode, String prodCode, Date frDt, Date toDt, String billType) {
        ArrayList<ElegantBuySellConsolidation> elegantBuySellRepList = null;
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
        try {
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
            elegantBuySellRepList = purManager.getAllPendingOrders(PersistanceManager.getInstance().getElegantUser(), supCode, salesMan, prodCode, frDt, toDt, CustUtil.ORDERVSSALES);
            BuySellVO saleModel = (BuySellVO) AppManager.getInstance().getViewModelList().get("saleModel");
            saleModel.setPendingBuyRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllOrderVsSaleForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    public void showOrderVsSaleList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.orderVsSaleListPanel == null) {
            this.orderVsSaleListPanel = new PendingBuySellListView();
            this.orderVsSaleListPanel.labelHeader.setText("Enter Order Vs Sale Selection Criteria Below");
            this.orderVsSaleListPanel.labelSupCust.setText("Cust Code");
            GeneralBillHelp custhelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("custCodeHelp");
            GeneralBillHelp salesmanHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            GeneralBillHelp productHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("productHelp");
            this.setSupHelpPanel(custhelpPanel);
            this.setSalesManHelpPanel(salesmanHelpPanel);
            this.setProdHelpPanel(productHelpPanel);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.supHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            this.supHelpPanel.setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            this.getSalesManHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getSalesManHelpPanel().setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            this.getProdHelpPanel().setTableCellRenderer(customerTableCellRenderer);
            this.getProdHelpPanel().setTableColWidths();

            HelpTextField supHelpTextField = this.orderVsSaleListPanel.supCodeField;
            supHelpTextField.helpButton.setActionCommand("custHelpFieldPendRep");
            supHelpTextField.addButtonController(this);

            HelpTextField salesManHelpTextField = this.orderVsSaleListPanel.salesManCodeField;
            salesManHelpTextField.helpButton.setActionCommand("salesManHelpFieldPendSaleRep");
            salesManHelpTextField.addButtonController(this);

            HelpTextField productHelpTextField = this.orderVsSaleListPanel.productCodeField;
            productHelpTextField.helpButton.setActionCommand("productHelpFieldSaleRep");
            productHelpTextField.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, orderVsSaleListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = orderVsSaleListPanel.frmDtField.getDate();
                Date toDt = orderVsSaleListPanel.toDtField.getDate();
                String supCode = orderVsSaleListPanel.supCodeField.textField.getText();
                String salesManCode = orderVsSaleListPanel.salesManCodeField.textField.getText();
                String productCode = orderVsSaleListPanel.productCodeField.textField.getText();
                int rowHeight = new Integer(orderVsSaleListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(orderVsSaleListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(orderVsSaleListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(orderVsSaleListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(orderVsSaleListPanel.reportSetup.rightMarginField.getText());

                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(orderVsSaleListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                this.orderVsSaleListPanel.setCursor(hourglassCursor);
                getAllOrderVsSaleForReport(salesManCode, supCode, productCode, frDt, toDt, CustUtil.ORDERVSSALES);
                BuySellVO saleModel = (BuySellVO) AppManager.getInstance().getViewModelList().get("saleModel");
                if (saleModel == null) {
                    JOptionPane.showMessageDialog(orderVsSaleListPanel.getRootPane().getContentPane(), "You do not have enough access to view this Report.", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else if (saleModel.getPendingBuyRepList() == null || saleModel.getPendingBuyRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(orderVsSaleListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);

                } else {
                    pendingSellListReport = new PendingOrderListReport(saleModel.getPendingBuyRepList(), frDt, toDt, supCode, salesManCode, productCode, CustUtil.ORDERVSSALES, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = pendingSellListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                this.orderVsSaleListPanel.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
