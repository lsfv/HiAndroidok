package com.linson.android.hiandroid2.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;



public class PatternIndex extends AppCompatActivity implements View.OnClickListener
{
    private Button mBtnMvpgoogle;
    private Button mBtnMvp3;
    private Button mBtnMvp2;
    private Button mBtnLayer;
    private Button mBtnMvp;
    private Button mBtnNormal;


    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mBtnMvpgoogle = (Button) findViewById(R.id.btn_mvpgoogle);
        mBtnMvp3 = (Button) findViewById(R.id.btn_mvp3);
        mBtnMvp2 = (Button) findViewById(R.id.btn_mvp2);
        mBtnLayer = (Button) findViewById(R.id.btn_layer);
        mBtnMvp = (Button) findViewById(R.id.btn_mvp);
        mBtnNormal = (Button) findViewById(R.id.btn_normal);

        //set event handler
        mBtnMvp.setOnClickListener(this);
        mBtnNormal.setOnClickListener(this);
        mBtnLayer.setOnClickListener(this);
        mBtnMvp2.setOnClickListener(this);
        mBtnMvp3.setOnClickListener(this);
        mBtnMvpgoogle.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_mvp:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, MyMVP.class);
                break;
            }
            case R.id.btn_normal:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, Normal.class);
                break;
            }
            case R.id.btn_layer:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, layer.class);
                break;
            }
            case R.id.btn_mvp2:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, MyMVP2.class);
                break;
            }
            case R.id.btn_mvpgoogle:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, GoogleMvp.class);
                break;
            }
            case R.id.btn_mvp3:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, Databind.class);
                break;
            }
            default:
            {
                break;
            }
        }
    }
    //endregion

    //region other member variable
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_index);

        findControls();
    }

}