package operations;

import customVariables.variables.Ghost;
import customVariables.variables.Pacman;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Random;

public class TableModel extends AbstractTableModel {
    private int[][] items;
    private final Pacman pacman = new Pacman(this);
    boolean inGame = true;
    private final ArrayList<Ghost> ghosts = new ArrayList<>();

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
            return (new ImageIcon("src/images/wall.png"));
        } else if (items[rowIndex][columnIndex] == 1) {
            return (new ImageIcon("src/images/dot.png"));
        } else if (items[rowIndex][columnIndex] == 2) {
            return (new ImageIcon("src/images/pacRO.png"));
        } else if (items[rowIndex][columnIndex] == 3) {
            return new JLabel();
        } else if (items[rowIndex][columnIndex] == 4 || items[rowIndex][columnIndex] == 13 || items[rowIndex][columnIndex] == 50 || items[rowIndex][columnIndex] == 51 || items[rowIndex][columnIndex] == 52 || items[rowIndex][columnIndex] == 53 || items[rowIndex][columnIndex] == 54 || items[rowIndex][columnIndex] == 30 || items[rowIndex][columnIndex] == 31 || items[rowIndex][columnIndex] == 32 || items[rowIndex][columnIndex] == 33 || items[rowIndex][columnIndex] == 34) {
            return (new ImageIcon("src/images/ghost.png"));
        } else if (items[rowIndex][columnIndex] == 5) {
            return (new ImageIcon("src/images/pacBCl.png"));
        } else if (items[rowIndex][columnIndex] == 6) {
            return (new ImageIcon("src/images/pacBO.png"));
        } else if (items[rowIndex][columnIndex] == 7) {
            return (new ImageIcon("src/images/pacFrCl.png"));
        } else if (items[rowIndex][columnIndex] == 8) {
            return (new ImageIcon("src/images/pacFrO.png"));
        } else if (items[rowIndex][columnIndex] == 9) {
            return (new ImageIcon("src/images/pacLCl.png"));
        } else if (items[rowIndex][columnIndex] == 10) {
            return (new ImageIcon("src/images/pacLO.png"));
        } else if (items[rowIndex][columnIndex] == 11) {
            return (new ImageIcon("src/images/pacRCl.png"));
        } else if (items[rowIndex][columnIndex] == 12) {
            return (new ImageIcon("src/images/pacRO.png"));
        } else if (items[rowIndex][columnIndex] == 20 || items[rowIndex][columnIndex] == 40) {//hp
            return (new ImageIcon("src/images/boostBlue.png"));
        } else if (items[rowIndex][columnIndex] == 21 || items[rowIndex][columnIndex] == 41) {//points
            return (new ImageIcon("src/images/boostRed.png"));
        } else if (items[rowIndex][columnIndex] == 22 || items[rowIndex][columnIndex] == 42) {//speed
            return (new ImageIcon("src/images/boostOrange.png"));
        } else if (items[rowIndex][columnIndex] == 23 || items[rowIndex][columnIndex] == 43) {//invulnerability
            return (new ImageIcon("src/images/boostBrown.png"));
        } else if (items[rowIndex][columnIndex] == 24 || items[rowIndex][columnIndex] == 44) {//neutralize
            return (new ImageIcon("src/images/boostGreen.png"));
        }
        return null;
    }

    public void generateMap(int height, int width) {
        Random random = new Random();
        int[][] matrix = new int[height][width];

        pacman.setI(height / 2);
        pacman.setJ(width / 2);
        pacman.setStartI(height / 2);
        pacman.setStartJ(width / 2);

        int rateForMiddleSpace = width * height / 100;
        if (rateForMiddleSpace > 4)
            rateForMiddleSpace = 4;

        int rateForBorderWalls = rateForMiddleSpace;

        for (int i = 0; i < 3; i++) {
            Ghost ghost = new Ghost(this);
            if (i == 0) {
                ghost.setI(1);
                ghost.setJ(1);
            } else if (i == 1) {
                ghost.setI(height - 2);
                ghost.setJ(width - 2);
            } else {
                ghost.setI(height - 2);
                ghost.setJ(1);
            }
            ghosts.add(ghost);
        }

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1)
                    matrix[i][j] = 0;
                else if (i == 1 || j == 1 || i == height - 2 || j == width - 2)
                    matrix[i][j] = 1;

        int cellsToFill = Math.min(rateForMiddleSpace, Math.min(height, width));

        for (int i = height / 2 - cellsToFill / 2; i < height / 2 - cellsToFill / 2 + cellsToFill; i++)
            for (int j = width / 2 - cellsToFill / 2; j < width / 2 - cellsToFill / 2 + cellsToFill; j++)
                matrix[i][j] = 1;

        for (int i = 1; i < height - 1; i++)
            for (int j = 1; j < width - 1; j++) {
                int rand = random.nextInt(10) + 1;
                if (i <= rateForBorderWalls || i >= height - 1 - rateForBorderWalls || j <= rateForBorderWalls || j >= width - 1 - rateForBorderWalls)
                    if (rand == 1 && rateForMiddleSpace > 2)
                        matrix[i][j] = 0;
                    else
                        matrix[i][j] = 1;
            }
        matrix[height / 2][width / 2] = 2;

        for (int j = 1; j < width - 1; j++)
            if (matrix[height / 2][j] != 2)
                matrix[height / 2][j] = 1;

        for (int i = 1; i < height - 1; i++)
            if (matrix[i][width / 2] != 2) {
                matrix[i][width / 2] = 1;
                if (rateForMiddleSpace == 4) {
                    matrix[i][width / 3] = 1;
                    matrix[i][width / 3 + width / 3] = 1;
                }
            }

        this.items = matrix;
    }
}