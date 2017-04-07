package mpizzle.gameoflifething;

import java.util.Arrays;

/**
 * Created by mpizzle on 04/04/17.
 */
public class GameOfLifeEngine {
    public static boolean[][] step(boolean[][] grid, int rows, int columns) {
        boolean newGrid[][] = new boolean[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                int neighbours = 0;

                neighbours += grid[i][(j + 1) % columns] ? 1 : 0;
                neighbours += grid[i][j  - 1 > -1 ? j  - 1 : columns - 1] ? 1 : 0;
                neighbours += grid[(i + 1) % rows][j] ? 1 : 0;
                neighbours += grid[i  - 1 > -1 ? i  - 1 : rows - 1][j] ? 1 : 0;
                neighbours += grid[(i + 1) % rows][(j + 1) % columns] ? 1 : 0;
                neighbours += grid[(i + 1) % rows][j  - 1 > -1 ? j  - 1 : columns - 1] ? 1 : 0;
                neighbours += grid[i  - 1 > -1 ? i  - 1 : rows - 1][(j + 1) % columns] ? 1 : 0;
                neighbours += grid[i  - 1 > -1 ? i  - 1 : rows - 1][j  - 1 > -1 ? j  - 1 : columns - 1] ? 1 : 0;

                if (((grid[i][j]) && (neighbours == 2 || neighbours == 3)) || (!(grid[i][j]) && (neighbours == 3))) {
                    newGrid[i][j]= true;
                }
            }
        }

        return newGrid;
    }
}
