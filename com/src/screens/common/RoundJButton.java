package screens.common;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundJButton extends JButton {
    private Shape shape;
    private final int radius;
    private final Color color;

    public RoundJButton(int radius, Color color) {
        this.radius = radius;
        this.color = color;
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(color);
        g.fillRoundRect(0, 0, getSize().width-1, getSize().height-1, radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        }

        return shape.contains(x, y);
    }
}
