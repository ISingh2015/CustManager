package com.custmanager.view;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Inderjit
 */
public class GradientPanel extends JPanel  {

    private static final long serialVersionUID = 1L;

    public GradientPanel(Color background) {
        setBackground(background);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isOpaque()) {
             Color controlColor = UIManager.getColor("control");
//             Color controlColor = new Color(252, 198, 82);
//            Color controlColor = new Color(99, 153, 255);
            int width = getWidth();
            int height = getHeight();

            Graphics2D g2 = (Graphics2D) g;
            Paint oldPaint = g2.getPaint();
            g2.setPaint(new GradientPaint(0, 0, getBackground(), width, 0,
                    controlColor));
            g2.fillRect(0, 0, width, height);
            g2.setPaint(oldPaint);
        }
    }


}
