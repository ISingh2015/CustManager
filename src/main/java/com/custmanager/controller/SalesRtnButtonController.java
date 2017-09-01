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
import com.cust.domain.vo.ElegantCustomer;
import com.cust.domain.vo.ElegantSalesMan;
import com.cust.domain.vo.ElegantUser;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.BuySellManager;
import com.custmanager.AppManager;
import com.custmanager.util.CustUtil;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.model.BuySellVO;
import com.custmanager.model.CustTableModel;
import com.custmanager.model.SalesManTableModel;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.BuySellBillReport;
import com.custmanager.reports.BuySellListReport;
import com.custmanager.view.BillPrintView;
import com.custmanager.view.BuySellListView;
import com.custmanager.view.OKCancelDialogHelp;
import com.custmanager.view.GeneralBillHelp;
import com.custmanager.view.SalesRtnView;
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

/**
 *
 * @author Inderjit
 */
//@SuppressWarnings("unused")
public class SalesRtnButtonController implements ActionListener {

    private BuySellVO saleRtnModel;
    private SalesRtnView saleRtnView;
    private BuySellListReport buySellListReport;
    private BuySellListView buySellListPanel;
    private GeneralBillHelp custHelpPanel, salesManHelpPanel, salesRtnHelpPanel;
    private BillPrintView salesRtnBillPrintView;

    public SalesRtnButtonController() {
//           timer.ScheduledTaskSalesRtn(new ScheduledTaskPurchase(), 100,10000); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            CustUtil.disableOrEnableForAuth(saleRtnView, true);
            getSaleRtnView().initTextFields();
            getSaleRtnView().initButtonsForNew();
            saleRtnModel.setElegantSaleRtn(new ElegantBuySell());
            saleRtnModel.setSaleRtnDetailsList(new ArrayList<>());
            getSaleRtnView().searchField.setEnabled(false);
        } else if (e.getActionCommand().equalsIgnoreCase("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(getSaleRtnView(), "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("PrintList")) {
            showDialogSalesRtnList();
        } else if (e.getActionCommand().equalsIgnoreCase("PrintBill")) {
            showDialogSalesRtnBillPrint();
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            if (saveUpdate()) {
                CustUtil.disableOrEnableForAuth(saleRtnView, true);
                getSaleRtnView().initTextFields();
                getSaleRtnView().initButtons();
                saleRtnModel.setElegantSaleRtn(new ElegantBuySell());
                saleRtnModel.setSaleRtnDetailsList(new ArrayList<>());
                getSaleRtnView().searchField.setEnabled(true);
                getSaleRtnView().authPanel.setVisible(false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Delete")) {
            if (CustUtil.confirmDelete(getSaleRtnView())) {
                if (delete()) {
                    getSaleRtnView().initTextFields();
                    getSaleRtnView().initButtons();
                    saleRtnModel.setElegantSaleRtn(new ElegantBuySell());
                    saleRtnModel.setSaleRtnDetailsList(new ArrayList<>());
                    getSaleRtnView().searchField.setEnabled(true);
                    getSaleRtnView().authPanel.setVisible(false);

                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Discard")) {
            CustUtil.disableOrEnableForAuth(saleRtnView, true);
            getSaleRtnView().initTextFields();
            getSaleRtnView().initButtons();
            saleRtnModel.setElegantSaleRtn(new ElegantBuySell());
            saleRtnModel.setSaleRtnDetailsList(new ArrayList<>());
            getSaleRtnView().searchField.setEnabled(true);
            if (!AppManager.getInstance().getElegantInventory().menuSalesRtnRep.isEnabled()) {
                getSaleRtnView().buttonPanel.printButton.setEnabled(false);
            }
            getSaleRtnView().authPanel.setVisible(false);
        } else if (e.getActionCommand().equalsIgnoreCase("salRtnBillHelp")) {
            showSalesRtnBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("custHelp")) {
            showCustomerBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("salesManHelp")) {
            showSalesManCodeHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("addRow")) {
            addRow();
        } else if (e.getActionCommand().equalsIgnoreCase("removeRow")) {
            removeRow();
        } else if (e.getActionCommand().equalsIgnoreCase("salRtnHelpFieldRep1")) {
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalRtnHelpPanel());
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = this.getSalRtnHelpPanel().tableHelp.getSelectedRow();
                this.getSaleRtnBillPrintView().helpTextField1.textField.setText(Long.toString((Long) this.getSalRtnHelpPanel().tableHelp.getValueAt(row, 0)));
            }
        } else if (e.getActionCommand().equalsIgnoreCase("salRtnHelpFieldRep2")) {
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalRtnHelpPanel());
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = this.getSalRtnHelpPanel().tableHelp.getSelectedRow();
                this.getSaleRtnBillPrintView().helpTextField2.textField.setText(Long.toString((Long) this.getSalRtnHelpPanel().tableHelp.getValueAt(row, 0)));
            }

        } else if (e.getActionCommand().equalsIgnoreCase("custHelpFieldRep")) {
            if (this.getCustHelpPanel() != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getCustHelpPanel());
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.getCustHelpPanel().tableHelp.getSelectedRow();
                    this.buySellListPanel.supCodeField.textField.setText(Long.toString((Long) this.getCustHelpPanel().tableHelp.getValueAt(row, 0)));
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
            if (saleRtnView.authPanel.authStatCombo.getSelectedIndex() == 1) {
//                purchaseView.authPanel.itemStockPost.setSelected(true);
                saleRtnView.authPanel.itemStockPost.setEnabled(true);
            } else {
                saleRtnView.authPanel.itemStockPost.setSelected(false);
                saleRtnView.authPanel.itemStockPost.setEnabled(false);
            }
        }

//        if (saleView.tableSalesDetails.getRowCount()>0 ) 
        getSaleRtnView().setTableColWidths();
    }

