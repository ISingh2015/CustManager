package com.custmanager.util;

import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import com.custmanager.AppManager;
import com.custmanager.RootFrame;
import com.custmanager.images.ImagesDir;
import com.custmanager.images.ImagesDir;
import com.custmanager.view.GradientPanel;
import com.custmanager.view.UserAdminView;
import com.inder.customcomponents.ActionPanel;
import com.inder.customcomponents.CustomButton;
import com.inder.customcomponents.CustomComboBox;
import com.inder.customcomponents.CustomDate;
import com.inder.customcomponents.INumberField;
import com.inder.customcomponents.ITextField;
import com.thoughtworks.xstream.XStream;

import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.JViewport;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.CalendarUtils;

/**
 *
 * @author Inderjit
 */
public class CustUtil {

    private static XStream xStream;
    public static String REPORTDATEFORMAT = "MM/dd/yyyy HH:mm:ss", DISPLAYDATEFORMAT = "dd/MM/yyyy", NUMBERFORMAT = "###,###,###,##0.00", STATUSDATEFORMAT = "EEE yyyy MMMMM dd hh:mm:ss aaa";
    public static String APPNAME = "Elegant Systems. ", APPIMAGE = "elegant.png";
    public static long TIMERDURATION = 5000;
    public static Integer PURCHASEBILLTYPE = 100;
    public static Integer SALESBILLTYPE = 200;
    public static Integer PURCHASERTNTYPE = 300;
    public static Integer SALESRTNBILLTYPE = 400;
    public static final String PENDINGPURCHASEORDERS = "pendingpurchaseorder";
    public static final String PENDINGSALESORDERS = "pendingsalesorder";
    public static final String ORDERVSSALES = "ordervssale";
    public static final String PERFSERVICE = "PreferenceService";

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String getReportDate() {
        return new SimpleDateFormat(REPORTDATEFORMAT).format(new Date());
    }

    public static void showMessageDialogue(String message) {
        JOptionPane.showMessageDialog(RootFrame.getRootFrame(), message, APPNAME, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorDialogue(String message) {
        JOptionPane.showMessageDialog(RootFrame.getRootFrame(), message, APPNAME, JOptionPane.ERROR_MESSAGE);
    }

    public static XStream getXStreamInstance() {
        if (xStream != null) {
            xStream = null;
        }
        xStream = new XStream();
        return xStream;
    }

    public static boolean confirmDelete(GradientPanel view) {
        int optionSelected = JOptionPane.showConfirmDialog(view.getRootPane().getContentPane(), "Are you sure to Delete?", APPNAME, JOptionPane.YES_NO_OPTION);
        return optionSelected == JOptionPane.YES_OPTION ? true : false;
    }

    public static StyleBuilder elegantStyle() {
        StyleBuilder elegantStyle = new StyleBuilder(true);
        elegantStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        elegantStyle.setFont(new Font(6, Font._FONT_GEORGIA, true));
        return elegantStyle;
    }

    public static void toggleCheckBoxSelect(Component textFieldsList) {
        JPanel jPanel = (JPanel) textFieldsList;
        UserAdminView userAdmin = (UserAdminView) AppManager.getInstance().getViewsList().get("userAdmin");
        boolean toggleSelected = false;
        if (userAdmin != null) {
            toggleSelected = userAdmin.selectAllCheckBox.isSelected();
            System.out.println(toggleSelected);
        }
        Component[] textFieldsListInternal = jPanel.getComponents();
        for (Component textFieldsListInternal1 : textFieldsListInternal) {
            if (textFieldsListInternal1 instanceof JPanel) {
                toggleCheckBoxSelect(textFieldsListInternal1);
            } else if (textFieldsListInternal1 instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) textFieldsListInternal1;
                if (toggleSelected) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
            }
        }
    }

    /**
     * Initialize components on View
     *
     * @param textFieldsList
     */
    public static void initComponentsInPanel(Component textFieldsList) {
        JPanel jPanel = (JPanel) textFieldsList;
        Component[] textFieldsListInternal = jPanel.getComponents();
        for (Component textFieldsListInternal1 : textFieldsListInternal) {
            if (textFieldsListInternal1 instanceof JPanel) {
                initComponentsInPanel(textFieldsListInternal1);
            } else if (textFieldsListInternal1 instanceof JTextField) {
                JTextField jTextField = (JTextField) textFieldsListInternal1;
                jTextField.setText("");
            } else if (textFieldsListInternal1 instanceof ITextField) {
                ITextField iTextField = (ITextField) textFieldsListInternal1;
                iTextField.setText("");
                iTextField.setEnabled(true);
            } else if (textFieldsListInternal1 instanceof INumberField) {
                INumberField iNumberField = (INumberField) textFieldsListInternal1;
                iNumberField.setText("0");
            } else if (textFieldsListInternal1 instanceof JXDatePicker) {                
                JXDatePicker jXDatePicker = (JXDatePicker) textFieldsListInternal1;
                jXDatePicker.setDate(new Date());
                jXDatePicker.getMonthView().setLowerBound(null);
                jXDatePicker.getMonthView().setUpperBound(null);                
            } else if (textFieldsListInternal1 instanceof CustomComboBox) {
                CustomComboBox jComboBox = (CustomComboBox) textFieldsListInternal1;
                if (jComboBox.getModel().getSize() > 0) {
                    jComboBox.setSelectedIndex(0);
                    jComboBox.repaint();
                }

            } else if (textFieldsListInternal1 instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) textFieldsListInternal1;
                checkBox.setSelected(false);
            }
        }
    }

