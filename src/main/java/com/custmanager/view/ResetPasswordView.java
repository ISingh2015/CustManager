/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.cust.domain.vo.ElegantUser;
import com.cust.persistance.managers.LoginManager;
import com.custmanager.AppManager;
import com.custmanager.CustFrame;
import com.custmanager.images.ImagesDir;
import com.custmanager.util.CustUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Base64;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Inderjit Singh Sanhotra
 */
public class ResetPasswordView extends AbstractViewDialog {

    /**
     * Creates new form ResetPasswordView
     */
    public ResetPasswordView(CustFrame parent, boolean modal) {
        super(parent, modal);
        super.setResizable(false);
        super.setTitle(CustUtil.APPNAME + " - " + "Reset Password Window");
        initComponents();
        init();
    }

    public ResetPasswordView() {
        this(new CustFrame(), true);
    }

    private void init() {
        super.setIconImage(ImagesDir.getImage("elegant.png").getImage());
        displayResetFields(false);
        PlainDocument document = (PlainDocument) newPasswordField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 30) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        document = (PlainDocument) confirmPasswordField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 30) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        submitButton.setIcon(ImagesDir.getImage("ok.png"));
        cancelButton.setIcon(ImagesDir.getImage("cancel.png"));
        submitButton1.setIcon(ImagesDir.getImage("ok.png"));
        cancelButton1.setIcon(ImagesDir.getImage("cancel.png"));
        emailField.setAllowSpaceSymbol(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();
        submitButton1 = new javax.swing.JButton();
        cancelButton1 = new javax.swing.JButton();
        newPasswordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();
        errorLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mobilNoField = new com.inder.customcomponents.INumberField();
        emailField = new com.inder.customcomponents.ITextField(35,false,true,true);

        setPreferredSize(new java.awt.Dimension(669, 308));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Enter Registerd Email-Id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Enter New Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 12, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Confirm New Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 12, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        submitButton.setMnemonic('u');
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 66;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(52, 12, 0, 0);
        getContentPane().add(submitButton, gridBagConstraints);

        cancelButton.setMnemonic('c');
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(52, 4, 0, 0);
        getContentPane().add(cancelButton, gridBagConstraints);

        messageLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(0, 153, 51));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText("To Reset your password, enter your registered email address below");
        messageLabel.setMinimumSize(new java.awt.Dimension(100, 22));
        messageLabel.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 461;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(38, 12, 0, 12);
        getContentPane().add(messageLabel, gridBagConstraints);

        submitButton1.setMnemonic('u');
        submitButton1.setText("Update");
        submitButton1.setToolTipText("");
        submitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 12, 0, 0);
        getContentPane().add(submitButton1, gridBagConstraints);

        cancelButton1.setMnemonic('c');
        cancelButton1.setText("Cancel");
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(cancelButton1, gridBagConstraints);

        newPasswordField.setMinimumSize(new java.awt.Dimension(200, 22));
        newPasswordField.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(newPasswordField, gridBagConstraints);

        confirmPasswordField.setMinimumSize(new java.awt.Dimension(200, 22));
        confirmPasswordField.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(confirmPasswordField, gridBagConstraints);

        errorLabel.setForeground(new java.awt.Color(255, 0, 51));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        errorLabel.setMaximumSize(new java.awt.Dimension(236, 16));
        errorLabel.setMinimumSize(new java.awt.Dimension(100, 22));
        errorLabel.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 343;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(errorLabel, gridBagConstraints);

        jLabel4.setText("Enter Register Mob No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jLabel4, gridBagConstraints);

        mobilNoField.setText("iNumberField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 120);
        getContentPane().add(mobilNoField, gridBagConstraints);

        emailField.setText("iTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(emailField, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        if (emailField.equals(null) || emailField.getText().equals("") ) {
            errorLabel.setText("Email-Id not Entered");            
            return;
        }
        if (mobilNoField.equals(null) || mobilNoField.getText().equals("0") ) {
            errorLabel.setText("Mobile No not Entered");            
            return;
        }
        
        if (emailAddressFound()) {
            hideEmailField();
            displayResetFields(true);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    boolean emailAddressFound() {
        boolean found = false;
        if (this.emailField.getText().length() == 0 || ((this.emailField.getText().indexOf("@") < 0 || this.emailField.getText().indexOf(".") < 0) || this.emailField.getText().indexOf(" ") > 0)) {
            errorLabel.setText("Please Enter a valid Email-Id");
            return found;
        }
        try {
            LoginManager loginManager = (LoginManager) AppManager.getInstance().getViewManagerList().get("resetPasswordManaager");
            loginManager.getPreferenceList();
            LoginView loginView = (LoginView) AppManager.getLoginView();
            ArrayList<ElegantUser> userList = loginManager.getUserByEmailAndMobile(this.emailField.getText(), this.mobilNoField.getText());
            if (userList != null && !userList.isEmpty()) {
                ElegantUser user = userList.get(0);
                if (user.getAccountLocked() != 0) {
                    errorLabel.setForeground(Color.red);
                    errorLabel.setText("Password Cannot be Re-set. Account Locked");
                    dispose();
                } else {
                    errorLabel.setText("");
                    found = true;
                }
            } else {
                errorLabel.setForeground(Color.red);
                errorLabel.setText("Email and Mobile combination is Invalid or Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return found;
    }
    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButton1ActionPerformed

    private void submitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButton1ActionPerformed
        if (newPasswordField.getText().length() == 0) {
            errorLabel.setText("Please Enter New Password");
            newPasswordField.requestFocus();
            return;
        } else if (confirmPasswordField.getText().length() == 0) {
            errorLabel.setText("Please Enter Confirm Password");
            newPasswordField.requestFocus();
            return;
        } else if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("New and Confirm Passwords Do Not Match");
            return;
        } else {
            errorLabel.setText("");
        }
        try {
            LoginManager loginManager = (LoginManager) AppManager.getInstance().getViewManagerList().get("resetPasswordManaager");
            LoginView loginView = (LoginView) AppManager.getLoginView();
            ArrayList<ElegantUser> userList = loginManager.getUserByEmailAndMobile(this.emailField.getText(), this.mobilNoField.getText());
            if (userList != null && !userList.isEmpty()) {
                ElegantUser user = userList.get(0);
                if (user.getAccountLocked() != 0) {
                    errorLabel.setForeground(Color.red);
                    errorLabel.setText("");
                    loginView.errorLabel.setText("Password Cannot be Re-set. Account Locked");
                    dispose();
                }
                String encoded = new String(Base64.getEncoder().encode(this.confirmPasswordField.getText().getBytes()));
                user.setUserLoginPwd(encoded);
                loginManager.saveUserList(userList,true);
//                loginView.setChoice(0);
                this.setVisible(false);
                errorLabel.setText("");
                loginView.errorLabel.setText("Password is Re-set. Use New Password to Login");
                dispose();
            } else {
                errorLabel.setForeground(Color.red);
                errorLabel.setText("Email Id is Invalid or Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_submitButton1ActionPerformed

    void hideEmailField() {
        jLabel1.setVisible(false);
        submitButton.setVisible(false);
        cancelButton.setVisible(false);
        emailField.setVisible(false);
    }

    void displayResetFields(boolean option) {
        jLabel2.setVisible(option);
        jLabel3.setVisible(option);
        newPasswordField.setVisible(option);
        confirmPasswordField.setVisible(option);
        submitButton1.setVisible(option);
        cancelButton1.setVisible(option);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton cancelButton;
    public javax.swing.JButton cancelButton1;
    public javax.swing.JPasswordField confirmPasswordField;
    public com.inder.customcomponents.ITextField emailField;
    public javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel messageLabel;
    private com.inder.customcomponents.INumberField mobilNoField;
    public javax.swing.JPasswordField newPasswordField;
    public javax.swing.JButton submitButton;
    public javax.swing.JButton submitButton1;
    // End of variables declaration//GEN-END:variables

    @Override
    void updateFieldsOnView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
