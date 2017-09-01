/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.caucho.hessian.HessianException;
import com.cust.domain.vo.ElegantUser;
import com.cust.persistance.CustServiceConstants;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.LoginManager;
import com.custmanager.AppManager;
import com.custmanager.model.LoginVO;
import com.custmanager.util.CustUtil;
import com.custmanager.view.LoginView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ISanhot
 */
public class LoginButtonController implements ActionListener {

    private LoginView loginView;
    private ElegantUser user = null;
    private LoginManager loginManager = null;
    private ArrayList<ElegantUser> userList;
    private int retryCnt = 5;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("signin")) {
            doLoginAction();
        } else if (e.getActionCommand().equalsIgnoreCase("password")) {
            doPassAction();
        } else if (e.getActionCommand().equalsIgnoreCase("newregistration")) {
            doRegAction();
        } else if (e.getActionCommand().equalsIgnoreCase("cancel")) {
            System.exit(0);
        }
    }

    /**
     * @return the loginView
     */
    public LoginView getLoginView() {
        return loginView;
    }

    /**
     * @param loginView the loginView to set
     */
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    private boolean doLoginAction() {
        if (retryCnt == 0 && userList.size() == 1) {
            user = userList.get(0);
            user.setAccountLocked(1);
            user.setAccountLockedTime(new Date());
            loginManager.saveUserList(userList, false);
            getLoginView().errorLabel.setText("You Account has been Locked.");
            CustUtil.showErrorDialogue(getLoginView().errorLabel.getText());
            System.exit(0);
        }

        if (getLoginView().loginName.getText().equals("") || getLoginView().passWord.getPassword().length == 0) {
            getLoginView().errorLabel.setForeground(Color.red);
            getLoginView().errorLabel.setText("Please Enter User Name & Password");
            return false;
        }
        try {
            String s = (String) getLoginView().connectServerCombo.getSelectedItem();
            if (s.isEmpty()) {
                PersistanceManager.getInstance().setLoginServer(CustServiceConstants.URL);
            } else {
                if (s.equalsIgnoreCase("elegantservers")) {
                    s = CustServiceConstants.URL;
                }
                PersistanceManager.getInstance().setLoginServer(s);
            }
            loginManager = (LoginManager) AppManager.getInstance().getViewManagerList().get("loginManager");
            LoginVO loginVO = (LoginVO) AppManager.getInstance().getViewModelList().get("loginModel");
            String errorText = "";
            String encoded = new String(Base64.getEncoder().encode(getLoginView().passWord.getText().getBytes()));
            loginManager.getPreferenceList();
            if (PersistanceManager.getInstance().getPreferenceVO() == null) {
                errorText = "Could not get Preferences... \nMaybe Server is Down. Plesae retry in a while. Exiting";
                CustUtil.showErrorDialogue(errorText);
                System.exit(0);
            }
            userList = loginManager.getUserByName(getLoginView().loginName.getText());
            if (userList.size() == 1) {
                user = userList.get(0);
                if (user.getAccountStatus() == 1) {
                    getLoginView().errorLabel.setForeground(new Color(37,230,105));
                    getLoginView().errorLabel.setText("Your Account is Frozen or not accessible");
                    return false;
                } else if (user.getAccountLocked() != 0) {
                    getLoginView().errorLabel.setForeground(new Color(37,230,105));
                    getLoginView().errorLabel.setText("Your Account is Locked");
                    return false;
                }

            } else {
                getLoginView().errorLabel.setForeground(Color.red);
                errorText = "User Does not Exist.. Please retry";
                getLoginView().errorLabel.setText(errorText);
                getLoginView().loginName.requestFocus();
                return false;

            }
            user = loginManager.dogetUserByCredentials(getLoginView().loginName.getText(), encoded);
            if (user == null && retryCnt >= 1) {
                getLoginView().errorLabel.setForeground(Color.red);                
                errorText = "User Password is Invalid.. Retries Left " + retryCnt;
                getLoginView().errorLabel.setText(errorText);
                getLoginView().passWord.requestFocus();
                retryCnt--;
                return false;
            }
            if (userList.size() == 1 && user.getAccountStatus() == 1) {
                    getLoginView().errorLabel.setForeground(new Color(37,230,105));
                getLoginView().errorLabel.setText("Your Account is Frozen or not accessible");
                return false;
            }
            if (userList.size() == 1 && user.getAccountLocked() != 0) {
                    getLoginView().errorLabel.setForeground(new Color(37,230,105));
                getLoginView().errorLabel.setText("Your Account is Locked");
                retryCnt = 5;
                return false;
            }
            if (validateData(user) && user.getAccountLocked() == 0) {
                AppManager.getInstance().setLoggedIn(true);
                loginVO.setId(user.getUserID());
                loginVO.setName(user.getUserLoginName());
                loginVO.setPassword(user.getUserLoginPwd());
                PersistanceManager.getInstance().getSessionInfo().getUserInfo().setLoginId(loginVO.getName());
                loginManager.doSaveUserLogin(user);
                getLoginView().setVisible(false);
                return true;
            }
        } catch (HessianException e) {
            getLoginView().errorLabel.setForeground(Color.red);
            getLoginView().errorLabel.setText(e.getMessage());
        } catch (Exception ex) {
            getLoginView().errorLabel.setForeground(Color.red);
            getLoginView().errorLabel.setText("User does not exist or Account is Locked");
        }
        return false;
    }

    private boolean validateData(ElegantUser user) {
        String errorText = "";
        if (user.getAccountStatus() == null || user.getMembershipDate() == null || user.getGracePeriod() == null) {
            errorText = "Error in User Account.";
            errorText += user.getAccountStatus() == null ? "\nStatus Field is Null " : "";
            errorText += user.getMembershipDate() == null ? "\nMemberShipDate is Null" : "";
            errorText += user.getGracePeriod() == null ? "\nGrace Period is Null" : "";
            errorText += "\nPlease email HelpDesk [eleganInfo@gmail.com] to get the issue resolved. ";
            CustUtil.showErrorDialogue(errorText);
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date now = calendar.getTime();

        calendar.setTime(user.getActivationDate());
        Date userMemberShipDate = calendar.getTime();

        int newGracePeriod = (int) ((now.getTime() - userMemberShipDate.getTime()) / (1000 * 60 * 60 * 24));
//        calendar.setTime(now);
//        calendar.add(Calendar.DATE, user.getGracePeriod() * -1);
        user.setGracePeriod(newGracePeriod);
        if (user.getAccountType() >= 1 && newGracePeriod > 365) {
            errorText = "The 365 day Membership Period is expired on " + userMemberShipDate.toString() + "\nPlease renew your membership by visiting www.elegantSoftware.in \nThank you for your co.operatioin.\n ";
            CustUtil.showErrorDialogue(errorText);
            System.exit(0);
        } else if (user.getAccountType() == 0 && newGracePeriod> 365) {
            errorText = "The 30 day Trial Membership Period is expired on " + userMemberShipDate.toString() + "\nPlease renew your membership by visiting www.elegantSoftware.in \nThank you for your co.operatioin.\n ";
            CustUtil.showErrorDialogue(errorText);
            System.exit(0);

        } else if (user.getAccountLocked() != 0) {
            getLoginView().errorLabel.setForeground(Color.red);
            getLoginView().errorLabel.setText("Your Account is Locked");
            retryCnt = 5;
            return false;
        } else if (user.getAccessInventory() == 0) {
            getLoginView().errorLabel.setForeground(Color.red);
            getLoginView().errorLabel.setText("You do not have access to the Inventory Module");
            return false;
        }

//        if (user.getAccountLocked() != 0) {
//            long timeInSecs = (secs - user.getAccountLockedTime().getTime()) / 1000;
//            if (timeInSecs < 10) { // retry in 10 mins=
//                errorText = "We are Sorry this account is locked due to unsuccessfull login attempts. \nAs a security meassure, Please retry after 10 minutes. Thank you for your co.operatioin.\n ";
//                CustUtil.showErrorDialogue(errorText);
////                userList.get(0).setAccountLocked(0);
////                loginManager.saveUserList(userList, false);
//
//                System.exit(0);
//            }
//        }
        return true;
    }

    private boolean doPassAction() {
        String s = (String) getLoginView().connectServerCombo.getSelectedItem();
        if (s.isEmpty()) {
            PersistanceManager.getInstance().setLoginServer(CustServiceConstants.URL);
        } else {
            if (s.equalsIgnoreCase("elegantservers")) {
                s = CustServiceConstants.URL;
            }
            PersistanceManager.getInstance().setLoginServer(s);
        }
//        this.setChoice(2); // forgot password
        getLoginView().setVisible(false);
        return true;
    }

    private boolean doRegAction() {
        String s = (String) getLoginView().connectServerCombo.getSelectedItem();
        if (s.isEmpty()) {
            PersistanceManager.getInstance().setLoginServer(CustServiceConstants.URL);
        } else {
            if (s.equalsIgnoreCase("elegantservers")) {
                s = CustServiceConstants.URL;
            }
            PersistanceManager.getInstance().setLoginServer(s);
        }
//        this.setChoice(1); // new registration
        getLoginView().setVisible(false);
        return true;
    }
}
