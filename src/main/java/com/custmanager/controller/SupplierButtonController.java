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
import com.cust.domain.vo.ElegantSupplier;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.CountryManager;
import com.cust.persistance.managers.SupplierManager;
import com.custmanager.AppManager;
import com.custmanager.model.SupplierVO;
import com.custmanager.reports.SupListReport;
import com.custmanager.util.CustUtil;
import com.custmanager.view.SupplierView;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.view.SupplierListView;
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
public class SupplierButtonController implements ActionListener {

    private SupplierVO supplierModel;
    private SupplierView supplierView;
    private SupplierListView supListPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("New")) {
            supplierView.initTextFields();
            supplierView.initButtonsForNew();
            supplierModel.setSupplier(new ElegantSupplier());
            supplierModel.setAddressXML(new AddressXML("", "", "", 0l, 0l, "", "", 0l, 0l, 0l));
            supplierView.tableSuppliers.setRowSorter(new TableRowSorter(supplierView.tableSuppliers.getModel()));
//            sortTableData();
            supplierView.searchText.setEnabled(false);
            supplierView.tableSuppliers.setEnabled(false);
        } else if (e.getActionCommand().contains("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(supplierView, "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
                supplierView.tableSuppliers.setRowSorter(new TableRowSorter(supplierView.tableSuppliers.getModel()));
            }

        } else if (e.getActionCommand().contains("Print")) {
            showDialogSuppList();
        } else if (e.getActionCommand().contains("Save")) {
            if (saveUpdate()) {
                supplierView.initTextFields();
                supplierView.initButtons();
                supplierView.tableSuppliers.setRowSorter(new TableRowSorter(supplierView.tableSuppliers.getModel()));
                supplierView.countryCombo.setSelectedIndex(0);
//                sortTableData();
            }
        } else if (e.getActionCommand().contains("Delete")) {
            if (CustUtil.confirmDelete(supplierView)) {
                if (delete()) {
                    supplierView.initTextFields();
                    supplierView.initButtons();
                    supplierView.tableSuppliers.setRowSorter(new TableRowSorter(supplierView.tableSuppliers.getModel()));
                    supplierView.countryCombo.setSelectedIndex(0);
//                    sortTableData();
                } else {
                    CustUtil.showMessageDialogue("Sorry !!! Could Not Delete Supplier. Transactions Exist ");
                }
            }
        } else if (e.getActionCommand().contains("Discard")) {
            supplierView.initTextFields();
            supplierView.initButtons();
            supplierView.tableSuppliers.setRowSorter(new TableRowSorter(supplierView.tableSuppliers.getModel()));
            supplierView.countryCombo.setSelectedIndex(0);
//            sortTableData();
            supplierView.searchText.setEnabled(true);
        }
        supplierView.setTableColWidths();
        if (supplierView.tableSuppliers.getRowCount() != 0) {
            sortTableData();
        }

    }

    void sortTableData() {
        DefaultRowSorter sorter = ((DefaultRowSorter) supplierView.tableSuppliers.getRowSorter());
        if (sorter != null && sorter.getModelRowCount() > 0) {
            ArrayList list = new ArrayList();
            list.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
            sorter.setSortKeys(list);
            sorter.sort();
        }
    }

    public void initAllFields(boolean initCountry) {
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
        FilterCriteria filterCriteria = null;
        CountryManager countryManager = (CountryManager) AppManager.getInstance().getViewManagerList().get("countryManager");
        SupplierManager supManager = (SupplierManager) AppManager.getInstance().getViewManagerList().get("suppManager");
        try {
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("disabled");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.add(filterCriteria);
                queryCriteria.setFilterCriteria(filterCriteriaList);                
            }
            if (initCountry) {
                PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
                ArrayList<ElegantCountry> elegantCountryList = countryManager.getCountryList();
                this.supplierModel.setCountryList(elegantCountryList);
                if (!queryCriteria.getFilterCriteria().isEmpty()) queryCriteria.getFilterCriteria().remove(0);
            }
            if (PersistanceManager.getInstance().getElegantUser().getAccountType() != 2) {            
                filterCriteria = new FilterCriteria();
                filterCriteria.setFilterFieldName("frozen");
                filterCriteria.setFilterCondition("=");
                filterCriteria.setFilterFieldValue("0");
                filterCriteriaList.add(filterCriteria);
                queryCriteria.setFilterCriteria(filterCriteriaList);                
            }
            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("supID");
            sortCriteria.setSortDirection("DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);

            ArrayList<ElegantSupplier> elegantSupplierList = supManager.getAllSupplier(PersistanceManager.getInstance().getElegantUser());
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);

            this.supplierModel.setSupList(elegantSupplierList);
            this.supplierView.setTableColWidths();
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            getSupplierView().setSupplierTableCellRenderer(customerTableCellRenderer);

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
        ArrayList<ElegantSupplier> supList = new ArrayList<ElegantSupplier>();
        SupplierManager supManager = (SupplierManager) AppManager.getInstance().getViewManagerList().get("suppManager");
        try {
            ElegantSupplier sup = updateSupplier(supplierModel.getSupplier());
            supList.add(sup);
            supManager.saveOrUpdateSupplier(supList);

            ArrayList<ElegantSupplier> elegantSupplierList = supManager.getAllSupplier(PersistanceManager.getInstance().getElegantUser());
            this.supplierModel.setSupList(elegantSupplierList);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            this.supplierView.setSupplierTableCellRenderer(customerTableCellRenderer);

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
        SupplierManager supManager = (SupplierManager) AppManager.getInstance().getViewManagerList().get("suppManager");
        try {
            ElegantSupplier sup = supplierModel.getSupplier();
            if (supManager.deleteSupplier(sup)) {
                supplierModel.getSupList().remove(sup);
                deleted = true;
            }
        } catch (Exception e) {
            System.out.println("delete " + e.getMessage());
        }
        return deleted;
    }

    private boolean validateData() {
        boolean validated = false;
        if (supplierView.supplierIdField.getText().equals("") || supplierView.nameField.getText().equals("")
                || supplierView.emailField.getText().equals("") || supplierView.emailField.getText().equals("")) {

            CustUtil.showMessageDialogue("Fields Highlighted in Blue are manditory");
            return validated;
        }
        validated = true;
        return validated;
    }

    private ElegantSupplier updateSupplier(ElegantSupplier sup) {
        sup.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
        if (sup.getUserID() == 0) {
            sup.setUserID(PersistanceManager.getInstance().getElegantUser().getUserID());
        }
        sup.setSupID(Long.parseLong((supplierView.supplierIdField.getText()).equals("") ? "0" : supplierView.supplierIdField.getText()));
        sup.setSupName(supplierView.nameField.getText());
        sup.setCreditLimit(Double.parseDouble(supplierView.creditLimitField.getText()));
        sup.setPaymentTerms(Integer.parseInt(supplierView.creditDaysField.getText()));
        sup.setFrozen(supplierView.statusCheckBox.isSelected() ? 1 : 0);
        if (sup.getSupID() == 0) {
            sup.setCreateDate(new Date());
            sup.setFrozen(0);
        }

        AddressXML addressXMLVO = updateXML(supplierModel.getAddressXML());
        String addressXML = CustUtil.getXStreamInstance().toXML(addressXMLVO);

        sup.setAddressesXML(addressXML);
        return sup;
    }

    private AddressXML updateXML(AddressXML addressXML) {
        addressXML.setAddress(supplierView.addressField.getText());
//        System.out.println(((ElegantCountry) supplierView.countryCombo.getSelectedItem()).getCountryID());
        addressXML.setCountry(((ElegantCountry) supplierView.countryCombo.getSelectedItem()).getCountryID());
        addressXML.setCity(supplierView.cityField.getText());
        addressXML.setState(supplierView.stateField.getText());
        if (!supplierView.zipField.getText().equals("")) {
            addressXML.setPin(Long.parseLong(supplierView.zipField.getText()));
        }
        if (!supplierView.telNoField.getText().equals("")) {
            addressXML.setTelephone(Long.parseLong(supplierView.telNoField.getText()));
        }
        if (!supplierView.mobileNoField.getText().equals("")) {
            addressXML.setMobile(Long.parseLong(supplierView.mobileNoField.getText()));
        }
        addressXML.setEmailId(supplierView.emailField.getText());
        addressXML.setWebSite(supplierView.websiteField.getText());
        return addressXML;
    }

    private ArrayList<ElegantSupplier> getAllSuppliersForReport(Date frDt, Date toDt, boolean isActive, int crDays, double crLimit, int sortField, int sortDir) {
        ArrayList<ElegantSupplier> elegantSupplierRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        SupplierManager supManager = (SupplierManager) AppManager.getInstance().getViewManagerList().get("suppManager");
        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("createDate");
            filterCriteria.setFilterCondition(">=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(frDt));

            filterCriteriaList.add(filterCriteria);
            System.out.println(frDt + " - " + filterCriteria.getFilterFieldValue());

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("createDate");
            filterCriteria.setFilterCondition("<=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(toDt));
            filterCriteriaList.add(filterCriteria);
            System.out.println(toDt + " - " + filterCriteria.getFilterFieldValue());

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
                sortCriteria.setSortFieldName("supID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("supName");
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
//            ServiceControl sc = supManager.getServiceControl();
//            sc.setQueryCriteria(queryCriteria);
//            custManager.setServiceControl(sc);

            elegantSupplierRepList = supManager.getAllSupplier(PersistanceManager.getInstance().getElegantUser());
            supplierModel.setSupRepList(elegantSupplierRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
            System.out.println("getAllSuppliersForReport " + e.getMessage());
        }

        return elegantSupplierRepList;
    }

    void showDialogSuppList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (this.supListPanel == null) {
            this.supListPanel = new SupplierListView();
        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, supListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = supListPanel.frmDtField.getDate();
                Date toDt = supListPanel.toDtField.getDate();
                boolean supActive = (supListPanel.custActive.isSelected() ? supListPanel.custActive.isSelected() : false);
                int crDays = Integer.parseInt(supListPanel.repCrDays.getText());
                double crLimit = Double.parseDouble(supListPanel.repCrLimit.getText());
                int sortField = (supListPanel.idSort.isSelected() ? 1 : (supListPanel.nameSort.isSelected() ? 2 : (supListPanel.crLimitSort.isSelected() ? 3 : 4)));
                int sortDir = (supListPanel.ascDirection.isSelected() ? 1 : 2);
                int rowHeight = new Integer(supListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(supListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(supListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(supListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(supListPanel.reportSetup.rightMarginField.getText());
                boolean createBorder = supListPanel.reportSetup.createBorder.isSelected();
                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(supListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                supplierView.setCursor(hourglassCursor);
                supListPanel.setCursor(hourglassCursor);
                getAllSuppliersForReport(frDt, toDt, supActive, crDays, crLimit, sortField, sortDir);
                if (supplierModel.getSupRepList() == null || supplierModel.getSupRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(supListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    supListPanel.setCursor(hourglassCursor);
                    SupListReport supListReport = new SupListReport(supplierModel.getSupRepList(), frDt, toDt, supActive, createBorder, crDays, crLimit, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = supListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                supplierView.setCursor(hourglassCursor);
                supListPanel.setCursor(hourglassCursor);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * @return the supplierModel
     */
    public SupplierVO getSupplierModel() {
        return supplierModel;
    }

    /**
     * @param supplierModel the supplierModel to set
     */
    public void setSupplierModel(SupplierVO supplierModel) {
        this.supplierModel = supplierModel;
    }

    /**
     * @return the supplierView
     */
    public SupplierView getSupplierView() {
        return supplierView;
    }

    /**
     * @param supplierView the supplierView to set
     */
    public void setSupplierView(SupplierView supplierView) {
        this.supplierView = supplierView;
    }

}
