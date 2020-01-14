package com.linson.android.hiandroid2.UI.advanceControls;


import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.linson.android.hiandroid2.R;

import app.lslibrary.androidHelper.LSLog;


public class MyToolbar extends AppCompatActivity implements View.OnClickListener
{
    private Toolbar mMytoolbar;
    private TextView mBarTitle;
    private FloatingActionButton mFabAdd;
    private TextView mTextView19;


    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mMytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        mBarTitle = (TextView) findViewById(R.id.barTitle);
        mFabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        mTextView19 = (TextView) findViewById(R.id.textView19);
        //set event handler
       // XXX.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            default: { break; }
        }
    }
    //endregion

    //region other member variable
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_toolbar);

        findControls();

        toolbar();
        fab();
    }

    private void fab()
    {
        mFabAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LSLog.Log_INFO("hi,fab");
            }
        });
    }

    private void toolbar()
    {
        mMytoolbar.setTitle("title");
        mMytoolbar.setSubtitle("subtitle");
        mMytoolbar.setLogo(R.drawable.wxfix);

        //ssetSupportActionBar(mMytoolbar);
        mMytoolbar.setNavigationIcon(R.mipmap.ic_launcher);


        mMytoolbar.inflateMenu(R.menu.activity_navi2_drawer);//设置右上角的填充菜单

        mMytoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                LSLog.Log_INFO(menuItem.getTitle().toString());
                return false;
            }
        });

        mMytoolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LSLog.Log_INFO("navigation");
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_navi2_drawer, menu);
//
//
//
//        return true;
//    }
}