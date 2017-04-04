package mpizzle.gameoflifething;

import java.util.Arrays;

/**
 * Created by mpizzle on 04/04/17.
 */
public class GameOfLifeEngine {
    public static boolean[] step(boolean[] grid) {
        boolean newGrid[] = new boolean[1000000];
        Arrays.fill(newGrid, false);

        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 1000; ++j) {
                int neighbours = 0;

                neighbours += grid[(i * 1000) + ((j + 1) % 1000)] ? 1 : 0;
                neighbours += grid[(i * 1000) + ((j  - 1) > -1 ? (j  - 1) : 999)] ? 1 : 0;
                neighbours += grid[(((i + 1) % 1000) * 1000) + j] ? 1 : 0;
                neighbours += grid[(((i  - 1) > -1 ? (i  - 1) : 999) * 1000) + j] ? 1 : 0;
                neighbours += grid[(((i + 1) % 1000) * 1000) + ((j + 1) % 1000)] ? 1 : 0;
                neighbours += grid[(((i + 1) % 1000) * 1000) + ((j  - 1) > -1 ? (j  - 1) : 999)] ? 1 : 0;
                neighbours += grid[(((i  - 1) > -1 ? (i  - 1) : 999) * 1000) + ((j + 1) % 1000)] ? 1 : 0;
                neighbours += grid[(((i  - 1) > -1 ? (i  - 1) : 909) * 1000) + ((j  - 1) > -1 ? (j  - 1) : 999)] ? 1 : 0;

                if (((grid[(i * 1000) + j]) && (neighbours == 2 || neighbours == 3)) || (!(grid[(i * 1000) + j]) && (neighbours == 3))) {
                        newGrid[(i * 1000) + j] = true;
                }
            }
        }

        return newGrid;
    }
}
