package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import app.lslibrary.androidHelper.LSLog;

public class MyHorizontalScrollViewEx extends HorizontalScrollView
{
    public MyHorizontalScrollViewEx(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if(getParent()!=null && ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }
}