package com.linson.android.hiandroid2.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;

//分层模式
//1.业务逻辑分离出去了. 但是和view联系还是比较紧密，假如button改为imageview 呢？
//根据开闭原则，必须把修改放到一个地方，这样不需要修改的地方就关闭了。修改的地方就开放了。这就是mvc.
public class layer extends AppCompatActivity implements View.OnClickListener
{
    private EditText mEtName;
    private Button mBtnSubmit;
    private TextView mTvMsg;
    private TextView mTvTitle;

    private layer_business layer_business=new layer_business();

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
            default: { break; }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        findControls();

        mTvTitle.setText("layer");
    }

    private void getStrLength()
    {
        String str=mEtName.getText().toString().trim();
        mTvMsg.setText(layer_business.getStringInfo(str));
    }
}