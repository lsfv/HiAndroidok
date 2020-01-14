package com.linson.android.hiandroid2.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;

//普通的模式：
//1.要处理业务逻辑：str= str+".length:"+str.length();
//2.和控件联系紧密：mTvMsg.setText(str);
//坏处，逻辑变，要修改代码，不符合开闭原则，所以把先把业务逻辑放出去。也就是分层模式.
public class Normal extends AppCompatActivity implements View.OnClickListener
{
    private EditText mEtName;
    private Button mBtnSubmit;
    private TextView mTvMsg;
    private TextView mTvTitle;



    private void findControls()
    {
        mEtName = (EditText) findViewById(R.id.et_name);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mTvTitle= (TextView) findViewById(R.id.tv_title);

        mBtnSubmit.setOnClickListener(this);
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
            default:
            {
                break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        findControls();
        mTvTitle.setText("normal");
    }

    private void getStrLength()
    {
        String str=mEtName.getText().toString().trim();
        str= str+".length:"+str.length();
        mTvMsg.setText(str);
    }
}