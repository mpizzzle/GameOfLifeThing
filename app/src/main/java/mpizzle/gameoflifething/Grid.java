package mpizzle.gameoflifething;

import java.util.ArrayList;

/**
 * Created by mpizzle on 01/04/17.
 */
public class Grid {
    private boolean[][] grid;

    Grid() {
        grid = new boolean[100][100];
    }

    public boolean[][] getGrid() {
        return grid;
    }
}
