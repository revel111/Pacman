package customVariables.variables;

import operations.TableModel;

import java.util.Random;

public class Ghost extends Thread{
    private int i;
    private int j;
    private final TableModel tableModel;
    int boost = -1;

    public Ghost(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public void moveGhostCoordinates(int iN, int jN) {
//    1  dot
//    13 dot & ghost
//    20 boost
//    30 boost & dot & ghost
//    40 boost & dot
//    50 boost & ghost
        if (tableModel.getItems()[i + iN][j + jN] != 0 && tableModel.getItems()[i + iN][j + jN] != 4 && tableModel.getItems()[i + iN][j + jN] != 13 && tableModel.getItems()[i + iN][j + jN] != 50 && tableModel.getItems()[i + iN][j + jN] != 51 && tableModel.getItems()[i + iN][j + jN] != 52 && tableModel.getItems()[i + iN][j + jN] != 53 && tableModel.getItems()[i + iN][j + jN] != 54 && tableModel.getItems()[i + iN][j + jN] != 30 && tableModel.getItems()[i + iN][j + jN] != 31 && tableModel.getItems()[i + iN][j + jN] != 32 && tableModel.getItems()[i + iN][j + jN] != 33 && tableModel.getItems()[i + iN][j + jN] != 34) {
            if (tableModel.getItems()[i][j] == 13) {
                tableModel.getItems()[i][j] = 1;
                if (boost == 0) {
                    tableModel.getItems()[i][j] = 40;
                    boost = -1;
                } else if (boost == 1) {
                    tableModel.getItems()[i][j] = 41;
                    boost = -1;
                } else if (boost == 2) {
                    tableModel.getItems()[i][j] = 42;
                    boost = -1;
                } else if (boost == 3) {
                    tableModel.getItems()[i][j] = 43;
                    boost = -1;
                } else if (boost == 4) {
                    tableModel.getItems()[i][j] = 44;
                    boost = -1;
                }
            } else if (tableModel.getItems()[i][j] == 4) {
                tableModel.getItems()[i][j] = 3;
                if (boost == 0) {
                    tableModel.getItems()[i][j] = 20;
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
            } else if (tableModel.getItems()[i][j] == 30) {
                tableModel.getItems()[i][j] = 40;
            } else if (tableModel.getItems()[i][j] == 51) {
                tableModel.getItems()[i][j] = 21;
            } else if (tableModel.getItems()[i][j] == 31) {
                tableModel.getItems()[i][j] = 41;
            } else if (tableModel.getItems()[i][j] == 52) {
                tableModel.getItems()[i][j] = 22;
            } else if (tableModel.getItems()[i][j] == 32) {
                tableModel.getItems()[i][j] = 42;
            } else if (tableModel.getItems()[i][j] == 53) {
                tableModel.getItems()[i][j] = 23;
            } else if (tableModel.getItems()[i][j] == 33) {
                tableModel.getItems()[i][j] = 43;
            } else if (tableModel.getItems()[i][j] == 54) {
                tableModel.getItems()[i][j] = 24;
            } else if (tableModel.getItems()[i][j] == 34) {
                tableModel.getItems()[i][j] = 44;
            }

            if (tableModel.getItems()[i + iN][j + jN] == 5 || tableModel.getItems()[i + iN][j + jN] == 2 || tableModel.getItems()[i + iN][j + jN] == 6 || tableModel.getItems()[i + iN][j + jN] == 7 || tableModel.getItems()[i + iN][j + jN] == 8 || tableModel.getItems()[i + iN][j + jN] == 9 || tableModel.getItems()[i + iN][j + jN] == 10 || tableModel.getItems()[i + iN][j + jN] == 11 || tableModel.getItems()[i + iN][j + jN] == 12 || tableModel.getItems()[i][j] == 5 || tableModel.getItems()[i][j] == 2 || tableModel.getItems()[i][j] == 6 || tableModel.getItems()[i][j] == 7 || tableModel.getItems()[i][j] == 8 || tableModel.getItems()[i][j] == 9 || tableModel.getItems()[i][j] == 10 || tableModel.getItems()[i][j] == 11 || tableModel.getItems()[i][j] == 12) {
                if (!tableModel.getPacman().isInvulnerability())
                    tableModel.getPacman().hitPac();
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 1) {
                tableModel.getItems()[i + iN][j + jN] = 13;
            } else if (tableModel.getItems()[i + iN][j + jN] == 20) {
                tableModel.getItems()[i + iN][j + jN] = 50;
            } else if (tableModel.getItems()[i + iN][j + jN] == 40) {
                tableModel.getItems()[i + iN][j + jN] = 30;
            } else if (tableModel.getItems()[i + iN][j + jN] == 21) {
                tableModel.getItems()[i + iN][j + jN] = 51;
            } else if (tableModel.getItems()[i + iN][j + jN] == 41) {
                tableModel.getItems()[i + iN][j + jN] = 31;
            } else if (tableModel.getItems()[i + iN][j + jN] == 22) {
                tableModel.getItems()[i + iN][j + jN] = 52;
            } else if (tableModel.getItems()[i + iN][j + jN] == 42) {
                tableModel.getItems()[i + iN][j + jN] = 32;
            } else if (tableModel.getItems()[i + iN][j + jN] == 23) {
                tableModel.getItems()[i + iN][j + jN] = 53;
            } else if (tableModel.getItems()[i + iN][j + jN] == 43) {
                tableModel.getItems()[i + iN][j + jN] = 33;
            } else if (tableModel.getItems()[i + iN][j + jN] == 24) {
                tableModel.getItems()[i + iN][j + jN] = 54;
            } else if (tableModel.getItems()[i + iN][j + jN] == 44) {
                tableModel.getItems()[i + iN][j + jN] = 34;
            } else {
                tableModel.getItems()[i + iN][j + jN] = 4;
            }
            i += iN;
            j += jN;
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!this.isInterrupted()) {
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
                System.out.println("Ghost was removed");
            }
        }
    }
}