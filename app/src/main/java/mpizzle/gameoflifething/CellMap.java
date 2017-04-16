package mpizzle.gameoflifething;

import java.util.Random;

/**
 * Created by mpizzle on 09/04/17.
 */
public class CellMap {
    public static final int ROWS = 30;
    public static final int COLUMNS = 30;
    public static final int PADDING = 2;

    public boolean[][] currentGeneration;
    public boolean[][] nextGeneration;
    public int[][] currentGenerationHeated;
    public int[][] nextGenerationHeated;
    public boolean wrappingEnabled;

    public CellMap(boolean wrappingEnabled, boolean randomize) {
        this.wrappingEnabled = wrappingEnabled;

        currentGeneration = new boolean[COLUMNS + PADDING][ROWS + PADDING];
        currentGenerationHeated = new int[COLUMNS + PADDING][ROWS + PADDING];

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                currentGeneration[i][j] = randomize ? r.nextBoolean() : false;
                currentGenerationHeated[i][j] = randomize ? r.nextInt(2) : 0;
            }
        }
    }
}
