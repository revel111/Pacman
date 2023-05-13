package customVariables.variables;

import operations.TableModel;

import javax.swing.*;
import java.awt.event.KeyEvent;


public class Pacman extends JLabel implements Runnable {
    private int hp = 1;
    private int score = 0;
    private boolean mouth = true;
    private int keyPressed;
    private int i;
    private int j;
    private int startI;
    private int startJ;
    private int speed = 300;
    private TableModel tableModel;

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

    public void moveLeftPac() {
        if (tableModel.getItems()[i][j - 1] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i][j - 1] == 1) //dot
                score += 10;
            else if (tableModel.getItems()[i][j - 1] == 4) {
                hp -= 1;
            }
            j -= 1;
        }
    }

    public void moveRightPac() {
        if (tableModel.getItems()[i][j + 1] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i][j + 1] == 1) //dot
                score += 10;
            else if (tableModel.getItems()[i][j + 1] == 4) {
                hp -= 1;
            }
            j += 1;
        }
    }

    public void moveUpPac() {
        if (tableModel.getItems()[i - 1][j] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i - 1][j] == 1) //dot
                score += 10;
            else if (tableModel.getItems()[i - 1][j] == 4) {
                hp -= 1;
            }
            i -= 1;
        }
    }

    public void moveDownPac() {
        if (tableModel.getItems()[i + 1][j] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i + 1][j] == 1) //dot
                score += 10;
            else if (tableModel.getItems()[i + 1][j] == 4) {
                hp -= 1;
            }
            i += 1;
        }
    }

    public void movePac() {
        while (tableModel.isInGame()) {
            if (hp == 0)
                tableModel.setInGame(false);
            if (keyPressed == KeyEvent.VK_LEFT) {
                moveLeftPac();
                if (isMouth())
                    tableModel.getItems()[i][j] = 9;
                else
                    tableModel.getItems()[i][j] = 10;
                mouth = !mouth;
            } else if (keyPressed == KeyEvent.VK_RIGHT) {
                moveRightPac();
                if (isMouth())
                    tableModel.getItems()[i][j] = 11;
                else
                    tableModel.getItems()[i][j] = 2;
                mouth = !mouth;
            } else if (keyPressed == KeyEvent.VK_DOWN) {
                moveDownPac();
                if (isMouth())
                    tableModel.getItems()[i][j] = 5;
                else
                    tableModel.getItems()[i][j] = 6;
                mouth = !mouth;
            } else if (keyPressed == KeyEvent.VK_UP) {
                moveUpPac();
                if (isMouth())
                    tableModel.getItems()[i][j] = 7;
                else
                    tableModel.getItems()[i][j] = 8;
                mouth = !mouth;
            }

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tableModel.checkIfVictory();
        }
    }


//    public void move() {
//        while (true){
//            // if direction(right) -> movePacRight()
//            if(keyPressed == KeyEvent.VK_RIGHT) {
//
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    @Override
    public void run() {
    }
}