package com.custmanager.view;

import com.custmanager.images.ImagesDir;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import com.inder.customcomponents.CustomButton;
import javax.swing.JDialog;

public class OKCancelDialogPrint extends JDialog {

    public static final int KINT_OK = 1;
    public static final int KINT_CANCEL = 0;

    protected Component printComponent;
    protected GradientPanel buttonPanel = new GradientPanel(Color.darkGray);

    protected CustomButton okButton = new CustomButton();
    protected CustomButton cancelButton = new CustomButton();

    protected int userResponse;
//    private Object imagesDir;

    public OKCancelDialogPrint(Frame frmOwner, String istrTitle, Component ocmpDisplay) {

        super(frmOwner, istrTitle, true);
        super.setIconImage(ImagesDir.getImage("elegant.png").getImage());
        super.pack();
        setResizable(false);

        this.getContentPane().setLayout(new BorderLayout());

        this.printComponent = ocmpDisplay;

        this.getContentPane().add(BorderLayout.CENTER, this.printComponent);
        this.buttonPanel.setLayout(new FlowLayout());
        //this.m_jpnlButtonShell.add(m_jpnlButtons);
        this.getContentPane().add(BorderLayout.SOUTH, this.buttonPanel);
        this.setOKCancelButtons();
        this.buttonPanel.requestFocus();
        this.pack();        
        this.setAlwaysOnTop(true);
    }

    public OKCancelDialogPrint(String istrTitle, Component ocmpDisplay) {
        this(RootFrame.getRootFrame(), istrTitle, ocmpDisplay);
    }

    protected void setOKCancelButtons() {
        try {
            buttonPanel.setBackground(Color.white);
            okButton.setText("  OK  ");
            okButton.setIcon(ImagesDir.getImage("ok.png"));
            buttonPanel.add(okButton);
            okButton.setBounds(11, 12, 96, 30);
            this.okButton.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    OKCancelDialogPrint.this.buttonOKAction();
                }
            });
            cancelButton.setText("Cancel");

            cancelButton.setIcon(ImagesDir.getImage("cancel.png"));
            buttonPanel.add(cancelButton);
            cancelButton.setBounds(117, 12, 96, 30);
            this.cancelButton.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    OKCancelDialogPrint.this.buttonCancelAction();
                }
            });

            this.getRootPane().setDefaultButton(this.okButton);
        } catch (Exception e) {
        }
    }

    protected void buttonOKAction() {
        try {
            this.userResponse = KINT_OK;

            this.setVisible(false);
        } catch (Exception exp) {
        }
    }

    protected void buttonCancelAction() {
        try {
            this.userResponse = KINT_CANCEL;
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

            intResponse = this.userResponse;
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