    public static void setActionButtonIcons(ActionPanel buttonPanel) {
        ImageIcon icon = ImagesDir.getImage("new.png");
        buttonPanel.newButton.setIcon(icon);
        icon = ImagesDir.getImage("exit.png");
        buttonPanel.deleteButton.setIcon(icon);
        icon = ImagesDir.getImage("print.png");
        buttonPanel.printButton.setIcon(icon);
        icon = ImagesDir.getImage("discard.png");
        buttonPanel.resetButton.setIcon(icon);
        icon = ImagesDir.getImage("save.gif");
        buttonPanel.saveButton.setIcon(icon);

    }

    public static void matchSize(JPanel panel) {
        panel.setSize(new Dimension(panel.getWidth(), AppManager.getInstance().getElegantInventory().getHeight() - 100));
    }

    /**
     *
     * @param comp
     * @param enableFlag Enable or disable components on view excluding the
     * authorization panel
     */
    public static void disableOrEnableForAuth(Component comp, boolean enableFlag) {
        Component[] component = null;
        if (comp instanceof JPanel) {
            JPanel jPanel = (JPanel) comp;
            component = jPanel.getComponents();
        } else if (comp instanceof JViewport) {
            JViewport jViewport = (JViewport) comp;
            component = jViewport.getComponents();

        } else if (comp instanceof JScrollPane) {
            JScrollPane jScrollPane = (JScrollPane) comp;
            component = jScrollPane.getComponents();
        }
        for (Component subComponent : component) {
            if (subComponent instanceof JPanel) {
                if (subComponent.getName() != null && subComponent.getName().equals("authPanel")) {
                    continue;
                }
                disableOrEnableForAuth(subComponent, enableFlag);
            } else if (subComponent instanceof JTextField) {
                JTextField jTextField = (JTextField) subComponent;
                jTextField.setEnabled(enableFlag);
            } else if (subComponent instanceof ITextField) {
                ITextField iTextField = (ITextField) subComponent;
                iTextField.setEnabled(enableFlag);
            } else if (subComponent instanceof INumberField) {
                INumberField iNumberField = (INumberField) subComponent;
                iNumberField.setEnabled(enableFlag);
            } else if (subComponent instanceof JViewport) {
                disableOrEnableForAuth(subComponent, enableFlag);
            } else if (subComponent instanceof JScrollPane) {
                disableOrEnableForAuth(subComponent, enableFlag);
            } else if (subComponent instanceof JTable) {
                JTable jTable = (JTable) subComponent;
                jTable.setEnabled(enableFlag);
            } else if (subComponent instanceof JXDatePicker) {
                JXDatePicker jxDatePicker = (JXDatePicker) subComponent;
                jxDatePicker.setEnabled(enableFlag);
            } else if (subComponent instanceof CustomButton) {
                CustomButton button = (CustomButton) subComponent;
                if (button.getName() != null && (button.getName().equals("refreshButton") || button.getName().equals("deleteButton") || button.getName().equals("addRow") || button.getName().equals("removeRow"))) {
                    button.setEnabled(enableFlag);
                }
            }

        }
    }

    public static void setDateLimitForAuthOrBills(CustomDate date, CustomDate date1, int days) {
        Calendar cal = date.getMonthView().getCalendar();
        CalendarUtils.startOfMonth(cal);
        date1.getMonthView().setLowerBound(cal.getTime());
        CalendarUtils.endOfMonth(cal);
        cal.add(Calendar.DAY_OF_MONTH, days); // days to add or substract
        date1.getMonthView().setUpperBound(cal.getTime());
    }
}
