package com.linson.android.hiandroid2.UI;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.linson.android.hiandroid2.R;

public class InflateControl extends ConstraintLayout
{
    public InflateControl(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //肯定要把xml添加到这个自定义控件。否则干嘛来了。
        LayoutInflater.from(context).inflate(R.layout.inflatetest, null,false);
    }
}