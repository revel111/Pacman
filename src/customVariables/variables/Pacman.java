package customVariables.variables;

import operations.TableModel;

import javax.swing.*;
import java.awt.event.KeyEvent;


public class Pacman extends JLabel {
    private int hp = 1;
    private int score = 0;
    private boolean mouth = true;
    private int keyPressed;
    private int i;
    private int j;
    private int startI;
    private int startJ;
    private int speed = 300;
    private final TableModel tableModel;

    public Pacman(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public boolean isMouth() {
        return mouth;
    }

    public void setMouth(boolean mouth) {
        this.mouth = mouth;
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

    public void setScore(int score) {
        this.score = score;
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

    public int getKeyPressed() {
        return keyPressed;
    }

    public void movePacCoordinates(int iN, int jN) {
        if (tableModel.getItems()[i + iN][j + jN] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i + iN][j + jN] == 1) //dot
                score += 10;
            else if (tableModel.getItems()[i + iN][j + jN] == 4)
                hp -= 1;
            i += iN;
            j += jN;
        }
    }

    public void movePac() {
        while (tableModel.isInGame()) {
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
}