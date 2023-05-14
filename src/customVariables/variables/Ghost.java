package customVariables.variables;

import operations.TableModel;

import java.util.Random;

public class Ghost {
    private int i;
    private int j;
    private int startJ;
    private int startI;
    private final TableModel tableModel;

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

//    public void moveLeftGhost() {
//        if (tableModel.getItems()[i][j - 1] != 0) {//wall
//            tableModel.getItems()[i][j] = 3;//black
//            if (tableModel.getItems()[i][j - 1] == 4) {
//                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
//                // поставить призраков и пакмана на место
//            }
////             else if (tableModel.getItems()[i][j - 1] == 1)
////                tableModel.getItems()[i][j - 1] = 13;
//            j -= 1;
//        }
//    }
//
//    public void moveRightGhost() {
//        if (tableModel.getItems()[i][j + 1] != 0) {//wall
//            tableModel.getItems()[i][j] = 3;//black
//            if (tableModel.getItems()[i][j + 1] == 4) {
//                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
//                // поставить призраков и пакмана на место
//            }
////             else if (tableModel.getItems()[i][j - 1] == 1)
////                tableModel.getItems()[i][j - 1] = 13;
//            j += 1;
//        }
//    }
//
//    public void moveUpGhost() {
//        if (tableModel.getItems()[i - 1][j] != 0) {//wall
//            tableModel.getItems()[i][j] = 3;//black
//            if (tableModel.getItems()[i - 1][j] == 4) {
//                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
//                // поставить призраков и пакмана на место
//            }
////             else if (tableModel.getItems()[i][j - 1] == 1)
////                tableModel.getItems()[i][j - 1] = 13;
//            i -= 1;
//        }
//    }
//
//    public void moveDownGhost() {
//        if (tableModel.getItems()[i + 1][j] != 0) {//wall
//            tableModel.getItems()[i][j] = 3;//black
//            if (tableModel.getItems()[i + 1][j] == 4) {
//                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
//                // поставить призраков и пакмана на место
//            }
////             else if (tableModel.getItems()[i][j - 1] == 1)
////                tableModel.getItems()[i][j - 1] = 13;
//            i += 1;
//        }
//    }

    public void moveGhostCoordinates(int iN, int jN) {
        if (tableModel.getItems()[i + iN][j + jN] != 0) {//wall
            tableModel.getItems()[i][j] = 3;//black
            if (tableModel.getItems()[i + iN][j + jN] == 4) {
                tableModel.getPacman().setHp(tableModel.getPacman().getHp() - 1);
                // поставить призраков и пакмана на место
            }
//             else if (tableModel.getItems()[i][j - 1] == 1)
//                tableModel.getItems()[i][j - 1] = 13;
            i += 1;
            j += 1;
        }
    }

    public void moveGhost() {
        Random random = new Random();
        int rand = random.nextInt(4) + 1;

        switch (rand) {
            case 1:
                this.moveGhostCoordinates(0, -1);
            case 2:
                this.moveGhostCoordinates(0, 1);
            case 3:
                this.moveGhostCoordinates(-1, 0);
            case 4:
                this.moveGhostCoordinates(1, 0);
        }

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}