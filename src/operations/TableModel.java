package operations;

import windows.Game;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Random;

public class TableModel extends AbstractTableModel {
    private int[][] items;
    private int keyPressed;

    public TableModel(int[][] items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.length;
    }

    @Override
    public int getColumnCount() {
        return items[0].length;
    }


    public int getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        return items[rowIndex][columnIndex];

        if (items[rowIndex][columnIndex] == 0) {
            ImageIcon pacIcon = (new ImageIcon("src/images/wall.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pacIcon;
        } else if (items[rowIndex][columnIndex] == 1) {
            ImageIcon dot = (new ImageIcon("src/images/dot.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return dot;
        } else if (items[rowIndex][columnIndex] == 2) {
            ImageIcon dot = (new ImageIcon("src/images/pacBCl.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return dot;
        } else if (items[rowIndex][columnIndex] == 3) {
            ImageIcon dot = (new ImageIcon("src/images/pacBCl.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return dot;
//        } else if (items[rowIndex][columnIndex] == 4) {
//            ImageIcon pac2 = (new ImageIcon("src/images/pacBO.png"));
//            fireTableCellUpdated(rowIndex, columnIndex);
//            return pac2;
//        }
//        if(items[rowIndex][columnIndex] == 1) {
//            fireTableCellUpdated(rowIndex,columnIndex);
//            return new JPanel() {
//                @Override
//                protected void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//                    Graphics2D g2d = (Graphics2D) g.create();
//                    //fill the background with black
//                    g2d.setColor(Color.BLACK);
//                    g2d.fillRect(0, 0, getWidth(), getHeight());
//                    //draw the yellow dot
//                    g2d.setColor(Color.YELLOW);
//                    int centerX = getWidth() / 2;
//                    int centerY = getHeight() / 2;
//                    int dotX = centerX - (3);
//                    int dotY = centerY - (3);
//                    g2d.fillOval(dotX, dotY, 6, 6);
//                    g2d.dispose();
//                }
//            };
//        }
//
        }
        return null;
    }

    public void generateMap(int height, int width) {
        Random random = new Random();
        int[][] matrix = new int[height][width];
        int counterMax = width * height / 5;
        int counter = 0;
        matrix[height / 2][width / 2] = 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1)
                    matrix[i][j] = 0;
                else {
                    int rand = random.nextInt(5) + 1;
                    if (matrix[i][j] == 2)
                        continue;
                    else if (counter == counterMax)
                        matrix[i][j] = 1;
                    else if (rand == 1) {
                        matrix[i][j] = 0;
                        counter++;
                    } else
                        matrix[i][j] = 1;
                }
            }
        }
        this.items = matrix;

//        for (int[] ints : matrix) {
//            for (int anInt : ints)
//                System.out.print(anInt + " ");
//            System.out.println();
//        }
    }
}