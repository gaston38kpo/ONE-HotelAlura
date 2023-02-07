package ar.com.hotel.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class UtilsUI {


    public static void setTextFieldPadding(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
    }

    public static void setColorsJCalendar(com.toedter.calendar.JDateChooser jDateChooser, Color colorForeground, Color colorBackground) {
        for (Component c : jDateChooser.getComponents()) {
            ((JComponent) c).setBackground(colorBackground);
            ((JComponent) c).addPropertyChangeListener("foreground", event -> {
                if (Color.BLACK.equals(event.getNewValue())) {
                    ((JComponent) c).setForeground(colorForeground);
                }
            });
        }
    }

    public static void setColorsCombobox(JComboBox<String> jComboBox, Color colorForeground, Color colorBackground) {
        jComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setBackground(colorBackground);
                setForeground(colorForeground);
                setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                super.paint(g);
            }
        });
    }
    

}
