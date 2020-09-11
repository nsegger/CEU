/*
 * Created by JFormDesigner on Mon Aug 31 01:01:10 BRT 2020
 */

package screens.login;

import app.user.User;
import app.user.UserInterface;
import framework.Logger;
import framework.core.ui.JFrameManager;
import screens.Screen;
import screens.common.RoundJButton;
import screens.common.RoundJPasswordField;
import screens.common.RoundJTextField;
import screens.stocks.Stocks;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Nicholas Segger
 */
public class Login extends Screen {
    private boolean register;
    private final String loginTitle = "CEU - Controle de Estoque Universal";
    private final String registerTitle = "CEU - Cadastro";


    public Login (JFrameManager frameManager) {
        this(frameManager, false);
    }

    public Login(JFrameManager frameManager, boolean showRegister) {
        super("CEU - Controle de Estoque Universal", frameManager);
        this.register = showRegister;

        initComponents();
        registerLabel.setFocusPainted(false);
        registerLabel.setMargin(new Insets(0, 0, 0, 0));
        registerLabel.setContentAreaFilled(false);
        registerLabel.setBorderPainted(false);
        registerLabel.setOpaque(false);

        try {
            BufferedImage cloudSvg = ImageIO.read(getClass().getResource("/resources/ceu.svg"));
            logo.setIcon(new ImageIcon(cloudSvg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        registerLabel.addActionListener(this::labelMouseClicked);
        loginButton.addActionListener(this::loginMouseClicked);
        pwInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        if (register) showRegister();
    }

    private void loginMouseClicked(ActionEvent e) {
        String username = userInput.getText();
        String password = new String(pwInput.getPassword());

        if (username.isEmpty() || username.isBlank() || password.isBlank() || password.isEmpty()) {
            Logger.info("Usuário ou senha inválidas!");

            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Usuário e/ou senha inválidos!",
                    "CEU - Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        UserInterface userInterface = (UserInterface) frameManager.getInterface("user");

        User user = userInterface.login(username, password);

        if (user != null) {
            frameManager.setLoggedUser(user);

            Stocks stockScreen = new Stocks(frameManager);

            frameManager.load(stockScreen);
        } else {
            Logger.info("Usuário inválido!");

            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Usuário e/ou senha inválidos!",
                    "CEU - Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void labelMouseClicked(ActionEvent e) {
        register = true;
        showRegister();
    }

    private void registerMouseClicked(ActionEvent e) {
        String username = userInput.getText();
        String password = new String(pwInput.getPassword());

        if (username.isEmpty() || username.isBlank() || password.isBlank() || password.isEmpty()) {
            Logger.info("Usuário ou senha inválidas!");

            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Usuário e/ou senha inválidos!",
                    "CEU - Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        UserInterface userInterface = (UserInterface) frameManager.getInterface("user");

        if (userInterface.register(username, password)) {
            Logger.info("Usuário " + username + " registrado com sucesso");

            register = false;
            showRegister();
        } else {
            Logger.info("Não foi possível cadastrar usuário!");

            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Usuário já cadastrado!",
                    "CEU - Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void showRegister() {
        SwingUtilities.invokeLater(() -> {
            ActionListener[] listeners = loginButton.getActionListeners();
            KeyListener[] keyListeners = pwInput.getKeyListeners();

            Arrays.stream(listeners).forEach(listener -> loginButton.removeActionListener(listener));
            Arrays.stream(keyListeners).forEach(listener -> pwInput.removeKeyListener(listener));

            if (register) {
                Logger.info("Carregando tela de registro");
                loginButton.setText("Cadastrar");

                loginButton.addActionListener(this::registerMouseClicked);
                pwInput.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            loginButton.doClick();
                        }
                    }
                });

                registerLabel.setVisible(false);
                registerLabel.setEnabled(false);

                super.getFrameManager().setTitle(registerTitle);
            } else {
                Logger.info("Carregando tela de login");
                loginButton.setText("Login");

                loginButton.addActionListener(this::loginMouseClicked);
                pwInput.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            loginButton.doClick();
                        }
                    }
                });

                registerLabel.setVisible(true);
                registerLabel.setEnabled(true);

                super.getFrameManager().setTitle(loginTitle);
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas Segger
        logo = new JLabel();
        logoName = new JLabel();
        userLabel = new JLabel();
        userInput = new RoundJTextField();
        pwInput = new RoundJPasswordField();
        pwLabel = new JLabel();
        loginButton = new RoundJButton();
        registerLabel = new JButton();

        //---- logo ----
        logo.setText(" ");

        //---- logoName ----
        logoName.setText("CEU");
        logoName.setFont(new Font("Nova Round", logoName.getFont().getStyle() | Font.BOLD, 72));
        logoName.setForeground(new Color(185, 163, 209));

        //---- userLabel ----
        userLabel.setText("Nome de usu\u00e1rio");
        userLabel.setFont(new Font("Montserrat Medium", userLabel.getFont().getStyle(), 14));

        //---- pwLabel ----
        pwLabel.setText("Senha");
        pwLabel.setFont(new Font("Montserrat Medium", pwLabel.getFont().getStyle(), 14));

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.setFont(new Font("Montserrat", loginButton.getFont().getStyle() | Font.BOLD, 12));
        loginButton.setBorderPainted(false);

        //---- registerLabel ----
        registerLabel.setText("N\u00e3o tem conta ainda? Cadastre-se");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setFont(new Font("Montserrat Medium", registerLabel.getFont().getStyle(), 14));
        registerLabel.setForeground(new Color(148, 88, 214));
        registerLabel.setBorderPainted(false);
        registerLabel.setContentAreaFilled(false);
        registerLabel.setFocusable(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(119, 119, 119)
                    .addComponent(logo, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                    .addGap(194, 194, 194)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(registerLabel, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(userLabel, GroupLayout.Alignment.LEADING)
                                .addComponent(pwLabel, GroupLayout.Alignment.LEADING))
                            .addGap(0, 137, Short.MAX_VALUE))
                        .addComponent(pwInput, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addComponent(userInput, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGap(131, 131, 131))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(226, 642, Short.MAX_VALUE)
                    .addComponent(logoName)
                    .addGap(193, 193, 193))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(90, Short.MAX_VALUE)
                    .addComponent(logoName)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(logo, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(userLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(userInput, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(pwLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pwInput, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerLabel)))
                    .addGap(130, 130, 130))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas Segger
    private JLabel logo;
    private JLabel logoName;
    private JLabel userLabel;
    private RoundJTextField userInput;
    private RoundJPasswordField pwInput;
    private JLabel pwLabel;
    private RoundJButton loginButton;
    private JButton registerLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
