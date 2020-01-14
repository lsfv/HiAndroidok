package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.linson.LSLibrary.AndroidHelper.LSUI;
import com.linson.android.hiandroid2.Adapter.AdapterSignalRecycleView;
import com.linson.android.hiandroid2.R;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyRecycleView2;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.ScrollChildLayout2;

import java.util.ArrayList;
import java.util.List;

import app.lslibrary.androidHelper.LSLog;

public class same2 extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same2);

        mMyControls=new MyControls();//cut it into 'onCreate'

        setupRecycleView();

        mMyControls.mLeftContent.setOnTouchListener(new MyTouchListener());

        mMyControls.mRvList.setOnTouchListener(new MyTouchListener2());
    }

    private void setupRecycleView()
    {
        List<Drawable> images=new ArrayList<>();

        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.mypic)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.doingok)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.watingok)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.h1)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.h1)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.h1)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.h1)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.h1)));
        images.add(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.h1)));

        AdapterSignalRecycleView adapterSignalRecycleView=new AdapterSignalRecycleView(this, images);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mMyControls.mRvList.setAdapter(adapterSignalRecycleView);
        mMyControls.mRvList.setLayoutManager(linearLayoutManager);
    }

    //region touchListener
    public class MyTouchListener implements View.OnTouchListener
    {
        private boolean isScrollDown=false;
        private int prex=-1,prey=-1;
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                isScrollDown=false;
                prex=-1;
                prey=-1;
                return true;//直接触摸到本控件，会触发，否则，是不会到这里的，down会给其他控件。我们并没有也不能截断down。
            }
            else if(event.getAction()==MotionEvent.ACTION_MOVE)
            {
                //但是有一个问题，中途得到控制怎么办？处理者转为截取者，此事件向原来的处理这发送cancel。
                //1先要有第一点。2要出现下滑动才激发动作。再扑捉每次滑动值。
                if(prex==-1 || prey==-1)
                {
                    LSLog.Log_INFO("first point");
                    prex=(int)event.getX();
                    prey=(int)event.getY();
                }
                else
                {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    if(isScrollDown == false)
                    {
                        LSLog.Log_INFO("check down");
                        if (Math.abs(x - prex) < Math.abs(y - prey) && y>prey)
                        {
                            isScrollDown = true;
                            LSLog.Log_INFO("down ok");
                        }
                        else
                        {
                            isScrollDown = false;
                            LSLog.Log_INFO("down fail");
                        }
                    }
                    if(isScrollDown)
                    {
                        LSLog.Log_INFO("start event");
                        int distanceY=y-prey;
                        ConstraintLayout.LayoutParams layoutParams=(ConstraintLayout.LayoutParams) mMyControls.mRvList.getLayoutParams();
                        layoutParams.setMargins(0, layoutParams.topMargin+distanceY, 0, 0);
                        mMyControls.mRvList.setLayoutParams(layoutParams);
                    }

                    prex=x;
                    prey=y;
                }

            }
            else if(event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL)
            {
                ConstraintLayout.LayoutParams layoutParams=(ConstraintLayout.LayoutParams) mMyControls.mRvList.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                mMyControls.mRvList.setLayoutParams(layoutParams);

                isScrollDown=false;
                prex=-1;
                prey=-1;
            }
            return true;
        }
    }


    //region touchListener
    public class MyTouchListener2 implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                LSLog.Log_INFO("");
                if(LSUI.checkVScrollType(mMyControls.mRvList)!=LSUI.Scroll_Type.contentIsTop)
                {
                    LSLog.Log_INFO("");
                    if( mMyControls.mLeftContent instanceof ScrollChildLayout2)
                    {
                        LSLog.Log_INFO("");
                        ScrollChildLayout2 scrollChildLayout2=(ScrollChildLayout2)mMyControls.mLeftContent;
                        scrollChildLayout2.setBottomWantScrollDown(true);
                    }
                }
            }
            return false;
        }
    }

    //region The class of FindControls
    private MyControls mMyControls=null;
    public class MyControls
    {
        private ConstraintLayout mLeftContent;
        private ImageView mImageHead;
        private RecyclerView mRvList;




        public MyControls()
        {
            mLeftContent = (ConstraintLayout) findViewById(R.id.leftContent);
            mImageHead = (ImageView) findViewById(R.id.image_head);
            mRvList = (RecyclerView) findViewById(R.id.rv_list);
        }
    }
    //endregion

}