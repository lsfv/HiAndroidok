package com.linson.android.hiandroid2.Pattern;

public class MyMVP2_contract
{
    public interface IControl
    {
        void getStrLength(String str);
    }

    public interface Iview
    {
        void disPlayStrInfo(String str);
    }
}