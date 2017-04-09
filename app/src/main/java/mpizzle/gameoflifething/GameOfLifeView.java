package mpizzle.gameoflifething;

import android.view.View;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;

import java.util.Random;

import static mpizzle.gameoflifething.CellMap.ROWS;
import static mpizzle.gameoflifething.CellMap.COLUMNS;
import static mpizzle.gameoflifething.CellMap.PADDING;

/**
 * Created by mpizzle on 01/04/17.
 */
public class GameOfLifeView extends View {
    public static final int SIZE_OF_PALETTE = 16;

    private final int CELL_HEIGHT = 15;
    private final int CELL_WIDTH = 15;

    private Paint[][] randomPalette;
    private Paint[] heatPalette;
    private Paint p;
    private CellMap cellMap;
    private int paletteOption;

    public GameOfLifeView(Context context) {
        this(context, null);
    }

    public GameOfLifeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setWillNotDraw(false);

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        cellMap = new CellMap(r);
        randomPalette = new Paint[SIZE_OF_PALETTE][SIZE_OF_PALETTE];

        for (int i = 0; i < SIZE_OF_PALETTE; ++i) {
            for (int j = 0; j < SIZE_OF_PALETTE; ++j) {
                randomPalette[i][j] = new Paint();
                randomPalette[i][j].setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            }
        }

        heatPalette = new Paint[SIZE_OF_PALETTE];
        int increment = 0;
        int decrement = 0;
        boolean blep = true;

        for (int i = 0; i < SIZE_OF_PALETTE; ++i) {
            heatPalette[i] = new Paint();
            heatPalette[i].setARGB(255, increment, 255 - decrement, 0);
            if (blep) {
                increment += 32;
            }
            else {
                decrement += 32;
            }
            if (increment > 255) {
                increment = 255;
                blep = false;
            }
            if (decrement  <  0) {
                decrement = 0;
            }
        }

        p = new Paint();
        p.setARGB(255, 0, 255, 0);

        paletteOption = 2;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setCustomIntProperty(int value){
        if (paletteOption < 2) {
            cellMap.nextGeneration = new boolean[COLUMNS + PADDING][ROWS + PADDING];
            cellMap.currentGeneration = GameOfLifeEngine.step(cellMap.currentGeneration, cellMap.nextGeneration, COLUMNS, ROWS);
        }
        else {
            cellMap.nextGenerationHeated = new int[COLUMNS + PADDING][ROWS + PADDING];
            cellMap.currentGenerationHeated = GameOfLifeEngine.step(cellMap.currentGenerationHeated, cellMap.nextGenerationHeated, COLUMNS, ROWS);
        }
        invalidate();
    }

        @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        for (int i = 1; i <= COLUMNS; i++) {
            for (int j = 1; j <= ROWS; j++) {
                switch (paletteOption) {
                    case 0:
                        if (cellMap.currentGeneration[i][j]) {
                            canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, p);
                        }
                        break;
                    case 1:
                        if (cellMap.currentGeneration[i][j]) {
                            canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, randomPalette[i % SIZE_OF_PALETTE][j % SIZE_OF_PALETTE]);
                        }
                        break;
                    case 2:
                        if (cellMap.currentGenerationHeated[i][j] > 0) {
                            canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, heatPalette[cellMap.currentGenerationHeated[i][j] - 1]);
                        }
                        break;
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(CELL_WIDTH * COLUMNS, CELL_HEIGHT * ROWS);
    }
}