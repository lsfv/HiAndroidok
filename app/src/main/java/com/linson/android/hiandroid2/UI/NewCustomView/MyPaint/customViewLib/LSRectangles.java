package com.linson.android.hiandroid2.UI.NewCustomView.MyPaint.customViewLib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LSRectangles extends View
{
    private Paint mPaint;
    public LSRectangles(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        //canvas.drawCircle(0, 0, 100, mPaint);
    }

    @Override
    public void onDrawForeground(Canvas canvas)
    {
        super.onDrawForeground(canvas);
    }
}