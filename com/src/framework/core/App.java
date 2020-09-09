package framework.core;

import app.user.UserInterface;
import com.bulenkov.darcula.DarculaLaf;
import framework.core.db.MySQL;
import framework.core.ui.JFrameManager;
import framework.Logger;
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

        MySQL mysql = new MySQL();

        Logger.initialization();

        UserInterface userInterface = new UserInterface();


        frameManager.load(new Login(frameManager));
    }

    public JFrameManager getFrameManager() {
        return frameManager;
    }
}
