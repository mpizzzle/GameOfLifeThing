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
public class PixelGridView extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint p = new Paint();
    private Paint[][] palette;
    private boolean[][] grid;
    private Random r;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.numRows = 100;
        this.numColumns = 100;
        this.setWillNotDraw(false);

        r = new Random();
        r.setSeed(System.currentTimeMillis());
        grid = new boolean[numColumns][numRows];
        palette = new Paint[numColumns][numRows];

        for (int i = 0; i < numColumns; ++i) {
            for (int j = 0; j < numRows; ++j) {
                palette[i][j] = new Paint();
                palette[i][j].setStyle(Paint.Style.FILL_AND_STROKE);
                palette[i][j].setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
                grid[i][j] = r.nextBoolean();
            }
        }

        this.cellWidth = 25;
        this.cellHeight = 25;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setCustomIntProperty(int value){
        grid = GameOfLifeEngine.step(grid, numColumns, numRows);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (grid[i][j]) {
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, palette[i][j]);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(cellWidth * numColumns, cellHeight * numRows);
    }
}