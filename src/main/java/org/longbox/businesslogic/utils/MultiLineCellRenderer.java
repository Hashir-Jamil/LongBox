package org.longbox.businesslogic.utils;

import org.longbox.persistence.entity.Comment;

import javax.swing.*;
import java.awt.*;

public class MultiLineCellRenderer extends JLabel implements ListCellRenderer<Comment> {
    public MultiLineCellRenderer() {
//        setLineWrap(true);
//        setWrapStyleWord(true);
        setOpaque(true);
        setPreferredSize(new Dimension(495, 55));
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Comment> list, Comment value, int index,
            boolean isSelected, boolean cellHasFocus) {
        setText("<html><b>" + value.getUserName() + "</b>: " + value.getMessage() + "</html>");
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        setVerticalAlignment(SwingConstants.TOP);
        return this;
    }
}
