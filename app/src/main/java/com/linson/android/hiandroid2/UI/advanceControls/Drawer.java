package com.linson.android.hiandroid2.UI.advanceControls;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.linson.android.hiandroid2.R;

public class Drawer extends AppCompatActivity implements View.OnClickListener
{
    private DrawerLayout mDrawer;
    private Button mButton20;




    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mButton20 = (Button) findViewById(R.id.button20);

        //set event handler
        mButton20.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            default:
            {
                drawerAction();
                break;
            }
        }
    }
    //endregion

    //region other member variable
    private boolean isopen=false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        findControls();
    }

    private void drawerAction()
    {
        isopen=!isopen;
        if(isopen)
        {
            mDrawer.openDrawer(Gravity.LEFT);
        }
        else
        {
            mDrawer.closeDrawer(Gravity.LEFT);
        }
    }

}