    private void addRow() {
        ArrayList<ElegantBuySellDetails> tempList = saleRtnModel.getSaleRtnDetailsList();
        ElegantBuySellDetails elegantBuySellDetails = new ElegantBuySellDetails();
        long srl = tempList.size() == 0 ? 1 : tempList.size() + 1;
        elegantBuySellDetails.setSrl(srl);
        tempList.add(elegantBuySellDetails);
        long newSrl = 1;
        for (ElegantBuySellDetails temp : tempList) {
            temp.setSrl(newSrl);
            newSrl++;
        }
        updateSaleRtn(this.saleRtnModel.getElegantSaleRtn());
        saleRtnModel.setSaleRtnDetailsList(tempList);
    }

    private void removeRow() {
        ArrayList<ElegantBuySellDetails> tempList = saleRtnModel.getSaleRtnDetailsList();
        int indexToDelete = getSaleRtnView().tableSalesRtnDetails.getSelectedRow();
        if (indexToDelete > 0) {
            tempList.remove(indexToDelete);
            long newSrl = 1;
            for (ElegantBuySellDetails temp : tempList) {
                temp.setSrl(newSrl);
                newSrl++;
            }
            updateSaleRtn(this.saleRtnModel.getElegantSaleRtn());
            saleRtnModel.setSaleRtnDetailsList(tempList);
        }
    }

