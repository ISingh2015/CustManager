package com.inder.customcomponents.render;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class IListRenderer extends JLabel implements ListCellRenderer<Object>{
    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return this;
    }
    
}
