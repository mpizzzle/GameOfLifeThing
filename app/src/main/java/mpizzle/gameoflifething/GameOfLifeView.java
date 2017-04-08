package mpizzle.gameoflifething;

import android.view.View;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;

import java.util.Random;

/**
 * Created by mpizzle on 01/04/17.
 */
public class GameOfLifeView extends View {
    public static final int ROWS = 1000;
    public static final int COLUMNS = 1000;
    public static final int PADDING = 2;

    private Paint[][] randomPalette;
    private Paint[] heatPalette;
    private Paint p;
    private boolean[][] currentGeneration;
    private boolean[][] nextGeneration;
    private int[][] currentGenerationHeated;
    private int[][] nextGenerationHeated;
    private final int CELL_HEIGHT = 15;
    private final int CELL_WIDTH = 15;
    private final int SIZE_OF_PALETTE = 16;

    public GameOfLifeView(Context context) {
        this(context, null);
    }

    public GameOfLifeView(Context context, AttributeSet attrs) {
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

        currentGenerationHeated = new int[COLUMNS + PADDING][ROWS + PADDING];

        for (int i = 1; i <= COLUMNS; ++i) {
            for (int j = 1; j <= ROWS; ++j) {
                currentGenerationHeated[i][j] = r.nextInt(2);
            }
        }

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
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setCustomIntProperty(int value){
        //nextGeneration = new boolean[COLUMNS + PADDING][ROWS + PADDING];
        //currentGeneration = GameOfLifeEngine.step(currentGeneration, nextGeneration, COLUMNS, ROWS);
        nextGenerationHeated = new int[COLUMNS + PADDING][ROWS + PADDING];
        currentGenerationHeated = GameOfLifeEngine.step(currentGenerationHeated, nextGenerationHeated, COLUMNS, ROWS);
        invalidate();
    }

        @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        for (int i = 1; i <= COLUMNS; i++) {
            for (int j = 1; j <= ROWS; j++) {
                if (currentGenerationHeated[i][j] > 0) {
                    //if (currentGeneration[i][j]) {
                    //canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, p);
                    //canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, randomPalette[i % SIZE_OF_PALETTE][j % SIZE_OF_PALETTE]);
                    canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, heatPalette[currentGenerationHeated[i][j] - 1]);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(CELL_WIDTH * COLUMNS, CELL_HEIGHT * ROWS);
    }
}