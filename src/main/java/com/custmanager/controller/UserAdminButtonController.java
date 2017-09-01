/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.SortCriteria;
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserAccess;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.CountryManager;
import com.cust.persistance.managers.LoginManager;
import com.cust.persistance.managers.UserAdminManager;
import com.custmanager.AppManager;
import com.custmanager.model.UserAdminVO;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.reports.UserAdminListReport;
import com.custmanager.util.CustUtil;
import com.custmanager.util.EmailValidator;
import com.custmanager.view.OKCancelDialogPrint;
import com.custmanager.view.UserAdminListView;
import com.custmanager.view.UserAdminView;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
public class UserAdminButtonController implements ActionListener {

    private UserAdminVO userAdminModel;
    private UserAdminView userAdminView;
    private UserAdminListView userAdminListPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("New")) {
            getUserAdminView().initTextFields();
            getUserAdminView().initButtonsForNew();
            getUserAdminModel().setElegantUser(new ElegantUser());

            getUserAdminView().tableUsers.setRowSorter(new TableRowSorter(getUserAdminView().tableUsers.getModel()));
            getUserAdminView().searchText.setEnabled(false);
            getUserAdminView().tableUsers.setEnabled(false);
        } else if (e.getActionCommand().contains("refreshTable")) {
            int option = JOptionPane.showConfirmDialog(getUserAdminView(), "Changes Will Be Lost", CustUtil.APPNAME, JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                initAllFields(false);
            }

        } else if (e.getActionCommand().contains("Print")) {
            showDialogUserList();
        } else if (e.getActionCommand().contains("Save")) {
            if (saveUpdate()) {
                getUserAdminView().initTextFields();
                getUserAdminView().initButtons();
                getUserAdminModel().setElegantUser(new ElegantUser());
                getUserAdminView().tableUsers.setRowSorter(new TableRowSorter(getUserAdminView().tableUsers.getModel()));
//                sortTableData();
            }

        } else if (e.getActionCommand().contains("Delete")) {
            if (CustUtil.confirmDelete(getUserAdminView())) {
                if (delete()) {
                    getUserAdminView().initTextFields();
                    getUserAdminView().initButtons();
                    getUserAdminModel().setElegantUser(new ElegantUser());
                    getUserAdminView().tableUsers.setRowSorter(new TableRowSorter(getUserAdminView().tableUsers.getModel()));
//                    sortTableData();
                } else {
                    CustUtil.showMessageDialogue("We are Sorry !!! Could Not Delete User. Transactions Exist ");
                }
            }

        } else if (e.getActionCommand().contains("Discard")) {
            getUserAdminView().initTextFields();
            getUserAdminView().initButtons();
            getUserAdminModel().setElegantUser(new ElegantUser());
            getUserAdminView().tableUsers.setRowSorter(new TableRowSorter(getUserAdminView().tableUsers.getModel()));
//            sortTableData();
            getUserAdminView().searchText.setEnabled(true);
        } else if (e.getActionCommand().equalsIgnoreCase("selectUnselectAll")) {
            CustUtil.toggleCheckBoxSelect(getUserAdminView().rightsPanel);
        }
        getUserAdminView().setTableColWidths();
        if (getUserAdminView().tableUsers.getRowCount() != 0) {
            sortTableData();
        }
    }

    void sortTableData() {
        DefaultRowSorter sorter = ((DefaultRowSorter) getUserAdminView().tableUsers.getRowSorter());
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
    public UserAdminView getUserAdminView() {
        return userAdminView;
    }

    /**
     * @param userAdminView the userAdminView to set
     */
    public void setUserAdminView(UserAdminView userAdminView) {
        this.userAdminView = userAdminView;
    }

    public void initAllFields(boolean initCountry) {
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<>();
        QueryCriteria queryCriteria = new QueryCriteria();

        CountryManager countryManager = (CountryManager) AppManager.getInstance().getViewManagerList().get("countryManager");
        UserAdminManager userAdminManager = (UserAdminManager) AppManager.getInstance().getViewManagerList().get("userAdminManager");
        try {
            if (initCountry) {
                PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
                ArrayList<ElegantCountry> elegantCountryList = countryManager.getCountryList();
                this.getUserAdminModel().setCountryList(elegantCountryList);
            }
            SortCriteria sortCriteria = new SortCriteria();
            sortCriteria.setSortFieldName("userID");
            sortCriteria.setSortDirection("DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setFilterCriteria(null);
            queryCriteria.setSortCriteria(sortCriteriaList);

            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
//            ServiceControl sc = userAdminManager.getServiceControl();
//            sc.setQueryCriteria(queryCriteria);

            ArrayList<ElegantUser> elegantUserList = userAdminManager.getAllUsers(PersistanceManager.getInstance().getElegantUser());
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
            getUserAdminModel().setElegantUserList(elegantUserList);
            getUserAdminView().setTableColWidths();
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            getUserAdminView().setUserTableCellRenderer(customerTableCellRenderer);

        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
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
        ArrayList<ElegantUser> userList = new ArrayList<>();
        UserAdminManager userAdminManager = (UserAdminManager) AppManager.getInstance().getViewManagerList().get("userAdminManager");

        try {
            ElegantUser userAdmin = updateUser(getUserAdminModel().getElegantUser());
            userList.add(userAdmin);
            userAdminManager.saveOrUpdateUser(userList);

            ArrayList<ElegantUser> elegantUserList = userAdminManager.getAllUsers(PersistanceManager.getInstance().getElegantUser());
            getUserAdminModel().setElegantUserList(elegantUserList);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            getUserAdminView().setUserTableCellRenderer(customerTableCellRenderer);

            getUserAdminModel().setElegantUser(userAdmin);
            if (PersistanceManager.getInstance().getElegantUser().getUserID() == userAdmin.getUserID()) {
                PersistanceManager.getInstance().setElegantUser(userAdmin);
            }
            saved = true;

        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
        }
        return saved;
    }

    private ElegantUser updateUser(ElegantUser userAdmin) {
        String countryName = "";
        int division=0,roleNo=0;
        try {
            userAdmin.setCompID(PersistanceManager.getInstance().getElegantUser().getCompID());
            if (userAdmin.getUserID() == 0) {
                userAdmin.setUserID(userAdmin.getUserID());
            }
            userAdmin.setUserName(getUserAdminView().nameField.getText());
            userAdmin.setUserLoginName(getUserAdminView().userLogin.getText());
            String pwd = getUserAdminView().userPassword.getText();
            pwd = new String(Base64.getEncoder().encode(pwd.getBytes()));
            userAdmin.setUserLoginPwd(pwd);
            userAdmin.setUserAddress(getUserAdminView().addressField.getText());
            if (getUserAdminView().countryCombo.getSelectedItem() != null) {
                countryName = ((ElegantCountry) getUserAdminView().countryCombo.getSelectedItem()).getCountryName();
            }
            userAdmin.setCountry(countryName);
            userAdmin.setCity(getUserAdminView().cityField.getText());
            userAdmin.setState(getUserAdminView().stateField.getText());
            userAdmin.setPinCode(new Integer(getUserAdminView().zipField.getText()));
            userAdmin.setTelephoneNo(getUserAdminView().telNoField.getText());
            userAdmin.setMobileNo(getUserAdminView().mobileNoField.getText());
            userAdmin.setEmailId(getUserAdminView().emailField.getText());
            userAdmin.setWebSite(getUserAdminView().websiteField.getText());
            userAdmin.setAccountStatus(getUserAdminView().statusCheckBox.isSelected() ?1:0);
            if (getUserAdminView().divisionCombo.getSelectedItem() != null) {
                division = getUserAdminView().divisionCombo.getSelectedIndex();
                userAdmin.setDivision(division);
                userAdmin.setAccessInventory(1);
            }
            
            if (getUserAdminView().roleCombo.getSelectedItem() != null) {
                roleNo = getUserAdminView().roleCombo.getSelectedIndex();
                userAdmin.setRole(roleNo);
            }
            
            if (userAdmin.getAccountType() == null) {
                userAdmin.setAccountType(0);
            }
            if (userAdmin.getUserID() == 0) {
                userAdmin.setMembershipDate(new Date());
                userAdmin.setGracePeriod(30);
                userAdmin.setAccountLocked(0);
            }
            if (userAdmin.getElegantUserAccessList() == null || userAdmin.getElegantUserAccessList().isEmpty()) {
                userAdmin.setElegantUserAccessList(populateNewAccessRights(userAdmin));

            }
            for (ElegantUserAccess elegantUserAccess : userAdmin.getElegantUserAccessList()) {

                if (elegantUserAccess.getMenuId() == 1 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().countryMasCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 2 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().productMasCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 3 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().salesManMasCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 4 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().supplierMasCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 5 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().customerMasCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 6 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(true);
                } else if (elegantUserAccess.getMenuId() == 7 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().purCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 10 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().purRTNCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 9 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().salesCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 11 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().salesRTNCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 12 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().menuAdmin.isSelected());

                } else if (elegantUserAccess.getMenuId() == 15 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().countryMasRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 16 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().salesManMasRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 17 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().productMasRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 18 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().supMasRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 19 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().custMasRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 22 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().purchRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 23 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().purchRTNRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 24 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().salesRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 25 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().salesRTNRepCheck.isSelected());
                } else if (elegantUserAccess.getMenuId() == 30 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().ordervsSaleRep.isSelected());
                } else if (elegantUserAccess.getMenuId() == 31 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().pendOrderRep.isSelected());
                } else if (elegantUserAccess.getMenuId() == 32 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().pendSalesRep.isSelected());
                } else if (elegantUserAccess.getMenuId() == 33 && elegantUserAccess.getUserAccessId() < 2000) {                    
                    elegantUserAccess.setMenuAllowed(getUserAdminView().adminUserList.isSelected());                    
                } else if (elegantUserAccess.getMenuId() == 39 && elegantUserAccess.getUserAccessId() < 2000) {
                    elegantUserAccess.setMenuAllowed(getUserAdminView().elegantDoc.isSelected());                                        
                } else if (elegantUserAccess.getMenuId() == 40 && elegantUserAccess.getUserAccessId() < 2000) {         
                    elegantUserAccess.setMenuAllowed(getUserAdminView().elegantChat.isSelected());                                        
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
//            CustUtil.showErrorDialogue(e.getMessage());
        }
        return userAdmin;
    }

    private ArrayList<ElegantUserAccess> populateNewAccessRights(ElegantUser elegantUser) {
        ArrayList<ElegantUserAccess> elegantUserAccessList = new ArrayList<>();
        for (int cnt = 1; cnt <= 40; cnt++) {
            ElegantUserAccess elegantUserAccess = new ElegantUserAccess();
            elegantUserAccess.setCompID(elegantUser.getCompID());
            elegantUserAccess.setUserID(elegantUser.getUserID());
            elegantUserAccess.setUserAccessId(cnt <= 40 ? 1000 + cnt : 2000 + (cnt - 40));
            boolean accessAllowed = ((cnt==6 || cnt==39 || cnt==40) ? true: false);
            int menuId = 0;
            if (cnt <= 40) {
                menuId = cnt;
            } else {
                menuId = cnt - 40;
            }
//            if ((elegantUser.getAccountType() != null && elegantUser.getAccountType() == 0)) {
//                if (cnt != 37) accessAllowed = true;
//            } else if ((elegantUser.getAccountType() != null && elegantUser.getAccountType() != 0)) {
//                accessAllowed = true;                
//            }
            elegantUserAccess.setMenuId(menuId);
            elegantUserAccess.setMenuAllowed(accessAllowed);
            elegantUserAccessList.add(elegantUserAccess);
        }
        return elegantUserAccessList;
    }

    private boolean validateData() {
        boolean validated = false;
        if (getUserAdminView().userLogin.getText().equals("") || getUserAdminView().userPassword.getText().equals("") || getUserAdminView().userConfirmPassword.getText().equals("") || getUserAdminView().mobileNoField.getText().equals("0") || getUserAdminView().emailField.getText().equals("")) {
            CustUtil.showErrorDialogue("Fields in Blue are Manidatory");
            return validated;
        }
        if (!getUserAdminView().userPassword.getText().equals(getUserAdminView().userConfirmPassword.getText())) {
            CustUtil.showErrorDialogue("Passwords dont match");
            return validated;
        }
        if (getUserAdminView().userPassword.getText().contains(getUserAdminView().userLogin.getText())) {
            CustUtil.showErrorDialogue("Password cannot be the same as Login Name");
            return validated;

        }
        EmailValidator emailValidator = new EmailValidator();
        boolean validateEmail = (getUserAdminView().emailField.getText().length() != 0 ? emailValidator.validate(getUserAdminView().emailField.getText()) : false);
        if (!validateEmail) {
            CustUtil.showErrorDialogue("Invalid Email Address Entered");
            return validated;

        }
        try {
            LoginManager loginManager = (LoginManager) AppManager.getInstance().getViewManagerList().get("loginManager");
            ArrayList<ElegantUser> userList = loginManager.getUserByName(getUserAdminView().userLogin.getText());
            if (!userList.isEmpty()) {
                ElegantUser user = userList.get(0);
                if (user.getUserID() != getUserAdminModel().getElegantUser().getUserID()) {
                    CustUtil.showErrorDialogue("User Login is already registered");
                    return validated;
                }
            }
            userList = loginManager.getUserByEmail(this.getUserAdminView().emailField.getText());
            if (!userList.isEmpty()) {
                ElegantUser user = userList.get(0);
                if (user.getUserID() != getUserAdminModel().getElegantUser().getUserID()) {
                    CustUtil.showErrorDialogue("Email Address is already registered");
                    return validated;
                }
            }
            userList = loginManager.getUserByMobile(getUserAdminView().mobileNoField.getText());
            if (!userList.isEmpty()) {
                ElegantUser user = userList.get(0);
                if (user.getUserID() != getUserAdminModel().getElegantUser().getUserID()) {
                    CustUtil.showErrorDialogue("User Mobile No is already registered");
                    return validated;
                }
            }
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        validated = true;
        return validated;
    }

    public boolean delete() {
        boolean deleted = false;
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return deleted;
        }
        UserAdminManager userAdminManager = (UserAdminManager) AppManager.getInstance().getViewManagerList().get("userAdminManager");
        try {
            ElegantUser userAdmin = getUserAdminModel().getElegantUser();
            if (userAdminManager.deleteUser(userAdmin)) {
                getUserAdminModel().getElegantUserList().remove(userAdmin);
                deleted = true;
            }

        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
        }
        return deleted;
    }

    void showDialogUserList() {
        if (!AppManager.getInstance().isUserLoggedIn()) {
            return;
        }
        if (userAdminListPanel == null) {
            userAdminListPanel = new UserAdminListView();
        }
        OKCancelDialogPrint repDialog = new OKCancelDialogPrint(CustUtil.APPNAME, userAdminListPanel);
        int resp = repDialog.showDialog();
        if (resp == OKCancelDialogPrint.KINT_OK) {
            try {
                Date frDt = userAdminListPanel.frmDtField.getDate();
                Date toDt = userAdminListPanel.toDtField.getDate();
                boolean userActive = (userAdminListPanel.custActive.isSelected() ? userAdminListPanel.custActive.isSelected() : false);
                int sortField = (userAdminListPanel.idSort.isSelected() ? 1 : (userAdminListPanel.loginNameSort.isSelected() ? 2 : (userAdminListPanel.nameSort.isSelected() ? 3 : 4)));
                int sortDir = (userAdminListPanel.ascDirection.isSelected() ? 1 : 2);
                int rowHeight = new Integer(userAdminListPanel.reportSetup.rowHeightField.getText());
                int topMargin = new Integer(userAdminListPanel.reportSetup.topMarginField.getText());
                int bottomMargin = new Integer(userAdminListPanel.reportSetup.bottomMarginField.getText());
                int leftMargin = new Integer(userAdminListPanel.reportSetup.leftMarginField.getText());
                int rightMargin = new Integer(userAdminListPanel.reportSetup.rightMarginField.getText());

                if (frDt.after(toDt)) {
                    JOptionPane.showMessageDialog(userAdminListPanel.getRootPane().getContentPane(), "Please enter Valid From-To Dates", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                getUserAdminView().setCursor(hourglassCursor);
                userAdminListPanel.setCursor(hourglassCursor);
                getAllUsersForReport(frDt, toDt, userActive, sortField, sortDir);
                if (getUserAdminModel().getElegantUserRepList() == null || getUserAdminModel().getElegantUserRepList().isEmpty()) {
                    JOptionPane.showMessageDialog(userAdminListPanel.getRootPane().getContentPane(), "Nothing Found for Entered Criteria", CustUtil.APPNAME, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    userAdminListPanel.setCursor(hourglassCursor);
                    UserAdminListReport userAdminListReport = new UserAdminListReport(getUserAdminModel().getElegantUserRepList(), frDt, toDt, userActive, rowHeight, topMargin, bottomMargin, leftMargin, rightMargin);
                    JasperPrint jp = userAdminListReport.getReport();
                    AppManager.getInstance().showReport(jp);
                }
                hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                getUserAdminView().setCursor(hourglassCursor);
                userAdminListPanel.setCursor(hourglassCursor);

            } catch (Exception ex) {
//                ex.printStackTrace();
            }
        }

    }

    private ArrayList<ElegantUser> getAllUsersForReport(Date frDt, Date toDt, boolean isActive, int sortField, int sortDir) {
        ArrayList<ElegantUser> elegantUserRepList = null;
        QueryCriteria queryCriteria = new QueryCriteria();
        ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
        ArrayList<SortCriteria> sortCriteriaList = new ArrayList<SortCriteria>();
        UserAdminManager userAdminManager = (UserAdminManager) AppManager.getInstance().getViewManagerList().get("userAdminManager");
        try {
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("membershipDate");
            filterCriteria.setFilterCondition(">=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(frDt));
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("membershipDate");
            filterCriteria.setFilterCondition("<=");
            filterCriteria.setFilterFieldValue(new SimpleDateFormat(CustUtil.REPORTDATEFORMAT).format(toDt));
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("compID");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(new Long(PersistanceManager.getInstance().getElegantUser().getCompID()).toString());
            filterCriteriaList.add(filterCriteria);

            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("disabled");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue(isActive ? "0" : "1");
            filterCriteriaList.add(filterCriteria);
            queryCriteria.setFilterCriteria(filterCriteriaList);

            SortCriteria sortCriteria = new SortCriteria();
            if (sortField == 1) {
                sortCriteria.setSortFieldName("userID");
            }
            if (sortField == 2) {
                sortCriteria.setSortFieldName("userLoginName");
            }
            if (sortField == 3) {
                sortCriteria.setSortFieldName("userName");
            }
            sortCriteria.setSortDirection(sortDir == 1 ? "ASC" : "DESC");
            sortCriteriaList.add(sortCriteria);
            queryCriteria.setSortCriteria(sortCriteriaList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(queryCriteria);
//            ServiceControl sc = userAdminManager.getServiceControl();
//            sc.setQueryCriteria(queryCriteria);
//            userAdminManager.setServiceControl(sc);

            elegantUserRepList = userAdminManager.getAllUsers(PersistanceManager.getInstance().getElegantUser());
            getUserAdminModel().setElegantUserRepList(elegantUserRepList);
            PersistanceManager.getInstance().getServiceControl().setQueryCriteria(null);
//            sc.setQueryCriteria(null);
        } catch (Exception e) {
//            System.out.println("getAllCustomersForReport " + e.getMessage());
        }

        return elegantUserRepList;
    }

    /**
     *
     * @return the userAdminModel
     */
    public UserAdminVO getUserAdminModel() {
        return userAdminModel;
    }

    /**
     * @param userAdminModel the userAdminModel to set
     */
    public void setUserAdminModel(UserAdminVO userAdminModel) {
        this.userAdminModel = userAdminModel;
    }

}
