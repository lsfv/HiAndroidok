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

public class GoogleMvp_fragment2 extends Fragment implements View.OnClickListener ,GoogleMvp_constract.Iview
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
        mTvTitle.setText("my pure mvc2");
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
                mTvMsg.setText(info+".hehe");

            }
            default: { break; }
        }
    }
}
