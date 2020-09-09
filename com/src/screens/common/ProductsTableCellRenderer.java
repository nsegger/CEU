package screens.common;

import framework.Logger;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ProductsTableCellRenderer extends JLabel implements TableCellRenderer {
    private int hoveredRow = -1;

    public ProductsTableCellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ProductsJTable customTable = (ProductsJTable) table;

        if (isSelected) {
            setBackground(customTable.getSelectionBackground());
            setForeground(customTable.getSelectionForeground());
        } else if (row == hoveredRow) {
            setBackground(customTable.getHoveredColor());
        } else {
            setBackground(customTable.getBackground());
            setForeground(customTable.getForeground());
        }

        setText(value.toString());

        return this;
    }

    public void setHoveredRow(int row) {
        hoveredRow = row;
        Logger.info("Hovered row is now " + hoveredRow);
    }

    public int getHoveredRow() {
        return hoveredRow;
    }

}
