package com.linson.android.hiandroid2.Pattern;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.hiandroid2.R;



//谁创建，谁销毁。谁接收，谁处理。
//所以每当ui层接收了ui动作，那么涉及此动作的ui逻辑，应该放到view层。该剥离，该放入到contril层的是业务逻辑。
public class GoogleMvp_fragment extends Fragment implements View.OnClickListener ,GoogleMvp_constract.Iview
{
    private EditText mEtName;
    private Button mBtnSubmit;
    private TextView mTvMsg;
    private TextView mTvTitle;

    private GoogleMvp_constract.IControl mControl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_googlemvp, container, false);
        findControls(view);
        //todo 这里又是不足。 纯靠人工不出错。只定义接口不够，必须模板模式。android底层不提供。自己要简单实现mvc没戏。
        //google 的例子是放入到 resume的。实在不能理解。为什么返回页面强制固定执行一次初始化？而且还是让control来主导。
        //关于纯mvc的定义和google的例子还是有不同。todo把control作为主导。这样才能纯。但是让view掌握自己的初始化会比较简洁把。
        initView();
        return view;
    }

    @Override
    public void setControl(GoogleMvp_constract.IBaseControl baseControl)
    {
        mControl=(GoogleMvp_constract.IControl)baseControl;
    }

    @Override
    public void initView()
    {
        mTvTitle.setText("my pure mvc");
    }


    private void findControls(View view)
    {
        mEtName = (EditText) view.findViewById(R.id.et_name);
        mBtnSubmit = (Button) view.findViewById(R.id.btn_submit);
        mTvMsg = (TextView) view.findViewById(R.id.tv_msg);

        mTvTitle = (TextView) view.findViewById(R.id.tv_title);

        mBtnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_submit:
            {
                String str = mEtName.getText().toString().trim();
                String info=mControl.getStrLength(str);
                //感觉这里二种做法都有道理。
                //1.返回string,自己处理。因为这里是ui逻辑。不同的ui有不同的处理方式。control只做逻辑。
                //2.返回空，提供一个接口让control调用。不同的ui有不同的处理方式。control只调用。感觉2更合理。1更简洁。
                mTvMsg.setText(info);

            }
            default: { break; }
        }
    }
}