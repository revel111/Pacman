package operations;

import javax.swing.table.AbstractTableModel;
import java.util.Random;

public class TableModel extends AbstractTableModel {
    private int[][] items;

    public TableModel(int[][] items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.length;
    }

    @Override
    public int getColumnCount() {
        return items.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return items[rowIndex][columnIndex];
    }

    public void generateMap(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];
        int counterMax = size * size / 5;
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1)
                    matrix[i][j] = 0; //paint blue
                else {
                    int rand = random.nextInt(5) + 1;
                    if (counter == counterMax)
                        matrix[i][j] = 1;
                    else if (rand == 1) {
                        matrix[i][j] = 0;
                        counter++;
                    } else
                        matrix[i][j] = 1;
                }
            }
        }
        this.items = matrix;
//
//        for (int[] ints : matrix) {
//            for (int anInt : ints)
//                System.out.print(anInt + " ");
//            System.out.println();
//        }
    }
}
