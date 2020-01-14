package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import app.lslibrary.androidHelper.LSLog;

public class ScrollViewEXInner extends ScrollView
{
    public ScrollViewEXInner(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if(ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        LSLog.Log_INFO(ev.toString());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        boolean res = super.onInterceptTouchEvent(ev);
        LSLog.Log_INFO(res + "..." + ev.toString());
        return res;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean res = super.onTouchEvent(event);
        LSLog.Log_INFO(res + "..." + event.toString());
        return res;
    }
}