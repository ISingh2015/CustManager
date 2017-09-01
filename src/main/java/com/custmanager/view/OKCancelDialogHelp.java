package com.custmanager.view;

import com.custmanager.images.ImagesDir;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import com.inder.customcomponents.CustomButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class OKCancelDialogHelp extends JDialog {

    public static final int KINT_OK = 1;
    public static final int KINT_CANCEL = 0;

    protected Component m_cmpComponent;
    protected JPanel displayPanel = new JPanel();

    protected CustomButton okButton = new CustomButton();
    protected CustomButton cancelButton = new CustomButton();

    protected int m_intResponse;
    private Object imagesDir;

    public OKCancelDialogHelp(Frame frmOwner, String istrTitle, Component ocmpDisplay) {

        super(frmOwner, istrTitle, true);
        super.setIconImage(ImagesDir.getImage("elegant.png").getImage());
        setResizable(false);

        this.getContentPane().setLayout(new BorderLayout());

        this.m_cmpComponent = ocmpDisplay;

        this.getContentPane().add(BorderLayout.CENTER, this.m_cmpComponent);
        this.displayPanel.setLayout(new FlowLayout());
        //this.m_jpnlButtonShell.add(m_jpnlButtons);
        this.getContentPane().add(BorderLayout.SOUTH, this.displayPanel);
        this.setOKCancelButtons();
        this.pack();        
        this.setAlwaysOnTop(true);
    }

    public OKCancelDialogHelp(String istrTitle, Component ocmpDisplay) {
        this(RootFrame.getRootFrame(), istrTitle, ocmpDisplay);
    }

    protected void setOKCancelButtons() {
        try {
            displayPanel.setBackground(Color.white);
            okButton.setText("  OK  ");
            okButton.setIcon(ImagesDir.getImage("ok.png"));
            displayPanel.add(okButton);
            okButton.setBounds(11, 12, 96, 30);
            this.okButton.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    OKCancelDialogHelp.this.buttonOKAction();
                }
            });

            cancelButton.setText("Cancel");
            cancelButton.setIcon(ImagesDir.getImage("cancel.png"));
            displayPanel.add(cancelButton);
            cancelButton.setBounds(117, 12, 96, 30);
            this.cancelButton.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    OKCancelDialogHelp.this.buttonCancelAction();
                }
            });

            this.getRootPane().setDefaultButton(this.okButton);
        } catch (Exception e) {
        }
    }

    protected void buttonOKAction() {
        try {
            this.m_intResponse = KINT_OK;

            this.setVisible(false);
        } catch (Exception exp) {
        }
    }

    protected void buttonCancelAction() {
        try {
            this.m_intResponse = KINT_CANCEL;
            this.setVisible(false);
        } catch (Exception exp) {
        }
    }

    public void show() {
    }

    public int showDialog() {
        int intResponse = KINT_CANCEL;

        try {
            this.setupDialogPosition();
            super.show();

            intResponse = this.m_intResponse;
        } catch (Exception exp) {
        }

        return intResponse;
    }

    protected void setupDialogPosition() {
        try {
            Dimension dmnScreenDimension = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((dmnScreenDimension.width - this.getWidth()) / 2, (dmnScreenDimension.height - this.getHeight()) / 2);
        } catch (Exception e) {
        }
    }

    public void setOKButtonEnabled(boolean ibolEnabled) {
        this.okButton.setEnabled(ibolEnabled);
    }

}
