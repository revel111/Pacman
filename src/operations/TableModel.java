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
        return items[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return items[rowIndex][columnIndex];
    }

    public void generateMap(int height, int width) {
        Random random = new Random();
        int[][] matrix = new int[height][width];
        int counterMax = width * height / 5;
        int counter = 0;
        matrix[height / 2][width / 2] = 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1)
                    matrix[i][j] = 0;
                else {
                    int rand = random.nextInt(5) + 1;
                    if (matrix[i][j] == 2)
                        continue;
                    else if (counter == counterMax)
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

//        for (int[] ints : matrix) {
//            for (int anInt : ints)
//                System.out.print(anInt + " ");
//            System.out.println();
//        }
    }
}
