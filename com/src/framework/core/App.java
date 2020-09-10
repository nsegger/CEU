package framework.core;

import app.product.ProductInterface;
import app.stock.StockInterface;
import app.user.UserInterface;
import com.bulenkov.darcula.DarculaLaf;
import framework.core.db.DatabaseInterface;
import framework.core.db.MySQL;
import framework.core.ui.JFrameManager;
import framework.Logger;
import screens.login.Login;


import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.sql.Connection;
import java.util.HashMap;

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

        Connection connection = mysql.getConnection();

        HashMap<String, DatabaseInterface<?> > interfaces = new HashMap<>();

        interfaces.put("user", new UserInterface(connection));
        interfaces.put("stock", new StockInterface(connection));
        interfaces.put("product", new ProductInterface(connection));
        frameManager.setInterfaces(interfaces);

        frameManager.load(new Login(frameManager));
    }

    public JFrameManager getFrameManager() {
        return frameManager;
    }
}
