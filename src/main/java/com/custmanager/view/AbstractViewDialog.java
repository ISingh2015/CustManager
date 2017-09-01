/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import com.custmanager.AppManager;
import com.custmanager.CustFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRootPane;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author Inderjit
 */
public abstract class AbstractViewDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    public AbstractViewDialog() {
        super.setLocationRelativeTo(null);

        super.setUndecorated(true);
        super.getRootPane().setWindowDecorationStyle(
                JRootPane.FRAME
        );
        setLookAndFeelFrame();
    }
    void setLookAndFeelFrame() {
        DefaultMetalTheme z
                = new DefaultMetalTheme() {
                    //inactive title color
                    public ColorUIResource
                    getWindowTitleInactiveBackground() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    //active title color
                    public ColorUIResource
                    getWindowTitleBackground() {
                        return new ColorUIResource(java.awt.Color.GREEN);
                    }

                    //start ActiveBumps
                    public ColorUIResource
                    getPrimaryControlHighlight() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getPrimaryControlDarkShadow() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getPrimaryControl() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }
                //end ActiveBumps

                    //start inActiveBumps
                    public ColorUIResource
                    getControlHighlight() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getControlDarkShadow() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getControl() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }
                    //end inActiveBumps

                };

        MetalLookAndFeel.setCurrentTheme(
                z
        );

    }

    public AbstractViewDialog(CustFrame parent, boolean modal) {
        super(parent, modal);
//        super.setBackground(new Color(173, 174, 92));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                AppManager appManager = AppManager.getInstance();
                appManager.getViewList().clear();
                System.exit(0);
            }
        });
    }

    abstract void updateFieldsOnView();
}
