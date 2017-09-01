/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.ElegantSalesMan;
import com.cust.domain.vo.ElegantUser;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.SalesManManager;
import com.custmanager.AppManager;
import com.custmanager.util.CustUtil;
import com.custmanager.view.OKCancelDialogHelp;
import com.custmanager.view.SalesManListView;
import com.custmanager.model.SalesManVO;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.SalesManListReport;
import com.custmanager.view.GeneralBillHelp;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.view.SalesManView;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultRowSorter;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Inderjit
 */
public class SalesManButtonController implements ActionListener {

    private SalesManVO salesManModel;
    private SalesManView salesManView;
    private SalesManListView salesManListPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("New")) {
            salesManView.initTextFields();
            salesManView.initButtonsForNew();
            salesManModel.setSalesMan(new ElegantSalesMan());
            salesManView.tableSalesMan.setRowSorter(new TableRowSorter(salesManView.tableSalesMan.getModel()));
            salesManView.searchText.setEnabled(false);
            salesManView.tableSalesMan.setEnabled(false);
        } else if (e.getActionCommand().contains("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(salesManView, "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields();
                salesManView.tableSalesMan.setRowSorter(new TableRowSorter(salesManView.tableSalesMan.getModel()));
            }
        } else if (e.getActionCommand().contains("Print")) {
            showDialogSalesManList();
        } else if (e.getActionCommand().contains("Save")) {
            if (saveUpdate()) {
                salesManView.initTextFields();
                salesManView.initButtons();
                salesManView.tableSalesMan.setRowSorter(new TableRowSorter(salesManView.tableSalesMan.getModel()));
            }
        } else if (e.getActionCommand().contains("Delete")) {
            if (CustUtil.confirmDelete(salesManView)) {
                if (delete()) {
                    salesManView.initTextFields();
                    salesManView.initButtons();
                    salesManView.tableSalesMan.setRowSorter(new TableRowSorter(salesManView.tableSalesMan.getModel()));

                }
            }
        } else if (e.getActionCommand().contains("Discard")) {
            salesManView.initTextFields();
            salesManView.initButtons();
            salesManView.tableSalesMan.setRowSorter(new TableRowSorter(salesManView.tableSalesMan.getModel()));

            salesManView.searchText.setEnabled(true);
        } else if (e.getActionCommand().contains("...")) {
            showManagerHelpList();
        }
        salesManView.setTableColWidths();
        if (salesManView.tableSalesMan.getRowCount() != 0) {
            sortTableData();
        }

    }

    void sortTableData() {
        DefaultRowSorter sorter = ((DefaultRowSorter) salesManView.tableSalesMan.getRowSorter());
        if (sorter != null && sorter.getModelRowCount() > 0) {
            ArrayList list = new ArrayList();
            list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
            sorter.setSortKeys(list);
            sorter.sort();
        }
    }

    void sortTableDataManagerHelp(GeneralBillHelp managerHelp) {
        DefaultRowSorter sorter = ((DefaultRowSorter) managerHelp.tableHelp.getRowSorter());
        ArrayList list = new ArrayList();
        list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(list);
        sorter.sort();
    }

    public void initAllFields() {
        QueryCriteria queryCriteria = null;
        if (PersistanceManager.getInstance().getServiceControl().getQueryCriteria() == null) {
            queryCriteria = new QueryCriteria();
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
        }
        
        List<SortCriteria> sortCriteriaList = null;
        List<FilterCriteria> filterCriteriaList = null;
        if (PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getSortCriteria()!= null && PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            sortCriteriaList = PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getSortCriteria();
        }
        if (PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getFilterCriteria()!= null && PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
            filterCriteriaList = PersistanceManager.getInstance().getServiceControl().getQueryCriteria().getFilterCriteria();
        }
       
        if (filterCriteriaList == null) {
            filterCriteriaList = new ArrayList<>();
        }
        if (sortCriteriaList == null) {
            sortCriteriaList = new ArrayList<>();
        }
        SalesManManager salesManManager = (SalesManManager) AppManager.getInstance().getViewManagerList().get("salesManManager");
        try {
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
                FilterCriteria filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("frozen");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.add(filterCriteria);
                queryCriteria.setFilterCriteria(filterCriteriaList);
            }
            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("salesManID");
            sortCriteria.setSortDirection("DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
            ArrayList<ElegantSalesMan> elegantSalesManList = salesManManager.getAllSalesMans(PersistanceManager.getInstance().getElegantUser());
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);

            this.salesManModel.setSalesManList(elegantSalesManList);
            this.salesManView.setTableColWidths();
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            getSalesManView().setSalesManTableCellRenderer(customerTableCellRenderer);

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
        ArrayList<ElegantSalesMan> salesManList = new ArrayList<>();
        SalesManManager salesManManager = (SalesManManager) AppManager.getInstance().getViewManagerList().get("salesManManager");
        try {
            if (salesManView.managerIdField.getText() == null || salesManView.managerIdField.getText() == "") {
                salesManView.managerIdField.requestFocus();
                return saved;
            }
            ElegantSalesMan salesMan = updateCustomer(salesManModel.getSalesMan());
            salesManList.add(salesMan);
            salesManList = salesManManager.saveOrUpdateSalesMan(salesManList);

            ArrayList<ElegantSalesMan> elegantSalesManList = salesManManager.getAllSalesMans(PersistanceManager.getInstance().getElegantUser());
            this.salesManModel.setSalesManList(elegantSalesManList);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.salesManView.setSalesManTableCellRenderer(customerTableCellRenderer);
            saved = true;
        } catch (Exception e) {
            System.out.println("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    public boolean delete() {
        boolean deleted = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return deleted;
        }
        SalesManManager salesManManager = (SalesManManager) AppManager.getInstance().getViewManagerList().get("salesManManager");
        try {
            ElegantSalesMan salesMan = salesManModel.getSalesMan();
            if (salesManManager.deleteSalesMan(salesMan)) {
                salesManModel.getSalesManList().remove(salesMan);
                deleted = true;
            } else {
                CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Sales Man. Transactions Exist ");
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private boolean validateData() {
        boolean validated = false;
        if (salesManView.salesManIdField.getText().equals("") || salesManView.nameField.getText().equals("") || salesManView.managerIdField.equals("")) {
            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
            return validated;
        }
        validated = true;
        return validated;
    }

    private ElegantSalesMan updateCustomer(ElegantSalesMan salesMan) {
        salesMan.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
        salesMan.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
        salesMan.setSalesManID(Long.parseLong((salesManView.salesManIdField.getText()).equals("") ? "0" : salesManView.salesManIdField.getText()));
        salesMan.setSalesManName(salesManView.nameField.getText());
        salesMan.setManagerId(Long.parseLong(salesManView.managerIdField.getText()));
        if (salesMan.getSalesManID() == 0) {
            salesMan.setCreateDate(new Date());
            salesMan.setFrozen(0);
        }
        salesMan.setFrozen(salesManView.statusCheckBox.isSelected() ? 1 : 0);
        return salesMan;
    }

    private ArrayList<ElegantSalesMan> getAllSalesManForReport(Date frDt, Date toDt, boolean isActive, int sortField, int sortDir) {
        ArrayList<ElegantSalesMan> elegantSalesManRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        SalesManManager salesManManager = (SalesManManager) AppManager.getInstance().getViewManagerList().get("salesManManager");
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
            filterCriteria.setFilterFieldName("frozen");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(isActive ? "0" : "1");
            filterCriteriaList.add(filterCriteria);

            SortCriteria sortCriteria = new SortCriteria();
            if (sortField == 1) {
                sortCriteria.setSortFieldName("salesManID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("salesManName");
            }
            if (sortField == 3) {
                sortCriteria.setSortFieldName("managerId");
            }
            sortCriteria.setSortDirection(sortDir == 1 ? "ASC" : "DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);

            queryCriteria.setFilterCriteria(filterCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
//            ServiceControl sc = salesManManager.getServiceControl();
//            sc.setQueryCriteria(queryCriteria);
//            salesManManager.setServiceControl(sc);

            elegantSalesManRepList = salesManManager.getAllSalesMans(PersistanceManager.getInstance().getElegantUser());
            salesManModel.setSalesManRepList(elegantSalesManRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllCustomersForReport " + e.getMessage());
        }

        return elegantSalesManRepList;
    }

    void showManagerHelpList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        SalesManManager salesManManager = (SalesManManager) AppManager.getInstance().getViewManagerList().get("salesManManager");
        try {
            ArrayList<ElegantUser> managerList = salesManManager.getAllUsers();
            salesManModel.setManagerList(managerList);

            GeneralBillHelp managerHelpPanel = (GeneralBillHelp) AppManager.getInstance().getViewHelpList().get("managerHelp");

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            managerHelpPanel.setTableCellRenderer(customerTableCellRenderer);

            OKCancelDialogHelp repDialog = new OKCancelDialogHelp("Select Manager Code from List", managerHelpPanel);
            int resp = repDialog.showDialog();
            if (resp == OKCancelDialogHelp.KINT_OK) {
                int row = managerHelpPanel.tableHelp.getSelectedRow();
                long value = (Long) managerHelpPanel.tableHelp.getModel().getValueAt(row, 0);
                salesManView.managerIdField.setText(Long.toString(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showDialogSalesManList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (salesManListPanel == null) {
            salesManListPanel = new SalesManListView();
            int accountType = PersistanceManager.getInstance().getElegantUser().getAccountType();
            if (accountType == 0) {
                salesManListPanel.listQuotaLabel.setVisible(false);
                salesManListPanel.yesQuota.setVisible(false);
            }
        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, salesManListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = salesManListPanel.frmDtField.getDate();
                Date toDt = salesManListPanel.toDtField.getDate();
                boolean custActive = (salesManListPanel.custActive.isSelected() ? salesManListPanel.custActive.isSelected() : false);
                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(salesManListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int sortField = (salesManListPanel.idSort.isSelected() ? 1 : (salesManListPanel.nameSort.isSelected() ? 2 : 3));
                int sortDir = (salesManListPanel.ascDirection.isSelected() ? 1 : 2);
                int displayQuota = (salesManListPanel.yesQuota.isSelected() ? 1 : 0);
                int rowHeight = new Integer(salesManListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(salesManListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(salesManListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(salesManListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(salesManListPanel.reportSetup.rightMarginField.getText());
                boolean createBorder = salesManListPanel.reportSetup.createBorder.isSelected();
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                salesManView.setCursor(hourglassCursor);
                salesManListPanel.setCursor(hourglassCursor);
                getAllSalesManForReport(frDt, toDt, custActive, sortField, sortDir);
                if (salesManModel.getSalesManRepList() == null || salesManModel.getSalesManRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(salesManListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    salesManListPanel.setCursor(hourglassCursor);
                    SalesManListReport salesManListReport = new SalesManListReport(salesManModel.getSalesManRepList(), frDt, toDt, custActive, createBorder, displayQuota, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = salesManListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                salesManListPanel.setCursor(hourglassCursor);
                salesManView.setCursor(hourglassCursor);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the salesManModel
     */
    public SalesManVO getSalesManModel() {
        return salesManModel;
    }

    /**
     * @param SalesManModel the salesManModel to set
     */
    public void setSalesManModel(SalesManVO SalesManModel) {
        this.salesManModel = SalesManModel;
    }

    /**
     * @return the salesManView
     */
    public SalesManView getSalesManView() {
        return salesManView;
    }

    /**
     * @param SalesManView the salesManView to set
     */
    public void setSalesManView(SalesManView SalesManView) {
        this.salesManView = SalesManView;
    }

}
