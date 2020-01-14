package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import app.lslibrary.androidHelper.LSLog;

public class MyInnerLayout extends ConstraintLayout
{
    public MyInnerLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        LSLog.Log_INFO(ev.toString());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        LSLog.Log_INFO(ev.toString());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        LSLog.Log_INFO(event.toString());
        return super.onTouchEvent(event);
    }
}