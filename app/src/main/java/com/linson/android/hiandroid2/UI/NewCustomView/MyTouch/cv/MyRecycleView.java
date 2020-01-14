package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.linson.LSLibrary.AndroidHelper.LSTouch;
import com.linson.LSLibrary.AndroidHelper.LSUI;

import java.util.ArrayList;
import java.util.List;

import app.lslibrary.androidHelper.LSLog;

public class MyRecycleView extends RecyclerView
{
    private List<MotionEvent> mEvents=new ArrayList<>();

    public MyRecycleView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if(ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            mEvents.clear();
            mEvents.add(MotionEvent.obtain(ev));
            LSLog.Log_INFO("add down");
            if(getParent()!=null)
            {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        else if(ev.getAction()==MotionEvent.ACTION_MOVE)
        {
            if(mEvents.size()==1)
            {
                mEvents.add(MotionEvent.obtain(ev));
            }
            LSLog.Log_INFO("add move");
            //如果第一动作是下拉，并且同时列表数据已经是头部。那么此次主动权给上层。
            if(mEvents.size()==2)
            {
                LSTouch.scrollDirection direction=LSTouch.getscrollDirection(mEvents.get(0), mEvents.get(1));
                LSUI.Scroll_Type type = LSUI.checkVScrollType(this);
                LSLog.Log_INFO("type:"+type+".direction:"+direction);
                if (type == LSUI.Scroll_Type.contentIsTop && direction==LSTouch.scrollDirection.DOWN)
                {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}