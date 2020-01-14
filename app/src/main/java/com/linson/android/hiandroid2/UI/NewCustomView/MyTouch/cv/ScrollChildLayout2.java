package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.linson.LSLibrary.AndroidHelper.LSTouch;


//外部截断法和myrecycleview2配合。  myrecycleview2不用做任何改动。外部截断逻辑全部在控件树上层控件。
public class ScrollChildLayout2 extends ConstraintLayout
{
    private MotionEvent mMotionEvent=null;
    private boolean bottomWantScrollDown=false;
    public ScrollChildLayout2(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void  setBottomWantScrollDown(boolean b)
    {
        bottomWantScrollDown=b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if(ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            mMotionEvent=MotionEvent.obtain(ev);
            bottomWantScrollDown=false;
            return false;
        }
        else if(ev.getAction()==MotionEvent.ACTION_MOVE)
        {
            if(LSTouch.getscrollDirection(mMotionEvent, ev)==LSTouch.scrollDirection.DOWN && bottomWantScrollDown==false)
            {
                getParent().requestDisallowInterceptTouchEvent(true);//一旦有满足我的事件，强制上层不能再截断了。否则就处理cancel事件。如果不想那么灵活，其实就可以这样，省下处理cancel。
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}