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
    boolean inGame = true;
//    protected Thread threadPac = new Thread();

    public TableModel(int[][] items) {
//        new Thread(() -> pacman.move()).start();
        this.items = items;
        new Thread(this::movePac).start();
//        new Thread(this::trackChanges).start();
//        new Thread(this::checkIfVictory).start();
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

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void moveLeftPac() {
        if (items[getPacman().getI()][getPacman().getJ() - 1] != 0) {//wall
            items[getPacman().getI()][getPacman().getJ()] = 3;//black
            if (items[getPacman().getI()][getPacman().getJ() - 1] == 1) //dot
                getPacman().setScore(getPacman().getScore() + 10);
            else if (items[getPacman().getI()][getPacman().getJ() - 1] == 4) {
                getPacman().setHp(getPacman().getHp() - 1);
            }
            getPacman().setJ(getPacman().getJ() - 1);
        }
    }

    public void moveRightPac() {
        if (items[getPacman().getI()][getPacman().getJ() + 1] != 0) {
            items[getPacman().getI()][getPacman().getJ()] = 3;
            if (items[getPacman().getI()][getPacman().getJ() + 1] == 1)
                getPacman().setScore(getPacman().getScore() + 10);
            else if (items[getPacman().getI()][getPacman().getJ() + 1] == 4) {
                getPacman().setHp(getPacman().getHp() - 1);
            }
            getPacman().setJ(getPacman().getJ() + 1);
        }
    }

    public void moveUpPac() {
        if (items[getPacman().getI() - 1][getPacman().getJ()] != 0) {
            items[getPacman().getI()][getPacman().getJ()] = 3;
            if (items[getPacman().getI() - 1][getPacman().getJ()] == 1)
                getPacman().setScore(getPacman().getScore() + 10);
            else if (items[getPacman().getI() - 1][getPacman().getJ()] == 4) {
                getPacman().setHp(getPacman().getHp() - 1);
            }
            getPacman().setI(getPacman().getI() - 1);
        }
    }

    public void moveDownPac() {
        if (items[getPacman().getI() + 1][getPacman().getJ()] != 0) {
            items[getPacman().getI()][getPacman().getJ()] = 3;
            if (items[getPacman().getI() + 1][getPacman().getJ()] == 1)
                getPacman().setScore(getPacman().getScore() + 10);
            else if (items[getPacman().getI() + 1][getPacman().getJ()] == 4) {
                getPacman().setHp(getPacman().getHp() - 1);
            }
            getPacman().setI(getPacman().getI() + 1);
        }
    }

    public void movePac() {
        while (inGame) {
            if (getPacman().getHp() == 0)
                inGame = false;
            if (getPacman().getKeyPressed() == KeyEvent.VK_LEFT) {
                moveLeftPac();
                if (getPacman().isMouth())
                    items[getPacman().getI()][getPacman().getJ()] = 9;
                else
                    items[getPacman().getI()][getPacman().getJ()] = 10;
                getPacman().setMouth(!getPacman().isMouth());
            } else if (getPacman().getKeyPressed() == KeyEvent.VK_RIGHT) {
                moveRightPac();
                if (getPacman().isMouth())
                    items[getPacman().getI()][getPacman().getJ()] = 11;
                else
                    items[getPacman().getI()][getPacman().getJ()] = 2;
                getPacman().setMouth(!getPacman().isMouth());
            } else if (getPacman().getKeyPressed() == KeyEvent.VK_DOWN) {
                moveDownPac();
                if (getPacman().isMouth())
                    items[getPacman().getI()][getPacman().getJ()] = 5;
                else
                    items[getPacman().getI()][getPacman().getJ()] = 6;
                getPacman().setMouth(!getPacman().isMouth());
            } else if (getPacman().getKeyPressed() == KeyEvent.VK_UP) {
                moveUpPac();
                if (getPacman().isMouth())
                    items[getPacman().getI()][getPacman().getJ()] = 7;
                else
                    items[getPacman().getI()][getPacman().getJ()] = 8;
                getPacman().setMouth(!getPacman().isMouth());
            }

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            checkIfVictory();
        }
    }

    public void checkIfVictory() {
        for (int[] item : items)
            for (int j = 0; j < items[0].length; j++)
                if (item[j] == 1) {
                    inGame = true;
                    return;
                }
        inGame = false;
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
        } else if (items[rowIndex][columnIndex] == 5) {
            ImageIcon pac = (new ImageIcon("src/images/pacBCl.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 6) {
            ImageIcon pac = (new ImageIcon("src/images/pacBO.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 7) {
            ImageIcon pac = (new ImageIcon("src/images/pacFrCl.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 8) {
            ImageIcon pac = (new ImageIcon("src/images/pacFrO.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 9) {
            ImageIcon pac = (new ImageIcon("src/images/pacLCl.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 10) {
            ImageIcon pac = (new ImageIcon("src/images/pacLO.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 11) {
            ImageIcon pac = (new ImageIcon("src/images/pacRCl.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
        } else if (items[rowIndex][columnIndex] == 12) {
            ImageIcon pac = (new ImageIcon("src/images/pacRO.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return pac;
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
        int ghostMax = 3;
        int ghostCounter = 0;
        pacman.setI(height / 2);
        pacman.setJ(width / 2);
        pacman.setStartI(height / 2);
        pacman.setStartJ(width / 2);
        matrix[height / 2][width / 2] = 2;
        matrix[height / 3][width / 3] = 4;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1)
                    matrix[i][j] = 0;
                else {
                    int rand = random.nextInt(10) + 1;
                    if (matrix[i][j] == 2)
                        continue;
                    else if (matrix[i][j] == 4)
                        continue;
                    else if (counter == counterMax)
                        matrix[i][j] = 1;
                    else if (ghostCounter == ghostMax)
                        matrix[i][j] = 1;
                    else if (rand == 1) {
                        matrix[i][j] = 0;
                        counter++;
                    } /*else if (matrix[i][j] == 4) {
                        matrix[i][j] = 0;
                        ghostCounter++;
                    }*/ else
                        matrix[i][j] = 1;
                }
            }
        }
        this.items = matrix;
    }

}