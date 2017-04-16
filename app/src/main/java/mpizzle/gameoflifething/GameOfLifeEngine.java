package mpizzle.gameoflifething;

import java.util.Arrays;

import static mpizzle.gameoflifething.CellMap.ROWS;
import static mpizzle.gameoflifething.CellMap.COLUMNS;
import static mpizzle.gameoflifething.CellMap.PADDING;

/**
 * Created by mpizzle on 04/04/17.
 */
public class GameOfLifeEngine {
    public static int[][] step(CellMap cellMap) {
        int[][] nextGen = new int[COLUMNS + PADDING][ROWS + PADDING];

        if (cellMap.isWrappingEnabled()) {
            cellMap.setCellMap(copyCells(cellMap.getCellMap()));
        }

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                int neighbours = (cellMap.getCellMap()[i][j + 1] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i][j - 1] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i + 1][j] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i - 1][j] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i + 1][j + 1] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i + 1][j - 1] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i - 1][j + 1] > 0 ? 1 : 0)
                        + (cellMap.getCellMap()[i - 1][j - 1] > 0 ? 1 : 0);

                if ((cellMap.getCellMap()[i][j] > 0 && (neighbours == 2 || neighbours == 3)) || (!(cellMap.getCellMap()[i][j] > 0) && neighbours == 3)) {
                    nextGen[i][j] = cellMap.getCellMap()[i][j] + 1;
                }
            }
        }

        return nextGen;
    }

    private static int[][] copyCells(int[][] cellGrid)
    {
        int[][] cellGridCopy = Arrays.copyOf(cellGrid, COLUMNS + PADDING);
        cellGridCopy[0] = Arrays.copyOf(cellGridCopy[COLUMNS], ROWS + PADDING);
        cellGridCopy[COLUMNS + 1] = Arrays.copyOf(cellGridCopy[1], ROWS + PADDING);

        for (int i = 1; i <= COLUMNS; ++i) {
            cellGridCopy[i][0] = cellGridCopy[i][ROWS];
            cellGridCopy[i][ROWS + 1] = cellGridCopy[i][1];
        }

        //is there a better way to do this?
        cellGridCopy[0][0] = cellGridCopy[COLUMNS][ROWS];
        cellGridCopy[COLUMNS + 1][0] = cellGridCopy[1][ROWS];
        cellGridCopy[0][ROWS + 1] = cellGridCopy[COLUMNS][1];
        cellGridCopy[COLUMNS + 1][ROWS + 1] = cellGridCopy[1][1];

        return cellGridCopy;
    }
}
