package screens.common;

import javax.swing.*;
import java.awt.*;

public class StocksListCellRenderer extends JLabel implements ListCellRenderer<String> {
    private int hoveredIndex = -1;

    public StocksListCellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        StocksJList stocksList = (StocksJList) list;

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else if (index == hoveredIndex) {
            setBackground(stocksList.getHoveredColor());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setText(value);

        return this;
    }

    public void setHoveredIndex(int index) {
        hoveredIndex = index;
    }

    public int getHoveredIndex() {
        return hoveredIndex;
    }
}
