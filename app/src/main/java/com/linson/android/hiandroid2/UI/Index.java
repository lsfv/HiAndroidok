package com.linson.android.hiandroid2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.linson.LSLibrary.AndroidHelper.LSBaseActivity;
import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;
import com.linson.android.hiandroid2.UI.myCustomView.*;

import java.sql.RowId;
import com.linson.android.hiandroid2.UI.advanceControls.*;


//whyabc
public class Index extends LSBaseActivity implements View.OnClickListener
{
    private Button mBtnUiviewpager;
    private Button mBtnLayoutInflate;
    private Button mBtnLayoutLinear;
    private Button mBtnToolbar;
    private Button mBtnActionButton;
    private Button mBtnCoordinator;
    private Button mBtnBar;
    private Button mBtnNavigation;
    private Button mBtnDrawer;
    private Button mBtnAdvanceview;
    private Button mBtnUi6;
    private Button mBtnUi5;
    private Button mBtnUi4;
    private Button mBtnUi3;
    private Button mBtnUi2;
    private Button mBtnUi;
    private Button mBtnUi1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index4);

        findcontrols();
    }

    private void findcontrols()
    {
        mBtnUiviewpager = (Button) findViewById(R.id.btn_uiviewpager);
        mBtnLayoutInflate = (Button) findViewById(R.id.btn_layout_inflate);
        mBtnLayoutLinear = (Button) findViewById(R.id.btn_layout_linear);
        mBtnToolbar = (Button) findViewById(R.id.btn_toolbar);
        mBtnActionButton = (Button) findViewById(R.id.btn_actionButton);
        mBtnCoordinator = (Button) findViewById(R.id.btn_coordinator);
        mBtnBar = (Button) findViewById(R.id.btn_bar);
        mBtnNavigation = (Button) findViewById(R.id.btn_navigation);
        mBtnDrawer = (Button) findViewById(R.id.btn_drawer);
        mBtnAdvanceview = (Button) findViewById(R.id.btn_advanceview);
        mBtnUi6 = (Button) findViewById(R.id.btn_ui6);
        mBtnUi5 = (Button) findViewById(R.id.btn_ui5);
        mBtnUi4 = (Button) findViewById(R.id.btn_ui4);
        mBtnUi3 = (Button) findViewById(R.id.btn_ui3);
        mBtnUi2 = (Button) findViewById(R.id.btn_ui2);
        mBtnUi = (Button) findViewById(R.id.btn_ui);
        mBtnUi1 = (Button) findViewById(R.id.btn_ui1);

        mBtnUi.setOnClickListener(this);
        mBtnUi1.setOnClickListener(this);
        mBtnUi2.setOnClickListener(this);
        mBtnUi3.setOnClickListener(this);
        mBtnUi4.setOnClickListener(this);
        mBtnUi5.setOnClickListener(this);
        mBtnUi6.setOnClickListener(this);
        mBtnAdvanceview.setOnClickListener(this);
        mBtnLayoutInflate.setOnClickListener(this);
        mBtnDrawer.setOnClickListener(this);
        mBtnNavigation.setOnClickListener(this);
        mBtnCoordinator.setOnClickListener(this);
        mBtnDrawer.setOnClickListener(this);
        mBtnToolbar.setOnClickListener(this);
        mBtnActionButton.setOnClickListener(this);
        mBtnUiviewpager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_ui1:
            {
                LSComponentsHelper.startActivity(this, TextViewButtom.class);break;
            }
            case R.id.btn_ui:
            {
                LSComponentsHelper.startActivity(this, ImageViewUI.class);break;
            }
            case R.id.btn_ui2:
            {
                LSComponentsHelper.startActivity(this, alertdialog.class);break;
            }
            case R.id.btn_ui3:
            {
                LSComponentsHelper.startActivity(this, layout.class);break;
            }
            case R.id.btn_ui4:
            {
                LSComponentsHelper.startActivity(this, listView.class);break;
            }
            case R.id.btn_ui5:
            {
                LSComponentsHelper.startActivity(this, customView.class);break;
            }
            case R.id.btn_ui6:
            {
                LSComponentsHelper.startActivity(this, OtherControls.class);break;
            }
            case R.id.btn_advanceview:
            {
                LSComponentsHelper.startActivity(this,index.class);break;
            }
            case R.id.btn_drawer:
            {
                startActivity(new Intent(this, Drawer.class)); break;
            }
            case R.id.btn_navigation:
            {
                startActivity(new Intent(this, Navegation.class));break;
            }
            case R.id.btn_coordinator:
            {
                startActivity(new Intent(this, coordination.class));break;
            }
            case R.id.btn_toolbar:
            {
                startActivity(new Intent(this, MyToolbar.class));break;
            }
            case R.id.btn_layout_inflate:
            {
                startActivity(new Intent(this, InflateTest.class));break;
            }
            case R.id.btn_uiviewpager:
            {
                startActivity(new Intent(this, MyViewPager.class));break;
            }
        }
    }
}