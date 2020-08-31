package core;

import com.bulenkov.darcula.DarculaLaf;
import core.ui.JFrameManager;
import screens.login.Login;


import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class App {
    private final JFrameManager frameManager;

    public App() {
        frameManager = new JFrameManager();
    }

    public void run() {
        BasicLookAndFeel darcula = new DarculaLaf();
        try {
            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frameManager.load(new Login(frameManager));
    }

    public JFrameManager getFrameManager() {
        return frameManager;
    }
}
