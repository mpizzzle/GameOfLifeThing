package mpizzle.gameoflifething;

import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;

import java.util.Random;

import static mpizzle.gameoflifething.CellMap.ROWS;
import static mpizzle.gameoflifething.CellMap.COLUMNS;

/**
 * Created by mpizzle on 01/04/17.
 */
public class GameOfLifeView extends View {
    private final int SIZE_OF_PALETTE = 16;
    private final int CELL_HEIGHT = 25;
    private final int CELL_WIDTH = 25;
    private final int LINE_WIDTH = 4;

    private Paint[][] randomPalette;
    private Paint[] heatPalette;
    private Paint p;
    private Paint line;
    private CellMap cellMap;
    private int paletteOption;
    private boolean drawGrid;
    private ObjectAnimator animator;

    public GameOfLifeView(Context context) {
        this(context, null);
    }

    public GameOfLifeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        cellMap = new CellMap(true, false);
        randomPalette = new Paint[SIZE_OF_PALETTE][SIZE_OF_PALETTE];

        for (int i = 0; i < SIZE_OF_PALETTE; ++i) {
            for (int j = 0; j < SIZE_OF_PALETTE; ++j) {
                randomPalette[i][j] = new Paint();
                randomPalette[i][j].setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            }
        }

        heatPalette = new Paint[SIZE_OF_PALETTE];
        int brushes[] = new int[2];
        int b = 0;

        for (int i = 0; i < SIZE_OF_PALETTE; ++i) {
            heatPalette[i] = new Paint();
            heatPalette[i].setARGB(255, brushes[0], 255 - brushes[1], 0);
            brushes[b] += 32;
            if (brushes[b] > 255) {
                brushes[b] = 255;
                b = 1;
            }
        }

        p = new Paint();
        p.setARGB(255, 0, 255, 0);

        line = new Paint();
        line.setARGB(128, 255, 255, 255);

        paletteOption = 2;
        drawGrid = true;
    }

    public void setNextStep(int value){
        cellMap.setCellMap(GameOfLifeEngine.step(cellMap));
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        for (int i = 1; i <= COLUMNS; i++) {
            for (int j = 1; j <= ROWS; j++) {

                if (cellMap.getCellMap()[i][j] > 0) {
                    switch (paletteOption) {
                        case 0:
                                canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, p);
                            break;
                        case 1:
                                canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, randomPalette[i % SIZE_OF_PALETTE][j % SIZE_OF_PALETTE]);
                            break;
                        case 2:
                                int paletteIdx = (cellMap.getCellMap()[i][j] >= SIZE_OF_PALETTE) ? SIZE_OF_PALETTE - 1 : cellMap.getCellMap()[i][j] - 1;
                                canvas.drawRect((i - 1) * CELL_WIDTH, (j - 1) * CELL_HEIGHT, i * CELL_WIDTH, j * CELL_HEIGHT, heatPalette[paletteIdx]);
                            break;
                    }
                }
            }

            if (drawGrid) {
                canvas.drawRect((i - 1) * CELL_WIDTH, 0, ((i - 1) * CELL_WIDTH) + LINE_WIDTH, ROWS * CELL_HEIGHT, line);
            }
        }

        if (drawGrid) {
            for (int i = 1; i <= ROWS; i++) {
                canvas.drawRect(0, (i - 1) * CELL_HEIGHT, COLUMNS * CELL_WIDTH, ((i - 1) * CELL_HEIGHT) + LINE_WIDTH, line);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!animator.isRunning()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int column = (int) (event.getX() / CELL_WIDTH) + 1;
                int row = (int) (event.getY() / CELL_HEIGHT) + 1;
                cellMap.getCellMap()[column][row] = (cellMap.getCellMap()[column][row] > 0) ? 0 : 1;
                invalidate();
            }
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(CELL_WIDTH * COLUMNS, CELL_HEIGHT * ROWS);
    }

    public ObjectAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(ObjectAnimator animator) {
        this.animator = animator;
    }

    public void setDrawGrid(boolean drawGrid) {
        this.drawGrid = drawGrid;
        invalidate();
    }

    public void setWrapping(boolean wrapping) {
        this.cellMap.setWrappingEnabled(wrapping);
    }

    public void setPalette(boolean paletteStyle) {
        this.paletteOption = (paletteStyle) ? 2 : 0;
        invalidate();
    }

    public void randomizeCellMap() {
        cellMap = new CellMap(cellMap.isWrappingEnabled(), true);
        invalidate();
    }

    public void clearCellMap() {
        cellMap = new CellMap(cellMap.isWrappingEnabled(), false);
        invalidate();
    }
}