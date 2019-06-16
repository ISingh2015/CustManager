package com.custmanager;

import com.lowagie.text.Font;
import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;
/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */

public class CustFrame extends JFrame {

    public CustFrame() {
        super.setLocationRelativeTo(null);

        super.setUndecorated(true);
        super.getRootPane().setWindowDecorationStyle(
                JRootPane.FRAME
        );
        setLookAndFeelFrame();
        super.setResizable(true);
    }

    private void setLookAndFeelFrame() {
        DefaultMetalTheme defaultMetalTheme
                = new DefaultMetalTheme() {
                    //inactive title color
                    @Override
                    public ColorUIResource
                    getWindowTitleInactiveBackground() {
                        return new ColorUIResource(java.awt.Color.LIGHT_GRAY);
                    }

                    //active title color
                    public ColorUIResource
                    getWindowTitleBackground() {
                        return new ColorUIResource(java.awt.Color.GREEN);
                    }

                    //start ActiveBumps
                    public ColorUIResource
                    getPrimaryControlHighlight() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getPrimaryControlDarkShadow() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getPrimaryControl() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }
                //end ActiveBumps

                    //start inActiveBumps
                    public ColorUIResource
                    getControlHighlight() {
                        return new ColorUIResource(new Color(0,255,255)); // light blue
                    }

                    public ColorUIResource
                    getControlDarkShadow() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }

                    public ColorUIResource
                    getControl() {
                        return new ColorUIResource(java.awt.Color.orange);
                    }
                    //end inActiveBumps

                    @Override
                    public ColorUIResource getFocusColor() {
                        return new ColorUIResource(new Color(0,255,255));
                    }

                    // Font section
                    @Override
                    public FontUIResource getControlTextFont() {
                        return new FontUIResource("Arial", Font.ITALIC, 10);
                    }

                    @Override
                    public FontUIResource getSystemTextFont() {
                        return new FontUIResource("Arial", Font.BOLDITALIC, 10);
                    }

                    @Override
                    public FontUIResource getUserTextFont() {
                        return new FontUIResource("Arial", Font.BOLD, 10);
                    }

                    @Override
                    public FontUIResource getWindowTitleFont() {
                        return new FontUIResource("Arial", Font.BOLD, 12);
                    }

                    @Override
                    public FontUIResource getMenuTextFont() {
                        return new FontUIResource("Arial", Font.NORMAL, 10);
                    }

                    // end Font Section
                };
        MetalLookAndFeel.setCurrentTheme(defaultMetalTheme
        );

    }

//    public static void main_helper(String args[]) {
////        JFrame f = new JFrame();
//        CustFrame f = new CustFrame();
//        f.setDefaultCloseOperation(
//                JFrame.DISPOSE_ON_CLOSE
//        );
//        f.setTitle("Zed Application");
//        f.setSize(300, 300);
////        f.setTitle("Test Frame");
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        panel.setBackground(java.awt.Color.white);
//        panel.add(new JLabel("Enter Some Text Label"));
//        panel.add(new JTextField(10));
//        panel.add(new JButton("Click Me"));
//        f.setContentPane(panel);
//
//        try {
//            UIManager.setLookAndFeel(
//                    new MetalLookAndFeel()
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        SwingUtilities.updateComponentTreeUI(f);
//
//        f.setVisible(true);
//        f.setLocationRelativeTo(null);
//    }

//    public static void main(final String args[]) {
//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    public void run() {
//                        main_helper(args);
//                    }
//                }
//        );
//    }
}
