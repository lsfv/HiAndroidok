package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import app.lslibrary.androidHelper.LSLog;

public class MyButtonOk extends android.support.v7.widget.AppCompatButton
{
    public MyButtonOk(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        LSLog.Log_INFO(event.toString());
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        LSLog.Log_INFO(event.toString());
        return super.onTouchEvent(event);
    }
}