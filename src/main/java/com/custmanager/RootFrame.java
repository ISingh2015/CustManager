package com.custmanager;

import java.awt.*;
import javax.swing.JOptionPane;

public class RootFrame {

    private static Frame m_frmRoot;

    public synchronized static Frame getRootFrame() {
        if (m_frmRoot == null) {
            m_frmRoot = JOptionPane.getRootFrame();
        }

        return m_frmRoot;
    }

    public synchronized static void setRootFrame(Frame frmRoot) {
        m_frmRoot = frmRoot;
    }
}
