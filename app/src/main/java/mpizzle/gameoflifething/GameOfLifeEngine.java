package mpizzle.gameoflifething;

/**
 * Created by mpizzle on 04/04/17.
 */
public class GameOfLifeEngine {
    private static final int padding = 2;

    public static boolean[][] step(boolean[][] grid, int rows, int columns) {
        boolean newGrid[][] = new boolean[columns + padding][rows + padding];

        for (int i = 1; i <= columns; ++i) {
            for (int j = 1; j <= rows; ++j) {
                int neighbours = 0;
                neighbours += grid[i][j + 1] ? 1 : 0;
                neighbours += grid[i][j  - 1] ? 1 : 0;
                neighbours += grid[i + 1][j] ? 1 : 0;
                neighbours += grid[i  - 1][j] ? 1 : 0;
                neighbours += grid[i + 1][j + 1] ? 1 : 0;
                neighbours += grid[i + 1][j  - 1] ? 1 : 0;
                neighbours += grid[i  - 1][j + 1] ? 1 : 0;
                neighbours += grid[i  - 1][j  - 1] ? 1 : 0;

                if ((grid[i][j] && (neighbours == 2 || neighbours == 3)) || (!grid[i][j] && neighbours == 3)) {
                    newGrid[i][j]= true;
                }
            }
        }

        return newGrid;
    }
}
