package mpizzle.gameoflifething;

import java.util.Arrays;

import static mpizzle.gameoflifething.CellMap.ROWS;
import static mpizzle.gameoflifething.CellMap.COLUMNS;
import static mpizzle.gameoflifething.CellMap.PADDING;

/**
 * Created by mpizzle on 04/04/17.
 */
public class GameOfLifeEngine {
    public static boolean[][] step(boolean[][] currentGen, boolean[][] nextGen, int rows, int columns) {
        currentGen = copyCells(currentGen);
        for (int i = 1; i <= columns; ++i) {
            for (int j = 1; j <= rows; ++j) {
                int neighbours = 0;
                neighbours += currentGen[i][j + 1] ? 1 : 0;
                neighbours += currentGen[i][j  - 1] ? 1 : 0;
                neighbours += currentGen[i + 1][j] ? 1 : 0;
                neighbours += currentGen[i  - 1][j] ? 1 : 0;
                neighbours += currentGen[i + 1][j + 1] ? 1 : 0;
                neighbours += currentGen[i + 1][j  - 1] ? 1 : 0;
                neighbours += currentGen[i  - 1][j + 1] ? 1 : 0;
                neighbours += currentGen[i  - 1][j  - 1] ? 1 : 0;

                if ((currentGen[i][j] && (neighbours == 2 || neighbours == 3)) || (!currentGen[i][j] && neighbours == 3)) {
                    nextGen[i][j]= true;
                }
            }
        }

        return nextGen;
    }
    public static int[][] step(int[][] currentGen, int[][] nextGen, int rows, int columns) {
        currentGen = copyCells(currentGen);

        for (int i = 1; i <= columns; ++i) {
            for (int j = 1; j <= rows; ++j) {
                int neighbours = 0;
                neighbours += currentGen[i][j + 1] > 0? 1 : 0;
                neighbours += currentGen[i][j  - 1] > 0 ? 1 : 0;
                neighbours += currentGen[i + 1][j] > 0 ? 1 : 0;
                neighbours += currentGen[i  - 1][j] > 0 ? 1 : 0;
                neighbours += currentGen[i + 1][j + 1] > 0 ? 1 : 0;
                neighbours += currentGen[i + 1][j  - 1] > 0 ? 1 : 0;
                neighbours += currentGen[i  - 1][j + 1] > 0 ? 1 : 0;
                neighbours += currentGen[i  - 1][j  - 1] > 0 ? 1 : 0;

                if ((currentGen[i][j] > 0 && (neighbours == 2 || neighbours == 3)) || (!(currentGen[i][j] > 0) && neighbours == 3)) {
                    nextGen[i][j] = currentGen[i][j] + 1;
                }
            }
        }

        return nextGen;
    }

    private static boolean[][] copyCells(boolean[][] cellGrid)
    {
        cellGrid[0] = Arrays.copyOf(cellGrid[COLUMNS - 1], ROWS + PADDING);
        cellGrid[COLUMNS + 1] = Arrays.copyOf(cellGrid[1], ROWS + PADDING);

        for (int i = 1; i <= ROWS; ++i) {
            cellGrid[i][0] = cellGrid[i][ROWS  - 1];
            cellGrid[i][ROWS + 1] = cellGrid[i][1];
        }

        return cellGrid;
    }

    private static int[][] copyCells(int[][] cellGrid)
    {
        cellGrid[0] = Arrays.copyOf(cellGrid[COLUMNS - 1], ROWS + PADDING);
        cellGrid[COLUMNS + 1] = Arrays.copyOf(cellGrid[1], ROWS + PADDING);

        for (int i = 1; i <= ROWS; ++i) {
            cellGrid[i][0] = cellGrid[i][ROWS  - 1];
            cellGrid[i][ROWS + 1] = cellGrid[i][1];
        }

        return cellGrid;
    }
}
