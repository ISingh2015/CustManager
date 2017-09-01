package com.custmanager;

import com.inder.customcomponents.IFadingPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Example {

    IFadingPanel glassPane = new IFadingPanel();
    JFrame f = new JFrame();

    public static void main(String[] args) throws Exception {
        Example ex = new Example();
        ex.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ex.f.setGlassPane(ex.glassPane);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new java.awt.GridLayout(10, 10));
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                JButton jButton = new JButton("Fading");
                jButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ex.glassPane.beginFade();
                    }
                });
                contentPane.add(jButton);
            }
        }
        ex.f.setContentPane(contentPane);

        ex.f.pack();
        ex.f.setVisible(true);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ex.glassPane.setVisible(true);
    }
}
