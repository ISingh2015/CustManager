/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.UserAdminVO;
import com.custmanager.view.UserAdminView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author Inderjit
 */
public class UserAdminMouseController implements MouseListener {

    private UserAdminVO userAdminModel;
    private UserAdminView userAdminView;

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        if (e.getClickCount() == 2) {
            int row = target.getSelectedRow();
            getUserAdminModel().setElegantUser(getUserAdminModel().getElegantUserList().get(target.convertRowIndexToModel(row)));
            target.setEnabled(false);
            getUserAdminView().searchText.setEnabled(false);
            String s =  getUserAdminModel().getElegantUser().getCountry();
            if (s!= null && s!= "") getUserAdminModel().getCountryComboModel().setSelectedCountryByName(s);
            getUserAdminView().initButtonsFordelete();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
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

}
