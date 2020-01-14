package com.linson.android.hiandroid2.UI.NewCustomView.MyPaint;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.hiandroid2.R;


//基本画图:文字和基本图形
public class PaintIndex extends AppCompatActivity implements View.OnClickListener
{
    private Button mBtnNew3;
    private Button mBtnNew2;
    private Button mBtnNew1;
    private Button mBtnBp2;
    private Button mBtnBp;



    //region  findcontrols and bind click event.
    private void findControls()
    {
        //findControls
        mBtnNew3 = (Button) findViewById(R.id.btn_new3);
        mBtnNew2 = (Button) findViewById(R.id.btn_new2);
        mBtnNew1 = (Button) findViewById(R.id.btn_new1);
        mBtnBp2 = (Button) findViewById(R.id.btn_bp2);
        mBtnBp = (Button) findViewById(R.id.btn_bp);

        //set event handler
        mBtnBp.setOnClickListener(this);
        mBtnBp2.setOnClickListener(this);
        mBtnNew1.setOnClickListener(this);
        mBtnNew2.setOnClickListener(this);
        mBtnNew3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_bp:
            {
                LSComponentsHelper.startActivity(this, BasePaint.class);
                break;
            }
            case R.id.btn_bp2:
            {
                LSComponentsHelper.startActivity(this, ComplexPaint.class);
                break;
            }
            case R.id.btn_new1:
            {
                LSComponentsHelper.LS_Activity.startActivity(this, NewPaint1.class);
                break;
            }
            default:
            {
                break;
            }
        }
    }
    //endregion

    //member variable

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_index);

        findControls();
    }
}