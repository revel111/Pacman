package customVariables.variables;

import operations.TableModel;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Pacman extends JLabel {
    private int hp = 10;
    private int score = 0;
    private boolean mouth = true;
    private int keyPressed;
    private int i;
    private int j;
    private int startI;
    private int startJ;
    private int speed = 300;
    private int boost = -1;
    private final TableModel tableModel;

    public Pacman(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public boolean isMouth() {
        return mouth;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getScore() {
        return score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getStartI() {
        return startI;
    }

    public void setStartI(int startI) {
        this.startI = startI;
    }

    public int getStartJ() {
        return startJ;
    }

    public void setStartJ(int startJ) {
        this.startJ = startJ;
    }

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public void movePacCoordinates(int iN, int jN) {
        if (tableModel.getItems()[i + iN][j + jN] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i + iN][j + jN] == 1) //dot
                score += 10;
            else if (tableModel.getItems()[i + iN][j + jN] == 13) { //ghost + dot
                score += 10;
                hp -= 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 20) {
                hp += 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 30) {
                score += 10;
            } else if (tableModel.getItems()[i + iN][j + jN] == 40) {
                score += 10;
                hp += 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 50) {
                score += 10;
            } else if (tableModel.getItems()[i + iN][j + jN] == 21) {
                score += 50;
            } else if (tableModel.getItems()[i + iN][j + jN] == 31) {
                hp -= 1;
                score += 60;
            } else if (tableModel.getItems()[i + iN][j + jN] == 41) {
                score += 60;
                hp -= 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 51) {
                score += 10;
                hp -= 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 22) {
                boost = 2;
            } else if (tableModel.getItems()[i + iN][j + jN] == 32) {
                boost = 2;
                score += 10;
                hp -= 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 42) {
                score += 10;
                boost = 2;
            } else if (tableModel.getItems()[i + iN][j + jN] == 52) {
                boost = 2;
                hp -= 1;
            } else if (tableModel.getItems()[i + iN][j + jN] == 4 || tableModel.getItems()[i][j] == 4 || tableModel.getItems()[i][j] == 13 || tableModel.getItems()[i][j] == 50 || tableModel.getItems()[i][j] == 51 || tableModel.getItems()[i][j] == 52 || tableModel.getItems()[i][j] == 53 || tableModel.getItems()[i][j] == 54 || tableModel.getItems()[i][j] == 40 || tableModel.getItems()[i][j] == 41 || tableModel.getItems()[i][j] == 42 || tableModel.getItems()[i][j] == 43 || tableModel.getItems()[i][j] == 44) {
                hp -= 1;
                tableModel.getItems()[i][j] = 3;
                i = startI;
                j = startJ;
                tableModel.getItems()[startI][startJ] = 2;
                return;
            }
            i += iN;
            j += jN;
        }
    }

    public void movePac() {
        if (keyPressed == KeyEvent.VK_LEFT) {
            movePacCoordinates(0, -1);
            if (isMouth())
                tableModel.getItems()[i][j] = 9;
            else
                tableModel.getItems()[i][j] = 10;
            mouth = !mouth;
        } else if (keyPressed == KeyEvent.VK_RIGHT) {
            movePacCoordinates(0, 1);
            if (isMouth())
                tableModel.getItems()[i][j] = 11;
            else
                tableModel.getItems()[i][j] = 2;
            mouth = !mouth;
        } else if (keyPressed == KeyEvent.VK_DOWN) {
            movePacCoordinates(1, 0);
            if (isMouth())
                tableModel.getItems()[i][j] = 5;
            else
                tableModel.getItems()[i][j] = 6;
            mouth = !mouth;
        } else if (keyPressed == KeyEvent.VK_UP) {
            movePacCoordinates(-1, 0);
            if (isMouth())
                tableModel.getItems()[i][j] = 7;
            else
                tableModel.getItems()[i][j] = 8;
            mouth = !mouth;
        }
        try {
            Thread.sleep(tableModel.getPacman().getSpeed());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}