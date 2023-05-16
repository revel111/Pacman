package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import customVariables.variables.Pacman;
import operations.TableModel;
import operations.ObjectScore;

public class Game extends JFrame implements KeyListener {
    private final TableModel tableModel = new TableModel(null);
    private int counter = 0;
    private final JPanel panelTable = new JPanel();
    private JFrame jframe = new JFrame("Pacman");

    public Game(int height, int width) {
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


        JLabel scoreLabel = new JLabel("Score: " + tableModel.getPacman().getScore());
        JLabel hpLabel = new JLabel("Health: " + tableModel.getPacman().getHp());
        JLabel time = new JLabel("Time:" + counter);

        scoreLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.YELLOW);

        time.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        time.setForeground(Color.YELLOW);


        hpLabel.setIcon(new ImageIcon("src/images/heart.png"));
        hpLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        hpLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 35));
        hpLabel.setForeground(Color.YELLOW);

        hpScorePanel.add(scoreLabel, new FlowLayout(FlowLayout.LEFT));
        hpScorePanel.add(hpLabel, new FlowLayout(FlowLayout.RIGHT));
        hpScorePanel.add(time, new FlowLayout(FlowLayout.CENTER));
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

        new Thread(() -> {
            while (tableModel.isInGame()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                counter++;
                time.setText("Time: " + counter);
                time.repaint();
            }
        }).start();

        new Thread(() -> {
            while (tableModel.isInGame()) {
                scoreLabel.setText("Score: " + tableModel.getPacman().getScore());
                hpLabel.setText("Health: " + tableModel.getPacman().getHp());
                scoreLabel.repaint();
                hpLabel.repaint();

                tableModel.checkIfVictory();
                if (tableModel.getPacman().getHp() == 0)
                    tableModel.setInGame(false);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (tableModel.isInGame())
                tableModel.getPacman().movePac();
            try {
                end();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        for (int i = 0; i < tableModel.getGhosts().size(); i++) {
            int finalI = i;
            new Thread(() -> {
                while (tableModel.isInGame())
                    tableModel.getGhosts().get(finalI).moveGhost();
            }).start();
        }

        for (int i = 0; i < tableModel.getGhosts().size(); i++) {
            int finalI = i;
            new Thread(() -> {
                while (tableModel.isInGame()) {
                    Random random = new Random();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    int boost = random.nextInt(5);
                    tableModel.getGhosts().get(finalI).setBoost(boost);
                }
            }).start();
        }

//        for (int i = 0; i < tableModel.getGhosts().size(); i++) {
//            int finalI = i;
//            new Thread(() -> {
//                while (tableModel.isInGame()) {
//                    if (tableModel.getPacman().getBoost() == 2) {
//                        tableModel.getPacman().setSpeed(tableModel.getPacman().getSpeed() / 2);
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                        tableModel.getPacman().setSpeed(tableModel.getPacman().getSpeed() * 2);
//                    }
//                }
//            }).start();
//        }

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
                } else if (value instanceof JLabel) {
                    JLabel jLabel = (JLabel) value;

                    jLabel.setBackground(Color.BLACK);

                    panelTable.repaint();
                    return jLabel;
                }
                panelTable.repaint();
                return label;
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
    }

    public void end() throws IOException {
        String name = JOptionPane.showInputDialog(null, "Enter a nickname.", "Input name", JOptionPane.PLAIN_MESSAGE);

        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You wrote nothing.", "Error", JOptionPane.ERROR_MESSAGE);
            SwingUtilities.invokeLater(MainMenu::new);
            jframe.dispose();
            return;
        }
        ObjectScore objectScore = new ObjectScore(tableModel.getPacman().getScore(), name);
        ArrayList<ObjectScore> arrayList = ObjectScore.readObjects();
        arrayList.add(objectScore);
        ObjectScore.writeObject(arrayList);
        jframe.dispose();
        SwingUtilities.invokeLater(MainMenu::new);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_LEFT);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_UP);
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            tableModel.getPacman().setKeyPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}