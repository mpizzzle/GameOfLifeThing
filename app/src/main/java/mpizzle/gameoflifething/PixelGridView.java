package mpizzle.gameoflifething;

import android.view.View;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by mpizzle on 01/04/17.
 */
public class PixelGridView extends View {
    private Paint[][] palette;
    private Paint p;
    private boolean[][] currentGeneration;
    private boolean[][] nextGeneration;
    private final int ROWS = 100;
    private final int COLUMNS = 100;
    private final int CELL_HEIGHT = 20;
    private final int CELL_WIDTH = 20;
    private final int SIZE_OF_PALETTE = 16;
    private final int PADDING = 2;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setWillNotDraw(false);

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        currentGeneration = new boolean[COLUMNS + PADDING][ROWS + PADDING];

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                currentGeneration[i][j] = r.nextBoolean();
            }
        }

        palette = new Paint[SIZE_OF_PALETTE][SIZE_OF_PALETTE];

        for (int i = 0; i < SIZE_OF_PALETTE; ++i) {
            for (int j = 0; j < SIZE_OF_PALETTE; ++j) {
                palette[i][j] = new Paint();
                palette[i][j].setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            }
        }

        p = new Paint();
        p.setARGB(255, 0, 255, 0);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setCustomIntProperty(int value){
        currentGeneration = copyCells(currentGeneration);
        currentGeneration = GameOfLifeEngine.step(currentGeneration, COLUMNS, ROWS);
        invalidate();
    }

    private boolean[][] copyCells(boolean[][] cellGrid)
    {
        cellGrid[0] = Arrays.copyOf(cellGrid[COLUMNS - 1], ROWS + PADDING);
        cellGrid[COLUMNS + 1] = Arrays.copyOf(cellGrid[1], ROWS + PADDING);

        for (int i = 1; i <= ROWS; ++i) {
            cellGrid[i][0] = cellGrid[i][ROWS  - 1];
            cellGrid[i][ROWS + 1] = cellGrid[i][1];
        }

        return cellGrid;
    }

        @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 100, 100, 100);// 0, 0, 0);
        for (int i = 1; i <= COLUMNS; i++) {
            for (int j = 1; j <= ROWS; j++) {
                if (currentGeneration[i][j]) {
                    canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, p);// palette[i % SIZE_OF_PALETTE][j % SIZE_OF_PALETTE]);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(CELL_WIDTH * COLUMNS, CELL_HEIGHT * ROWS);
    }
}