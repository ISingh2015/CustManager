/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.AddressXML;
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.vo.ElegantCustomer;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.CountryManager;
import com.cust.persistance.managers.CustomerManager;
import com.custmanager.AppManager;

import com.custmanager.model.CustomerVO;
import com.custmanager.reports.CustListReport;
import com.custmanager.util.CustUtil;
import com.custmanager.view.CustomerView;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.view.CustomerListView;
import com.custmanager.renders.CustomerTableCellRenderer;
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
public class CustomerButtonController implements ActionListener {

    private CustomerVO customerModel;
    private CustomerView customerView;
    private CustomerListView custListPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("New")) {
            customerView.initTextFields();
            customerView.initButtonsForNew();
            customerModel.setCustomer(new ElegantCustomer());
            customerModel.setAddressXML(new AddressXML("", "", "", 0l, 0l, "", "", 0l, 0l, 0l));
            customerView.tableCustomers.setRowSorter(new TableRowSorter(customerView.tableCustomers.getModel()));
            customerView.searchText.setEnabled(false);
            customerView.tableCustomers.setEnabled(false);
        } else if (e.getActionCommand().equalsIgnoreCase("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(customerView, "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
                customerView.initTextFields();
                customerView.initButtons();
                customerView.tableCustomers.setRowSorter(new TableRowSorter(customerView.tableCustomers.getModel()));
                customerView.countryCombo.setSelectedIndex(0);
                customerView.searchText.setEnabled(true);

            }
        } else if (e.getActionCommand().contains("Print")) {
            showDialogCustList();
        } else if (e.getActionCommand().contains("Save")) {
            if (saveUpdate()) {
                customerView.initTextFields();
                customerView.initButtons();
                customerView.tableCustomers.setRowSorter(new TableRowSorter(customerView.tableCustomers.getModel()));
                customerView.countryCombo.setSelectedIndex(0);
//                sortTableData();
            }
        } else if (e.getActionCommand().contains("Delete")) {
            if (CustUtil.confirmDelete(customerView)) {
                if (delete()) {
                    customerView.initTextFields();
                    customerView.initButtons();
                    customerView.tableCustomers.setRowSorter(new TableRowSorter(customerView.tableCustomers.getModel()));
                    customerView.countryCombo.setSelectedIndex(0);
                } else {
                    CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Customer. Transactions Exist ");
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Discard")) {
            customerView.initTextFields();
            customerView.initButtons();

            customerView.tableCustomers.setRowSorter(new TableRowSorter(customerView.tableCustomers.getModel()));
            customerView.countryCombo.setSelectedIndex(0);
            customerView.searchText.setEnabled(true);
        }
        customerView.setTableColWidths();
        if (customerView.tableCustomers.getRowCount() != 0) {
            sortTableData();
        }
    }

    void sortTableData() {
        DefaultRowSorter sorter = ((DefaultRowSorter) customerView.tableCustomers.getRowSorter());
        if (sorter != null && sorter.getModelRowCount() > 0) {
            ArrayList list = new ArrayList();
            list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
            sorter.setSortKeys(list);
            sorter.sort();
        }
    }

    /**
     * @param customerModel the customerModel to set
     */
    public void setCustomerModel(CustomerVO customerModel) {
        this.customerModel = customerModel;
    }

    /**
     * @param customerView the customerView to set
     */
    public void setCustomerView(CustomerView customerView) {
        this.customerView = customerView;
    }

    public void initAllFields(boolean initCountry) {
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
        CountryManager countryManager = (CountryManager) AppManager.getInstance().getViewManagerList().get("countryManager");
        CustomerManager custManager = (CustomerManager) AppManager.getInstance().getViewManagerList().get("custManager");
        try {
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("disabled");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.add(filterCriteria);
            }
            if (initCountry) {
                PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
                ArrayList<ElegantCountry> elegantCountryList = countryManager.getCountryList();
                this.customerModel.setCountryList(elegantCountryList);
                if (!queryCriteria.getFilterCriteria().isEmpty()) {
                    queryCriteria.getFilterCriteria().remove(0);
                }
            }
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("frozen");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.clear();
                filterCriteriaList.add(filterCriteria);
                queryCriteria.setFilterCriteria(filterCriteriaList);
            }
            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("custID");
            sortCriteria.setSortDirection("DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
            List<ElegantCustomer> elegantCustomerList = custManager.getAllCustomer(PersistanceManager.getInstance().getElegantUser());
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
            this.customerModel.setCustList(elegantCustomerList);
            this.customerView.setTableColWidths();
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.customerView.setCustomerTableRenderer(customerTableCellRenderer);

        } catch (Exception e) {
            System.out.println("Init All Fields " + e.getMessage());
        }
    }

    public boolean saveUpdate() {
        boolean saved = false;
        if (!validateData()) {
            return saved;
        }
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return saved;
        }
        ArrayList<ElegantCustomer> custList = new ArrayList<ElegantCustomer>();
        CustomerManager custManager = (CustomerManager) AppManager.getInstance().getViewManagerList().get("custManager");
        try {
            ElegantCustomer cust = updateCustomer(customerModel.getCustomer());
            custList.add(cust);
            custList = custManager.saveOrUpdateCustomer(custList);

            List<ElegantCustomer> elegantCustomerList = custManager.getAllCustomer(PersistanceManager.getInstance().getElegantUser());
            this.customerModel.setCustList(elegantCustomerList);
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.customerView.setCustomerTableRenderer(customerTableCellRenderer);
            saved = true;
        } catch (Exception e) {
            System.out.println("SaveUpdate " + e.getMessage());
        }
        return saved;
    }

    private boolean validateData() {
        boolean validated = false;
        if (customerView.customerIdField.getText().equals("") || customerView.nameField.getText().equals("")
                || customerView.emailField.getText().equals("") || customerView.emailField.getText().equals("")) {
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
        CustomerManager custManager = (CustomerManager) AppManager.getInstance().getViewManagerList().get("custManager");
        try {
            ElegantCustomer cust = customerModel.getCustomer();
            if (custManager.deleteCustomer(cust)) {
                customerModel.getCustList().remove(cust);
                deleted = true;
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }

        return deleted;
    }

    private ElegantCustomer updateCustomer(ElegantCustomer cust) {
        cust.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
        if (cust.getUserID() == 0) {
            cust.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
        }
        cust.setCustID(Long.parseLong((customerView.customerIdField.getText()).equals("") ? "0" : customerView.customerIdField.getText()));
        cust.setCustName(customerView.nameField.getText());
        cust.setCreditLimit(Double.parseDouble(customerView.creditLimitField.getText()));
        cust.setPaymentTerms(Integer.parseInt(customerView.creditDaysField.getText()));
        cust.setFrozen(customerView.statusCheckBox.isSelected() ? 1 : 0);
        if (cust.getCustID() == 0) {
            cust.setCreateDate(new Date());
            cust.setFrozen(0);
        }
        AddressXML addressXMLVO = updateXML(customerModel.getAddressXML());
        String addressXML = CustUtil.getXStreamInstance().toXML(addressXMLVO);

        cust.setAddressesXML(addressXML);
        return cust;
    }

    private AddressXML updateXML(AddressXML addressXML) {
        addressXML.setAddress(customerView.addressField.getText());
        addressXML.setCountry(((ElegantCountry) customerView.countryCombo.getSelectedItem()).getCountryID());
        addressXML.setCity(customerView.cityField.getText());
        addressXML.setState(customerView.stateField.getText());
        if (!customerView.zipField.getText().equals("")) {
            addressXML.setPin(Long.parseLong(customerView.zipField.getText()));
        }
        if (!customerView.telNoField.getText().equals("")) {
            addressXML.setTelephone(Long.parseLong(customerView.telNoField.getText()));
        }
        if (!customerView.mobileNoField.getText().equals("")) {
            addressXML.setMobile(Long.parseLong(customerView.mobileNoField.getText()));
        }
        addressXML.setEmailId(customerView.emailField.getText());
        addressXML.setWebSite(customerView.websiteField.getText());
        return addressXML;
    }

    private ArrayList<ElegantCustomer> getAllCustomersForReport(Date frDt, Date toDt, boolean isActive, int crDays, double crLimit, int sortField, int sortDir) {
        ArrayList<ElegantCustomer> elegantCustomerRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        CustomerManager custManager = (CustomerManager) AppManager.getInstance().getViewManagerList().get("custManager");
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
            if (crDays > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("paymentTerms");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(Integer.toString(crDays));
                filterCriteriaList.add(filterCriteria);
            }
            if (crLimit > 0) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("creditLimit");
                filterCriteria.setFilterCondition(">=");
                filterCriteria.setFilterFieldValue(Double.toString(crLimit));
                filterCriteriaList.add(filterCriteria);
            }
            SortCriteria sortCriteria = new SortCriteria();
            if (sortField == 1) {
                sortCriteria.setSortFieldName("custID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("custName");
            }
            if (sortField == 3) {
                sortCriteria.setSortFieldName("creditLimit");
            }
            if (sortField == 4) {
                sortCriteria.setSortFieldName("paymentTerms");
            }
            sortCriteria.setSortDirection(sortDir == 1 ? "ASC" : "DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);

            queryCriteria.setFilterCriteria(filterCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
//            custManager.setServiceControl(sc);

            elegantCustomerRepList = custManager.getAllCustomer(PersistanceManager.getInstance().getElegantUser());
            this.customerModel.setCustRepList(elegantCustomerRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllCustomersForReport " + e.getMessage());
        }

        return elegantCustomerRepList;
    }

    void showDialogCustList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (custListPanel == null) {
            custListPanel = new CustomerListView();
        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, custListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = custListPanel.frmDtField.getDate();
                Date toDt = custListPanel.toDtField.getDate();
                boolean custActive = (custListPanel.custActive.isSelected() ? custListPanel.custActive.isSelected() : false);
                int crDays = Integer.parseInt(custListPanel.repCrDays.getText());
                int sortField = (custListPanel.idSort.isSelected() ? 1 : (custListPanel.nameSort.isSelected() ? 2 : (custListPanel.crLimitSort.isSelected() ? 3 : 4)));
                int sortDir = (custListPanel.ascDirection.isSelected() ? 1 : 2);
                int rowHeight = new Integer(custListPanel.reportSetup.rowHeightField.getText());
                int top = new Integer(custListPanel.reportSetup.topMarginField.getText());
                int bottom = new Integer(custListPanel.reportSetup.bottomMarginField.getText());
                int left = new Integer(custListPanel.reportSetup.leftMarginField.getText());
                int right = new Integer(custListPanel.reportSetup.rightMarginField.getText());
                boolean createBorder = custListPanel.reportSetup.createBorder.isSelected();
                double crLimit = Double.parseDouble(custListPanel.repCrLimit.getText());
                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(custListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                customerView.setCursor(hourglassCursor);
                custListPanel.setCursor(hourglassCursor);
                getAllCustomersForReport(frDt, toDt, custActive, crDays, crLimit, sortField, sortDir);
                if (customerModel.getCustRepList() == null || customerModel.getCustRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(custListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    custListPanel.setCursor(hourglassCursor);
                    CustListReport custListReport = new CustListReport(this.customerModel.getCustRepList(), frDt, toDt, custActive, createBorder, crDays, crLimit, rowHeight, top, bottom, left, right);
                    JasperPrint jp = custListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                customerView.setCursor(hourglassCursor);
                custListPanel.setCursor(hourglassCursor);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
