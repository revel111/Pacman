package operations;

import customVariables.variables.Ghost;
import customVariables.variables.Pacman;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Random;

public class TableModel extends AbstractTableModel {
    private int[][] items;
    private Pacman pacman = new Pacman(this);
    private Ghost ghost = new Ghost(this);
    boolean inGame = true;

    private ArrayList<Ghost> ghosts = new ArrayList<>();

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

    public Pacman getPacman() {
        return pacman;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public int[][] getItems() {
        return items;
    }

    public void checkIfVictory() {
        for (int[] item : items)
            for (int j = 0; j < items[0].length; j++)
                if (item[j] == 1 || item[j] == 13 || item[j] == 30 || item[j] == 40 || item[j] == 31 || item[j] == 41 || item[j] == 32 || item[j] == 42 || item[j] == 33 || item[j] == 43 || item[j] == 34 || item[j] == 44) {
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
            JLabel jLabel = new JLabel();
            fireTableCellUpdated(rowIndex, columnIndex);
            return jLabel;
        } else if (items[rowIndex][columnIndex] == 4 || items[rowIndex][columnIndex] == 13 || items[rowIndex][columnIndex] == 50 || items[rowIndex][columnIndex] == 51 || items[rowIndex][columnIndex] == 52 || items[rowIndex][columnIndex] == 53 || items[rowIndex][columnIndex] == 54 || items[rowIndex][columnIndex] == 40 || items[rowIndex][columnIndex] == 41 || items[rowIndex][columnIndex] == 42 || items[rowIndex][columnIndex] == 43 || items[rowIndex][columnIndex] == 44) {
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
        } else if (items[rowIndex][columnIndex] == 20 || items[rowIndex][columnIndex] == 30) {//hp
            ImageIcon boost = (new ImageIcon("src/images/boostBlue.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return boost;
        } else if (items[rowIndex][columnIndex] == 21 || items[rowIndex][columnIndex] == 31) {//points
            ImageIcon boost = (new ImageIcon("src/images/boostRed.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return boost;
        } else if (items[rowIndex][columnIndex] == 22 || items[rowIndex][columnIndex] == 32) {//speed
            ImageIcon boost = (new ImageIcon("src/images/boostOrange.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return boost;
        } else if (items[rowIndex][columnIndex] == 23 || items[rowIndex][columnIndex] == 33) {//invulnerability
            ImageIcon boost = (new ImageIcon("src/images/boostBrown.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return boost;
        } else if (items[rowIndex][columnIndex] == 24 || items[rowIndex][columnIndex] == 34) {//killa
            ImageIcon boost = (new ImageIcon("src/images/boostGreen.png"));
            fireTableCellUpdated(rowIndex, columnIndex);
            return boost;
        }
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


//        ghost.setI(height / 3);
//        ghost.setJ(height / 3);

        matrix[height / 2][width / 2] = 2;
        matrix[height / 3][width / 3] = 13;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1)
                    matrix[i][j] = 0;
                else {
                    int rand = random.nextInt(10) + 1;
                    if (matrix[i][j] == 2)
                        continue;
                    else if (matrix[i][j] == 13)
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

        for (int i = 0; i < 3; i++) {
            Ghost ghost = new Ghost(this);
            ghost.setI(height / 3);
            ghost.setJ(height / 3);
            ghosts.add(ghost);
        }
    }

}