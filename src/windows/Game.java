package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

import operations.TableModel;
import customVariables.variables.Pacman;

public class Game extends JFrame implements KeyListener {
    private final TableModel tableModel = new TableModel(null);
    private final JLabel scoreLabel = new JLabel("Score: " + tableModel.getPacman().getScore());
    private final JLabel hpLabel = new JLabel("Health: " + tableModel.getPacman().getHp());

    public Game(int height, int width) {
        JFrame jframe = new JFrame("Pacman");
        Image frameImage = new ImageIcon("src/images/icon.png").getImage(); //taskbar icon
        jframe.setIconImage(frameImage);
        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        tableModel.generateMap(height, width);
        JTable jTable = new JTable(tableModel);
        jTable.setBackground(Color.BLACK);

        JPanel hpScorePanel = new JPanel();
        hpScorePanel.setBackground(Color.BLACK);
        hpScorePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        scoreLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.YELLOW);

        hpLabel.setIcon(new ImageIcon("src/images/heart.png"));
        hpLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        hpLabel.setForeground(Color.YELLOW);

        hpScorePanel.add(scoreLabel, new FlowLayout(FlowLayout.LEFT));
        hpScorePanel.add(hpLabel, new FlowLayout(FlowLayout.RIGHT));
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        panelTable.add(jTable, BorderLayout.CENTER);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(panelTable, BorderLayout.CENTER);
        parentPanel.add(hpScorePanel, BorderLayout.SOUTH);

        jframe.add(parentPanel, BorderLayout.CENTER);
        parentPanel.setFocusable(true);
        parentPanel.requestFocusInWindow();
        parentPanel.addKeyListener(this);

        jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {  //draw map
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setText(null); //no text
                table.setShowGrid(false); //no grid lines
                table.setIntercellSpacing(new Dimension(0, 0)); //no grid lines
                JLabel label = new JLabel();

                if (value instanceof ImageIcon) {
                    ImageIcon imageIcon = (ImageIcon) value;

//                    Image originalImage = imageIcon.getImage();
//                    Image scaledImage = originalImage.getScaledInstance(83, 51, Image.SCALE_SMOOTH);
//                    label.setIcon(new ImageIcon(scaledImage));
                    label.setIcon(imageIcon);
                } else if (value instanceof Pacman) {
//                    label = (JLabel) value;
//                Pacman pacman;
//                    Pacman pacman = (Pacman) value;
//                    label.setHorizontalAlignment(SwingConstants.CENTER);
//                    tableModel.setPacman(pacman);
                    panelTable.repaint();
                    return tableModel.getPacman();
                } else if (value instanceof JPanel panel) {

                    panelTable.repaint();
                    return panel;
                }
                panelTable.repaint();
                return label;
//                if (value.toString().equals("0")) {
//                    setBackground(new Color(0, 0, 47));
//                    setBorder(BorderFactory.createLineBorder(new Color(0, 0, 185), 1));
//                } else if (value.toString().equals("1")) {
//                    setBackground(Color.BLACK);
//                    return new JPanel() {
//                        @Override
//                        protected void paintComponent(Graphics g) {
//                            super.paintComponent(g);
//                            Graphics2D g2d = (Graphics2D) g.create();
//                            //fill the background with black
//                            g2d.setColor(Color.BLACK);
//                            g2d.fillRect(0, 0, getWidth(), getHeight());
//                            //draw the yellow dot
//                            g2d.setColor(Color.YELLOW);
//                            int centerX = getWidth() / 2;
//                            int centerY = getHeight() / 2;
//                            int dotX = centerX - (3);
//                            int dotY = centerY - (3);
//                            g2d.fillOval(dotX, dotY, 6, 6);
//                            g2d.dispose();
//                        }
//                    };
//                } else if (value.toString().equals("2")) { // pac
//                    if (pacman.getKeyPressed() == KeyEvent.VK_RIGHT) {
//                        ImageIcon pacIcon = (new ImageIcon("src/images/pacBO.png"));
//                        JLabel label = new JLabel();
//                        label.setIcon(scaleImage(pacIcon, table.getRowHeight(), table.getRowHeight()));
//                        label.setOpaque(true);
//                        label.setBackground(table.getBackground());
//                        label.setForeground(table.getForeground());
//                        return label;
//                    } else if (pacman.getKeyPressed() == KeyEvent.VK_LEFT) {
//                        ImageIcon pacIcon = (new ImageIcon("src/images/pacFrO.png"));
//                        JLabel label = new JLabel();
//                        label.setIcon(scaleImage(pacIcon, table.getRowHeight(), table.getRowHeight()));
//                        label.setOpaque(true);
//                        label.setBackground(table.getBackground());
//                        label.setForeground(table.getForeground());
//                        return label;
//                    }
//                }
//                else
//                    setBackground(Color.BLACK);

//                return c = table.getValueAt(row,column);
            }
        });
        jTable.addComponentListener(new ComponentAdapter() { // resize-able
            @Override
            public void componentResized(ComponentEvent e) {
                jTable.setRowHeight(jTable.getHeight() / jTable.getRowCount());
                for (int i = 0; i < jTable.getColumnCount(); i++)
                    jTable.getColumnModel().getColumn(i).setPreferredWidth(jTable.getWidth() / jTable.getColumnCount());
            }
        });

        KeyStroke ctrlShiftQ = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);//shortcut to close window
        jframe.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrlShiftQ, "closeWindow");
        jframe.getRootPane().getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(MainMenu::new);
                jframe.dispose();
            }
        });

        new Thread(tableModel::checkIfVictory).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            tableModel.setKeyPressed(KeyEvent.VK_RIGHT);
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_RIGHT);
//            tableModel.moveRightPac();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            tableModel.setKeyPressed(KeyEvent.VK_LEFT);
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_LEFT);
//            tableModel.moveLeftPac();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
//            tableModel.setKeyPressed(KeyEvent.VK_UP);
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_UP);
//            tableModel.moveUpPac();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            tableModel.setKeyPressed(KeyEvent.VK_DOWN);
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_DOWN);
//            tableModel.moveDownPac();
        }
        scoreLabel.setText("Score: " + tableModel.getPacman().getScore());
        hpLabel.setText("Health: " + tableModel.getPacman().getHp());
        scoreLabel.repaint();
        hpLabel.repaint();

//        tableModel.checkIfVictory();

        if (!tableModel.isInGame()) {
            System.exit(100);
//            SwingUtilities.invokeLater(MainMenu::new);
        }
//        tableModel.fireTableDataChanged();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}