/*
 * Created by JFormDesigner on Fri Sep 04 21:01:16 BRT 2020
 */

package screens.stocks;

import core.ui.JFrameManager;
import screens.Screen;
import screens.common.StocksJList;
import screens.common.StocksListCellRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.SwingConstants.CENTER;

/**
 * @author Nicholas Segger
 */
public class Stocks extends Screen {
    private String welcomeMessage;
    private ArrayList<String> stocks;

    public Stocks(JFrameManager frameManager) {
        this(frameManager, "Crie um novo estoque para começar!", new ArrayList<>());
    }

    public Stocks(JFrameManager frameManager, String welcomeMessage) {
        this(frameManager, welcomeMessage, new ArrayList<>());
    }

    public Stocks(JFrameManager frameManager, String welcomeMessage, ArrayList<String> stocks) {
        super("CEU - Estoques", frameManager);
        this.welcomeMessage = welcomeMessage;
        this.stocks = stocks;

        initComponents();
        startMessage.setText(welcomeMessage);
        stockList.setModel(new AbstractListModel<>() {
            private final ArrayList<String> values = stocks;

            @Override
            public int getSize() {
                return values.size();
            }

            @Override
            public String getElementAt(int index) {
                return values.get(index);
            }
        });

        try {
            cloudIcon.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/ceu-50x30.svg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Nicholas Segger
        stockScroll = new JScrollPane();
        stockList = new StocksJList();
        startMessage = new JLabel();
        cloudIcon = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(985, 511));
        setPreferredSize(new Dimension(985, 585));

        //======== stockScroll ========
        {
            stockScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            stockScroll.setViewportBorder(null);
            stockScroll.setBorder(null);

            //---- stockList ----
            stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            stockList.setVisibleRowCount(6);
            stockList.setBorder(null);
            stockScroll.setViewportView(stockList);
        }

        //---- startMessage ----
        startMessage.setHorizontalAlignment(SwingConstants.CENTER);
        startMessage.setFont(new Font("Montserrat SemiBold", startMessage.getFont().getStyle(), 15));
        startMessage.setText(" ");

        //---- label2 ----
        label2.setText("CEU");
        label2.setFont(new Font("Nova Round", label2.getFont().getStyle(), 24));
        label2.setForeground(new Color(148, 88, 214));
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(stockScroll, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startMessage, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(cloudIcon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label2)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label2))
                        .addComponent(cloudIcon, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockScroll, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(277, 277, 277)
                    .addComponent(startMessage)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Nicholas Segger
    private JScrollPane stockScroll;
    private StocksJList stockList;
    private JLabel startMessage;
    private JLabel cloudIcon;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
