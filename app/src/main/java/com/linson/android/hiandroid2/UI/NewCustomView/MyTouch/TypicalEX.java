package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyButtonOk;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyInnerLayout;
import com.linson.android.hiandroid2.UI.NewCustomView.MyTouch.cv.MyOuterLayout;

public class TypicalEX extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typical_ex);

        mMyControls=new MyControls();//cut it into 'onCreate'

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