/*
 * Created by JFormDesigner on Tue Sep 08 20:33:29 BRT 2020
 */

package screens.stocks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout;
import screens.common.*;

/**
 * @author IamN5
 */
public class StockForm extends JDialog {
    public StockForm(Window owner) {
        super(owner);
        setModal(true);

        initComponents();

        try {
            logo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/ceu-270x168.svg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - awASAF ASD
        name = new RoundJTextField();
        nameLabel = new JLabel();
        amountLabel = new JLabel();
        spinner1 = new JSpinner();
        add = new RoundJButton();
        logo = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(name);
        name.setBounds(75, 270, 351, 35);

        //---- nameLabel ----
        nameLabel.setText("Nome");
        nameLabel.setFont(new Font("Montserrat", nameLabel.getFont().getStyle(), 14));
        contentPane.add(nameLabel);
        nameLabel.setBounds(new Rectangle(new Point(75, 235), nameLabel.getPreferredSize()));

        //---- amountLabel ----
        amountLabel.setText("Quantidade");
        amountLabel.setFont(new Font("Montserrat", amountLabel.getFont().getStyle(), 14));
        contentPane.add(amountLabel);
        amountLabel.setBounds(new Rectangle(new Point(75, 335), amountLabel.getPreferredSize()));
        contentPane.add(spinner1);
        spinner1.setBounds(75, 370, spinner1.getPreferredSize().width, 35);

        //---- add ----
        add.setText("Adicionar");
        contentPane.add(add);
        add.setBounds(75, 460, 351, 35);

        //---- logo ----
        logo.setText(" ");
        contentPane.add(logo);
        logo.setBounds(120, 30, 270, 168);

        contentPane.setPreferredSize(new Dimension(510, 560));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - awASAF ASD
    private RoundJTextField name;
    private JLabel nameLabel;
    private JLabel amountLabel;
    private JSpinner spinner1;
    private RoundJButton add;
    private JLabel logo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
