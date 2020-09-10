/*
 * Created by JFormDesigner on Fri Sep 04 21:01:16 BRT 2020
 */

package screens.stocks;

import java.awt.event.*;

import app.product.Product;
import framework.core.ui.JFrameManager;
import framework.Logger;
import screens.Screen;
import screens.common.*;
import screens.common.StocksJList;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * @author Nicholas Segger
 */
public class Stocks extends Screen {
    private String welcomeMessage;
    private ArrayList<String> stocks;
    ArrayList<Product> products;

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
        this.products = new ArrayList<>();

        initComponents();
        stockTable.setShowVerticalLines(false);
        startMessage.setText(welcomeMessage);

        stockPane.setVisible(false);

        create.setFocusPainted(false);
        create.setMargin(new Insets(0, 0, 0, 0));
        create.setContentAreaFilled(false);
        create.setBorderPainted(false);
        create.setOpaque(false);

        delete.setFocusPainted(false);
        delete.setMargin(new Insets(0, 0, 0, 0));
        delete.setContentAreaFilled(false);
        delete.setBorderPainted(false);
        delete.setOpaque(false);

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
        
        stockList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Logger.info("StockJList value is now: " + stockList.getSelectedValue());
                delete.setVisible(true);
                stockPane.setVisible(true);
                startMessage.setVisible(false);

                products.clear();

                products.add(new Product(0, "Produto 1", 20, 0, new Date()));
                products.add(new Product(1, "Produto 2", 1, 0, new Date()));

