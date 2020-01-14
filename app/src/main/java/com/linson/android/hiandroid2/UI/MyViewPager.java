package com.linson.android.hiandroid2.UI;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linson.android.hiandroid2.R;
import com.linson.android.hiandroid2.UI.adapter.adapter_viewpager;

import java.util.ArrayList;
import java.util.List;

import app.lslibrary.androidHelper.LSLog;

public class MyViewPager extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pager);

        mMyControls=new MyControls();//cut it into 'onCreate'


        setupViewPager();
        mMyControls.mButton23.setOnClickListener(new ClickListener());
        mMyControls.mButton24.setOnClickListener(new ClickListener());
    }


    private void setupViewPager()
    {
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new MyViewPager_fragment1());
        fragments.add(new MyViewPager_fragment2());
        adapter_viewpager page=new adapter_viewpager(getSupportFragmentManager(), fragments);
        mMyControls.mVpContentmain.setAdapter(page);
        mMyControls.mVpContentmain.setCurrentItem(1);
    }


    //region click Listener
    public class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if(v.getId()==R.id.button23)
            {
                LSLog.Log_INFO("");
                mMyControls.mVpContentmain.setCurrentItem(0);
            }
            else if(v.getId()==R.id.button24)
            {
                LSLog.Log_INFO("");
                mMyControls.mVpContentmain.setCurrentItem(1);
            }
        }
    }
    //endregion


    //region The class of FindControls
    private MyControls mMyControls=null;
    public class MyControls
    {
        private ViewPager mVpContentmain;
        private Button mButton23;
        private Button mButton24;

        public MyControls()
        {
            mVpContentmain = (ViewPager) findViewById(R.id.vp_contentmain);
            mButton23 = (Button) findViewById(R.id.button23);
            mButton24 = (Button) findViewById(R.id.button24);
        }
    }
    //endregion
}