package com.inder.customcomponents;

import com.custmanager.view.GradientPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ITitlePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    protected JPanel northPanel;
    protected JLabel label;
    GradientPanel titlePanel;
    private static final int N = 32;

    /**
     * Constructs a titled panel.
     *
     * @param title the title
     * @param icon
     * @param content the JComponent that contains the content
     * @param outerBorder the outer border
     */

    public ITitlePanel(String title, Icon icon, JComponent content, Border outerBorder) {
        if (outerBorder==null) {
            this.setBorder(BorderFactory.createEmptyBorder(N, N, N, N));
        } else {
            this.setBorder(outerBorder);
        }
        setLayout(new BorderLayout());
        label = new JLabel(title, icon, JLabel.LEADING);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);

        titlePanel = new GradientPanel(Color.MAGENTA);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(label, BorderLayout.NORTH);
        int borderOffset = 2;
        if (icon == null) {
            borderOffset += 1;
        }
        titlePanel.setBorder(BorderFactory.createEmptyBorder(borderOffset, 4, borderOffset, 1));
        add(titlePanel, BorderLayout.NORTH);

        GradientPanel northPanel = new GradientPanel(Color.blue);
        northPanel.setLayout(new BorderLayout());
        northPanel.add(content, BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        add(northPanel, BorderLayout.CENTER);

        if (outerBorder == null) {
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
            setBorder(BorderFactory.createCompoundBorder(outerBorder,
                    BorderFactory.createLineBorder(Color.GRAY)));
        }
    }

    public void setTitle(String label, Icon icon) {
        this.label.setText(label);
        this.label.setIcon(icon);
    }

}
