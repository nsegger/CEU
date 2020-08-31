package core.ui;

import screens.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class JFrameManager {
    private JFrame frame;

    public void load(Screen pane) {
        load(pane, true);
    }

    public void load(Screen pane, boolean centerFrame) {
        load(pane, centerFrame, 0, 0);
    }

    public void load(Screen pane, boolean centerFrame, int width, int height) {
        if (frame != null) frame.dispose();

        frame = new JFrame(pane.getTitle());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(pane);
        frame.pack();
        frame.setResizable(false);
        frame.setIconImages(getIconList());
        frame.setVisible(true);

        if (width != 0 && height != 0) frame.setSize(width, height);
        if (centerFrame) center();
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    private ArrayList<Image> getIconList() {
        ArrayList<Image> imageList = new ArrayList<>();

        URL cloud20Url = getClass().getResource("/resources/ceu-20x12.svg");
        URL cloud40Url = getClass().getResource("/resources/ceu-40x24.svg");
        URL cloud50Url = getClass().getResource("/resources/ceu-50x30.svg");

        BufferedImage img20 = null;
        BufferedImage img40 = null;
        BufferedImage img50 = null;
        try {
            img20 = ImageIO.read(cloud20Url);
            img40 = ImageIO.read(cloud40Url);
            img50 = ImageIO.read(cloud50Url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageList.add(img20);
        imageList.add(img40);
        imageList.add(img50);

        return imageList;
    }

    public void center() {
        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dimensions.width - frame.getWidth()) / 2, (dimensions.height - frame.getHeight()) / 2);
    }
}
