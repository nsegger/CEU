/*
 * Created by JFormDesigner on Mon Aug 31 01:01:10 BRT 2020
 */

package screens.login;

import com.bulenkov.darcula.DarculaLaf;
import core.ui.JFrameManager;
import screens.Screen;
import screens.common.RoundJButton;
import screens.common.RoundJTextField;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Nicholas Segger
 */
public class Login extends Screen {

    public Login(JFrameManager frameManager) {
        super("CEU - Controle de Estoque Universal", frameManager);
        initComponents();

        try {
            BufferedImage cloudSvg = ImageIO.read(getClass().getResource("/resources/ceu.svg"));
            logo.setIcon(new ImageIcon(cloudSvg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas Segger
        logo = new JLabel();
        logoName = new JLabel();
        userLabel = new JLabel();
        userInput = new RoundJTextField(5);
        pwInput = new RoundJTextField(5);
        pwLabel = new JLabel();
        loginButton = new RoundJButton(5, new Color(148, 88, 214));

        label3 = new JLabel();

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

        //---- label3 ----
        label3.setText("N\u00e3o tem conta ainda? Cadastre-se");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Montserrat Medium", label3.getFont().getStyle(), 14));
        label3.setForeground(new Color(148, 88, 214));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(119, 119, 119)
                    .addComponent(logo, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                    .addGap(194, 194, 194)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(userInput, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(131, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(pwInput, GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(userLabel, GroupLayout.Alignment.LEADING)
                                        .addComponent(pwLabel, GroupLayout.Alignment.LEADING))
                                    .addGap(0, 137, Short.MAX_VALUE)))
                            .addGap(131, 131, 131))))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(642, Short.MAX_VALUE)
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
                            .addComponent(label3)))
                    .addGap(130, 130, 130))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas Segger
    private JLabel logo;
    private JLabel logoName;
    private JLabel userLabel;
    private JTextField userInput;
    private JTextField pwInput;
    private JLabel pwLabel;
    private JButton loginButton;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
