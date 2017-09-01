/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Inderjit Singh Sanhotra
 */
public class WaitPanel extends JDialog implements Runnable{
    
    private Thread thrdMain;
    @Override
    public void run() {
        try {
            this.setVisible(true);
        } catch (Exception e) {
            
        }
    }

    public WaitPanel (JLabel label) {
        super.add(label);
        super.setModal(true);
        init();
    }
    
    private void init () {
        int width = 250;
        int height = 55;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        this.setBounds(x, y, width, height);
    }

    private Thread getMainThread() {
        if (this.thrdMain == null) {
            this.thrdMain = new Thread(this);
        }
        return this.thrdMain;
    }
    
    public void start() {
        this.getMainThread().start();

    }
    public void stop() {
        this.setVisible(false);
        this.thrdMain = null;
    }

}
