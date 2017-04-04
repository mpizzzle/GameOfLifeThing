package mpizzle.gameoflifething;

import android.view.View;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Random;

/**
 * Created by mpizzle on 01/04/17.
 */
public class PixelGridView extends View {
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint greenPaint = new Paint();
    private Paint whitePaint = new Paint();
    private boolean[] grid;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        greenPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        greenPaint.setColor(Color.GREEN);
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint.setColor(Color.WHITE);
        this.numRows = 1000;
        this.numColumns = 1000;
        this.setWillNotDraw(false);

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        grid = new boolean[numColumns * numRows];
        for (int i = 0; i < numColumns * numRows; ++i) {
            grid[i] = r.nextBoolean();
        }

        this.cellWidth = 25;
        this.cellHeight = 25;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (grid[(i * numColumns) + j]) {
                    canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, greenPaint);
                }
            }
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, whitePaint);
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, whitePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
           // int x = (int)(event.getX() / cellWidth);
            //int y = (int)(event.getY() / cellHeight);

            //grid[(x * 100) + y] = !grid[(x * 100) + y];
            grid = GameOfLifeEngine.step(grid);
            invalidate();
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(cellWidth * numColumns, cellHeight * numRows);
    }
}