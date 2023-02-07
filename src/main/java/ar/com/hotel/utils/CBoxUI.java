package ar.com.hotel.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class CBoxUI extends BasicComboBoxUI {

    Color c = new Color(0, 0, 0);

    public static ComboBoxUI createUI(JComponent com) {
        return new CBoxUI();
    }

    @Override
    protected JButton createArrowButton() {
        JButton btn = new JButton();
        btn.setBorder(BorderFactory.createLineBorder(c, 4));
        btn.setContentAreaFilled(true);
        return btn;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.setColor(c);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    protected ListCellRenderer<Object> createRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                list.setSelectionBackground(c);

                return this;
            }

        };
    }
}
