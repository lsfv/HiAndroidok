package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.linson.LSLibrary.AndroidHelper.LSTouch;
import com.linson.android.hiandroid2.R;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyConstrainLayoutEx;

import javax.xml.datatype.Duration;

import app.lslibrary.androidHelper.LSLog;

public class DiffDirection extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffdirections);

        mMyControls=new MyControls();//cut it into 'onCreate'

        mMyControls.mBtnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LSLog.Log_INFO("you click play");
            }
        });

        //v1.原来这里只有 兜底的时候才会触发。现在是多加了一种情况，就是截断move。所以要做些修改：down是不会触发这里的。所以 down必须在onintercept保存。这里使用。当然你也可以用2个move。
        //v2.因为最外层group设定为只要move就认定为是自己处理，导致所有move和up都不会传递下去。所以其子控件无法接触move事件。
        //所以v2版，需要修改截断函数为标准的外部截断套路：1.down,up,cancel放行。2. move，只截断自己特定要的。
        //v3.如果有同向的滑动，这个用外部截断法，就不是很方便处理。因为外部截断法的基本操作时必须分辨出我的和其他事件的区别。如果无法分辨。一般会使用内部截断法。
        //但是内部截断法，个人不喜欢。除非必要。否则优先外部。因为内部法，必须内外结合，不便于理解和排错。最重要的是如果需要内部放弃某些动作。还必须知道上层控件，这个操作就明显的耦合太高。
        //你看外部法。根本不需要知道子控件的事件，每层次过来就是知道自己要什么就可以。非常好理解和排错，耦合度还低。
        //但是对于同向分配，就不好处理，但是还是可以处理的。

        mMyControls.mLayoutControlPanel.setOnTouchListener(new View.OnTouchListener()
        {
            private MotionEvent[] mEvents=new MotionEvent[2];
            private LSTouch.scrollDirection mDirection=LSTouch.scrollDirection.init;
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    mEvents[0]=MotionEvent.obtain(event);
                }
                else if(event.getAction()==MotionEvent.ACTION_MOVE)
                {
                    if(mEvents[0]==null && mMyControls.mLayoutControlPanel.mDownEvent!=null)
                    {
                        mEvents[0]=mMyControls.mLayoutControlPanel.mDownEvent;
                    }
                    if(mEvents[0]!=null && mEvents[1]==null)
                    {
                        mEvents[1] = MotionEvent.obtain(event);
                        if(LSTouch.getscrollDirection(mEvents[0],mEvents[1])==LSTouch.scrollDirection.LEFT)
                        {
                            LSLog.Log_INFO("left");
                            mDirection=LSTouch.scrollDirection.LEFT;
                        }
                        else if(LSTouch.getscrollDirection(mEvents[0],mEvents[1])==LSTouch.scrollDirection.RIGHT)
                        {
                            LSLog.Log_INFO("right");
                            mDirection=LSTouch.scrollDirection.RIGHT;
                        }
                    }
                }
                else if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(mDirection==LSTouch.scrollDirection.LEFT)
                    {
                        LSLog.Log_INFO("move left");
                    }
                    else if(mDirection==LSTouch.scrollDirection.RIGHT)
                    {
                        LSLog.Log_INFO("move right");
                    }
                    mEvents=new MotionEvent[2];//up是一定会执行的。down却只会在down在自己的时候触发。
                }

                return true;
            }
        });
    }


    //region The class of FindControls
    private MyControls mMyControls=null;
    public class MyControls
    {
        private MyConstrainLayoutEx mLayoutControlPanel;
        private Button mBtnPlay;
        private HorizontalScrollView mSvBottom;

        public MyControls()
        {
            mLayoutControlPanel = (MyConstrainLayoutEx) findViewById(R.id.layout_controlPanel);
            mBtnPlay = (Button) findViewById(R.id.btn_play);
            mSvBottom = (HorizontalScrollView) findViewById(R.id.sv_bottom);
        }
    }
    //endregion

}