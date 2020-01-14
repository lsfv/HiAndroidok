package com.linson.android.hiandroid2.Pattern;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.Fragment.FirstFragment;
import com.linson.android.hiandroid2.R;

import app.lslibrary.androidHelper.LSLog;

public class GoogleMvp extends AppCompatActivity
{
    private GoogleMvp_constract.Iview mMyview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_mvp);

        //初始化view.
        //静态的所以不需要处理
        //初始化control.并把view传给control。
        FragmentManager fragmentManager = getSupportFragmentManager();
        mMyview = (GoogleMvp_constract.Iview) fragmentManager.findFragmentById(R.id.myview);
        GoogleMvp_constract.IControl control = new GoogleMvp_Control(mMyview);

        //activity的工作完毕，之后view 有自己的生命周期，view自己初始化自己，接受用户输入。
    }
}