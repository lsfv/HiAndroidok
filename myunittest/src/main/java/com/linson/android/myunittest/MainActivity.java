package com.linson.android.myunittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView mWelcome;
    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcome = (TextView) findViewById(R.id.welcome);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button)
        {
            String str=mEditText.getText().toString().trim();
            mWelcome.setText(str);
        }
    }

    public String getETvalue()
    {
        return mEditText.getText().toString();
    }

    public static Integer add(Integer a,Integer b)
    {
        if(a==null || b==null)
        {
            return null;
        }
        else
        {
            return a + b;
        }
    }


    public static class myTestClass
    {
        public int add(int a,int b)
        {
            return a+b;
        }

        public int sub(int a,int b)
        {
            return a-b;
        }
    }
}