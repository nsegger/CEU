package core;

import com.bulenkov.darcula.DarculaLaf;
import core.ui.JFrameManager;
import screens.login.Login;


import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class App {

    public void run() {
        BasicLookAndFeel darcula = new DarculaLaf();
        try {
            UIManager.setLookAndFeel(darcula);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrameManager frameManager = new JFrameManager(new Login());
    }
}
