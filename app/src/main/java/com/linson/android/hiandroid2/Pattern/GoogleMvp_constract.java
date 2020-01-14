package com.linson.android.hiandroid2.Pattern;

public class GoogleMvp_constract
{
    public interface IControl extends IBaseControl
    {
        String getStrLength(String str);
    }

    public interface Iview extends IBaseView
    {
    }


    public interface IBaseControl
    {

    }

    public interface IBaseView
    {
        void setControl(IBaseControl baseControl);
        void initView();
    }
}