    private void initSaleRtn() {
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
        try {
            ArrayList<ElegantBuySell> elegantSellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESRTNBILLTYPE, true);
            this.saleRtnModel.setSaleRtnList(elegantSellList);
            getSaleRtnView().setTableColWidths();
        } catch (Exception e) {
            System.out.println("Init All Fields " + e.getMessage());
        }

    }

    public void initAllFields(boolean initDetails) {
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
        getSaleRtnView().authPanel.setVisible(false);
        try {
            ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESRTNBILLTYPE, true);
            if (elegantBuySellList != null) {
                Collections.sort(elegantBuySellList);
            }
            this.saleRtnModel.setSaleRtnList(elegantBuySellList);
            if (initDetails) {
                this.saleRtnModel.setElegantSaleRtn(new ElegantBuySell());
                this.saleRtnModel.setSaleRtnDetailsList(new ArrayList<>());
                getSaleRtnView().setTableColWidths();
            }

            if (!AppManager.getInstance().getElegantInventory().menuSalesRtnRep.isEnabled()) {
                getSaleRtnView().buttonPanel.printButton.setEnabled(false);
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
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
        try {
            if (!validateData()) {
                return saved;
            }
            ElegantBuySell buySell = updateSaleRtn(saleRtnModel.getElegantSaleRtn());
            buySellListToSave.add(buySell);
            saved = buySellManager.saveOrUpdateBuySell(buySellListToSave, false); // TODO post stock
            if (saved) {
                buySellListToSave = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESRTNBILLTYPE, true);
                this.saleRtnModel.setSaleRtnList(buySellListToSave);
                this.saleRtnModel.setElegantSaleRtn(new ElegantBuySell());
                this.saleRtnModel.setSaleRtnDetailsList(new ArrayList<ElegantBuySellDetails>());
                saved = true;
            }
        } catch (Exception e) {
            CustUtil.showErrorDialogue("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    private boolean validateData() {
        boolean validated = true;

        if (getSaleRtnView().invDate == null || getSaleRtnView().invDate.getDate().equals(null)) {
            validated = false;
        }
        if (getSaleRtnView().saleRtnNo.getText().equals(null) || getSaleRtnView().custIdField.getText().equals(null) || getSaleRtnView().custNameField.getText().equals(null)
                || getSaleRtnView().saleRtnNo.getText().equals("") || getSaleRtnView().custIdField.getText().equals("") || getSaleRtnView().custNameField.getText().equals("")) {
            validated = false;
        }
        if (getSaleRtnView().salesManIdField.getText().equals(null) || getSaleRtnView().salesManNameField.getText().equals(null) || getSaleRtnView().totBillAmt.getText().equals(null) ||
                getSaleRtnView().salesManIdField.getText().equals("") || getSaleRtnView().salesManNameField.getText().equals("") || getSaleRtnView().totBillAmt.getText().equals("")) {
            validated = false;
        }

        if (!validated) {
            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
        }
        return validated;
    }

    public boolean delete() {
        boolean deleted = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return deleted;
        }
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
        try {
            ElegantBuySell buySell = this.saleRtnModel.getElegantSaleRtn();
            if (buySellManager.deleteBuySell(buySell)) {
                ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESRTNBILLTYPE, true);
                this.saleRtnModel.setSaleRtnList(elegantBuySellList);
                this.saleRtnModel.setSaleRtnDetailsList(new ArrayList<>());
                deleted = true;
            } else {
                CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Sale Rtn. Transactions Exist ");
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private ElegantBuySell updateSaleRtn(ElegantBuySell saleRtn) {
        try {
            saleRtn.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
            saleRtn.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
            saleRtn.setBillID(Long.parseLong((getSaleRtnView().billIdField.getText()).equals("") ? "0" : getSaleRtnView().billIdField.getText()));
            saleRtn.setBillType(CustUtil.SALESRTNBILLTYPE);
            saleRtn.setBillNo(getSaleRtnView().saleRtnNo.getText());
            saleRtn.setBillDt(getSaleRtnView().invDate.getDate());
            saleRtn.setBuyerSellerCode(Integer.parseInt(getSaleRtnView().custIdField.getText()));
            saleRtn.setBuyerSellerName(getSaleRtnView().salesManNameField.getText());
            saleRtn.setBuyerSellBillNo("n/a");
            saleRtn.setBuyerSellerBillDt(getSaleRtnView().invDate.getDate());
            saleRtn.setSalesManCode(Integer.parseInt(getSaleRtnView().salesManIdField.getText()));
            saleRtn.setFreighTranspDedAmt(Double.parseDouble(getSaleRtnView().freightField.getText()));
            saleRtn.setTaxDedAmt(Double.parseDouble(getSaleRtnView().taxField.getText()));
            saleRtn.setFinalBillAmt(calculateRtnBillAmt(saleRtn));
            saleRtn.setRemarks(getSaleRtnView().remarks.getText());
            if (saleRtn.getBillID() == 0) {
                saleRtn.setCreateDate(new Date());
                saleRtn.setBillStatus(0);
            }
            ElegantUser user = PersistanceManager.getInstance().getElegantUser();
            if (user.getAccountType() == 2 || (user.getDivision() == 3 && (user.getRole() == 5 || user.getRole() == 6))) {
                if (saleRtnView.authPanel.authStatCombo.getSelectedIndex() > 0) {
                    doUpdateBillStatus(saleRtn);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleRtn;
    }

    private void doUpdateBillStatus(ElegantBuySell buySell) {
        if (buySell.getBillID() == 0) {
            buySell.setAuthRequired(0);
        }
        if (saleRtnView.authPanel.authDate.getDate() != null) {
            buySell.setAuthDate(saleRtnView.authPanel.authDate.getDate());
            buySell.setAuthRemarks(saleRtnView.authPanel.authRemark.getText());
            buySell.setAuthStatus(saleRtnView.authPanel.authStatCombo.getSelectedIndex());
            buySell.setStockPosted(saleRtnView.authPanel.itemStockPost.isSelected() ? 1 : 0);
            if ((buySell.getAuthStatus() != null && buySell.getAuthStatus() > 0) && (buySell.getStockPosted() != null && buySell.getStockPosted() == 1)) {
                buySell.setBillStatus(1); // lock bill from further updates as already authroised        
            }
        }
    }

    private double calculateRtnBillAmt(ElegantBuySell elegantBuySell) {
        double total = 0.0;

        ArrayList<ElegantBuySellDetails> elegantBuySellDetailsList = elegantBuySell.getBuySellDetailsList();
        for (ElegantBuySellDetails elegantBuySellDetails : elegantBuySellDetailsList) {
            double tempFinalAmt = (elegantBuySellDetails.getBilledRtnQty() * elegantBuySellDetails.getBilledRate()) - elegantBuySellDetails.getUnitDiscount();
            elegantBuySellDetails.setUnitAmt(tempFinalAmt);
            total += elegantBuySellDetails.getUnitAmt();
        }
        return total - elegantBuySell.getFreighTranspDedAmt() - elegantBuySell.getTaxDedAmt();
    }

    private ArrayList<ElegantBuySell> getAllSaleRtnForReport(Date frDt, Date toDt, boolean isActive, String supCode, String salesMan, String billAmt, int sortField, int sortDir) {
        ArrayList<ElegantBuySell> saleRtnRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        BuySellManager saleRtnManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
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
            saleRtnRepList = saleRtnManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESRTNBILLTYPE, true);
            saleRtnModel.setSaleRtnRepList(saleRtnRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllSaleRtnForReport " + e.getMessage());
        }
        return saleRtnRepList;
    }

    private ArrayList<ElegantBuySell> getAllSaleRtnBillsForReport(String fromBill, String toBill) {
        ArrayList<ElegantBuySell> saleRtnRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<>();
        BuySellManager saleRtnManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
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
            filterCriteria.setFilterFieldValue(Integer.toString(CustUtil.SALESRTNBILLTYPE));
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
// for report
            saleRtnRepList = saleRtnManager.getAllBuySellForRep(PersistanceManager.getInstance().getElegantUser());
            saleRtnModel.setSaleRtnRepList(saleRtnRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllSaleRtnBillsForReport " + e.getMessage());
        }
        return saleRtnRepList;
    }

    void showDialogSalesRtnBillPrint() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.getSaleRtnBillPrintView() == null) {
            this.setSalesRtnBillPrintView(new BillPrintView());
            this.getSaleRtnBillPrintView().headerLabel.setText("Enter Invoice Return Nos to View Below");
            GeneralBillHelp salesRtnHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("saleBillRtnHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            salesRtnHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesRtnHelpPanel.setTableColWidths();

            this.setSaleRtnHelpPanel(salesRtnHelpPanel);
            HelpTextField saleRtnHelpTextField1 = this.getSaleRtnBillPrintView().helpTextField1;
            saleRtnHelpTextField1.helpButton.setActionCommand("salRtnHelpFieldRep1");
            saleRtnHelpTextField1.addButtonController(this);

            saleRtnHelpTextField1 = this.getSaleRtnBillPrintView().helpTextField2;
            saleRtnHelpTextField1.helpButton.setActionCommand("salRtnHelpFieldRep2");
            saleRtnHelpTextField1.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, getSaleRtnBillPrintView());
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                String fromBillNo = this.getSaleRtnBillPrintView().helpTextField1.textField.getText();
                String toBillNo = this.getSaleRtnBillPrintView().helpTextField2.textField.getText();
                if (fromBillNo.isEmpty() || toBillNo.isEmpty() || (Integer.parseInt(toBillNo) == 0 || Integer.parseInt(toBillNo) == 0)) {
                    JOptionPane.showMessageDialog(this.getSaleRtnBillPrintView().getRootPane().getContentPane(), "Please Enter Bill No in From and To Fields", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (Long.parseLong(fromBillNo) > Long.parseLong(toBillNo)) {
                    JOptionPane.showMessageDialog(this.getSaleRtnBillPrintView().getRootPane().getContentPane(), "Please enter Valid Bill No in From and To Fields", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int no = Integer.parseInt(fromBillNo), no1 = Integer.parseInt(toBillNo);
                if (no1 - no >= 1000) {
                    JOptionPane.showMessageDialog(this.getSaleRtnBillPrintView().getRootPane().getContentPane(), "Please Select Less than 1000 Bills at a time for Print", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int rowHeight = new Integer(getSaleRtnBillPrintView().reportSetup.rowHeightField.getText());
                int topMargin = new Integer(getSaleRtnBillPrintView().reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(getSaleRtnBillPrintView().reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(getSaleRtnBillPrintView().reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(getSaleRtnBillPrintView().reportSetup.rightMarginField.getText());
                boolean createBorder = getSaleRtnBillPrintView().reportSetup.createBorder.isSelected();

                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                getSaleRtnView().setCursor(hourglassCursor);
                getAllSaleRtnBillsForReport(fromBillNo, toBillNo);
                if (saleRtnModel.getSaleRtnRepList() == null || saleRtnModel.getSaleRtnRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(this.getSaleRtnBillPrintView().getRootPane().getContentPane(), "Bills Not Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<BuySellReportData> reportData = createValueObjectForBills(saleRtnModel.getSaleRtnRepList());
                    BuySellBillReport buySellBillReport = new BuySellBillReport(reportData, createBorder, rowHeight, topMargin, leftMargin, bottomMargin, rightMargin);
                    JasperPrint jp = buySellBillReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }

                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                getSaleRtnView().setCursor(hourglassCursor);

            } catch (NumberFormatException | HeadlessException | ColumnBuilderException | JRException | ClassNotFoundException | SecurityException e) {
//                e.printStackTrace();
                System.out.println("showDialogSaleRtnBillPrint" + e.getMessage());
            }
        }
    }

// java 8 syntax for loops
    ArrayList<BuySellReportData> createValueObjectForBills(ArrayList<ElegantBuySell> salesRtnList) {
        ArrayList<BuySellReportData> returnList = new ArrayList<BuySellReportData>();
        for (ElegantBuySell elegantBuySell : salesRtnList) {
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

    void showDialogSalesRtnList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.buySellListPanel == null) {
            this.buySellListPanel = new BuySellListView();
            this.buySellListPanel.labelHeader.setText("Invoice Rtn Selection Criteria");
            this.buySellListPanel.labelSupCust.setText("Cust Code");
            GeneralBillHelp custHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("custCodeHelp");
            GeneralBillHelp salesManHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            custHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            custHelpPanel.setTableColWidths();
            customerTableCellRenderer = new CustomerTableCellRenderer();
            salesManHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesManHelpPanel.setTableColWidths();

            this.setSupHelpPanel(custHelpPanel);
            HelpTextField supHelpTextField = this.buySellListPanel.supCodeField;
            supHelpTextField.helpButton.setActionCommand("custHelpFieldRep");
            supHelpTextField.addButtonController(this);

            this.setSalesManHelpPanel(salesManHelpPanel);
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
                String custCode = buySellListPanel.supCodeField.textField.getText();
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
                getSaleRtnView().setCursor(hourglassCursor);
                getAllSaleRtnForReport(frDt, toDt, custActive, custCode, salesManCode, billAmt, sortField, sortDir);
                if (saleRtnModel.getSaleRtnRepList() == null || saleRtnModel.getSaleRtnRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(buySellListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    buySellListReport = new BuySellListReport(saleRtnModel.getSaleRtnRepList(), frDt, toDt, custCode, salesManCode, billAmt, custActive, CustUtil.SALESRTNBILLTYPE, createBorder, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = buySellListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                getSaleRtnView().setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the saleModel
     */
    public BuySellVO getSaleModel() {
        return saleRtnModel;
    }

    /**
     * @param saleModel the purchaseModel to set
     */
    public void setSaleModel(BuySellVO saleModel) {
        this.saleRtnModel = saleModel;
    }

    void showSalesRtnBillHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            GeneralBillHelp salesRtnHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("saleBillRtnHelp");
            if (salesRtnHelpPanel == null) {
                CustUtil.showErrorDialogue("Sales Rtn Help Panel Not Found");
                return;
            }
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            salesRtnHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesRtnHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, salesRtnHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                JTable target = salesRtnHelpPanel.tableHelp;
                int row = salesRtnHelpPanel.tableHelp.getSelectedRow();
                if (row >= 0) {
                    ElegantBuySell elegantBuySell = saleRtnModel.getSaleRtnList().get(target.convertRowIndexToModel(row));
                    BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleRtnManager");
                    ElegantBuySell tElegantBuySell = (ElegantBuySell) buySellManager.getBuySellById(PersistanceManager.getInstance().getElegantUser(), new Long(elegantBuySell.getBillID()).intValue());
                    if (tElegantBuySell != null) {
                        tElegantBuySell.setBuySellDetailsList(tElegantBuySell.getBuySellDetailsList());
                        saleRtnModel.setElegantSaleRtn(tElegantBuySell);
                        saleRtnModel.setSaleRtnDetailsList(tElegantBuySell.getBuySellDetailsList());
                        getSaleRtnView().searchField.setEnabled(true);
                        getSaleRtnView().initButtonsFordelete();
                    }
                    getSaleRtnView().saleRtnNo.requestFocus();
                    ElegantUser user = PersistanceManager.getInstance().getElegantUser();
                    CustUtil.setDateLimitForAuthOrBills(getSaleRtnView().invDate, getSaleRtnView().authPanel.authDate, 30);
                    if (user.getAccountType() == 2 || (user.getDivision() == 3 && (user.getRole() == 5 || user.getRole() == 6))) {
                        saleRtnView.authPanel.setVisible(true);
                        CustUtil.disableOrEnableForAuth(saleRtnView, false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showCustomerBillHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            GeneralBillHelp custHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("custCodeHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            custHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            custHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp("Select Customer from List", custHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = custHelpPanel.tableHelp.getSelectedRow();
                JTable target = custHelpPanel.tableHelp;
                if (row >= 0) {
                    ElegantCustomer elegantCustomer = ((CustTableModel) custHelpPanel.tableHelp.getModel()).getData().get(target.convertRowIndexToModel(row));
                    long buyerSellerCode = elegantCustomer.getCustID();
                    String buyerSellerName = elegantCustomer.getCustName();

                    updateSaleRtn(saleRtnModel.getElegantSaleRtn());

                    ElegantBuySell elegantBuySell = saleRtnModel.getElegantSaleRtn();
                    elegantBuySell.setBuyerSellerCode(new Long(buyerSellerCode).intValue());
                    elegantBuySell.setBuyerSellerName(buyerSellerName);
                    saleRtnModel.setElegantSaleRtn(elegantBuySell);
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

                    updateSaleRtn(saleRtnModel.getElegantSaleRtn());

                    ElegantBuySell elegantBuySell = this.saleRtnModel.getElegantSaleRtn();
                    elegantBuySell.setSalesManCode(new Long(salesManCode).intValue());
                    elegantBuySell.setSalesManName(salesManName);
                    this.saleRtnModel.setElegantSaleRtn(elegantBuySell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the supHelpPanel
     */
    public GeneralBillHelp getCustHelpPanel() {
        return custHelpPanel;
    }

    /**
     * @param supHelpPanel the supHelpPanel to set
     */
    public void setSupHelpPanel(GeneralBillHelp supHelpPanel) {
        this.custHelpPanel = supHelpPanel;
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
    public BillPrintView getSaleRtnBillPrintView() {
        return salesRtnBillPrintView;
    }

    /**
     * @param purchaseBillPrintView the purchaseBillPrintView to set
     */
    public void setSalesRtnBillPrintView(BillPrintView salesRtnBillPrintView) {
        this.salesRtnBillPrintView = salesRtnBillPrintView;
    }

    /**
     * @return the purHelpPanel
     */
    public GeneralBillHelp getSalRtnHelpPanel() {
        return salesRtnHelpPanel;
    }

    /**
     * @param purHelpPanel the purHelpPanel to set
     */
    public void setSaleRtnHelpPanel(GeneralBillHelp saleRtnHelpPanel) {
        this.salesRtnHelpPanel = saleRtnHelpPanel;
    }

    /**
     * @return the saleView
     */
    public SalesRtnView getSaleRtnView() {
        return saleRtnView;
    }

    /**
     * @param saleView the saleView to set
     */
    public void setSaleRtnView(SalesRtnView saleRtnView) {
        this.saleRtnView = saleRtnView;
    }

    public class ScheduledTaskSalesRtn extends TimerTask {

        @Override
        public void run() {
            initSaleRtn();
            System.out.println("Get all Sales Rtn " + new Date());
        }

    }
}
