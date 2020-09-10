/*
 * Created by JFormDesigner on Tue Sep 08 20:33:29 BRT 2020
 */

package screens.stocks;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

import app.product.Product;
import framework.Logger;
import screens.common.*;

/**
 * @author IamN5
 */
public class StockForm extends JDialog {
    private final Stocks stockScreen;
    private int productId;
    private int stockId;

    public StockForm(Window owner, Stocks stockScreen) {
        super(owner);
        setModal(true);

        this.stockScreen = stockScreen;
        initComponents();

        try {
            logo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/ceu-270x168.svg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNameField(String str) {
        name.setText(str);
    }

    public void setAmountField(int n) {
        amount.setValue(n);
    }

    public void setProductId(int id) {
        productId = id;
    }

    public void setStockId(int id) {
        stockId = id;
    }

    private void addMouseClicked(MouseEvent e) {
        Timestamp ts = new Timestamp(new Date().getTime());

        if (getTitle().equals("CEU - Editar produto")) {
            if (stockScreen.productInterface.patch(new Product(productId, name.getText(), (Integer) amount.getValue(), stockId, ts))) {
                stockScreen.fetchProducts();
            } else {
                Logger.info("Não foi possível atualizar produto");

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao atualizar produto!",
                        "CEU - Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            if (stockScreen.productInterface.store(new Product(name.getText(), (Integer) amount.getValue(), stockId, ts))) {
                stockScreen.fetchProducts();
            } else {
                Logger.info("Não foi possível criar produto");

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao criar produto!",
                        "CEU - Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - awASAF ASD
        name = new RoundJTextField();
        nameLabel = new JLabel();
        amountLabel = new JLabel();
        amount = new JSpinner();
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

        //---- amount ----
        amount.setModel(new SpinnerNumberModel(0, 0, null, 1));
        contentPane.add(amount);
        amount.setBounds(75, 370, 80, 35);

        //---- add ----
        add.setText("Adicionar");
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addMouseClicked(e);
            }
        });
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
    private JSpinner amount;
    private RoundJButton add;
    private JLabel logo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
