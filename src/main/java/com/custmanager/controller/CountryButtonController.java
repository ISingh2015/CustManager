/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.ElegantCountry;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.CountryManager;
import com.custmanager.AppManager;
import com.custmanager.model.CountryVO;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.CountryListReport;
import com.custmanager.util.CustUtil;
import com.custmanager.view.CountryListView;
import com.custmanager.view.CountryView;
import com.custmanager.view.OKCancelDialogPrint;
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
 * @author Inderjit Singh Sanhotra
 */
public class CountryButtonController implements ActionListener {
    
    private CountryVO countryModel;
    private CountryView countryView;
    private CountryListView countryListPanel;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("New")) {
            getCountryView().initTextFields();
            getCountryView().initButtonsForNew();
            getCountryModel().setCountry(new ElegantCountry());
            
            getCountryView().tableCountry.setRowSorter(new TableRowSorter(getCountryView().tableCountry.getModel()));
            getCountryView().searchText.setEnabled(false);
            getCountryView().tableCountry.setEnabled(false);
        } else if (e.getActionCommand().contains("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(getCountryView(), "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields();
                getCountryView().tableCountry.setRowSorter(new TableRowSorter(getCountryView().tableCountry.getModel()));
            }
            
        } else if (e.getActionCommand().contains("Print")) {
            showDialogCountryList();
        } else if (e.getActionCommand().contains("Save")) {
            if (saveUpdate()) {
                getCountryView().initTextFields();
                getCountryView().initButtons();
                getCountryView().searchText.setEnabled(true);
                getCountryView().tableCountry.setRowSorter(new TableRowSorter(getCountryView().tableCountry.getModel()));
                
            }
            
        } else if (e.getActionCommand().contains("Delete")) {
            if (CustUtil.confirmDelete(getCountryView())) {
                if (delete()) {
                    getCountryView().initTextFields();
                    getCountryView().initButtons();
                    getCountryView().tableCountry.setRowSorter(new TableRowSorter(getCountryView().tableCountry.getModel()));
                } else {
                    CustUtil.showMessageDialogue("We are Sorry !!! Could Not Country. Transactions Exist ");
                }
            }
            
        } else if (e.getActionCommand().contains("Discard")) {
            getCountryView().initTextFields();
            getCountryView().initButtons();
            getCountryModel().setCountry(new ElegantCountry());
            getCountryView().tableCountry.setRowSorter(new TableRowSorter(getCountryView().tableCountry.getModel()));
            getCountryView().searchText.setEnabled(true);
        }
        getCountryView().setTableColWidths();
        if (getCountryView().tableCountry.getRowCount() != 0) {
            sortTableData();
        }
        
    }
    
    public void sortTableData() {
        DefaultRowSorter sorter = ((DefaultRowSorter) getCountryView().tableCountry.getRowSorter());
        if (sorter != null && sorter.getModelRowCount() > 0) {
            ArrayList list = new ArrayList();
            list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
            sorter.setSortKeys(list);
            sorter.sort();
        }
    }

    /**
     * @return the userAdminView
     */
    public CountryView getCountryView() {
        return countryView;
    }
    
    public void setCountryView(CountryView countryView) {
        this.countryView = countryView;
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
        CountryManager countryManager = (CountryManager) AppManager.getInstance().getManagerList().get("countryManager");
        try {
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
                FilterCriteria filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("disabled");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.add(filterCriteria);
                queryCriteria.setFilterCriteria(filterCriteriaList);
            }
            
            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("countryID");
            sortCriteria.setSortDirection("DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            
            ServiceControl sc = PersistanceManager.getInstance().getServiceControl();
            sc.setQueryCriteria(queryCriteria);
            
            ArrayList<ElegantCountry> elegantCountryList = countryManager.getCountryList();
            sc.setQueryCriteria(null);
            this.getCountryModel().setCountryList(elegantCountryList);
            getCountryView().setTableColWidths();
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            getCountryView().setCountryTableRenderer(customerTableCellRenderer);
            
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
        ArrayList<ElegantCountry> countryList = new ArrayList<ElegantCountry>();
        CountryManager countryManager = (CountryManager) AppManager.getInstance().getManagerList().get("countryManager");
        
        try {
            ElegantCountry country = updateCountry(getCountryModel().getCountry());
            countryList.add(country);
            countryManager.saveOrUpdateCountry(countryList);
            
            ArrayList<ElegantCountry> elegantCountryList = countryManager.getCountryList();
            getCountryModel().setCountryList(elegantCountryList);
            
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            getCountryView().setCountryTableRenderer(customerTableCellRenderer);
            
            getCountryModel().setCountry(country);
            saved = true;
            
        } catch (Exception e) {
            
        }
        return saved;
    }
    
    private ElegantCountry updateCountry(ElegantCountry country) {
        try {
            long id = Long.parseLong(getCountryView().countryIdField.getText() == "" ? "0" : getCountryView().countryIdField.getText());
            country.setCountryID(id);
            country.setCountryCd(getCountryView().countryCdField.getText());
            country.setCountryName(getCountryView().nameField.getText());
            country.setCurrency(getCountryView().currencyField.getText());
            country.setExchangeRate(Double.parseDouble(getCountryView().exchangeRate.getText()));
            country.setDisabled(getCountryView().statusCheckBox.isSelected() ? 1 : 0);
            if (id == 0) {
                country.setCreateDate(new Date());
                country.setDisabled(0);
            }
        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
        }
        return country;
    }
    
    private boolean validateData() {
        boolean validated = false;
        if (getCountryView().countryIdField.getText().equals("") || getCountryView().nameField.getText().equals("")) {
            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
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
        
        CountryManager countryManager = (CountryManager) AppManager.getInstance().getManagerList().get("countryManager");
        try {
            ElegantCountry country = getCountryModel().getCountry();
            if (countryManager.deleteCountry(country)) {
                getCountryModel().getCountryList().remove(country);
                deleted = true;
            }
            
        } catch (Exception e) {
            
        }
        return deleted;
    }
    
    void showDialogCountryList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (countryListPanel == null) {
            countryListPanel = new CountryListView();
        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, countryListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = countryListPanel.frmDtField.getDate();
                Date toDt = countryListPanel.toDtField.getDate();
                int rowHeight = new Integer(countryListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(countryListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(countryListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(countryListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(countryListPanel.reportSetup.rightMarginField.getText());
                boolean userActive = (countryListPanel.custActive.isSelected() ? countryListPanel.custActive.isSelected() : false);
                boolean createBorder = countryListPanel.reportSetup.createBorder.isSelected();
                int sortField = (countryListPanel.idSort.isSelected() ? 1 : (countryListPanel.codeSort.isSelected() ? 2 : (countryListPanel.nameSort.isSelected() ? 3 : 4)));
                int sortDir = (countryListPanel.ascDirection.isSelected() ? 1 : 2);
                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(countryListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                getCountryView().setCursor(hourglassCursor);
                countryListPanel.setCursor(hourglassCursor);
                getAllCountriesForReport(frDt, toDt, userActive, sortField, sortDir);
                if (getCountryModel().getCountryRepList() == null || getCountryModel().getCountryRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(countryListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    countryListPanel.setCursor(hourglassCursor);
                    CountryListReport countryListReport = new CountryListReport(getCountryModel().getCountryRepList(), frDt, toDt, userActive, createBorder, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = countryListReport.getReport();
                    
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                getCountryView().setCursor(hourglassCursor);
                countryListPanel.setCursor(hourglassCursor);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    private ArrayList<ElegantCountry> getAllCountriesForReport(Date frDt, Date toDt, boolean isActive, int sortField, int sortDir) {
        ArrayList<ElegantCountry> elegantCountryRepList = null;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return elegantCountryRepList;
        }
        CountryManager countryManager = (CountryManager) AppManager.getInstance().getManagerList().get("countryManager");
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
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
            filterCriteria.setFilterFieldName("disabled");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(isActive ? "0" : "1");
            filterCriteriaList.add(filterCriteria);
            
            queryCriteria.setFilterCriteria(filterCriteriaList);
            
            SortCriteria sortCriteria = new SortCriteria();
            if (sortField == 1) {
                sortCriteria.setSortFieldName("countryID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("countryCd");
            }
            if (sortField == 3) {
                sortCriteria.setSortFieldName("countryName");
            }
            if (sortField == 4) {
                sortCriteria.setSortFieldName("currency");
            }
            sortCriteria.setSortDirection(sortDir == 1 ? "ASC" : "DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            
            ServiceControl sc = PersistanceManager.getInstance().getServiceControl();
            sc.setQueryCriteria(queryCriteria);
            elegantCountryRepList = countryManager.getCountryList();
            getCountryModel().setCountryRepList(elegantCountryRepList);
            sc.setQueryCriteria(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getAllCustomersForReport " + e.getMessage());
        }
        
        return elegantCountryRepList;
    }

    /**
     *
     * @return the userAdminModel
     */
    public CountryVO getCountryModel() {
        return countryModel;
    }

    /**
     * @param userAdminModel the userAdminModel to set
     */
    public void setCountryModel(CountryVO countryModel) {
        this.countryModel = countryModel;
    }
    
}
