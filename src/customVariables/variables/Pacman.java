package customVariables.variables;

import operations.TableModel;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Pacman extends JLabel {
    private int hp = 3;
    private boolean invulnerability = false;
    private boolean neutralized = false;
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

    public int getHp() {
        return hp;
    }

    public int getScore() {
        return score;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setStartI(int startI) {
        this.startI = startI;
    }

    public void setStartJ(int startJ) {
        this.startJ = startJ;
    }

    public void setKeyPressed(int keyPressed) {
        this.keyPressed = keyPressed;
    }

    public boolean isInvulnerability() {
        return invulnerability;
    }

    public void movePacCoordinates(int iN, int jN) {

//    1  dot
//    13 dot & ghost
//    20 boost
//    30 boost & dot & ghost
//    40 boost & dot
//    50 boost & ghost
        if (tableModel.getItems()[i + iN][j + jN] != 0) {
            tableModel.getItems()[i][j] = 3;
            if (tableModel.getItems()[i + iN][j + jN] == 1)
                score += 10;
            else if (tableModel.getItems()[i + iN][j + jN] == 13) {
                if (!invulnerability) {
                    score += 10;
                    hitPac();
                }
                return;
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
                if (!invulnerability) {
                    score += 60;
                    hitPac();
                }
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 41) {
                score += 60;
            } else if (tableModel.getItems()[i + iN][j + jN] == 51) {
                if (!invulnerability) {
                    score += 10;
                    hitPac();
                }
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 22) {
                this.addSpeed();
            } else if (tableModel.getItems()[i + iN][j + jN] == 32) {
                this.addSpeed();
                score += 10;
                if (!invulnerability)
                    hitPac();
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 42) {
                score += 10;
                this.addSpeed();
            } else if (tableModel.getItems()[i + iN][j + jN] == 52) {
                this.addSpeed();
                if (!invulnerability)
                    hitPac();
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 23) {
                addInvulnerability();
            } else if (tableModel.getItems()[i + iN][j + jN] == 33) {
                addInvulnerability();
                score += 10;
            } else if (tableModel.getItems()[i + iN][j + jN] == 43) {
                score += 10;
                addInvulnerability();
            } else if (tableModel.getItems()[i + iN][j + jN] == 53) {
                addInvulnerability();
            } else if (tableModel.getItems()[i + iN][j + jN] == 24) {
                neutralizeGhost();
                neutralized = true;
            } else if (tableModel.getItems()[i + iN][j + jN] == 34) {
                neutralizeGhost();
                neutralized = true;
                score += 10;
                if (!invulnerability)
                    hitPac();
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 44) {
                score += 10;
                neutralizeGhost();
                neutralized = true;
            } else if (tableModel.getItems()[i + iN][j + jN] == 54) {
                neutralizeGhost();
                neutralized = true;
                if (!invulnerability)
                    hitPac();
                return;
            }
            i += iN;
            j += jN;
        }
    }

    public void hitPac() {
        hp -= 1;
        tableModel.getItems()[i][j] = 3;
        i = startI;
        j = startJ;
        tableModel.getItems()[i][j] = 2;
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
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void addSpeed() {
        new Thread(() -> {
            speed = 150;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            speed = 300;
        }).start();
    }

    public synchronized void addInvulnerability() {
        new Thread(() -> {
            invulnerability = true;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            invulnerability = false;
        }).start();
    }

    public void neutralizeGhost() {
        if (!neutralized) {
            Ghost ghost = tableModel.getGhosts().get(0);

            int gi = ghost.getI();
            int gj = ghost.getJ();

            tableModel.getGhosts().get(0).stop();//.interrupt does not work properly
            tableModel.getGhosts().remove(0);
            tableModel.getItems()[gi][gj] = 1;
        }
    }
}