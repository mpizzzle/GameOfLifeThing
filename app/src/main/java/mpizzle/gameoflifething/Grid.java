package mpizzle.gameoflifething;

import java.util.ArrayList;

/**
 * Created by mpizzle on 01/04/17.
 */
public class Grid {
    private ArrayList<Boolean> grid;

    Grid() {
        grid = new ArrayList<>(10000);

        for (Boolean tile : grid)
        {
            tile = false;
        }
    }
}
