package mpizzle.gameoflifething;

import java.util.Random;

/**
 * Created by mpizzle on 09/04/17.
 */
public class CellMap {
    public static final int ROWS = 1000;
    public static final int COLUMNS = 1000;
    public static final int PADDING = 2;

    public boolean[][] currentGeneration;
    public boolean[][] nextGeneration;
    public int[][] currentGenerationHeated;
    public int[][] nextGenerationHeated;

    public CellMap(Random r) {
        r = new Random();
        r.setSeed(System.currentTimeMillis());

        currentGeneration = new boolean[COLUMNS + PADDING][ROWS + PADDING];

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                currentGeneration[i][j] = r.nextBoolean();
            }
        }

        currentGenerationHeated = new int[COLUMNS + PADDING][ROWS + PADDING];

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                currentGenerationHeated[i][j] = r.nextInt(2);
            }
        }
    }
}
