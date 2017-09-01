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
import com.custmanager.view.PurchaseRtnView;
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
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Inderjit
 */
public class PurchaseRtnButtonController implements ActionListener {

    private BuySellVO purchaseRtnModel;
    private PurchaseRtnView purchaseRtnView;
    private BuySellListReport buySellListReport;
    private BuySellListView purchRtnListPanel;
    private GeneralBillHelp supHelpPanel, salesManHelpPanel, purHelpPanel;
    private BillPrintView purchaseRtnBillPrintView;

    public PurchaseRtnButtonController() {
//           timer.scheduleAtFixedRate(new ScheduledTaskPurchase(), 100,10000); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            CustUtil.disableOrEnableForAuth(purchaseRtnView, true);
            purchaseRtnView.initTextFields();
            purchaseRtnView.initButtonsForNew();
            purchaseRtnModel.setElegantPurRtn(new ElegantBuySell());
            purchaseRtnModel.setPurRtnDetailsList(new ArrayList<>());
            purchaseRtnView.searchField.setEnabled(false);
            purchaseRtnView.authPanel.setVisible(false);
        } else if (e.getActionCommand().equalsIgnoreCase("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(purchaseRtnView, "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("PrintList")) {
            showDialogPurchaseRtnList();
        } else if (e.getActionCommand().equalsIgnoreCase("PrintBill")) {
            showDialogPurchaseRtnBillPrint();
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            if (saveUpdate()) {
                CustUtil.disableOrEnableForAuth(purchaseRtnView, true);
                purchaseRtnView.initTextFields();
                purchaseRtnView.initButtons();
                purchaseRtnModel.setElegantPurRtn(new ElegantBuySell());
                purchaseRtnModel.setPurRtnDetailsList(new ArrayList<>());
                purchaseRtnView.searchField.setEnabled(true);
                purchaseRtnView.authPanel.setVisible(false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Delete")) {
            if (CustUtil.confirmDelete(purchaseRtnView)) {
                if (delete()) {
                    purchaseRtnView.initTextFields();
                    purchaseRtnView.initButtons();
                    purchaseRtnModel.setElegantPurRtn(new ElegantBuySell());
                    purchaseRtnModel.setPurRtnDetailsList(new ArrayList<ElegantBuySellDetails>());
                    purchaseRtnView.searchField.setEnabled(true);
                    purchaseRtnView.authPanel.setVisible(false);
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Discard")) {
            CustUtil.disableOrEnableForAuth(purchaseRtnView, true);
            purchaseRtnView.initTextFields();
            purchaseRtnView.initButtons();
            purchaseRtnModel.setElegantPurRtn(new ElegantBuySell());
            purchaseRtnModel.setPurRtnDetailsList(new ArrayList<ElegantBuySellDetails>());
            purchaseRtnView.searchField.setEnabled(true);
            purchaseRtnView.authPanel.setVisible(false);
        } else if (e.getActionCommand().equalsIgnoreCase("purBillHelp")) {
            showPurchaseRtnBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("supHelp")) {
            showSupplierBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("salesManHelp")) {
            showSalesManCodeHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("addRow")) {
            addRow();
        } else if (e.getActionCommand().equalsIgnoreCase("removeRow")) {
            removeRow();
        } else if (e.getActionCommand().equalsIgnoreCase("purHelpFieldRep1")) {
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
                    this.purchRtnListPanel.supCodeField.textField.setText(Long.toString((Long) this.getSupHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("salesManHelpFieldRep")) {
            if (this.getSalesManHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalesManHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getSalesManHelpPanel().tableHelp.getSelectedRow();
                    this.purchRtnListPanel.salesManCodeField.textField.setText(Long.toString((Long) this.getSalesManHelpPanel().tableHelp.getValueAt(row, 0)));
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("authstatus")) {
            if (purchaseRtnView.authPanel.authStatCombo.getSelectedIndex() == 1) {
//                purchaseView.authPanel.itemStockPost.setSelected(true);
                purchaseRtnView.authPanel.itemStockPost.setEnabled(true);
            } else {
                purchaseRtnView.authPanel.itemStockPost.setSelected(false);
                purchaseRtnView.authPanel.itemStockPost.setEnabled(false);
            }
        }

        purchaseRtnView.setTableColWidths();
    }

    private void addRow() {
        ArrayList<ElegantBuySellDetails> tempList = purchaseRtnModel.getPurRtnDetailsList();
        ElegantBuySellDetails elegantBuySellDetails = new ElegantBuySellDetails();
        long srl = tempList.size() == 0 ? 1 : tempList.size() + 1;
        elegantBuySellDetails.setSrl(srl);
        tempList.add(elegantBuySellDetails);
        long newSrl = 1;
        for (ElegantBuySellDetails temp : tempList) {
            temp.setSrl(newSrl);
            newSrl++;
        }
        updateBuySell(this.purchaseRtnModel.getElegantPurRtn());
        purchaseRtnModel.setPurRtnDetailsList(tempList);
    }

    private void removeRow() {
        ArrayList<ElegantBuySellDetails> tempList = purchaseRtnModel.getPurRtnDetailsList();
        int indexToDelete = purchaseRtnView.tablePurchaseRtnDetails.getSelectedRow();
        if (indexToDelete > 0) {
            tempList.remove(indexToDelete);
            long newSrl = 1;
            for (ElegantBuySellDetails temp : tempList) {
                temp.setSrl(newSrl);
                newSrl++;
            }
            updateBuySell(this.purchaseRtnModel.getElegantPurRtn());
            purchaseRtnModel.setPurRtnDetailsList(tempList);
        }
    }

    private void initPurchaseRtn() {
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
        try {
            ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASERTNTYPE, true);
            this.purchaseRtnModel.setPurRtnList(elegantBuySellList);
            purchaseRtnView.setTableColWidths();
            System.out.println("Init All Fields Done");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void initAllFields(boolean initDetails) {
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
        purchaseRtnView.authPanel.setVisible(false);
        try {
            ArrayList<ElegantBuySell> purchRtnList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASERTNTYPE, true);
            if (purchRtnList != null) {
                Collections.sort(purchRtnList);
            }

            this.purchaseRtnModel.setPurRtnList(purchRtnList);
            if (initDetails) {
                this.purchaseRtnModel.setElegantPurRtn(new ElegantBuySell());
                this.purchaseRtnModel.setPurRtnDetailsList(new ArrayList<>());
                purchaseRtnView.setTableColWidths();
            }
            if (!AppManager.getInstance().getElegantInventory().menuPurRtnRep.isEnabled()) {
                purchaseRtnView.buttonPanel.printButton.setEnabled(false);
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
        ArrayList<ElegantBuySell> buySellListToSave = new ArrayList<ElegantBuySell>();
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
        try {
            ElegantBuySell purchRtn = updateBuySell(purchaseRtnModel.getElegantPurRtn());
            if (!validateData()) {
                return saved;
            }
            buySellListToSave.add(purchRtn);
            saved = buySellManager.saveOrUpdateBuySell(buySellListToSave, false); // TODO post stock
            if (saved) {
                ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get("productController");
                ProductManager productManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
                ArrayList<ElegantProduct> elegantProductList = productManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
                bttnController.getProductModel().setProdList(elegantProductList);

                buySellListToSave = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASERTNTYPE, true);
                if (buySellListToSave != null) {
                    Collections.sort(buySellListToSave);
                }

                this.purchaseRtnModel.setPurRtnList(buySellListToSave);
                this.purchaseRtnModel.setElegantPurRtn(new ElegantBuySell());
                this.purchaseRtnModel.setPurRtnDetailsList(new ArrayList<ElegantBuySellDetails>());
            }
        } catch (Exception e) {
            CustUtil.showErrorDialogue("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    private boolean validateData() {
        boolean validated = true;
        ElegantBuySell elegantBuySell = this.purchaseRtnModel.getElegantPurRtn();
        if ((elegantBuySell.getBillDt() == null || elegantBuySell.getBillNo() == null || elegantBuySell.getBuyerSellBillNo() == null || elegantBuySell.getFinalBillAmt() == null)
                || (elegantBuySell.getBillNo().equals("") || elegantBuySell.getBuyerSellBillNo().equals("") || elegantBuySell.getFinalBillAmt().equals(0.00))) {
            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
            validated = false;
        }
        if (elegantBuySell.getBillDt().before(elegantBuySell.getBuyerSellerBillDt())) {
            CustUtil.showMessageDialogue("Bill RTN date is Invalid or Before Supplier Bill Date");
            validated = false;

        }
        return validated;
    }

    public boolean delete() {
        boolean deleted = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return deleted;
        }
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
        try {
            ElegantBuySell purchRtn = purchaseRtnModel.getElegantPurRtn();
            if (buySellManager.deleteBuySell(purchRtn)) {
                ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get("productController");
                ProductManager productManager = (ProductManager) AppManager.getInstance().getViewManagerList().get("productManager");
                ArrayList<ElegantProduct> elegantProductList = productManager.getAllProducts(PersistanceManager.getInstance().getElegantUser());
                bttnController.getProductModel().setProdList(elegantProductList);

                ArrayList<ElegantBuySell> elegantBuySellListToDelete = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASERTNTYPE, true);
                if (elegantBuySellListToDelete != null) {
                    Collections.sort(elegantBuySellListToDelete);
                }

                this.purchaseRtnModel.setPurRtnList(elegantBuySellListToDelete);
                this.purchaseRtnModel.setPurRtnDetailsList(new ArrayList<ElegantBuySellDetails>());
                deleted = true;
            } else {
                CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Purchase Rtn. Transactions Exist ");
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private ElegantBuySell updateBuySell(ElegantBuySell buySell) {
        buySell.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
        buySell.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
        buySell.setBillID(Long.parseLong((purchaseRtnView.billIRtndField.getText()).equals("") ? "0" : purchaseRtnView.billIRtndField.getText()));
        buySell.setBillType(CustUtil.PURCHASERTNTYPE);
        if (purchaseRtnView.purOrderNo.getText().equals("")) {
            buySell.setBillNo(purchaseRtnView.purOrderNo.getText());
        }
        buySell.setBillDt(purchaseRtnView.purchaseDate.getDate());
        buySell.setBuyerSellerCode(Integer.parseInt(purchaseRtnView.supIdField.getText()));
        buySell.setBuyerSellerName(purchaseRtnView.salesManNameField.getText());
        buySell.setBuyerSellBillNo(purchaseRtnView.supBillNo.getText());
        buySell.setBuyerSellerBillDt(purchaseRtnView.supBillDt.getDate());
        buySell.setSalesManCode(Integer.parseInt(purchaseRtnView.salesManIdField.getText()));
        buySell.setFreighTranspDedAmt(Double.parseDouble(purchaseRtnView.freightField.getText()));
        buySell.setTaxDedAmt(Double.parseDouble(purchaseRtnView.taxField.getText()));
        buySell.setFinalBillAmt(calculateBillAmt(buySell));
        buySell.setRemarks(purchaseRtnView.remarks.getText());
        if (buySell.getBillID() == 0) {
            buySell.setCreateDate(new Date());
            buySell.setBillStatus(0);
        }
        ElegantUser user = PersistanceManager.getInstance().getElegantUser();
        if (user.getAccountType() == 2 || (user.getDivision() == 2 && (user.getRole() == 4 || user.getRole() == 6))) {
            if (purchaseRtnView.authPanel.authStatCombo.getSelectedIndex() > 0) {
                doUpdateBillStatus(buySell);
            }
        }

        return buySell;
    }

    private void doUpdateBillStatus(ElegantBuySell buySell) {
        if (buySell.getBillID() == 0) {
            buySell.setAuthRequired(0);
        }
        if (purchaseRtnView.authPanel.authDate.getDate()!= null) {
            buySell.setAuthDate(purchaseRtnView.authPanel.authDate.getDate());
            buySell.setAuthRemarks(purchaseRtnView.authPanel.authRemark.getText());
            buySell.setAuthStatus(purchaseRtnView.authPanel.authStatCombo.getSelectedIndex());
            buySell.setStockPosted(purchaseRtnView.authPanel.itemStockPost.isSelected() ? 1 : 0);
            if ((buySell.getAuthStatus() != null && buySell.getAuthStatus() > 0) && (buySell.getStockPosted() != null && buySell.getStockPosted() == 1)) {
                buySell.setBillStatus(1); // lock bill from further updates as already authroised        
            }
        }
    }

    private double calculateBillAmt(ElegantBuySell elegantBuySell) {
        double total = 0.0;

        ArrayList<ElegantBuySellDetails> elegantBuySellDetailsList = elegantBuySell.getBuySellDetailsList();
        for (ElegantBuySellDetails elegantBuySellDetails : elegantBuySellDetailsList) {
            double tempFinalAmt = (elegantBuySellDetails.getPurchRtnQty() * elegantBuySellDetails.getPurchRate()) - elegantBuySellDetails.getUnitDiscount();
            elegantBuySellDetails.setUnitAmt(tempFinalAmt);
            total += elegantBuySellDetails.getUnitAmt();
        }
        return total - elegantBuySell.getFreighTranspDedAmt() - elegantBuySell.getTaxDedAmt();
    }

    private ArrayList<ElegantBuySell> getAllPurchRtnForReport(Date frDt, Date toDt, boolean isActive, String supCode, String salesMan, String billAmt, int sortField, int sortDir) {
        ArrayList<ElegantBuySell> purchRtnRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<>();
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
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

            purchRtnRepList = purManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.PURCHASERTNTYPE, true);
            purchaseRtnModel.setPurRtnRepList(purchRtnRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllBuySellForReport " + e.getMessage());
        }
        return purchRtnRepList;
    }

    private ArrayList<ElegantBuySell> getAllBuySellBillsForReport(String fromBill, String toBill) {
        ArrayList<ElegantBuySell> elegantBuySellRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<>();
        BuySellManager purRtnManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
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
            filterCriteria.setFilterFieldValue(Integer.toString(CustUtil.PURCHASERTNTYPE));
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
// For Report
            elegantBuySellRepList = purRtnManager.getAllBuySellForRep(PersistanceManager.getInstance().getElegantUser());
            purchaseRtnModel.setPurRtnRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllBuySellBillsForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    void showDialogPurchaseRtnBillPrint() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.getPurchaseBillPrintView() == null) {
            this.setPurchaseBillPrintView(new BillPrintView());
            this.getPurchaseBillPrintView().headerLabel.setText("Enter Order Return Nos to View Below");
            GeneralBillHelp purchaseHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("purchaseRtnBillHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            purchaseHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            purchaseHelpPanel.setTableColWidths();

            this.setPurHelpPanel(purchaseHelpPanel);
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
                purchaseRtnView.setCursor(hourglassCursor);
                getAllBuySellBillsForReport(fromBillNo, toBillNo);
                if (purchaseRtnModel.getPurRtnRepList() == null || purchaseRtnModel.getPurRtnRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(this.getPurchaseBillPrintView().getRootPane().getContentPane(), "Bills Not Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<BuySellReportData> reportData = createValueObjectForBills(purchaseRtnModel.getPurRtnRepList());
                    BuySellBillReport buySellBillReport = new BuySellBillReport(reportData, createBorder, rowHeight, topMargin, leftMargin, bottomMargin, rightMargin);
                    JasperPrint jp = buySellBillReport.getReport();
                    JasperViewer jasperViewer = new JasperViewer(jp, false);
                    jasperViewer.setVisible(true);
                    jasperViewer.setAlwaysOnTop(false);
                    jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
                    jasperViewer.setTitle(CustUtil.APPNAME);

                }

                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                purchaseRtnView.setCursor(hourglassCursor);

            } catch (NumberFormatException | HeadlessException | ColumnBuilderException | JRException | ClassNotFoundException | SecurityException e) {
//                e.printStackTrace();
                System.out.println("showDialogPurchaseBillPrint" + e.getMessage());
            }
        }
    }

// java 8 syntax for loops
    ArrayList<BuySellReportData> createValueObjectForBills(ArrayList<ElegantBuySell> elegantBuySellList) {
        ArrayList<BuySellReportData> returnList = new ArrayList<BuySellReportData>();
        for (ElegantBuySell elegantBuySell : elegantBuySellList) {
            ArrayList<ElegantBuySellDetails> elegantBuySellDetailsList = elegantBuySell.getBuySellDetailsList();
            BuySellReportData buySellReportData = new BuySellReportData();
            if (!elegantBuySellDetailsList.isEmpty()) {
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

    void showDialogPurchaseRtnList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.purchRtnListPanel == null) {
            this.purchRtnListPanel = new BuySellListView();
            this.purchRtnListPanel.labelHeader.setText("Order RTN Selection Criteria");
            GeneralBillHelp supHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("supplierCodeHelp");
            GeneralBillHelp salesManHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            supHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            supHelpPanel.setTableColWidths();
            customerTableCellRenderer = new CustomerTableCellRenderer();
            salesManHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesManHelpPanel.setTableColWidths();

            this.setSupHelpPanel(supHelpPanel);
            HelpTextField supHelpTextField = this.purchRtnListPanel.supCodeField;
            supHelpTextField.helpButton.setActionCommand("supHelpFieldRep");
            supHelpTextField.addButtonController(this);

            this.setSalesManHelpPanel(salesManHelpPanel);
            HelpTextField salesManHelpTextField = this.purchRtnListPanel.salesManCodeField;
            salesManHelpTextField.helpButton.setActionCommand("salesManHelpFieldRep");
            salesManHelpTextField.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, purchRtnListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = purchRtnListPanel.frmDtField.getDate();
                Date toDt = purchRtnListPanel.toDtField.getDate();
                String supCode = purchRtnListPanel.supCodeField.textField.getText();
                String salesManCode = purchRtnListPanel.salesManCodeField.textField.getText();
                String billAmt = purchRtnListPanel.billAmtField.getText();
                int sortField = (purchRtnListPanel.idSort.isSelected() ? 1 : (purchRtnListPanel.billNoSort.isSelected() ? 2 : (purchRtnListPanel.billDtSort.isSelected() ? 3 : (purchRtnListPanel.supBillSort.isSelected() ? 4 : 5))));
                int sortDir = (purchRtnListPanel.ascDirection.isSelected() ? 1 : 2);
                int rowHeight = new Integer(purchRtnListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(purchRtnListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(purchRtnListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(purchRtnListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(purchRtnListPanel.reportSetup.rightMarginField.getText());
                boolean createBorder = purchRtnListPanel.reportSetup.createBorder.isSelected();
                boolean custActive = !purchRtnListPanel.custActive.isSelected();
                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(purchRtnListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                purchaseRtnView.setCursor(hourglassCursor);
                getAllPurchRtnForReport(frDt, toDt, custActive, supCode, salesManCode, billAmt, sortField, sortDir);
                if (purchaseRtnModel.getPurRtnRepList() == null || purchaseRtnModel.getPurRtnRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(purchRtnListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    buySellListReport = new BuySellListReport(purchaseRtnModel.getPurRtnRepList(), frDt, toDt, supCode, salesManCode, billAmt, custActive, CustUtil.PURCHASERTNTYPE, createBorder, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
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
                purchaseRtnView.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the purchaseModel
     */
    public BuySellVO getPurchaseModel() {
        return purchaseRtnModel;
    }

    /**
     * @param purchaseModel the purchaseModel to set
     */
    public void setPurchaseModel(BuySellVO purchaseModel) {
        this.purchaseRtnModel = purchaseModel;
    }

    /**
     * @return the purchaseView
     */
    public PurchaseRtnView getPurchaseView() {
        return purchaseRtnView;
    }

    /**
     * @param purchaseView the purchaseView to set
     */
    public void setPurchaseRtnView(PurchaseRtnView purchaseRtnView) {
        this.purchaseRtnView = purchaseRtnView;
    }

    void showPurchaseRtnBillHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            GeneralBillHelp purchaseRtnHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("purchaseRtnBillHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            purchaseRtnHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            purchaseRtnHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, purchaseRtnHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                JTable target = purchaseRtnHelpPanel.tableHelp;
                int row = purchaseRtnHelpPanel.tableHelp.getSelectedRow();
                if (row >= 0) {
                    ElegantBuySell elegantBuySell = purchaseRtnModel.getPurRtnList().get(target.convertRowIndexToModel(row));
                    BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("purchaseRtnManager");
                    ElegantBuySell purchaseRtn = (ElegantBuySell) buySellManager.getBuySellById(PersistanceManager.getInstance().getElegantUser(), new Long(elegantBuySell.getBillID()).intValue());
                    if (purchaseRtn != null) {
                        purchaseRtn.setBuySellDetailsList(purchaseRtn.getBuySellDetailsList());
                        this.purchaseRtnModel.setElegantPurRtn(purchaseRtn);
                        this.purchaseRtnModel.setPurRtnDetailsList(purchaseRtn.getBuySellDetailsList());
                        this.purchaseRtnView.searchField.setEnabled(true);
                        this.purchaseRtnView.initButtonsFordelete();
                    }
                    this.purchaseRtnView.purOrderNo.requestFocus();
                    CustUtil.setDateLimitForAuthOrBills(this.purchaseRtnView.purchaseDate, this.purchaseRtnView.authPanel.authDate, 30);
                    ElegantUser user = PersistanceManager.getInstance().getElegantUser();
                    if (user.getAccountType() == 2 || (user.getDivision() == 2 && (user.getRole() == 4 || user.getRole() == 6))) {
                        purchaseRtnView.authPanel.setVisible(true);
                        CustUtil.disableOrEnableForAuth(purchaseRtnView, false);
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

                    updateBuySell(this.purchaseRtnModel.getElegantPurRtn());

                    ElegantBuySell elegantBuySell = this.purchaseRtnModel.getElegantPurRtn();
                    elegantBuySell.setBuyerSellerCode(new Long(buyerSellerCode).intValue());
                    elegantBuySell.setBuyerSellerName(buyerSellerName);
                    this.purchaseRtnModel.setElegantBuy(elegantBuySell);
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

                    updateBuySell(this.purchaseRtnModel.getElegantPurRtn());

                    ElegantBuySell elegantBuySell = this.purchaseRtnModel.getElegantPurRtn();
                    elegantBuySell.setSalesManCode(new Long(salesManCode).intValue());
                    elegantBuySell.setSalesManName(salesManName);
                    this.purchaseRtnModel.setElegantBuy(elegantBuySell);
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
        return purchaseRtnBillPrintView;
    }

    /**
     * @param purchaseBillPrintView the purchaseBillPrintView to set
     */
    public void setPurchaseBillPrintView(BillPrintView purchaseBillPrintView) {
        this.purchaseRtnBillPrintView = purchaseBillPrintView;
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

    public class ScheduledTaskPurchase extends TimerTask {

        @Override
        public void run() {
            initPurchaseRtn();
            System.out.println("Get all purchases Rtn" + new Date());
        }

    }
}