                stockTable.setModel(new AbstractTableModel() {
                    private final ArrayList<Product> values = products;

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return String.class;
                    }

                    @Override
                    public String getColumnName(int column) {
                        return switch (column) {
                            case 0 -> "Nome";
                            case 1 -> "Última atualização";
                            case 2 -> "Quantidade";
                            default -> "";
                        };
                    }

                    @Override
                    public int getRowCount() {
                        return values.size();
                    }

                    @Override
                    public int getColumnCount() {
                        return 3;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        return switch (columnIndex) {
                            case 0 -> values.get(rowIndex).getName();
                            case 1 -> values.get(rowIndex).getLastUpdate().toString();
                            case 2 -> String.valueOf(values.get(rowIndex).getAmount());
                            default -> null;
                        };
                    }
                });
            }
        });

        stockTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && stockTable.getSelectedRow() != -1) {
                Logger.info("JTable selected value is: " + products.get(stockTable.getSelectedRow()).getName());
            }
        });

        buscar.addActionListener(e -> {
            // TODO Search action
        });

        buscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                buscar.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                buscar.setText("\uD83D\uDD0D   Buscar");
            }
        });

        try {
            cloudIcon.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/resources/ceu-50x30.svg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMouseClicked(MouseEvent e) {
        getFrameManager().loadModal(StockForm.class, "CEU - Adicionar produto");
    }

    private void removeMouseClicked(MouseEvent e) {
        if (stockTable.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Selecione um ou mais produtos para remover",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {

            int answer = JOptionPane.showConfirmDialog(
                    frameManager.getFrame(),
                    "Deseja remover " + stockTable.getSelectedRowCount() + " produtos?",
                    "CEU - Confirmação",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (answer == JOptionPane.NO_OPTION) return;

            int[] rows = stockTable.getSelectedRows();

            ArrayList<Product> selectedProducts = new ArrayList<>();

            IntStream.range(0, rows.length).forEach(index -> {
                selectedProducts.add(products.get(rows[index]));
                Logger.info("Adicionando produto de índice " + rows[index] + " à lista de remoção");
            });

            // TODO - Remove products from DB
        }

    }

    private void editMouseClicked(MouseEvent e) {

        if (stockTable.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Selecione apenas UM produto para editar",
                    "CEU - Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            Product selectedProduct = products.get(stockTable.getSelectedRow());

            SwingUtilities.invokeLater(() -> {
                frameManager.loadModal(StockForm.class, "CEU - Editar produto", selectedProduct);
            });
        }
    }

    private void generateStatsMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void createMouseClicked(MouseEvent e) {
        String stockName = JOptionPane.showInputDialog(
                frameManager.getFrame(),
                "Nome do estoque:",
                "CEU - Criar novo estoque",
                JOptionPane.PLAIN_MESSAGE
        );

        if (stockName == null || stockName.isBlank() || stockName.isEmpty()) {
            Logger.info("Tentativa de criação de estoque com nome inválido!");

            JOptionPane.showMessageDialog(
                    frameManager.getFrame(),
                    "Nome do estoque é inválido!",
                    "CEU - Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            stocks.add(stockName);
        }
    }

    private void deleteMouseClicked(MouseEvent e) {
        int answer = JOptionPane.showConfirmDialog(
                frameManager.getFrame(),
                "Deseja remover o estoque \"" + stockList.getSelectedValue() + "\"?",
                "CEU - Confirmação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (answer == JOptionPane.NO_OPTION) return;

        Logger.info("Removendo estoque " + stockList.getSelectedValue());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - awASAF ASD
        listScroll = new JScrollPane();
        stockList = new StocksJList();
        cloudIcon = new JLabel();
        label2 = new JLabel();
        create = new JButton();
        delete = new JButton();
        stockPane = new JPanel();
        buscar = new RoundJTextField();
        add = new RoundJButton();
        remove = new RoundJButton();
        edit = new RoundJButton();
        tableScroll = new JScrollPane();
        stockTable = new ProductsJTable();
        generateStats = new RoundJButton();
        startMessage = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(983, 601));
        setPreferredSize(new Dimension(983, 601));
        setLayout(null);

        //======== listScroll ========
        {
            listScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            listScroll.setViewportBorder(null);
            listScroll.setBorder(null);

            //---- stockList ----
            stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            stockList.setVisibleRowCount(6);
            stockList.setBorder(null);
            listScroll.setViewportView(stockList);
        }
        add(listScroll);
        listScroll.setBounds(0, 64, 213, 537);
        add(cloudIcon);
        cloudIcon.setBounds(25, 15, 50, 42);

        //---- label2 ----
        label2.setText("CEU");
        label2.setFont(new Font("Nova Round", label2.getFont().getStyle(), 24));
        label2.setForeground(new Color(148, 88, 214));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        add(label2);
        label2.setBounds(new Rectangle(new Point(90, 20), label2.getPreferredSize()));

        //---- create ----
        create.setText("+");
        create.setFont(new Font("Segoe UI", create.getFont().getStyle() | Font.BOLD, 19));
        create.setForeground(new Color(42, 172, 22));
        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createMouseClicked(e);
            }
        });
        add(create);
        create.setBounds(160, 10, 45, 20);

        //---- delete ----
        delete.setText("-");
        delete.setFont(new Font("Segoe UI", delete.getFont().getStyle() | Font.BOLD, 19));
        delete.setForeground(new Color(172, 22, 22));
        delete.setVisible(false);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteMouseClicked(e);
            }
        });
        add(delete);
        delete.setBounds(160, 30, 45, 20);

        //======== stockPane ========
        {
            stockPane.setBackground(new Color(36, 36, 36));
            stockPane.setLayout(null);

            //---- buscar ----
            buscar.setHorizontalAlignment(SwingConstants.LEFT);
            buscar.setText("\ud83d\udd0d   Buscar");
            stockPane.add(buscar);
            buscar.setBounds(27, 27, 181, 35);

            //---- add ----
            add.setText("Adicionar");
            add.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addMouseClicked(e);
                }
            });
            stockPane.add(add);
            add.setBounds(241, 27, 135, 35);

            //---- remove ----
            remove.setText("Remover");
            remove.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    removeMouseClicked(e);
                }
            });
            stockPane.add(remove);
            remove.setBounds(409, 27, 135, 35);

            //---- edit ----
            edit.setText("Editar");
            edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    editMouseClicked(e);
                }
            });
            stockPane.add(edit);
            edit.setBounds(577, 27, 135, 35);

            //======== tableScroll ========
            {

                //---- stockTable ----
                stockTable.setBorder(null);
                stockTable.setShowVerticalLines(false);
                stockTable.setFont(new Font("Montserrat SemiBold", stockTable.getFont().getStyle(), 14));
                tableScroll.setViewportView(stockTable);
            }
            stockPane.add(tableScroll);
            tableScroll.setBounds(27, 92, 700, 432);

            //---- generateStats ----
            generateStats.setText("Gerar estat\u00edsticas");
            generateStats.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    generateStatsMouseClicked(e);
                }
            });
            stockPane.add(generateStats);
            generateStats.setBounds(27, 545, 700, 35);
        }
        add(stockPane);
        stockPane.setBounds(213, 0, 772, 600);

        //---- startMessage ----
        startMessage.setHorizontalAlignment(SwingConstants.CENTER);
        startMessage.setFont(new Font("Montserrat SemiBold", startMessage.getFont().getStyle(), 15));
        startMessage.setText(" ");
        add(startMessage);
        startMessage.setBounds(215, 290, 770, startMessage.getPreferredSize().height);

        setPreferredSize(new Dimension(985, 600));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - awASAF ASD
    private JScrollPane listScroll;
    private StocksJList stockList;
    private JLabel cloudIcon;
    private JLabel label2;
    private JButton create;
    private JButton delete;
    private JPanel stockPane;
    private RoundJTextField buscar;
    private RoundJButton add;
    private RoundJButton remove;
    private RoundJButton edit;
    private JScrollPane tableScroll;
    private ProductsJTable stockTable;
    private RoundJButton generateStats;
    private JLabel startMessage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
