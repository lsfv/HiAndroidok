package com.linson.android.hiandroid2.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;

import app.lslibrary.androidHelper.LSLog;


//完全尴尬的mvc体验。当然是我搞错了。应该把activity作为view.而不是作为control.
//导致view 没有初始化自己的环境，也就是没有AppCompatActivity
public class MyMVP extends AppCompatActivity implements MyMVP_contract.IControl
{
    private layer_business layer_business=new layer_business();
    private MyMVP_contract.Iview mIview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            mIview = new myView(this, this);
            setContentView(mIview.getlayoutID());
            mIview.initView();
        } catch (Exception e)
        {
            LSLog.Log_Exception(e);
        }
    }


    @Override
    public String getStrLength(String str)
    {
        String msg= layer_business.getStringInfo(str);
        return msg;
    }




    private static class myView implements View.OnClickListener ,MyMVP_contract.Iview
    {
        private AppCompatActivity mAppCompatActivity;
        private MyMVP_contract.IControl mControl;

        private EditText mEtName;
        private Button mBtnSubmit;
        private TextView mTvMsg;
        private TextView mTvTitle;

        public myView(AppCompatActivity app,MyMVP_contract.IControl control)
        {
            mAppCompatActivity=app;
            mControl=control;
        }

        public void initView()
        {
            findControls();
        }

        @Override
        public int getlayoutID()
        {
            return R.layout.activity_normal;
        }

        private void findControls()
        {
            mEtName = (EditText) mAppCompatActivity.findViewById(R.id.et_name);
            mBtnSubmit = (Button) mAppCompatActivity.findViewById(R.id.btn_submit);
            mTvMsg = (TextView) mAppCompatActivity.findViewById(R.id.tv_msg);

            mTvTitle= (TextView) mAppCompatActivity.findViewById(R.id.tv_title);
            mBtnSubmit.setOnClickListener(this);

            mTvTitle.setText("mymvc1");
        }

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btn_submit:
                {
                    getStrLength();
                }
                default: { break; }
            }
        }

        private void getStrLength()
        {
            String str=mEtName.getText().toString().trim();
            str=mControl.getStrLength(str);
            mTvMsg.setText(str);
        }
    }


}