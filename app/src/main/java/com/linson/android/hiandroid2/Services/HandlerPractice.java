package com.linson.android.hiandroid2.Services;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;

import java.lang.ref.Reference;
import java.lang.reflect.Type;

import app.lslibrary.androidHelper.LSLog;

//模拟一个耗时的模块，比如计算数字的平方需要3秒。ui线程发送消息给后台计算。后台计算完毕之后把结果发送给ui。
//1.按道理应该是用系统提供的runOnUI是最简单的。
//2.handler的话就是android中最基础的api了。可以看成是android实现了一个特别的线程安全的消费生产模型。
//多线程生产，单线程消费。生产方法是public.消费方法是private,由handler所在线程的的looper去调用。
//安全的数据是消息集合，生产线程是任何获得handler的线程，消费线程只有一个，就是建立handler的线程。
//并且消费方法不是public的，而是让固定了让handler线程中的looper去消费。
// 只要建立handler。就建立了一个生产消费模式。必须建立一个handler的派生类，因为需要自己重载如何消费数据。
// 在其他线程中直接handler.sendmessage(msg).就可以把msg数据，线程安全的放入。
// 并且hander所在的线程的looper就会触发hander中实现的消费方法：void handleMessage(message)
//4.异步任务进一步封装了。我们都不需要接触线程了。


public class HandlerPractice extends AppCompatActivity
{

    private BackgroundCaculate backgroundCaculate;
    public enum asyncType
    {
        runonui,
        handler,
        aysncTask,
        interActive
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_practice);
        mMyControls=new MyControls();//cut it into 'onCreate'
        bindEvent();

        backgroundCaculate = new BackgroundCaculate(MyHandler_caculate.class);
        backgroundCaculate.start();
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        backgroundCaculate.mLooper.quitSafely();//退出
    }

    private void clickSquare()
    {
        int number1=Integer.valueOf(mMyControls.mEditText2.getText().toString());
        asyncType type=asyncType.interActive;
        if(type==asyncType.runonui)
        {
            Thread bgthread = new Thread(new ComplexJob(number1));
            bgthread.start();
        }
        else if(type==asyncType.handler)
        {
            //传递了主线程的handler过去。主线程默认是建立了looper的。
            Thread bgthread2=new Thread(new ComplexJob2(number1, new MyHandler()));
            bgthread2.start();
        }
        else if(type==asyncType.aysncTask)
        {
            MyAsyncTask myAsyncTask=new MyAsyncTask();
            myAsyncTask.execute(number1);
        }
        else if(type==asyncType.interActive)
        {
            try
            {
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = 2;
                msg.arg2 = 3;


                Message msg2 = new Message();
                msg2.what = 2;
                msg2.arg1 = 2;
                msg2.arg2 = 3;

                Handler bghandler=backgroundCaculate.mMyHandler;

                //Handler bghandler=new Handler(backgroundCaculate.getl)
                if(bghandler!=null)
                {
                    backgroundCaculate.mMyHandler.sendMessage(msg);
                    backgroundCaculate.mMyHandler.sendMessage(msg2);
                }



            }
            catch (Exception e)
            {
                LSLog.Log_Exception(e);
            }

        }
    }
    //region handlerthread
    public static class MyHandler_caculate2 extends Handler
    {
        public Looper mLooper;
        public MyHandler_caculate2(Looper looper)
        {
            mLooper=looper;
        }

        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what==1)
            {
                LSLog.Log_INFO("waiting...backgroud is working:");
                try
                {
                    Thread.sleep(3000);
                } catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                LSLog.Log_INFO("add:"+msg.arg1+msg.arg2);
            }
            else if(msg.what==2)
            {
                LSLog.Log_INFO("waiting...backgroud is working:");
                try
                {
                    Thread.sleep(3000);
                } catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                LSLog.Log_INFO("mutiple:"+msg.arg1*msg.arg2);
            }
        }
    }
    //endregion



    //region ui线程提供一个处理更形ui的handler，而后台线程提供一个计算服务的handler.
    //完全不需要关注数据的线程安全问题。
    //handler是需要在线程中的，所以应该是建立一个线程类。再放入handler
    private static class BackgroundCaculate extends Thread
    {
        public Handler mMyHandler;
        public Looper mLooper;
        public Class<? extends Handler> mHandlerClass;

        public BackgroundCaculate(Class<? extends Handler> classA)
        {
            mHandlerClass=classA;
        }

        @Override
        public void run()
        {
            Looper.prepare();
            try
            {
                mMyHandler = mHandlerClass.getConstructor().newInstance(); //new MyHandler_caculate();
                mLooper= Looper.myLooper();

            } catch (Exception e)
            {
                LSLog.Log_Exception(e);
            }
            Looper.loop();
        }
    }

    public static class MyHandler_caculate extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what==1)
            {
                LSLog.Log_INFO("waiting...backgroud is working:");
                try
                {
                    Thread.sleep(3000);
                } catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                LSLog.Log_INFO("add:"+(msg.arg1+msg.arg2));
            }
            else if(msg.what==2)
            {
                LSLog.Log_INFO("waiting...backgroud is working:");
                try
                {
                    Thread.sleep(3000);
                } catch (Exception e)
                {
                    LSLog.Log_Exception(e);
                }
                LSLog.Log_INFO("mutiple:"+(msg.arg1*msg.arg2));
            }
        }
    }

    //endregion


    private void bindEvent()
    {
        mMyControls.mBtnSquare.setOnClickListener(new MyClickListener());
    }

    //region type:asyncTask
    public class MyAsyncTask extends AsyncTask<Integer,Integer,String>
    {

        @Override
        protected String doInBackground(Integer... integers)
        {
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            final int res=integers[0]*integers[0];

            return res+"";
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            mMyControls.mTvMsg.setText(s);
        }
    }
    //endregion

    //region type:runonui
    private class ComplexJob implements Runnable
    {
        private int num1;
        public ComplexJob(int a)
        {
            num1=a;
        }

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            final int res=num1*num1;
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    mMyControls.mTvMsg.setText(res+"");
                }
            });
        }
    }
    //endregion

    //region type:handler
    private class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            LSLog.Log_INFO("ui");
            try
            {
                mMyControls.mTvMsg.setText(msg.arg1+"");
            } catch (Exception e)
            {
                LSLog.Log_Exception(e);
            }
        }
    }


    private class ComplexJob2 implements Runnable
    {
        private int num1;
        private Handler mHandler;
        public ComplexJob2(int a,Handler handler)
        {
            num1=a;
            mHandler=handler;
        }

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            final int res=num1*num1;
            Message msg=new Message();
            msg.arg1=res;
            LSLog.Log_INFO("before send");
            mHandler.sendMessage(msg);
            LSLog.Log_INFO("after send");
        }
    }
    //endregion


    //region click listener
    private class MyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if(v.getId()==R.id.btn_square)
            {
                clickSquare();
            }
        }
    }


    //endregion

    //region The class of FindControls
    private MyControls mMyControls=null;
    public class MyControls
    {
        private Button mBtnSquare;
        private Button mBtnStart;
        private TextView mTvMsg;
        private EditText mEditText2;

        public MyControls()
        {
            mBtnSquare = (Button) findViewById(R.id.btn_square);
            mBtnStart = (Button) findViewById(R.id.btn_start);
            mTvMsg = (TextView) findViewById(R.id.tv_msg);
            mEditText2 = (EditText) findViewById(R.id.editText2);
        }
    }
    //endregion
}