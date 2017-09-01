package com.custmanager.view;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 *
 * @author Inderjit
 */
public class RootFrame {
    private static Frame rootFrame;

    /**
     * @return the rootFrame
     */
    public static Frame getRootFrame() {
        if (rootFrame == null ) {
            rootFrame = JOptionPane.getRootFrame();
        }
        return rootFrame;
    }

    /**
     * @param aRootFrame the rootFrame to set
     */
    public static void setRootFrame(Frame aRootFrame) {
        rootFrame = aRootFrame;
    }
    
    
}
