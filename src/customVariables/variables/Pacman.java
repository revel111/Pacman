package customVariables.variables;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Pacman extends Thread{
    private int hp = 3;
    private int speed = 4;
    private int score = 0;
    private boolean isFirstImage = true;
    private JLabel jLabel;
    private int keyPressed;
    private int width;
    private int height;

//    public Pacman(int width, int height) {
//        this.width = width;
//        this.height = height;
//    }

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

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
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

    public JLabel getjLabel() {
        return jLabel;
    }

    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> {
                if (keyPressed == KeyEvent.VK_LEFT) {
                    if (isFirstImage)
                        jLabel.setIcon(new ImageIcon("src/images/pacLCl.png"));
                    else
                        jLabel.setIcon(new ImageIcon("src/images/pacLO.png"));
                    isFirstImage = !isFirstImage;
                } else if (keyPressed == KeyEvent.VK_RIGHT) {
                    if (isFirstImage)
                        jLabel.setIcon(new ImageIcon("src/images/pacRCl.png"));
                    else
                        jLabel.setIcon(new ImageIcon("src/images/pacRO.png"));
                    isFirstImage = !isFirstImage;
                } else if (keyPressed == KeyEvent.VK_DOWN) {
                    if (isFirstImage)
                        jLabel.setIcon(new ImageIcon("src/images/pacBCl.png"));
                    else
                        jLabel.setIcon(new ImageIcon("src/images/pacBO.png"));
                    isFirstImage = !isFirstImage;
                } else if (keyPressed == KeyEvent.VK_UP) {
                    if (isFirstImage)
                        jLabel.setIcon(new ImageIcon("src/images/pacFrCl.png"));
                    else
                        jLabel.setIcon(new ImageIcon("src/images/pacFrO.png"));
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