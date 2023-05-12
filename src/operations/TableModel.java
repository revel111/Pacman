package operations;

import customVariables.variables.Pacman;
import windows.Game;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TableModel extends AbstractTableModel {
    private int[][] items;
    private Pacman pacman = new Pacman();
    private JLabel score = new JLabel("Score: " + pacman.getScore());

    public TableModel(int[][] items) {
//        new Thread(() -> {
//            while (true) {
//                if (pacman.getKeyPressed() == KeyEvent.VK_RIGHT)
//                    this.moveRightPac();
//                else if (pacman.getKeyPressed() == KeyEvent.VK_LEFT)
//                    this.moveLeftPac();
//                else if (pacman.getKeyPressed() == KeyEvent.VK_UP)
//                    this.moveUpPac();
//                else if (pacman.getKeyPressed() == KeyEvent.VK_DOWN)
//                    this.moveDownPac();
//            }
//        }).start();
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

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public JLabel getScore() {
        return score;
    }

    public void setScore(JLabel score) {
        this.score = score;
    }

    public void moveLeftPac() {
        if (items[getPacman().getI()][getPacman().getJ() - 1] != 0) {//wall
            items[getPacman().getI()][getPacman().getJ()] = 3;//black
            if (items[getPacman().getI()][getPacman().getJ() - 1] == 1) {//dot
                getPacman().setScore(getPacman().getScore() + 10);
                score.setText("Score" + pacman.getScore());
                score.repaint();
            }
            items[getPacman().getI()][getPacman().getJ() - 1] = 2;//pack
            getPacman().setJ(getPacman().getJ() - 1);
        }
    }

    public void moveRightPac() {
        if (items[getPacman().getI()][getPacman().getJ() + 1] != 0) {
            items[getPacman().getI()][getPacman().getJ()] = 3;
            if (items[getPacman().getI()][getPacman().getJ() + 1] == 1)
                getPacman().setScore(getPacman().getScore() + 10);
            items[getPacman().getI()][getPacman().getJ() + 1] = 2;
            getPacman().setJ(getPacman().getJ() + 1);
        }
    }

    public void moveUpPac() {
        if (items[getPacman().getI() - 1][getPacman().getJ()] != 0) {
            items[getPacman().getI()][getPacman().getJ()] = 3;
            if (items[getPacman().getI() - 1][getPacman().getJ()] == 1) {
                getPacman().setScore(getPacman().getScore() + 10);
//                System.out.println("shit was collected");
            }
            items[getPacman().getI() - 1][getPacman().getJ()] = 2;
            getPacman().setI(getPacman().getI() - 1);
        }
    }

    public void moveDownPac() {
        if (items[getPacman().getI() + 1][getPacman().getJ()] != 0) {
            items[getPacman().getI()][getPacman().getJ()] = 3;
            if (items[getPacman().getI() + 1][getPacman().getJ()] == 1)
                getPacman().setScore(getPacman().getScore() + 10);
            items[getPacman().getI() + 1][getPacman().getJ()] = 2;
            getPacman().setI(getPacman().getI() + 1);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (items[rowIndex][columnIndex] == 0) {
            ImageIcon wall = (new ImageIcon("src/images/wall.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return wall;
        } else if (items[rowIndex][columnIndex] == 1) {
            ImageIcon dot = (new ImageIcon("src/images/dot.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return dot;
        } else if (items[rowIndex][columnIndex] == 2) {
            ImageIcon pac = (new ImageIcon("src/images/pacRO.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 3) {
            ImageIcon dot = (new ImageIcon("src/images/black.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return dot;
        } else if (items[rowIndex][columnIndex] == 4) {
            ImageIcon ghost = (new ImageIcon("src/images/ghost.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return ghost;
        }
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
        return null;
    }

    public void generateMap(int height, int width) {
        Random random = new Random();
        int[][] matrix = new int[height][width];
        int counterMax = width * height / 5;
        int counter = 0;
        pacman.setI(height / 2);
        pacman.setJ(width / 2);
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
    }
}