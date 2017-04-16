package mpizzle.gameoflifething;

import java.util.Random;

/**
 * Created by mpizzle on 09/04/17.
 */
public class CellMap {
    public static final int ROWS = 60;
    public static final int COLUMNS = 30;
    public static final int PADDING = 2;

    private int[][] cellMap;
    private boolean wrappingEnabled;

    public CellMap(boolean wrappingEnabled, boolean randomize) {
        this.wrappingEnabled = wrappingEnabled;

        cellMap = new int[COLUMNS + PADDING][ROWS + PADDING];

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                cellMap[i][j] = randomize ? r.nextInt(2) : 0;
            }
        }
    }

    public int[][] getCellMap() {
        return cellMap;
    }

    public void setCellMap(int[][] cellMap) {
        this.cellMap = cellMap;
    }

    public boolean isWrappingEnabled(){
        return wrappingEnabled;
    }

    public void setWrappingEnabled(boolean wrappingEnabled)
    {
        this.wrappingEnabled = wrappingEnabled;
    }
}
