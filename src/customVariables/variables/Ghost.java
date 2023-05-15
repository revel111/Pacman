package customVariables.variables;

import operations.TableModel;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Ghost {
    private int i;
    private int j;
    private int startJ;
    private int startI;
    private TableModel tableModel;
    int boost = -1;

    public Ghost(TableModel tableModel) {
        this.tableModel = tableModel;
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

    public int getStartJ() {
        return startJ;
    }

    public void setStartJ(int startJ) {
        this.startJ = startJ;
    }

    public int getStartI() {
        return startI;
    }

    public void setStartI(int startI) {
        this.startI = startI;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public void moveGhostCoordinates(int iN, int jN) {
        if (tableModel.getItems()[i + iN][j + jN] != 0 /*|| tableModel.getItems()[i + iN][j + jN] != 4*/) {//wall
            if (tableModel.getItems()[i][j] == 13) {
                tableModel.getItems()[i][j] = 1;
                if (boost == 0) {//точка и буст
                    tableModel.getItems()[i][j] = 30;  //blue hp
                    boost = -1;
                } else if (boost == 1) {
                    tableModel.getItems()[i][j] = 31;
                    boost = -1;
                } else if (boost == 2) {
                    tableModel.getItems()[i][j] = 32;
                    boost = -1;
                } else if (boost == 3) {
                    tableModel.getItems()[i][j] = 33;
                    boost = -1;
                } else if (boost == 4) {
                    tableModel.getItems()[i][j] = 34;
                    boost = -1;
                }
            } else if (tableModel.getItems()[i][j] == 4) {
                tableModel.getItems()[i][j] = 3;
                if (boost == 0) {//prosto
                    tableModel.getItems()[i][j] = 20;  //blue hp
                    boost = -1;
                } else if (boost == 1) {
                    tableModel.getItems()[i][j] = 21;
                    boost = -1;
                } else if (boost == 2) {
                    tableModel.getItems()[i][j] = 22;
                    boost = -1;
                } else if (boost == 3) {
                    tableModel.getItems()[i][j] = 23;
                    boost = -1;
                } else if (boost == 4) {
                    tableModel.getItems()[i][j] = 24;
                    boost = -1;
                }
            } else if (tableModel.getItems()[i][j] == 50) {
                tableModel.getItems()[i][j] = 20;
            } else if (tableModel.getItems()[i][j] == 40) {
                tableModel.getItems()[i][j] = 30;
            } else if (tableModel.getItems()[i][j] == 51) {
                tableModel.getItems()[i][j] = 21;
            } else if (tableModel.getItems()[i][j] == 41) {
                tableModel.getItems()[i][j] = 31;
            } else if (tableModel.getItems()[i][j] == 52) {
                tableModel.getItems()[i][j] = 22;
            } else if (tableModel.getItems()[i][j] == 42) {
                tableModel.getItems()[i][j] = 32;
            } else if (tableModel.getItems()[i][j] == 53) {
                tableModel.getItems()[i][j] = 23;
            } else if (tableModel.getItems()[i][j] == 43) {
                tableModel.getItems()[i][j] = 33;
            } else if (tableModel.getItems()[i][j] == 54) {
                tableModel.getItems()[i][j] = 24;
            } else if (tableModel.getItems()[i][j] == 44) {
                tableModel.getItems()[i][j] = 34;
            }

            if (tableModel.getItems()[i + iN][j + jN] == 5 || tableModel.getItems()[i + iN][j + jN] == 2 || tableModel.getItems()[i + iN][j + jN] == 6 || tableModel.getItems()[i + iN][j + jN] == 7 || tableModel.getItems()[i + iN][j + jN] == 8 || tableModel.getItems()[i + iN][j + jN] == 9 || tableModel.getItems()[i + iN][j + jN] == 10 || tableModel.getItems()[i + iN][j + jN] == 11 || tableModel.getItems()[i + iN][j + jN] == 12) {
                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
                tableModel.getItems()[tableModel.getPacman().getStartI()][tableModel.getPacman().getStartJ()] = 2;
                i = startI;
                j = startJ;
                tableModel.getItems()[tableModel.getPacman().getI()][tableModel.getPacman().getJ()] = 3;
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 1) { // есть точка
                tableModel.getItems()[i + iN][j + jN] = 13; //призрак и точка
            } else if (tableModel.getItems()[i + iN][j + jN] == 20) {// буст и призрак
                tableModel.getItems()[i + iN][j + jN] = 50; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 30) {// буст и точка и призрак
                tableModel.getItems()[i + iN][j + jN] = 40; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 21) {// буст и призрак
                tableModel.getItems()[i + iN][j + jN] = 51; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 31) {// буст и точка и призрак
                tableModel.getItems()[i + iN][j + jN] = 41; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 22) {// буст и призрак
                tableModel.getItems()[i + iN][j + jN] = 52; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 32) {// буст и точка и призрак
                tableModel.getItems()[i + iN][j + jN] = 42; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 23) {// буст и призрак
                tableModel.getItems()[i + iN][j + jN] = 53; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 33) {// буст и точка и призрак
                tableModel.getItems()[i + iN][j + jN] = 43; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 24) {// буст и призрак
                tableModel.getItems()[i + iN][j + jN] = 54; //буст и призрак
            } else if (tableModel.getItems()[i + iN][j + jN] == 34) {// буст и точка и призрак
                tableModel.getItems()[i + iN][j + jN] = 44; //буст и призрак
            } else {
                tableModel.getItems()[i + iN][j + jN] = 4;
            }
            i += iN;
            j += jN;
        }
    }


    public void moveGhost() {
        Random random = new Random();
        while (tableModel.isInGame()) {
            int randMove = random.nextInt(4) + 1;
            switch (randMove) {
                case 1 -> this.moveGhostCoordinates(0, -1);//left
                case 2 -> this.moveGhostCoordinates(0, 1);//right
                case 3 -> this.moveGhostCoordinates(-1, 0);//up
                case 4 -> this.moveGhostCoordinates(1, 0);//down
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}