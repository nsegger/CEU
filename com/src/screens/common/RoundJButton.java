package screens.common;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundJButton extends JButton {
    private Shape shape;
    private int radius;
    private Color backgroundColor;
    private Color pressedBackgroundColor;
    private Color hoverBackgroundColor;

    public RoundJButton() {
        this(5, new Color(148, 88, 214), new Color(127, 46, 214), new Color(101, 36, 171));
    }

    public RoundJButton(int radius, Color backgroundColor, Color hoverBackgroundColor, Color pressedBackgroundColor) {
        this.radius = radius;
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
        setOpaque(false);
        setContentAreaFilled(false);
        setRolloverEnabled(true);
        setForeground(Color.WHITE);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(backgroundColor);
        }

        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height-1, radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }

        return shape.contains(x, y);
    }
}
