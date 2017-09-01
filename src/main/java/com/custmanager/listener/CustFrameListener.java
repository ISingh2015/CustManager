/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.listener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Inderjit Singh Sanhotra
 */
public class CustFrameListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("-> resized " + e.getSource().toString() );
    }

    @Override
    public void componentMoved(ComponentEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
