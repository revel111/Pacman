package customVariables.variables;

import operations.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

public class Pacman extends JLabel implements Runnable {
    private int hp = 3;
    private int score = 0;
    private boolean isFirstImage = true;
    private int keyPressed;
    private int i;
    private int j;

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

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    public int getKeyPressed() {
        return keyPressed;
    }

    public void move() {
    }

    @Override
    public void run() {
    }
}