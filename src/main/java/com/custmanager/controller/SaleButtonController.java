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
import com.custmanager.view.SalesView;
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

/**
 *
 * @author Inderjit
 */
public class SaleButtonController implements ActionListener {

    private BuySellVO saleModel;
    private SalesView saleView;
    private BuySellListReport buySellListReport;
    private BuySellListView buySellListPanel;

    private GeneralBillHelp supHelpPanel, custHelpPanel, salesManHelpPanel, salesHelpPanel, prodHelpPanel;
    private BillPrintView salesBillPrintView;

    public SaleButtonController() {
//           timer.scheduleAtFixedRate(new ScheduledTaskPurchase(), 100,10000); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            CustUtil.disableOrEnableForAuth(saleView, true);
            getSaleView().initTextFields();
            getSaleView().initButtonsForNew();
            saleModel.setElegantSell(new ElegantBuySell());
            saleModel.setSellDetailsList(new ArrayList<>());
            getSaleView().searchField.setEnabled(false);
            getSaleView().authPanel.setVisible(false);
//            purchaseView.tablePurchaseDetails.setEnabled(false);
        } else if (e.getActionCommand().equalsIgnoreCase("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(getSaleView(), "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("PrintList")) {
            showDialogSalesList();
        } else if (e.getActionCommand().equalsIgnoreCase("PrintBill")) {
            showDialogSalesBillPrint();
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            if (saveUpdate()) {
                CustUtil.disableOrEnableForAuth(saleView, true);
                getSaleView().initTextFields();
                getSaleView().initButtons();
                saleModel.setElegantSell(new ElegantBuySell());
                saleModel.setSellDetailsList(new ArrayList<>());
                getSaleView().searchField.setEnabled(false);
                getSaleView().authPanel.setVisible(false);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Delete")) {
            if (CustUtil.confirmDelete(getSaleView())) {
                if (delete()) {
                    getSaleView().initTextFields();
                    getSaleView().initButtons();
                    saleModel.setElegantSell(new ElegantBuySell());
                    saleModel.setSellDetailsList(new ArrayList<>());
                    getSaleView().searchField.setEnabled(false);

                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Discard")) {
            CustUtil.disableOrEnableForAuth(saleView, true);
            getSaleView().initTextFields();
            getSaleView().initButtons();
            saleModel.setElegantSell(new ElegantBuySell());
            saleModel.setSellDetailsList(new ArrayList<>());
            getSaleView().searchField.setEnabled(true);
            getSaleView().authPanel.setVisible(false);
            if (!AppManager.getInstance().getElegantInventory().menuSalesRep.isEnabled()) {
                getSaleView().buttonPanel.printButton.setEnabled(false);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("salBillHelp")) {
            showSalesBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("custHelp")) {
            showCustomerBillHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("salesManHelp")) {
            showSalesManCodeHelpList();
        } else if (e.getActionCommand().equalsIgnoreCase("addRow")) {
            addRow();
        } else if (e.getActionCommand().equalsIgnoreCase("removeRow")) {
            removeRow();
        } else if (e.getActionCommand().equalsIgnoreCase("salHelpFieldRep1")) {
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalHelpPanel());
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = this.getSalHelpPanel().tableHelp.getSelectedRow();
                this.getSaleBillPrintView().helpTextField1.textField.setText(Long.toString((Long) this.getSalHelpPanel().tableHelp.getValueAt(row, 0)));
            }
        } else if (e.getActionCommand().equalsIgnoreCase("salHelpFieldRep2")) {
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.getSalHelpPanel());
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = this.getSalHelpPanel().tableHelp.getSelectedRow();
                this.getSaleBillPrintView().helpTextField2.textField.setText(Long.toString((Long) this.getSalHelpPanel().tableHelp.getValueAt(row, 0)));
            }

        } else if (e.getActionCommand().equalsIgnoreCase("custHelpFieldRep")) {
            if (this.supHelpPanel != null) {
                OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, this.supHelpPanel);
                int resp = repDialog.showDialog();
                if (resp == OKCancelDialogHelp.KINT_OK) {
                    int row = this.supHelpPanel.tableHelp.getSelectedRow();
                    this.buySellListPanel.supCodeField.textField.setText(Long.toString((Long) this.supHelpPanel.tableHelp.getValueAt(row, 0)));
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
            if (saleView.authPanel.authStatCombo.getSelectedIndex() == 1) {
//                purchaseView.authPanel.itemStockPost.setSelected(true);
                saleView.authPanel.itemStockPost.setEnabled(true);
            } else {
                saleView.authPanel.itemStockPost.setSelected(false);
                saleView.authPanel.itemStockPost.setEnabled(false);
            }
        }

//        if (saleView.tableSalesDetails.getRowCount()>0 ) 
        getSaleView().setTableColWidths();
    }

    private void addRow() {
        ArrayList<ElegantBuySellDetails> tempList = saleModel.getSellDetailsList();
        ElegantBuySellDetails elegantBuySellDetails = new ElegantBuySellDetails();
        long srl = tempList.size() == 0 ? 1 : tempList.size() + 1;
        elegantBuySellDetails.setSrl(srl);
        tempList.add(elegantBuySellDetails);
        long newSrl = 1;
        for (ElegantBuySellDetails temp : tempList) {
            temp.setSrl(newSrl);
            newSrl++;
        }
        updateBuySell(this.saleModel.getElegantSell());
        saleModel.setSellDetailsList(tempList);
    }

    private void removeRow() {
        ArrayList<ElegantBuySellDetails> tempList = saleModel.getSellDetailsList();
        int indexToDelete = getSaleView().tableSalesDetails.getSelectedRow();
        if (indexToDelete > 0) {
            tempList.remove(indexToDelete);
            long newSrl = 1;
            for (ElegantBuySellDetails temp : tempList) {
                temp.setSrl(newSrl);
                newSrl++;
            }
            updateBuySell(this.saleModel.getElegantSell());
            saleModel.setSellDetailsList(tempList);
        }
    }

//    private void initSale() {
//        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
//        try {
//            ArrayList<ElegantBuySell> elegantSellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESBILLTYPE);
//            this.saleModel.setSellList(elegantSellList);
//            getSaleView().setTableColWidths();
//        } catch (Exception e) {
//            System.out.println("Init All Fields " + e.getMessage());
//        }
//
//    }
    public void initAllFields(boolean initDetails) {
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
        saleView.authPanel.setVisible(false);
        try {
            ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESBILLTYPE, true);
            if (elegantBuySellList != null) {
                Collections.sort(elegantBuySellList);
            }
            saleModel.setSellList(elegantBuySellList);
            if (initDetails) {
                this.saleModel.setElegantSell(new ElegantBuySell());
                this.saleModel.setSellDetailsList(new ArrayList<>());
                getSaleView().setTableColWidths();
            }
            if (!AppManager.getInstance().getElegantInventory().menuSalesRep.isEnabled()) {
                saleView.buttonPanel.printButton.setEnabled(false);
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
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
        try {
            if (!validateData()) {
                return saved;
            }
            ElegantBuySell buySell = updateBuySell(saleModel.getElegantSell());
            buySellListToSave.add(buySell);
            saved = buySellManager.saveOrUpdateBuySell(buySellListToSave, false); // TOdo post stock
            if (saved) {
                buySellListToSave = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESBILLTYPE, true);
                this.saleModel.setSellList(buySellListToSave);
                this.saleModel.setElegantSell(new ElegantBuySell());
                this.saleModel.setSellDetailsList(new ArrayList<ElegantBuySellDetails>());
                saved = true;
            }
        } catch (Exception e) {
            CustUtil.showErrorDialogue("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    private boolean validateData() {
        boolean validated = true;
        if (getSaleView().invDate == null || getSaleView().invDate.getDate().equals(null)) {
            validated = false;
        }
        if (getSaleView().saleInvNo.getText().equals(null) || getSaleView().custIdField.getText().equals(null) || getSaleView().custNameField.getText().equals(null)
                || getSaleView().saleInvNo.getText().equals("") || getSaleView().custIdField.getText().equals("") || getSaleView().custNameField.getText().equals("")) {
            validated = false;
        }
        if (getSaleView().salesManIdField.getText().equals(null) || getSaleView().salesManNameField.getText().equals(null) || getSaleView().totBillAmt.getText().equals(null)
                || getSaleView().salesManIdField.getText().equals("") || getSaleView().salesManNameField.getText().equals("") || getSaleView().totBillAmt.getText().equals("")) {
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
        BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
        try {
            ElegantBuySell buySell = this.saleModel.getElegantSell();
            if (buySellManager.deleteBuySell(buySell)) {
                ArrayList<ElegantBuySell> elegantBuySellList = buySellManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESBILLTYPE, true);
                this.saleModel.setSellList(elegantBuySellList);
                this.saleModel.setSellDetailsList(new ArrayList<>());
                deleted = true;
            } else {
                CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Sale. Transactions Exist ");
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private ElegantBuySell updateBuySell(ElegantBuySell buySell) {
        try {
            buySell.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
            buySell.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
            buySell.setBillID(Long.parseLong((getSaleView().billIdField.getText()).equals("") ? "0" : getSaleView().billIdField.getText()));
            buySell.setBillType(CustUtil.SALESBILLTYPE);
            buySell.setBillNo(getSaleView().saleInvNo.getText());
            buySell.setBillDt(getSaleView().invDate.getDate());
            buySell.setBuyerSellerCode(Integer.parseInt(getSaleView().custIdField.getText()));
            buySell.setBuyerSellerName(getSaleView().custNameField.getText());
            buySell.setBuyerSellBillNo("n/a");
            buySell.setBuyerSellerBillDt(getSaleView().invDate.getDate());
            buySell.setSalesManCode(Integer.parseInt(getSaleView().salesManIdField.getText()));
            buySell.setSalesManName(getSaleView().salesManNameField.getText());
            buySell.setFreighTranspDedAmt(Double.parseDouble(getSaleView().freightField.getText()));
            buySell.setTaxDedAmt(Double.parseDouble(getSaleView().taxField.getText()));
            buySell.setFinalBillAmt(calculateBillAmt(buySell));
            buySell.setRemarks(getSaleView().remarks.getText());
            if (buySell.getBillID() == 0) {
                buySell.setCreateDate(new Date());
                buySell.setBillStatus(0);
            }
            ElegantUser user = PersistanceManager.getInstance().getElegantUser();
            if (user.getAccountType() == 2 || (user.getDivision() == 3 && (user.getRole() == 5 || user.getRole() == 6))) {
                if (saleView.authPanel.authStatCombo.getSelectedIndex() > 0) {
                    doUpdateBillStatus(buySell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buySell;
    }

    private void doUpdateBillStatus(ElegantBuySell buySell) {
        if (buySell.getBillID() == 0) {
            buySell.setAuthRequired(0);
        }
        if (saleView.authPanel.authDate.getDate() != null) {
            buySell.setAuthDate(saleView.authPanel.authDate.getDate());
            buySell.setAuthRemarks(saleView.authPanel.authRemark.getText());
            buySell.setAuthStatus(saleView.authPanel.authStatCombo.getSelectedIndex());
            buySell.setStockPosted(saleView.authPanel.itemStockPost.isSelected() ? 1 : 0);
            if ((buySell.getAuthStatus() != null && buySell.getAuthStatus() > 0) && (buySell.getStockPosted() != null && buySell.getStockPosted() == 1)) {
                buySell.setBillStatus(1); // lock bill from further updates as already authroised        
            }
        }
    }

    private double calculateBillAmt(ElegantBuySell elegantBuySell) {
        double total = 0.0;

        ArrayList<ElegantBuySellDetails> elegantBuySellDetailsList = elegantBuySell.getBuySellDetailsList();
        for (ElegantBuySellDetails elegantBuySellDetails : elegantBuySellDetailsList) {
            double tempFinalAmt = (elegantBuySellDetails.getBilledQty() * elegantBuySellDetails.getBilledRate()) - elegantBuySellDetails.getUnitDiscount();
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
        BuySellManager purManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
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

            elegantBuySellRepList = purManager.getAllBuySell(PersistanceManager.getInstance().getElegantUser(), CustUtil.SALESBILLTYPE, true);
            saleModel.setBuySellRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllBuySellForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    private ArrayList<ElegantBuySell> getAllBuySellBillsForReport(String fromBill, String toBill) {
        ArrayList<ElegantBuySell> elegantBuySellRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        BuySellManager saleManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
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
            filterCriteria.setFilterFieldValue(Integer.toString(CustUtil.SALESBILLTYPE));
            filterCriteriaList.add(filterCriteria);

            queryCriteria.setFilterCriteria(filterCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);

// For Report
            elegantBuySellRepList = saleManager.getAllBuySellForRep(PersistanceManager.getInstance().getElegantUser());
            saleModel.setBuySellRepList(elegantBuySellRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllBuySellBillsForReport " + e.getMessage());
        }
        return elegantBuySellRepList;
    }

    void showDialogSalesBillPrint() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.getSaleBillPrintView() == null) {
            this.setSalesBillPrintView(new BillPrintView());
            this.getSaleBillPrintView().headerLabel.setText("Enter Invoice Nos to View Below");
            salesHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("saleBillHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            salesHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesHelpPanel.setTableColWidths();

            this.setPurHelpPanel(salesHelpPanel);
            HelpTextField purHelpTextField1 = this.getSaleBillPrintView().helpTextField1;
            purHelpTextField1.helpButton.setActionCommand("salHelpFieldRep1");
            purHelpTextField1.addButtonController(this);

            purHelpTextField1 = this.getSaleBillPrintView().helpTextField2;
            purHelpTextField1.helpButton.setActionCommand("salHelpFieldRep2");
            purHelpTextField1.addButtonController(this);

        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, getSaleBillPrintView());
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                String fromBillNo = this.getSaleBillPrintView().helpTextField1.textField.getText();
                String toBillNo = this.getSaleBillPrintView().helpTextField2.textField.getText();
                if (fromBillNo.isEmpty() || toBillNo.isEmpty() || (Integer.parseInt(toBillNo) == 0 || Integer.parseInt(toBillNo) == 0)) {
                    JOptionPane.showMessageDialog(this.getSaleBillPrintView().getRootPane().getContentPane(), "Please Enter Bill No in From and To Fields", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (Long.parseLong(fromBillNo) > Long.parseLong(toBillNo)) {
                    JOptionPane.showMessageDialog(this.getSaleBillPrintView().getRootPane().getContentPane(), "Please enter Valid Bill No in From and To Fields", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int no = Integer.parseInt(fromBillNo), no1 = Integer.parseInt(toBillNo);
                if (no1 - no >= 1000) {
                    JOptionPane.showMessageDialog(this.getSaleBillPrintView().getRootPane().getContentPane(), "Please Select Less than 1000 Bills at a time for Print", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int rowHeight = new Integer(getSaleBillPrintView().reportSetup.rowHeightField.getText());
                int topMargin = new Integer(getSaleBillPrintView().reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(getSaleBillPrintView().reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(getSaleBillPrintView().reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(getSaleBillPrintView().reportSetup.rightMarginField.getText());
                boolean createBorder = getSaleBillPrintView().reportSetup.createBorder.isSelected();

                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                getSaleView().setCursor(hourglassCursor);
                getAllBuySellBillsForReport(fromBillNo, toBillNo);
                if (saleModel.getBuySellRepList() == null || saleModel.getBuySellRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(this.getSaleBillPrintView().getRootPane().getContentPane(), "Bills Not Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<BuySellReportData> reportData = createValueObjectForBills(saleModel.getBuySellRepList());
                    BuySellBillReport buySellBillReport = new BuySellBillReport(reportData, createBorder, rowHeight, topMargin, leftMargin, bottomMargin, rightMargin);
                    JasperPrint jp = buySellBillReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }

                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                getSaleView().setCursor(hourglassCursor);

            } catch (NumberFormatException | HeadlessException | ColumnBuilderException | JRException | ClassNotFoundException | SecurityException e) {
//                e.printStackTrace();
                System.out.println("showDialogPurchaseBillPrint" + e.getMessage());
            }
        }
    }

// java 8 syntax for loops
    ArrayList<BuySellReportData> createValueObjectForBills(ArrayList<ElegantBuySell> elegantBuySellList) {
        ArrayList<BuySellReportData> returnList = new ArrayList<>();
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

    public void showDialogSalesList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.buySellListPanel == null) {
            this.buySellListPanel = new BuySellListView();
            this.buySellListPanel.labelHeader.setText("Invoice Selection Criteria");
            this.buySellListPanel.labelSupCust.setText("Cust Code");
            supHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("custCodeHelp");
            salesManHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            supHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            supHelpPanel.setTableColWidths();
            customerTableCellRenderer = new CustomerTableCellRenderer();
            salesManHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesManHelpPanel.setTableColWidths();

            this.setSupHelpPanel(supHelpPanel);
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
                getSaleView().setCursor(hourglassCursor);
                getAllBuySellForReport(frDt, toDt, custActive, custCode, salesManCode, billAmt, sortField, sortDir);
                if (saleModel.getBuySellRepList() == null || saleModel.getBuySellRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(buySellListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    buySellListReport = new BuySellListReport(saleModel.getBuySellRepList(), frDt, toDt, custCode, salesManCode, billAmt, custActive, CustUtil.SALESBILLTYPE, createBorder, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = buySellListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                getSaleView().setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the saleModel
     */
    public BuySellVO getSaleModel() {
        return saleModel;
    }

    /**
     * @param saleModel the purchaseModel to set
     */
    public void setSaleModel(BuySellVO saleModel) {
        this.saleModel = saleModel;
    }

    /**
     * @return the salesView
     */
    public SalesView getPurchaseView() {
        return getSaleView();
    }

    /**
     * @param salesView the purchaseView to set
     */
    public void setSalesView(SalesView salesView) {
        this.setSaleView(salesView);
    }

    void showSalesBillHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        try {
            salesHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("saleBillHelp");
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            salesHelpPanel.setTableCellRenderer(customerTableCellRenderer);
            salesHelpPanel.setTableColWidths();
            OKCancelDialogHelp repDialog = new OKCancelDialogHelp(CustUtil.APPNAME, salesHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                JTable target = salesHelpPanel.tableHelp;
                int row = salesHelpPanel.tableHelp.getSelectedRow();
                if (row >= 0) {
                    ElegantBuySell elegantBuySell = saleModel.getSellList().get(target.convertRowIndexToModel(row));
                    BuySellManager buySellManager = (BuySellManager) AppManager.getInstance().getViewManagerList().get("saleManager");
                    ElegantBuySell tElegantBuySell = (ElegantBuySell) buySellManager.getBuySellById(PersistanceManager.getInstance().getElegantUser(), new Long(elegantBuySell.getBillID()).intValue());
                    if (tElegantBuySell != null) {
                        tElegantBuySell.setBuySellDetailsList(tElegantBuySell.getBuySellDetailsList());
                        saleModel.setElegantSell(tElegantBuySell);
                        saleModel.setSellDetailsList(tElegantBuySell.getBuySellDetailsList());
                        getSaleView().searchField.setEnabled(true);
                        getSaleView().initButtonsFordelete();
                    }
                    getSaleView().saleInvNo.requestFocus();
                    ElegantUser user = PersistanceManager.getInstance().getElegantUser();
                    CustUtil.setDateLimitForAuthOrBills(this.saleView.invDate, this.saleView.authPanel.authDate, 30);
                    if (user.getAccountType() == 2 || (user.getDivision() == 3 && (user.getRole() == 5 || user.getRole() == 6))) {
                        saleView.authPanel.setVisible(true);
                        CustUtil.disableOrEnableForAuth(saleView, false);
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
            custHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("custCodeHelp");
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

                    updateBuySell(this.saleModel.getElegantSell());

                    ElegantBuySell elegantBuySell = this.saleModel.getElegantSell();
                    elegantBuySell.setBuyerSellerCode(new Long(buyerSellerCode).intValue());
                    elegantBuySell.setBuyerSellerName(buyerSellerName);
                    this.saleModel.setElegantSell(elegantBuySell);
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
            salesManHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("salesManCodeHelp");

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

                    updateBuySell(this.saleModel.getElegantSell());

                    ElegantBuySell elegantBuySell = this.saleModel.getElegantSell();
                    elegantBuySell.setSalesManCode(new Long(salesManCode).intValue());
                    elegantBuySell.setSalesManName(salesManName);
                    this.saleModel.setElegantSell(elegantBuySell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public BillPrintView getSaleBillPrintView() {
        return salesBillPrintView;
    }

    /**
     * @param purchaseBillPrintView the purchaseBillPrintView to set
     */
    public void setSalesBillPrintView(BillPrintView purchaseBillPrintView) {
        this.salesBillPrintView = purchaseBillPrintView;
    }

    /**
     * @return the purHelpPanel
     */
    public GeneralBillHelp getSalHelpPanel() {
        return salesHelpPanel;
    }

    /**
     * @param purHelpPanel the purHelpPanel to set
     */
    public void setPurHelpPanel(GeneralBillHelp purHelpPanel) {
        this.salesHelpPanel = purHelpPanel;
    }

    /**
     * @return the saleView
     */
    public SalesView getSaleView() {
        return saleView;
    }

    /**
     * @param saleView the saleView to set
     */
    public void setSaleView(SalesView saleView) {
        this.saleView = saleView;
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
//            initSale();
//            System.out.println("Get all Sales Invoices " + new Date());
//        }
//
//    }
}
