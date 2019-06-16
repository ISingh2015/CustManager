package com.inder.customcomponents;

import com.custmanager.images.ImagesDir;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */

public class ITitleFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    protected JPanel northPanel;
    protected JLabel label;

    /**
     * Constructs a titled panel.
     *
     * @param title the title
     * @param content the JComponent that contains the content
     * @param outerBorder the outer border
     */
    public ITitleFrame(String title, Icon icon, JComponent content, Border outerBorder) {
        setLayout(new BorderLayout());

        ImageIcon imageIcon = ImagesDir.getImage("elegant.png");
        setIconImage(imageIcon.getImage());

        label = new JLabel(title, icon, JLabel.LEADING);
        label.setForeground(Color.WHITE);

        GradientPanel titlePanel = new GradientPanel(Color.BLACK);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(label, BorderLayout.WEST);
        int borderOffset = 2;
        if (icon == null) {
            borderOffset += 1;
        }
        titlePanel.setBorder(BorderFactory.createEmptyBorder(borderOffset, 4, borderOffset, 1));
        add(titlePanel, BorderLayout.NORTH);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(content, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        add(northPanel, BorderLayout.CENTER);

        if (outerBorder == null) {
//            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
//            setBorder(BorderFactory.createCompoundBorder(outerBorder,
//                    BorderFactory.createLineBorder(Color.GRAY)));
        }
    }

    public void setTitle(String label, Icon icon) {
        this.label.setText(label);
        this.label.setIcon(icon);
    }
        public void paintComponent(Graphics g) {
            if (isOpaque()) {
                // Color controlColor = UIManager.getColor("control");
                // Color controlColor = new Color(252, 198, 82);
                Color controlColor = new Color(99, 153, 255);
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

    private static class GradientPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private GradientPanel(Color background) {
            setBackground(background);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (isOpaque()) {
                // Color controlColor = UIManager.getColor("control");
                // Color controlColor = new Color(252, 198, 82);
                Color controlColor = new Color(99, 153, 255);
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
}
