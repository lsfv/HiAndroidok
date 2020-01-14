package com.linson.android.hiandroid2.Pattern;

public class MyMVP_contract
{
    public interface IControl
    {
        String getStrLength(String str);
    }

    public interface Iview
    {
        void initView();
        int getlayoutID();
    }
}