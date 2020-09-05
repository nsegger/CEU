package screens.common;

import javax.swing.*;
import java.awt.*;

public class StocksListCellRenderer implements ListCellRenderer<String> {
    private int hoveredIndex = -1;
    private final JLabel label = new JLabel(" ", JLabel.CENTER);

    public StocksListCellRenderer() {
        label.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        StocksJList stocksList = (StocksJList) list;

        if (isSelected) {
            label.setBackground(list.getSelectionBackground());
            label.setForeground(list.getSelectionForeground());
        } else if (index == hoveredIndex) {
            label.setBackground(stocksList.getHoveredColor());
        } else {
            label.setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }

        label.setText(value);

        return label;
    }

    public void setHoveredIndex(int index) {
        hoveredIndex = index;
    }

    public int getHoveredIndex() {
        return hoveredIndex;
    }
}
