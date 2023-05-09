package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

import operations.CellRenderer;
import operations.TableModel;
import customVariables.variables.Pacman;

public class Game extends JFrame implements KeyListener {
    Pacman pacman = new Pacman();
    public Game(int height, int width) {
        JFrame jframe = new JFrame("Pacman");
        Image frameImage = new ImageIcon("src/images/icon.png").getImage(); //taskbar icon
        jframe.setIconImage(frameImage);
        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.addKeyListener(this);
//        pacman.start();

        TableModel tableModel = new TableModel(null);
        tableModel.generateMap(height, width);
        JTable jTable = new JTable(tableModel);
        jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() { //draw map
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) c).setText(null); //no text
                table.setShowGrid(false); //no grid lines
                table.setIntercellSpacing(new Dimension(0, 0)); //no grid lines
                if (value.toString().equals("0")) {
                    setBackground(new Color(0, 0, 47));
                    setBorder(BorderFactory.createLineBorder(new Color(0, 0, 185), 1));
                } else if (value.toString().equals("1")) {
                    setBackground(Color.BLACK);
                    return new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2d = (Graphics2D) g.create();
                            //fill the background with black
                            g2d.setColor(Color.BLACK);
                            g2d.fillRect(0, 0, getWidth(), getHeight());
                            //draw the yellow dot
                            g2d.setColor(Color.YELLOW);
                            int centerX = getWidth() / 2;
                            int centerY = getHeight() / 2;
                            int dotX = centerX - (3);
                            int dotY = centerY - (3);
                            g2d.fillOval(dotX, dotY, 6, 6);
                            g2d.dispose();
                        }
                    };
                }  else if (value.toString().equals("2")) { // pac
                    setBackground(Color.PINK);
//                    pacman.start();

//                    pacman.setHeight(getHeight());
//                    pacman.setWidth(getWidth());
//                    pacman.run();
//                    return pacman;
//                    return new Pacman(getWidth(),getHeight());
                }
//                else
//                    setBackground(Color.BLACK);

                return c;
            }
        });
//        jTable.setDefaultRenderer(Object.class, new CellRenderer());
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
        jframe.add(jTable);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            pacman.setKeyPressed(KeyEvent.VK_RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            pacman.setKeyPressed(KeyEvent.VK_LEFT);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            pacman.setKeyPressed(KeyEvent.VK_UP);
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            pacman.setKeyPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            pacman.setKeyPressed(KeyEvent.VK_RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            pacman.setKeyPressed(KeyEvent.VK_LEFT);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            pacman.setKeyPressed(KeyEvent.VK_UP);
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            pacman.setKeyPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}