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
    private int columns, rows;
    private int cellWidth, cellHeight;
    private Paint[][] palette;
    private Paint p;
    private final int p_size = 16;
    private boolean[][] grid;
    private Random r;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.columns = 300;
        this.rows = 300;
        this.setWillNotDraw(false);

        r = new Random();
        r.setSeed(System.currentTimeMillis());
        grid = new boolean[columns][rows];

        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                grid[i][j] = r.nextBoolean();
            }
        }

        palette = new Paint[p_size][p_size];
        p = new Paint();
        p.setARGB(255, 0, 255, 0);

        for (int i = 0; i < p_size; ++i) {
            for (int j = 0; j < p_size; ++j) {
                palette[i][j] = new Paint();
                palette[i][j].setARGB(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
            }
        }

        this.cellWidth = 30;
        this.cellHeight = 30;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setCustomIntProperty(int value){
        grid = GameOfLifeEngine.step(grid, columns, rows);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (grid[i][j]) {
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, palette[i % p_size][j % p_size]);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(cellWidth * columns, cellHeight * rows);
    }
}