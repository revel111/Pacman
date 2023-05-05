package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


import operations.TableModel;

public class Game extends JFrame {
    public Game(int size) {
        JFrame jframe = new JFrame("Pacman");
        Image frameImage = new ImageIcon("src/images/icon.png").getImage();//taskbar icon
        jframe.setIconImage(frameImage);
        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        TableModel tableModel = new TableModel(null);
        tableModel.generateMap(size);
        JTable jTable = new JTable(tableModel);

        jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {//draw map
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                ((JLabel) c).setText(null);//no text
                table.setShowGrid(false);//no grid lines
                table.setIntercellSpacing(new Dimension(0, 0));//no grid lines

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

                            // Fill the background with black
                            g2d.setColor(Color.BLACK);
                            g2d.fillRect(0, 0, getWidth(), getHeight());

                            // Draw the yellow dot
                            g2d.setColor(Color.YELLOW);
                            int centerX = getWidth() / 2;
                            int centerY = getHeight() / 2;
                            int dotX = centerX - (3);
                            int dotY = centerY - (3);
                            g2d.fillOval(dotX, dotY, 3, 3);
                            g2d.dispose();
                        }
                    };
                } else
                    setBackground(Color.BLACK);

                return c;
            }
        });


        jTable.addComponentListener(new ComponentAdapter() { // resize-able
            @Override
            public void componentResized(ComponentEvent e) {
                int newHeight = jTable.getHeight() / jTable.getRowCount();
                int newWidth = jTable.getWidth() / jTable.getColumnCount();
                jTable.setRowHeight(newHeight);
                for (int i = 0; i < jTable.getColumnCount(); i++)
                    jTable.getColumnModel().getColumn(i).setPreferredWidth(newWidth);
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
}