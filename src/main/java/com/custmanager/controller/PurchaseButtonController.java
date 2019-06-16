/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.ElegantBuySellDetails;
import com.cust.domain.vo.ElegantBuySell;
import com.cust.domain.vo.ElegantProduct;
import com.cust.domain.vo.ElegantSalesMan;
import com.cust.domain.vo.ElegantSupplier;
import com.cust.domain.vo.ElegantUser;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.BuySellManager;
import com.cust.persistance.managers.ProductManager;
import com.custmanager.AppManager;
import static com.custmanager.AppManager.controllerHelp;
import com.custmanager.images.ImagesDir;
import com.custmanager.util.CustUtil;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.model.BuySellVO;
import com.custmanager.model.SalesManTableModel;
import com.custmanager.model.SupTableModel;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.BuySellBillReport;
import com.custmanager.reports.BuySellListReport;
import com.custmanager.view.BillPrintView;
import com.custmanager.view.BuySellListView;
import com.custmanager.view.OKCancelDialogHelp;
import com.custmanager.view.GeneralBillHelp;
import com.custmanager.view.PurchaseView;
import com.custmanager.vo.BuySellReportData;
import com.inder.customcomponents.HelpTextField;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Inderjit
 */
public class PurchaseButtonController implements ActionListener {

    private BuySellVO purchaseModel;
    private PurchaseView purchaseView;
    private BuySellListReport buySellListReport;
    private BuySellListView buySellListPanel;
    private GeneralBillHelp supHelpPanel, salesManHelpPanel, purHelpPanel, prodHelpPanel;
    private BillPrintView purchaseBillPrintView;

