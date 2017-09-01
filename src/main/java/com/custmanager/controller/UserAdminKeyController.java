/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.controller;

import com.custmanager.model.UserAdminVO;
import com.custmanager.model.UserTableModel1;
import com.custmanager.view.UserAdminView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class UserAdminKeyController implements KeyListener {

    private UserAdminVO userAdminModel;
    private UserAdminView userAdminView;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (userAdminView.searchText.getText() == null || userAdminView.searchText.getText().equals("")) {
            userAdminView.tableUsers.setRowSorter(new TableRowSorter(userAdminView.tableUsers.getModel()));
            return;
        }
        UserTableModel1 userTableModel = (UserTableModel1) userAdminView.tableUsers.getModel();
        TableRowSorter<UserTableModel1> tableRowSorter = new TableRowSorter<>(userTableModel);
        userAdminView.tableUsers.setRowSorter(tableRowSorter);
        String s = userAdminView.searchText.getText();
        s = (s != null && !s.equals("") ? "^" + s : "");
        tableRowSorter.setRowFilter(RowFilter.regexFilter(s));

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
