package customVariables.variables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

public class Pacman extends JLabel implements Runnable {
    private int hp = 3;
    private int speed = 4;
    private int score = 0;
    private boolean isFirstImage = true;
    private int keyPressed;
    private int i;
    private int j;

    public Pacman(/*int width, int height*/) {
//        setIcon(scaleImage(pacIcon, height, width));
        setOpaque(true);
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    //    @Override
//    public int getWidth() {
//        return width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    @Override
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }

    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> {
                if (keyPressed == KeyEvent.VK_LEFT) {
                    if (isFirstImage)
                        this.setIcon(new ImageIcon("src/pacLCl.png"));
                    else
                        this.setIcon(new ImageIcon("src/pacLO.png"));
                    isFirstImage = !isFirstImage;
                } else if (keyPressed == KeyEvent.VK_RIGHT) {
                    if (isFirstImage)
                        this.setIcon(new ImageIcon("src/pacRCl.png"));
                    else
                        this.setIcon(new ImageIcon("src/pacRO.png"));
                    isFirstImage = !isFirstImage;
                } else if (keyPressed == KeyEvent.VK_DOWN) {
                    if (isFirstImage)
                        this.setIcon(new ImageIcon("src/pacBCl.png"));
                    else
                        this.setIcon(new ImageIcon("src/pacBO.png"));
                    isFirstImage = !isFirstImage;
                } else if (keyPressed == KeyEvent.VK_UP) {
                    if (isFirstImage)
                        this.setIcon(new ImageIcon("src/pacFrCl.png"));
                    else
                        this.setIcon(new ImageIcon("src/pacFrO.png"));
                    isFirstImage = !isFirstImage;
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}