package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import app.lslibrary.androidHelper.LSLog;

//内部截断法。和myrecycleView 配合。
public class ScrollChildLayout extends ConstraintLayout
{
    public ScrollChildLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if(ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}