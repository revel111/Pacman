package customVariables.variables;

import operations.TableModel;

import java.util.Random;

public class Ghost {
    private int i;
    private int j;
    private int startJ;
    private int startI;
    private TableModel tableModel;

    public Ghost(TableModel tableModel) {
        this.tableModel = tableModel;
    }


    public void setTableModel(TableModel tableModel) {
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

    public void moveGhostCoordinates(int iN, int jN) {
        if (tableModel.getItems()[i + iN][j + jN] != 0 /*|| tableModel.getItems()[i + iN][j + jN] != 4*/) {//wall
            if (tableModel.getItems()[i][j] == 13) {
                tableModel.getItems()[i][j] = 1;
            } else
                tableModel.getItems()[i][j] = 3;

            if (tableModel.getItems()[i + iN][j + jN] == 5 || tableModel.getItems()[i + iN][j + jN] == 2 || tableModel.getItems()[i + iN][j + jN] == 6 || tableModel.getItems()[i + iN][j + jN] == 7 || tableModel.getItems()[i + iN][j + jN] == 8 || tableModel.getItems()[i + iN][j + jN] == 9 || tableModel.getItems()[i + iN][j + jN] == 10 || tableModel.getItems()[i + iN][j + jN] == 11 || tableModel.getItems()[i + iN][j + jN] == 12) {
                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
                tableModel.getItems()[tableModel.getPacman().getStartI()][tableModel.getPacman().getStartJ()] = 2;
                i = startI;
                j = startJ;
                tableModel.getItems()[tableModel.getPacman().getI()][tableModel.getPacman().getJ()] = 3;
                return;
            } else if (tableModel.getItems()[i + iN][j + jN] == 1)
                tableModel.getItems()[i + iN][j + jN] = 13;
            else {
                tableModel.getItems()[i + iN][j + jN] = 4;
            }
            i += iN;
            j += jN;
        }
    }

    public void moveGhost() {
        Random random = new Random();
        while (tableModel.isInGame()) {
            int rand = random.nextInt(1, 5);

            switch (rand) {
                case 1 -> this.moveGhostCoordinates(0, -1); //left
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