package com.linson.android.hiandroid2.UI.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


public class adapter_viewpager extends FragmentStatePagerAdapter
{
    private List<Fragment> mFragments;
    public adapter_viewpager(FragmentManager fm,List<Fragment> fragments)
    {
        super(fm);
        mFragments=fragments;
    }

    @Override
    public Fragment getItem(int i)
    {
        return mFragments.get(i);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }
}