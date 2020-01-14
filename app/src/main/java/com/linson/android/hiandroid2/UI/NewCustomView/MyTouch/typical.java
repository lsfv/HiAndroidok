package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyButtonOk;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyInnerLayout;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyOuterLayout;

import app.lslibrary.androidHelper.LSLog;

public class typical extends AppCompatActivity
{

    private enum TestType
    {
        btnClick,

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typical_ex);

        mMyControls=new MyControls();//cut it into 'onCreate'

        TestType testType=TestType.btnClick;

        if(testType==testType.btnClick)
        {
            justClick();
        }
    }

    private void justClick()
    {
        mMyControls.mButton19.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                LSLog.Log_INFO("");
                return false;
            }
        });

        mMyControls.mButton19.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LSLog.Log_INFO("i click button");
            }
        });
    }


    //region The class of FindControls
    private MyControls mMyControls=null;
    public class MyControls
    {
        private MyOuterLayout mOuter;
        private MyInnerLayout mInner;
        private MyButtonOk mButton19;
        private TextView mTextView17;

        public MyControls()
        {
            mOuter = (MyOuterLayout) findViewById(R.id.outer);
            mInner = (MyInnerLayout) findViewById(R.id.inner);
            mButton19 = (MyButtonOk) findViewById(R.id.button19);
            mTextView17 = (TextView) findViewById(R.id.textView17);
        }
    }
    //endregion

}





























//package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.linson.android.hiandroid2.R;
//import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyButtonOk;
//import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyInnerLayout;
//import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyOuterLayout;
//
//import java.util.ArrayList;



//import java.util.List;
//
//import app.lslibrary.androidHelper.LSLog;
//
////滑动冲突就是操作界面的平面性和本质是层叠布局所引发的冲突，用户操作的是一个平面的界面，而实际是多层的view叠加。
////从视觉上是被遮盖的下层希望把动作留住，而不传给上层。
////基本分为2种。留住全部动作或者留住后半部分。
////当然2种情况都可以加上先决性条件来进行扩展，如加上动作的范围在某某范围内才截取等。
//public class typical extends AppCompatActivity implements View.OnClickListener
//{
//    private MyOuterLayout mOuter;
//    private MyInnerLayout mInner;
//    private MyButtonOk mButton19;
//    private TextView mTextView17;
//
//
//    //region  findcontrols and bind click event.
//    private void findControls()
//    {   //findControls
//        mOuter = (MyOuterLayout) findViewById(R.id.outer);
//        mInner = (MyInnerLayout) findViewById(R.id.inner);
//        mButton19 = (MyButtonOk) findViewById(R.id.button19);
//        mTextView17 = (TextView) findViewById(R.id.textView17);
//
//        //set event handler
//        mButton19.setOnClickListener(this);
//        //mButton19.setClickable(false);
//        //mButton19.setLongClickable(false);
//    }
//
//    @Override
//    public void onClick(View v)
//    {
//        switch (v.getId())
//        {
//            case R.id.button19:
//            {
//                LSLog.Log_INFO("click button");
//                break;
//            }
//            default:
//            {
//                break;
//            }
//        }
//    }
//    //endregion
//
//    //region other member variable
//    //endregion
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_typical);
//        findControls();
//
//        //mInner.setOnTouchListener(new TouchListener());
//        //mOuter.setOnTouchListener(new TouchListener());
//
//        mInner.setOnTouchListener(new innerlistener());
//        mOuter.setOnTouchListener(new outlistener());
//    }
//
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        LSLog.Log_INFO(event.toString());
//        return super.onTouchEvent(event);
//    }
//
////
////    public static class outlistener implements View.OnTouchListener
////    {
////        @Override
////        public boolean onTouch(View v, MotionEvent event)
////        {
////            LSLog.Log_INFO(event.toString());
////            boolean isdefault=false;
////            if(isdefault)
////            {
////                return false;
////            }
////            else
////            {
////                if(event.getAction()==MotionEvent.ACTION_MOVE)
////                {
////                    return true;
////                }
////                else
////                {
////                    return false;
////                }
////            }
////        }
////    }
////
////    public static class innerlistener implements View.OnTouchListener
////    {
////        @Override
////        public boolean onTouch(View v, MotionEvent event)
////        {
////            LSLog.Log_INFO(event.toString());
////            boolean isdefault=false;
////            if(isdefault)
////            {
////                return false;
////            }
////            else
////            {
////                if(event.getAction()==MotionEvent.ACTION_DOWN)
////                {
////                    return true;
////                }
////                else
////                {
////                    return false;
////                }
////            }
////        }
////    }
//////
////    public static class TouchListener implements View.OnTouchListener
////    {
////        float dx = -1, dy = -1, mx = -1, my = -1;
////
////        private void initTouchRecord()
////        {
////            dx = -1;dy = -1;mx = -1;my = -1;
////        }
//
//
////        //如果要处理这个问题，
////        @Override
////        public boolean onTouch(View view, MotionEvent motionEvent)
////        {
////            LSLog.Log_INFO("onTouch listen");
////            boolean res = false;
////            if (view.getId() == R.id.inner)
////            {
////                //如果down事件唯一落在自己身上，那么一般这里会记录所有动作。不再自己身上最多触发dispatch 和oninteract.
////                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
////                {
////                    dx = motionEvent.getX();
////                    dy = motionEvent.getY();
////                    res=true;
////                }
////                else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE)
////                {
////                    mx = motionEvent.getX();
////                    my = motionEvent.getY();
////                    res=true;
////                }
////                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
////                {
////                    if (dx != -1 && dy != -1 && mx != -1 && my != -1)
////                    {
////                        if (Math.abs(dx-mx)<Math.abs(dy-my) && my-dy>10)
////                        {
////                            LSLog.Log_INFO("inner move down");
////                        }
////                        else if (Math.abs(dx-mx)<Math.abs(dy-my) && dy-my>10)
////                        {
////                            LSLog.Log_INFO("inner move up");
////                        }
////                    }
////                    initTouchRecord();
////                    res=true;
////                }
////            }
////            else if (view.getId() == R.id.outer)
////            {
////                LSLog.Log_INFO("outer ev:"+motionEvent.getAction());
////                if(motionEvent.getAction()==MotionEvent.ACTION_UP)
////                {
////                    MyOuterLayout myview = (MyOuterLayout) view;
////                    if (myview.dx > myview.mx)
////                    {
////                        LSLog.Log_INFO("outer move left");
////                    } else
////                    {
////                        LSLog.Log_INFO("outer move right");
////                    }
////                    myview.initTouchRecord();
////                    res=true;
////                }
////            }
////            return res;
////        }
//    }
//}