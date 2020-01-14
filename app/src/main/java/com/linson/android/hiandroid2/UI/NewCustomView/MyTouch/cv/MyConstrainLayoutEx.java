package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.linson.LSLibrary.AndroidHelper.LSTouch;

public class MyConstrainLayoutEx extends ConstraintLayout
{
    public MotionEvent mDownEvent=null;//down 动作。 因为down是不会被截断的。所以不会进入listener+touch。所以最好保存下，给listener+ontouch使用。
    private MotionEvent mLastInterceptEvent=null;//最新的move动作。

    private PointF mdisInterceptStart=null;
    private PointF mdisInterceptEnd=null;

    public MyConstrainLayoutEx(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    //v1.分配事件。放行click,一旦有move，那么之后就全部要。要注意,up放行。前提是没有触发move，move触发后，表示截断，那么onInterceptTouchEvent是不会再执行的。之后的move和up是会直接给listener+ontouch
    //v2.改动就在于截断move的时候加了一个条件判断。其他基本没动。
    //v3.如果同向，可以提供一个方法，用于告诉group，再那个区域的不要截断。好像这样和内部截断的功效一样，内部也是告诉group。别截断，但是本质是不一样的。内部法是内部从此掌握了所有事件。
    //如果上层还想要。必须内部放行。而我们画蛇添足的加入一个方法让外部调用。本质上还是上层控制主动。好处是耦合低，如果内部法有一个方法，可以让上层重新掌握主动。而不是靠内部来判读，那才算是耦合度合理。
    //但是不可能有，因为外部法，就是由于无法通过已有的方法，分辨出何时该放。何时该收。但是google为什么不多提供一个接口呢，而不是只能用内部这种不完美的方案。

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if(ev.getAction()==MotionEvent.ACTION_DOWN)
        {
            mDownEvent=MotionEvent.obtain(ev);//必须copy。因为ev是一个被所有事件共同使用的变量，随时会被更新，而不是new。
            return false;
        }
        else if(ev.getAction()==MotionEvent.ACTION_MOVE)
        {
            boolean res=false;
            if(mDownEvent!=null && ev!=null)//只截断左右滑动。
            {
                LSTouch.scrollDirection direction=LSTouch.getscrollDirection(mDownEvent, ev);
                if(direction==LSTouch.scrollDirection.LEFT || direction==LSTouch.scrollDirection.RIGHT)
                {
//                    if(ev.getRawX()>=0 && ev.getRawY()>=200)//这里做一个假设，可以提供一个方法，传递某个控件的位置，这样当触摸点在这个位置，那么不能截断。也是可以的。
//                    {
//                        res=false;
//                    }
//                    else
//                    {
//                        res = true;
//                    }
                    res=true;
                }
            }
            mLastInterceptEvent=MotionEvent.obtain(ev);
            return res;
        }
        else if(ev.getAction()==MotionEvent.ACTION_UP)
        {
            return false;
        }
        else//cancel 应该只有下级的cancel才会经过这里。如果是自己cancel。是会直接进入listener+ontouch.所以必须放行。
        {
            return false;
        }
    }
}