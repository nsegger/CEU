package screens.common;

import screens.stocks.Stocks;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class StocksJList extends JList<String> implements MouseMotionListener {
    private Color hoveredColor;
    StocksListCellRenderer renderer;

    public StocksJList() {
        this(new StocksListCellRenderer());
    }

    public StocksJList(StocksListCellRenderer renderer) {
        this(renderer, new Color(48, 48, 48));
    }

    public StocksJList(StocksListCellRenderer renderer, Color hoveredColor) {
        this.hoveredColor = hoveredColor;
        this.renderer = renderer;


        setCellRenderer(renderer);
        setSelectionBackground(new Color(36,36,36));
        setFixedCellHeight(45);

        addMouseMotionListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                renderer.setHoveredIndex(-1);
                repaint();
            }
        });

        addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                System.out.println("Selected value is now: " + getSelectedValue());
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
        int index = locationToIndex(e.getPoint());

        if (index != getSelectedIndex()) {
            renderer.setHoveredIndex(index);
            repaint();
        }
    }

    public void setHoveredColor(Color hoveredColor) {
        this.hoveredColor = hoveredColor;
    }

    public Color getHoveredColor() {
        return hoveredColor;
    }

    public StocksListCellRenderer getStocksCellRenderer() {
        return renderer;
    }
}
