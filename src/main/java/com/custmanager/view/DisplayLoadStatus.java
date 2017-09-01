package com.custmanager.view;

import javax.swing.*;
import java.awt.*;

public class DisplayLoadStatus extends JDialog implements Runnable {

    private Thread thrdMain;
    public JProgressBar progressBar;
    public JButton jButtonCancel;
    public JButton jButtonOK;
    public JLabel jLabelMessageInfo;
    public String strMessage;
    private int maxCount;
    private int totCount = 0;

    public DisplayLoadStatus(Frame ofrmParent) {
        super(ofrmParent, "Elegant Systems", false);
        super.setUndecorated(true);
        super.setOpacity(0.8f);
        init();
    }

    public DisplayLoadStatus() {
        this(RootFrame.getRootFrame());
    }

    protected void init() {
        // set the window's bounds, centering the window
        int width = 500;
        int height = 105;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        this.setBounds(x, y, width, height);

        JPanel jpnlCancel = new JPanel(new FlowLayout());
        this.jButtonOK = new JButton("OK");
        this.jButtonOK.setEnabled(false);
        jpnlCancel.add(this.jButtonOK, BorderLayout.CENTER);

        this.jButtonCancel = new JButton("Cancel");
        jpnlCancel.add(this.jButtonCancel, BorderLayout.CENTER);

        jpnlCancel.setPreferredSize(new Dimension(50, 35));

        this.progressBar = new JProgressBar(0, 100);
        this.progressBar.setPreferredSize(new Dimension(100, 20));
        this.progressBar.setValue(0);
        this.progressBar.setStringPainted(true);

        this.jLabelMessageInfo = new JLabel("Initializing...");
        this.jLabelMessageInfo.setFont(new Font(this.jLabelMessageInfo.getFont().getFamily(), Font.BOLD, 12));

        this.jLabelMessageInfo.setHorizontalAlignment(JLabel.CENTER);

        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(this.jLabelMessageInfo, BorderLayout.NORTH);
        this.getContentPane().add(this.progressBar, BorderLayout.CENTER);
        this.getContentPane().add(jpnlCancel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false);
    }

    /**
     *
     * sets the total count
     *
     */
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

    public void setValue(int iintValue) {
        this.progressBar.setValue(iintValue);
    }

    /**
     *
     * sets things to 0
     *
     */
    public void reset() {
        this.totCount = 0;
        this.progressBar.setValue(0);
    }

    /**
     *
     * sets the display message
     *
     */
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

    public void start() {
        this.getMainThread().start();

    }

    /**
     *
     * shuts down the thread
     *
     */
    public void stop() {
        this.setVisible(false);

        this.thrdMain = null;
    }

    /**
     *
     * run the diplay status
     *
     */
    public void run() {
        try {
            this.setValue(0);
            this.setVisible(true);

        } catch (Exception exp) {
        }
    }

    /**
     *
     * returns the main thread
     *
     */
    private Thread getMainThread() {
        if (this.thrdMain == null) {
            this.thrdMain = new Thread(this);
        }

        return this.thrdMain;
    }

    public static void main(String[] straArgs) throws Exception {
        DisplayLoadStatus clsUploadDisplayStatus = new DisplayLoadStatus();
        clsUploadDisplayStatus.setVisible(true);
        clsUploadDisplayStatus.start();
        for (int count = 0; count <= 100; count++) {
            clsUploadDisplayStatus.updateCount();
            Thread.sleep(100);
        }
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
}