    public PurchaseButtonController() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            CustUtil.disableOrEnableForAuth(purchaseView, true);
            purchaseView.initTextFields();
            purchaseView.initButtonsForNew();
            purchaseModel.setElegantBuy(new ElegantBuySell());
            purchaseModel.setBuyDetailsList(new ArrayList<>());
            purchaseView.searchField.setEnabled(false);
            purchaseView.authPanel.setVisible(false);

        } else if (e.getActionCommand().equalsIgnoreCase("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(purchaseView, "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("PrintList")) {
            showDialogPurchaseList();
        } else if (e.getActionCommand().equalsIgnoreCase("PrintBill")) {
            showDialogPurchaseBillPrint();
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            if (saveUpdate()) {
                CustUtil.disableOrEnableForAuth(purchaseView, true);
                purchaseView.initTextFields();
                purchaseView.initButtons();
                purchaseModel.setElegantBuy(new ElegantBuySell());
                purchaseModel.setBuyDetailsList(new ArrayList<>());
                purchaseView.searchField.setEnabled(false);
                purchaseView.authPanel.setVisible(false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Delete")) {
            if (CustUtil.confirmDelete(purchaseView)) {
                if (delete()) {
                    purchaseView.initTextFields();
                    purchaseView.initButtons();
                    purchaseModel.setElegantBuy(new ElegantBuySell());
                    purchaseModel.setBuyDetailsList(new ArrayList<>());
                    purchaseView.searchField.setEnabled(false);
                    purchaseView.authPanel.setVisible(false);
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Discard")) {
            CustUtil.disableOrEnableForAuth(purchaseView, true);
            purchaseView.initTextFields();
            purchaseView.initButtons();
            purchaseModel.setElegantBuy(new ElegantBuySell());
            purchaseModel.setBuyDetailsList(new ArrayList<>());
//            purchaseView.searchField.setEnabled(true);
            purchaseView.authPanel.setVisible(false);
            return;
        } else if (e.getActionCommand().equalsIgnoreCase("purBillHelp")) {
            showPurchaseBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("supHelp")) {
            showSupplierBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("salesManHelp")) {
            showSalesManCodeHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("addRow")) {
            addRow();
        } else if (e.getActionCommand().equalsIgnoreCase("removeRow")) {
            removeRow();
        } else if (e.getActionCommand().equals("purHelpFieldRep1")) {
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getPurHelpPanel());
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = this.getPurHelpPanel().tableHelp.getSelectedRow();
                this.getPurchaseBillPrintView().helpTextField1.textField.setText(Long.toString((Long) this.getPurHelpPanel().tableHelp.getValueAt(row, 0)));
            }
        } else if (e.getActionCommand().equalsIgnoreCase("purHelpFieldRep2")) {
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getPurHelpPanel());
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = this.getPurHelpPanel().tableHelp.getSelectedRow();
                this.getPurchaseBillPrintView().helpTextField2.textField.setText(Long.toString((Long) this.getPurHelpPanel().tableHelp.getValueAt(row, 0)));
            }

        } else if (e.getActionCommand().equalsIgnoreCase("supHelpFieldRep")) {
            if (this.getSupHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSupHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getSupHelpPanel().tableHelp.getSelectedRow();
                    this.buySellListPanel.supCodeField.textField.setText(Long.toString((Long) this.getSupHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("salesManHelpFieldRep")) {
            if (this.getSalesManHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalesManHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getSalesManHelpPanel().tableHelp.getSelectedRow();
                    this.buySellListPanel.salesManCodeField.textField.setText(Long.toString((Long) this.getSalesManHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("authstatus")) {
            if (purchaseView.authPanel.authStatCombo.getSelectedIndex() == 1) {
                purchaseView.authPanel.itemStockPost.setEnabled(true);
            } else {
                purchaseView.authPanel.itemStockPost.setSelected(false);
                purchaseView.authPanel.itemStockPost.setEnabled(false);
            }
        }

        purchaseView.setTableColWidths();
    }

    private void addRow() {
        ArrayList<ElegantBuySellDetails> tempList = purchaseModel.getBuyDetailsList();
        ElegantBuySellDetails elegantBuySellDetails = new ElegantBuySellDetails();
        long srl = tempList.size() == 0 ? 1 : tempList.size() + 1;
        elegantBuySellDetails.setSrl(srl);
        tempList.add(elegantBuySellDetails);
        long newSrl = 1;
        for (ElegantBuySellDetails temp : tempList) {
            temp.setSrl(newSrl);
            newSrl++;
        }
        updateBuySell(this.purchaseModel.getElegantBuy());
        purchaseModel.setBuyDetailsList(tempList);
    }

    private void removeRow() {
        ArrayList<ElegantBuySellDetails> tempList = purchaseModel.getBuyDetailsList();
        int indexToDelete = purchaseView.tablePurchaseDetails.getSelectedRow();
        if (indexToDelete > 0) {
            tempList.remove(indexToDelete);
            long newSrl = 1;
            for (ElegantBuySellDetails temp : tempList) {
                temp.setSrl(newSrl);
                newSrl++;
            }
            updateBuySell(this.purchaseModel.getElegantBuy());
            purchaseModel.setBuyDetailsList(tempList);
        }
    }

    public void initAllFields(boolean initDetails) {
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
        purchaseView.authPanel.setVisible(false);
        try {
            ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASEBILLTYPE, true);
            if (elegantBuySellList != null) {
                Collections.sort(elegantBuySellList);
            }

            this.purchaseModel.setBuyList(elegantBuySellList);
            if (initDetails) {
                this.purchaseModel.setElegantBuy(new ElegantBuySell());
                this.purchaseModel.setBuyDetailsList(new ArrayList<ElegantBuySellDetails>());
                purchaseView.setTableColWidths();

            }
            if (!AppManager.getInstance().getElegantInventory().menuPurRep.isEnabled()) {
                purchaseView.buttonPanel.printButton.setEnabled(false);
            }

        } catch (Exception e) {
            System.out.println("Init All Fields " + e.getMessage());
        }
    }

    public boolean saveUpdate() {
        boolean saved = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return saved;
        }
        ArrayList<ElegantBuySell> buySellListToSave = new ArrayList<>();
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
        try {
            ElegantBuySell buySell = updateBuySell(purchaseModel.getElegantBuy());
            if (!validateData()) {
                return saved;
            }
            buySellListToSave.add(buySell);
            saved = buySellManager.saveOrUpdateBuySell(buySellListToSave, purchaseView.authPanel.itemStockPost.isSelected());
            if (saved) {
                ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get(controllerHelp[1]);
                ProductManager productManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
                ArrayList<ElegantProduct> elegantProductList = productManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
                bttnController.getProductModel().setProdList(elegantProductList);

                buySellListToSave = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASEBILLTYPE, true);
                if (buySellListToSave != null) {
                    Collections.sort(buySellListToSave);
                }

                this.purchaseModel.setBuyList(buySellListToSave);
                this.purchaseModel.setElegantBuy(new ElegantBuySell());
                this.purchaseModel.setBuyDetailsList(new ArrayList<>());
            }
        } catch (Exception e) {
            CustUtil.showErrorDialogue("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    private boolean validateData() {
        boolean validated = true;
        ElegantBuySell elegantBuySell = this.purchaseModel.getElegantBuy();
        if ((elegantBuySell.getBillDt() == null || elegantBuySell.getBillNo() == null || elegantBuySell.getBuyerSellBillNo() == null || elegantBuySell.getFinalBillAmt() == null)
                || (elegantBuySell.getBillNo() == "" || elegantBuySell.getBuyerSellBillNo() == "" || elegantBuySell.getFinalBillAmt() == 0.00)) {
            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
            validated = false;
        }
        if (elegantBuySell.getBillDt().before(elegantBuySell.getBuyerSellerBillDt())) {
            CustUtil.showMessageDialogue("Bill date is Invalid or Before Supplier Bill Date");
            validated = false;

        }
        
        return validated;
    }

    public boolean delete() {
        boolean deleted = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return deleted;
        }
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
        try {
            ElegantBuySell buySell = purchaseModel.getElegantBuy();
            if (buySellManager.deleteBuySell(buySell)) {
                ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get(controllerHelp[1]);
                ProductManager productManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
                ArrayList<ElegantProduct> elegantProductList = productManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
                bttnController.getProductModel().setProdList(elegantProductList);

                ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASEBILLTYPE, true);
                if (elegantBuySellList != null) {
                    Collections.sort(elegantBuySellList);
                }

                this.purchaseModel.setBuyList(elegantBuySellList);
                this.purchaseModel.setBuyDetailsList(new ArrayList<>());
                deleted = true;
            } else {
                CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Purchase. Transactions Exist ");
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private ElegantBuySell updateBuySell(ElegantBuySell buySell) {
        buySell.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
        buySell.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
        buySell.setBillID(Long.parseLong((purchaseView.billIdField.getText()).equals("") ? "0" : purchaseView.billIdField.getText()));
        buySell.setBillType(CustUtil.PURCHASEBILLTYPE);
        buySell.setBillNo(purchaseView.purOrderNo.getText());
        buySell.setBillDt(purchaseView.purchaseDate.getDate());
        buySell.setBuyerSellerCode(Integer.parseInt(purchaseView.supIdField.getText()));
        buySell.setBuyerSellerName(purchaseView.salesManNameField.getText());
        buySell.setBuyerSellBillNo(purchaseView.supBillNo.getText());
        buySell.setBuyerSellerBillDt(purchaseView.supBillDt.getDate());
        buySell.setSalesManCode(Integer.parseInt(purchaseView.salesManIdField.getText()));
        buySell.setFreighTranspDedAmt(Double.parseDouble(purchaseView.freightField.getText()));
        buySell.setTaxDedAmt(Double.parseDouble(purchaseView.taxField.getText()));
        buySell.setFinalBillAmt(calculateBillAmt(buySell));
        buySell.setRemarks(purchaseView.remarks.getText());

        if (buySell.getBillID() == 0) {
            buySell.setCreateDate(new Date());
            buySell.setBillStatus(0);
        }
        ElegantUser user = PersistanceManager.getInstance().getElegantUser();
        if (user.getAccountType() == 2 || (user.getDivision() == 2 && (user.getRole() == "ROLE_ADMIN"))) {
            if (purchaseView.authPanel.authStatCombo.getSelectedIndex() > 0) {
                doUpdateBillStatus(buySell);
            }
        }
        return buySell;
    }

    private void doUpdateBillStatus(ElegantBuySell buySell) {
        if (buySell.getBillID() == 0) {
            buySell.setAuthRequired(0);
        }
        if (purchaseView.authPanel.authDate.getDate() != null) {
            buySell.setAuthDate(purchaseView.authPanel.authDate.getDate());
            buySell.setAuthRemarks(purchaseView.authPanel.authRemark.getText());
            buySell.setAuthStatus(purchaseView.authPanel.authStatCombo.getSelectedIndex());
            buySell.setStockPosted(purchaseView.authPanel.itemStockPost.isSelected() ? 1 : 0);
            if ((buySell.getAuthStatus() != null && buySell.getAuthStatus() > 0) && (buySell.getStockPosted() != null && buySell.getStockPosted() == 1)) {
                buySell.setBillStatus(1); // lock bill from further updates as already authroised        
            }
        }
    }

    private double calculateBillAmt(ElegantBuySell elegantBuySell) {
        double total = 0.0;

        ArrayList<ElegantBuySellDetails> elegantBuySellDetailsList = elegantBuySell.getBuySellDetailsList();
        for (ElegantBuySellDetails elegantBuySellDetails : elegantBuySellDetailsList) {
            double tempFinalAmt = (elegantBuySellDetails.getPurchQty() * elegantBuySellDetails.getPurchRate()) - elegantBuySellDetails.getUnitDiscount();
            elegantBuySellDetails.setUnitAmt(tempFinalAmt);
            total += elegantBuySellDetails.getUnitAmt();
        }
        return total - elegantBuySell.getFreighTranspDedAmt() - elegantBuySell.getTaxDedAmt();
    }

    private ArrayList<ElegantBuySell> getAllBuySellForReport(Date frDt, Date toDt, boolean isActive, String supCode, String salesMan, String billAmt, int sortField, int sortDir) {
        ArrayList<ElegantBuySell> elegantBuySellRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<>();
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
        try {

            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("billDt");
            filterCriteria.setFilterCondition(">=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(frDt));
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("billDt");
            filterCriteria.setFilterCondition("<=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(toDt));
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("billStatus");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(isActive ? "0" : "1");
            filterCriteriaList.add(filterCriteria);
            if (supCode != null && !supCode.equals("") && Integer.parseInt(supCode) > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("buyerSellerCode");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue(supCode);
                filterCriteriaList.add(filterCriteria);
            }
            if (salesMan != null && !salesMan.isEmpty() && Integer.parseInt(salesMan) > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("salesManCode");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue(salesMan);
                filterCriteriaList.add(filterCriteria);
            }
            if (billAmt != null && !billAmt.isEmpty() && Double.parseDouble(billAmt) > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("finalBillAmt");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(billAmt);
                filterCriteriaList.add(filterCriteria);
            }
            queryCriteria.setFilterCriteria(filterCriteriaList);
            SortCriteria sortCriteria = new SortCriteria();
            if (sortField == 1) {
                sortCriteria.setSortFieldName("billID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("billNo");
            }
            if (sortField == 3) {
                sortCriteria.setSortFieldName("billDt");
            }
            if (sortField == 4) {
                sortCriteria.setSortFieldName("buyerSellBillNo");
            }
            if (sortField == 5) {
                sortCriteria.setSortFieldName("buyerSellerBillDt");
            }
            sortCriteria.setSortDirection(sortDir == 1 ? "ASC" : "DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);

            elegantBuySellRepList = purManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASEBILLTYPE, true);
            purchaseModel.setBuySellRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllBuySellForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    private ArrayList<ElegantBuySell> getAllBuySellBillsForReport(String fromBill, String toBill) {
        ArrayList<ElegantBuySell> elegantBuySellRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<>();
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("billID");
            filterCriteria.setFilterCondition(">=");
            filterCriteria.setFilterFieldValue(fromBill);
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("billID");
            filterCriteria.setFilterCondition("<=");
            filterCriteria.setFilterFieldValue(toBill);
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("billType");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(Integer.toString(CustUtil.PURCHASEBILLTYPE));
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);

            elegantBuySellRepList = purManager.getAllBuySellForRep(PersistanceManager.getInstance().getElegantUser());
            purchaseModel.setBuySellRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllBuySellBillsForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    void showDialogPurchaseBillPrint() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.getPurchaseBillPrintView() == null) {
            this.setPurchaseBillPrintView(new BillPrintView());
            this.getPurchaseBillPrintView().headerLabel.setText("Enter Order Nos to View Below");
            GeneralBillHelp purchaseHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("purchaseBillHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();

            this.setPurHelpPanel(purchaseHelpPanel);
            purchaseHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            purchaseHelpPanel.setTableColWidths();

            HelpTextField purHelpTextField1 = this.getPurchaseBillPrintView().helpTextField1;
            purHelpTextField1.helpButton.setActionCommand("purHelpFieldRep1");
            purHelpTextField1.addButtonController(this);

            purHelpTextField1 = this.getPurchaseBillPrintView().helpTextField2;
            purHelpTextField1.helpButton.setActionCommand("purHelpFieldRep2");
            purHelpTextField1.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, getPurchaseBillPrintView());
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                String fromBillNo = this.getPurchaseBillPrintView().helpTextField1.textField.getText();
                String toBillNo = this.getPurchaseBillPrintView().helpTextField2.textField.getText();
                if (fromBillNo.isEmpty() || toBillNo.isEmpty() || (Integer.parseInt(toBillNo) == 0 || Integer.parseInt(toBillNo) == 0)) {
                    JOptionPane.showMessageDialog(this.getPurchaseBillPrintView().getRootPane().getContentPane(), "Please Enter Bill No in From and To Fields", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (Long.parseLong(fromBillNo) > Long.parseLong(toBillNo)) {
                    JOptionPane.showMessageDialog(this.getPurchaseBillPrintView().getRootPane().getContentPane(), "Please enter Valid Bill No in From and To Fields", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int no = Integer.parseInt(fromBillNo), no1 = Integer.parseInt(toBillNo);
                if (no1 - no >= 1000) {
                    JOptionPane.showMessageDialog(this.getPurchaseBillPrintView().getRootPane().getContentPane(), "Please Select Less than 1000 Bills at a time for Print", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int rowHeight = new Integer(getPurchaseBillPrintView().reportSetup.rowHeightField.getText());
                int topMargin = new Integer(getPurchaseBillPrintView().reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(getPurchaseBillPrintView().reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(getPurchaseBillPrintView().reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(getPurchaseBillPrintView().reportSetup.rightMarginField.getText());
                boolean createBorder = getPurchaseBillPrintView().reportSetup.createBorder.isSelected();
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                purchaseView.setCursor(hourglassCursor);
                getAllBuySellBillsForReport(fromBillNo, toBillNo);
                if (purchaseModel.getBuySellRepList() == null || purchaseModel.getBuySellRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(this.getPurchaseBillPrintView().getRootPane().getContentPane(), "Bills Not Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<BuySellReportData> reportData = createValueObjectForBills(purchaseModel.getBuySellRepList());
                    BuySellBillReport buySellBillReport = new BuySellBillReport(reportData, createBorder, rowHeight, topMargin, leftMargin, bottomMargin, rightMargin);
                    JasperPrint jp = buySellBillReport.getReport();
                    JasperViewer jasperViewer = new JasperViewer(jp, false);
                    jasperViewer.setVisible(true);
                    jasperViewer.setAlwaysOnTop(false);
                    jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
                    jasperViewer.setTitle(CustUtil.APPNAME);

                }

                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                purchaseView.setCursor(hourglassCursor);

            } catch (NumberFormatException | HeadlessException | ColumnBuilderException | JRException | ClassNotFoundException | SecurityException e) {
                System.out.println("showDialogPurchaseBillPrint" + e.getMessage());
            }
        }
    }

    ArrayList<BuySellReportData> createValueObjectForBills(ArrayList<ElegantBuySell> elegantBuySellList) {
        ArrayList<BuySellReportData> returnList = new ArrayList<BuySellReportData>();
        for (ElegantBuySell elegantBuySell : elegantBuySellList) {
            ArrayList<ElegantBuySellDetails> elegantBuySellDetailsList = elegantBuySell.getBuySellDetailsList();
            BuySellReportData buySellReportData = new BuySellReportData();
            if (elegantBuySellDetailsList != null && !elegantBuySellDetailsList.isEmpty()) {
                for (ElegantBuySellDetails elegantBuySellDetails : elegantBuySellDetailsList) {
                    buySellReportData.setBillId(elegantBuySellDetails.getBillID());
                    buySellReportData.setBillNo(elegantBuySell.getBuyerSellBillNo());
                    buySellReportData.setBillDt(elegantBuySell.getBillDt());
                    buySellReportData.setFreight(elegantBuySell.getFreighTranspDedAmt());
                    buySellReportData.setTax(elegantBuySell.getTaxDedAmt());
                    buySellReportData.setFinalBillAmt(elegantBuySell.getFinalBillAmt());
                    buySellReportData.setBuyerSellCode(elegantBuySell.getBuyerSellerCode());
                    buySellReportData.setBuyerSellerName(elegantBuySell.getBuyerSellerName());

                    buySellReportData.setProdId(elegantBuySellDetails.getProductId());
                    buySellReportData.setProdName(elegantBuySellDetails.getProductName());
                    buySellReportData.setPurchQty(elegantBuySellDetails.getPurchQty());
                    buySellReportData.setPurchQtyRtn(elegantBuySellDetails.getPurchRtnQty());
                    buySellReportData.setUnitDiscount(elegantBuySellDetails.getUnitDiscount());
                    buySellReportData.setPurchRate(elegantBuySellDetails.getPurchRate());
                    buySellReportData.setUnitPack(elegantBuySellDetails.getUnitPackaging());
                    buySellReportData.setUnitAmt(elegantBuySellDetails.getUnitAmt());
                    returnList.add(buySellReportData);
                };
            }
        };
        return returnList;
    }

    void showDialogPurchaseList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.buySellListPanel == null) {
            this.buySellListPanel = new BuySellListView();

            GeneralBillHelp suphelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("supplierCodeHelp");
            GeneralBillHelp salesmanHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.setSupHelpPanel(suphelpPanel);
            this.setSalesManHelpPanel(salesmanHelpPanel);

            supHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            supHelpPanel.setTableColWidths();

            customerTableCellRenderer = new CustomerTableCellRenderer();
            salesManHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesManHelpPanel.setTableColWidths();

            HelpTextField supHelpTextField = this.buySellListPanel.supCodeField;
            supHelpTextField.helpButton.setActionCommand("supHelpFieldRep");
            supHelpTextField.addButtonController(this);
            this.setSalesManHelpPanel(salesmanHelpPanel);

            HelpTextField salesManHelpTextField = this.buySellListPanel.salesManCodeField;
            salesManHelpTextField.helpButton.setActionCommand("salesManHelpFieldRep");
            salesManHelpTextField.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, buySellListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = buySellListPanel.frmDtField.getDate();
                Date toDt = buySellListPanel.toDtField.getDate();
                String supCode = buySellListPanel.supCodeField.textField.getText();
                String salesManCode = buySellListPanel.salesManCodeField.textField.getText();
                String billAmt = buySellListPanel.billAmtField.getText();
                int sortField = (buySellListPanel.idSort.isSelected() ? 1 : (buySellListPanel.billNoSort.isSelected() ? 2 : (buySellListPanel.billDtSort.isSelected() ? 3 : (buySellListPanel.supBillSort.isSelected() ? 4 : 5))));
                int sortDir = (buySellListPanel.ascDirection.isSelected() ? 1 : 2);
                int rowHeight = new Integer(buySellListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(buySellListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(buySellListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(buySellListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(buySellListPanel.reportSetup.rightMarginField.getText());
                boolean createBorder = buySellListPanel.reportSetup.createBorder.isSelected();
                boolean custActive = !buySellListPanel.custActive.isSelected();
                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(buySellListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                purchaseView.setCursor(hourglassCursor);
                getAllBuySellForReport(frDt, toDt, custActive, supCode, salesManCode, billAmt, sortField, sortDir);
                if (purchaseModel.getBuySellRepList() == null || purchaseModel.getBuySellRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(buySellListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    buySellListReport = new BuySellListReport(purchaseModel.getBuySellRepList(), frDt, toDt, supCode, salesManCode, billAmt, custActive, CustUtil.PURCHASEBILLTYPE, createBorder, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = buySellListReport.getReport();
                    JasperViewer jasperViewer = new JasperViewer(jp, false);
                    jasperViewer.setVisible(true);
                    jasperViewer.setAlwaysOnTop(false);
                    jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
                    jasperViewer.setTitle(CustUtil.APPNAME);
                    jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
                    jasperViewer.setTitle(CustUtil.APPNAME);

                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                purchaseView.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the purchaseModel
     */
    public BuySellVO getPurchaseModel() {
        return purchaseModel;
    }

    /**
     * @param purchaseModel the purchaseModel to set
     */
    public void setPurchaseModel(BuySellVO purchaseModel) {
        this.purchaseModel = purchaseModel;
    }

    /**
     * @return the purchaseView
     */
    public PurchaseView getPurchaseView() {
        return purchaseView;
    }

    /**
     * @param purchaseView the purchaseView to set
     */
    public void setPurchaseView(PurchaseView purchaseView) {
        this.purchaseView = purchaseView;
    }

    void showPurchaseBillHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            GeneralBillHelp purchaseHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("purchaseBillHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            purchaseHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            purchaseHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, purchaseHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                JTable target = purchaseHelpPanel.tableHelp;
                int row = purchaseHelpPanel.tableHelp.getSelectedRow();
                if (row >= 0) {
                    ElegantBuySell elegantBuySell = purchaseModel.getBuyList().get(target.convertRowIndexToModel(row));
                    BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseManager");
                    ElegantBuySell tElegantBuySell = (ElegantBuySell) buySellManager.getBuySellById(PersistanceManager.getInstance().getElegantUser(), new Long(elegantBuySell.getBillID()).intValue());
                    if (tElegantBuySell != null) {
                        tElegantBuySell.setBuySellDetailsList(tElegantBuySell.getBuySellDetailsList());
                        this.purchaseModel.setElegantBuy(tElegantBuySell);
                        this.purchaseModel.setBuyDetailsList(tElegantBuySell.getBuySellDetailsList());
                        this.purchaseModel.setBuyDetailsListOld(tElegantBuySell.getBuySellDetailsList());
                        this.purchaseView.searchField.setEnabled(true);
                        this.purchaseView.initButtonsFordelete();

                    }
                    this.purchaseView.purOrderNo.requestFocus();
                    CustUtil.setDateLimitForAuthOrBills(this.purchaseView.purchaseDate, this.purchaseView.authPanel.authDate, 30);
                    ElegantUser user = PersistanceManager.getInstance().getElegantUser();
                    if (user.getAccountType() == 2 || (user.getDivision() == 2 && (user.getRole() == "ROLE_ADMIN"))) {
                        purchaseView.authPanel.setVisible(true);
                        CustUtil.disableOrEnableForAuth(purchaseView, false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showSupplierBillHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            GeneralBillHelp supplierHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("supplierCodeHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            supplierHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            supplierHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp("Select Supplier from List", supplierHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = supplierHelpPanel.tableHelp.getSelectedRow();
                JTable target = supplierHelpPanel.tableHelp;
                if (row >= 0) {
                    ElegantSupplier elegantSupplier = ((SupTableModel) supplierHelpPanel.tableHelp.getModel()).getData().get(target.convertRowIndexToModel(row));
                    long buyerSellerCode = elegantSupplier.getSupID();
                    String buyerSellerName = elegantSupplier.getSupName();

                    updateBuySell(this.purchaseModel.getElegantBuy());

                    ElegantBuySell elegantBuySell = this.purchaseModel.getElegantBuy();
                    elegantBuySell.setBuyerSellerCode(new Long(buyerSellerCode).intValue());
                    elegantBuySell.setBuyerSellerName(buyerSellerName);
                    this.purchaseModel.setElegantBuy(elegantBuySell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showSalesManCodeHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            GeneralBillHelp salesManHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            salesManHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesManHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp("Select Sales Man from List", salesManHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                JTable target = salesManHelpPanel.tableHelp;
                int row = salesManHelpPanel.tableHelp.getSelectedRow();
                if (row >= 0) {
                    ElegantSalesMan elegantSalesMan = ((SalesManTableModel) salesManHelpPanel.tableHelp.getModel()).getData().get(target.convertRowIndexToModel(row));
                    long salesManCode = elegantSalesMan.getSalesManID();
                    String salesManName = elegantSalesMan.getSalesManName();
                    ElegantBuySell elegantBuySell = this.purchaseModel.getElegantBuy();
                    elegantBuySell.setSalesManCode(new Long(salesManCode).intValue());
                    elegantBuySell.setSalesManName(salesManName);
                    this.purchaseModel.setElegantBuy(elegantBuySell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (salesManHelpPanel != null) {
//            System.out.println("Found Sales man panel");
        }
    }

    /**
     * @return the purchaseBillPrintView
     */
    public BillPrintView getPurchaseBillPrintView() {
        return purchaseBillPrintView;
    }

    /**
     * @param purchaseBillPrintView the purchaseBillPrintView to set
     */
    public void setPurchaseBillPrintView(BillPrintView purchaseBillPrintView) {
        this.purchaseBillPrintView = purchaseBillPrintView;
    }

    /**
     * @return the purHelpPanel
     */
    public GeneralBillHelp getPurHelpPanel() {
        return purHelpPanel;
    }

    /**
     * @param purHelpPanel the purHelpPanel to set
     */
    public void setPurHelpPanel(GeneralBillHelp purHelpPanel) {
        this.purHelpPanel = purHelpPanel;
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

//    public class ScheduledTaskPurchase extends TimerTask {
//
//        @Override
//        public void run() {
//            initPurchase();
//            System.out.println("Get all purchases " + new Date());
//        }
//
//    }
}
