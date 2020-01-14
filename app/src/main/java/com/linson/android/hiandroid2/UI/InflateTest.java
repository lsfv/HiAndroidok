package com.linson.android.hiandroid2.UI;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.linson.android.hiandroid2.R;

import app.lslibrary.androidHelper.LSLog;

public class InflateTest extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflate_test);
        ConstraintLayout topview=findViewById(R.id.toplayout);

        try
        {
            //有root,那么意义就是是否添加到root中。 true: 添加。 false。用属性。
            //wu root.

            View xmlview = LayoutInflater.from(this).inflate(R.layout.inflatetest, topview, false);
            topview.addView(xmlview);
        } catch (Exception e)
        {
            LSLog.Log_Exception(e);
        }


    }
}