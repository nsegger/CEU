package screens.common;

import framework.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ProductsJTable extends JTable implements MouseMotionListener {
    private Color hoveredColor;
    ProductsTableCellRenderer renderer;

    public ProductsJTable() {
        this(new ProductsTableCellRenderer());
    }

    public ProductsJTable(ProductsTableCellRenderer renderer) {
        this(renderer, new Color(48, 48, 48));
    }

    public ProductsJTable(ProductsTableCellRenderer renderer, Color hoveredColor) {
        this.hoveredColor = hoveredColor;
        this.renderer = renderer;

        setDefaultRenderer(String.class, renderer);
        setSelectionBackground(new Color(36,36,36));
        setRowHeight(45);

        addMouseMotionListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                renderer.setHoveredRow(-1);
                repaint();
            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        isItemBeingHovered(e);
    }

    private void isItemBeingHovered(MouseEvent e) {
        int row = rowAtPoint(e.getPoint());

        if (row != getSelectedRow() && row != renderer.getHoveredRow()) {
            renderer.setHoveredRow(row);
            repaint();
        }
    }

    public void setHoveredColor(Color hoveredColor) {
        this.hoveredColor = hoveredColor;
    }

    public Color getHoveredColor() {
        return hoveredColor;
    }
}
