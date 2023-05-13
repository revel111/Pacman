package customVariables.variables;

import operations.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

    private TableModel tableModel;

    public Pacman() {
//        setIcon(scaleImage(pacIcon, height, width));
        setOpaque(true);
//        this.tableModel = tableModel;
//        setBackground(table.getBackground());
//        setForeground(table.getForeground());
//        setVisible(true);
        setBackground(Color.YELLOW);
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