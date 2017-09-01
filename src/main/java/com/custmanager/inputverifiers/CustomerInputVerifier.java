/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.inputverifiers;

import com.inder.customcomponents.INumberField;
import com.inder.customcomponents.ITextField;
import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPasswordField;

/**
 *
 * @author Inderjit
 */
public class CustomerInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        boolean verified = false;
        if (input == null) {
            return verified;
        }
        if (input instanceof ITextField) {
            String inputText = ((ITextField) input).getText();
            String name = input.getName();
            if (name.equals("loginNameField") && inputText.length() < 5) {
                input.setBackground(Color.PINK);
            } else if (name.equals("passWordField") && inputText.length() < 10) {
                input.setBackground(Color.PINK);
            } else if (name.equals("emailField") && (inputText.indexOf("@") < 0 || inputText.indexOf(".") < 0) || inputText.indexOf(" ") > 0) {
                input.setBackground(Color.PINK);
            } else {
                input.setBackground(Color.white);
                verified = true;
            }
        } else if (input instanceof JPasswordField) {
            String inputText = ((JPasswordField) input).getText();
            String name = input.getName();
            if (name.equals("passWordField") && inputText.length() < 10) {
                input.setBackground(Color.PINK);
            } else {
                input.setBackground(Color.white);
                verified = true;
            }
        } else if (input instanceof INumberField) {
            String inputText = ((INumberField) input).getText();
            String name = input.getName();
            if (name.equals("mobField") && inputText.length() <5) {
                input.setBackground(Color.PINK);
            } else {
                input.setBackground(Color.white);
                verified = true;
            }
        }
        return verified;
    }

}
