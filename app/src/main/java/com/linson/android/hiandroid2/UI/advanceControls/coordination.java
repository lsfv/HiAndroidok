package com.linson.android.hiandroid2.UI.advanceControls;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;

import java.util.ArrayList;
import java.util.List;

public class coordination extends AppCompatActivity implements View.OnClickListener
{
    private CoordinatorLayout mCoordinatorTest;
    private RecyclerView mRvTest2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordination);

        findControls();

        adapter_menu adapter_menu=new adapter_menu(getMenu());
        mRvTest2.setAdapter(adapter_menu);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRvTest2.setLayoutManager(linearLayoutManager);

    }

    //region  findcontrols and bind click event.
    private void findControls()
    {
        mRvTest2 = (RecyclerView) findViewById(R.id.rv_test2);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            default:
            {
                break;
            }
        }
    }
    //endregion

    //region business
    public List<String> getMenu()
    {
        List<String> menu=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            menu.add("hi"+i);
        }
        return menu;
    }
    //endregion
}