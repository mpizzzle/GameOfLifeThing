package mpizzle.gameoflifething;

/**
 * Created by mpizzle on 04/04/17.
 */
public class GameOfLifeEngine {
    public static boolean[][] step(boolean[][] currentGen, boolean[][] nextGen, int rows, int columns) {

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
}
