package com.linson.android.hiandroid2.UI.advanceControls;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;

import java.util.ArrayList;
import java.util.List;

//

public class Navegation extends AppCompatActivity implements View.OnClickListener
{
    private NavigationView mNavMenu;
    private Button mBtnLeftMenu;
    private DrawerLayout mDrawerMenu;
    private TextView mHi;
    private ConstraintLayout mLayoutMenu;
    private RecyclerView mRvMenu;

    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mNavMenu = (NavigationView) findViewById(R.id.nav_menu);
        mBtnLeftMenu = (Button) findViewById(R.id.btn_leftMenu);
        mDrawerMenu = (DrawerLayout) findViewById(R.id.drawer_menu);
        mHi = (TextView) findViewById(R.id.hi);
        mLayoutMenu = (ConstraintLayout) findViewById(R.id.layout_menu);
        mRvMenu = (RecyclerView) findViewById(R.id.rv_menu);

        //set event handler
        mBtnLeftMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_leftMenu:
            {
                mDrawerMenu.openDrawer(Gravity.LEFT);
            }
            default:
            {
                break;
            }
        }
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegation);

        findControls();
        setupRecycle();
    }

    private void setupRecycle()
    {
        List<String> menu=new ArrayList<>();
        menu.add("hi1");
        menu.add("hi2");
        menu.add("hi3");
        menu.add("hi4");

        LSComponentsHelper.VoidHandler voidHandler=new LSComponentsHelper.VoidHandler()
        {
            @Override
            public void doti()
            {
                mDrawerMenu.closeDrawer(Gravity.LEFT);
                mHi.setText("click menu");
            }
        };

        adapter_menu adapter_menu=new adapter_menu(menu);
        mRvMenu.setAdapter(adapter_menu);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRvMenu.setLayoutManager(linearLayoutManager);
    }
}