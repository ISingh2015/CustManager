/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.view;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JLabel;

/**
 *
 * @author ISanhot
 */
public class LoadStatus extends javax.swing.JDialog implements Runnable {

    private Thread mainThread;
    private int maxCount = 0, totCount = 0;
    public String strMessage;


    public LoadStatus(Frame frame) {
        super(frame, "Elegant Systems", false);
        super.setUndecorated(true);
        super.setOpacity(0.9f);
        super.setModal(false);
        initComponents();        
        init();        
    }

    public LoadStatus() {
        this(RootFrame.getRootFrame());
    }    

    protected void init() {
        int width = 250;
        int height = 50;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        this.setBounds(x, y, width, height);
     
        this.progressBar.setValue(0);
        this.progressBar.setStringPainted(true);
        this.jLabelMessageInfo = new JLabel("Initializing...");
        this.jLabelMessageInfo.setFont(new Font(this.jLabelMessageInfo.getFont().getFamily(), Font.BOLD, 12));
        this.jLabelMessageInfo.setHorizontalAlignment(JLabel.CENTER);
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

        jLabelMessageInfo = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Elegant Systems");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabelMessageInfo.setBackground(new java.awt.Color(251, 243, 243));
        jLabelMessageInfo.setFont(new java.awt.Font("Arial Black", 3, 12)); // NOI18N
        jLabelMessageInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMessageInfo.setText("Initializing");
        jLabelMessageInfo.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jLabelMessageInfo, gridBagConstraints);

        progressBar.setBackground(new java.awt.Color(251, 243, 243));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(progressBar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoadStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoadStatus dialog = new LoadStatus(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelMessageInfo;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        this.setValue(0);
        this.setVisible(true);
        System.out.println("running");
    }

    public void setValue(int iintValue) {
        this.progressBar.setValue(iintValue);
    }

    public void start() {
        setMainThread(new Thread(this));
        getMainThread().start();
    }

    public void stop() {
        this.setVisible(false);
        this.getMainThread().stop();
        this.setMainThread(null);
    }

    /**
     * @return the mainThread
     */
    public Thread getMainThread() {
        return mainThread;
    }

    /**
     * @param mainThread the mainThread to set
     */
    public void setMainThread(Thread mainThread) {
        if (mainThread == null) {
            this.mainThread = null;
            return;
        }
        if (this.mainThread == null) {
            this.mainThread = mainThread;
        }
        this.mainThread = mainThread;
    }

    /**
     * @return the maxCount
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * @param maxCount the maxCount to set
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * @return the totCount
     */
    public int getTotCount() {
        return totCount;
    }

    /**
     * @param totCount the totCount to set
     */
    public void setTotCount(int totCount) {
        this.totCount = totCount;
    }

    public void updateCount() {
        this.updateCount(1);
    }

    public void updateCount(int iintValue) {
        this.totCount += iintValue;
        Integer intNo = new Integer(this.totCount);
        Double dltValue = new Double((intNo.doubleValue() / this.maxCount) * 100);
        this.progressBar.setValue(dltValue.intValue());
        this.setMessage(this.strMessage);
    }

    public void setMessage(String istrMessage) {
        this.jLabelMessageInfo.setText(istrMessage);
        this.strMessage = istrMessage;

        if (this.getMaxCount() > 0) {
            if (this.totCount > this.getMaxCount()) {
                this.totCount = this.getMaxCount();
            }

            this.jLabelMessageInfo.setText(istrMessage + "  (" + this.totCount + " out of " + this.getMaxCount() + ")");
        }

        this.repaint();
    }

    /**
     *
     * return display messages
     *
     */
    public String getMessage() {
        return strMessage;
    }

}
