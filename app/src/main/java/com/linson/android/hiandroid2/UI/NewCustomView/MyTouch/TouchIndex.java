package com.linson.android.hiandroid2.UI.NewCustomView.MyTouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;

import app.lslibrary.androidHelper.LSLog;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class TouchIndex extends AppCompatActivity implements View.OnClickListener
{
    private Button mBtnTouchSame2;
    private Button mBtnTouchDiffdirection2;
    private Button mBtnTouchSamedirection;
    private Button mBtnTouch2;
    private Button mBtnTouch1;




    //region  findcontrols and bind click event.
    private void findControls()
    {
        //findControls
        mBtnTouchSame2 = (Button) findViewById(R.id.btn_touch_same2);
        mBtnTouchDiffdirection2 = (Button) findViewById(R.id.btn_touch_diffdirection2);
        mBtnTouchSamedirection = (Button) findViewById(R.id.btn_touch_samedirection);
        mBtnTouch2 = (Button) findViewById(R.id.btn_touch2);
        mBtnTouch1 = (Button) findViewById(R.id.btn_touch1);


        //set event handler
        mBtnTouch1.setOnClickListener(this);
        mBtnTouch2.setOnClickListener(this);
        mBtnTouchSamedirection.setOnClickListener(this);
        mBtnTouchDiffdirection2.setOnClickListener(this);
        mBtnTouchSame2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_touch1:
            {
                LSComponentsHelper.startActivity(this, typical.class);
                break;
            }
            case R.id.btn_touch_samedirection:
            {
                LSComponentsHelper.startActivity(this, SameDirection.class);
                break;
            }
            case R.id.btn_touch_diffdirection2:
            {
                LSComponentsHelper.startActivity(this, DiffDirection.class);
                break;
            }
            case R.id.btn_touch_same2:
            {
                LSComponentsHelper.startActivity(this, same2.class);
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
        setContentView(R.layout.activity_touch_index);

        findControls();
    }
}