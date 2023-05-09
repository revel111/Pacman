package operations;

import customVariables.variables.Pacman;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    private JLabel pacPicture;
    private Pacman pacman;
    public CellRenderer() {
//        pacPicture = new JLabel();
//        pacPicture.setHorizontalAlignment(SwingConstants.CENTER);
//        pacPicture.setOpaque(true);
        pacPicture = pacman.getjLabel();
        pacman.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
        pacman.getjLabel().setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        table.setFocusable(false);
//        ((JLabel) c).setText(null); //no text
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
        } else if (value.toString().equals("2")) { // pac
//            setBackground(Color.PINK);
            pacman.getjLabel().setVisible(true);
        }
//                else
//                    setBackground(Color.BLACK);

        return pacPicture;
    }
}
