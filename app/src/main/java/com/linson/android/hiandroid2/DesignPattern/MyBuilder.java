package com.linson.android.hiandroid2.DesignPattern;

import java.util.List;

import app.lslibrary.androidHelper.LSLog;

public abstract class MyBuilder
{
    public static void main()
    {
        MyExportFile myExportFile=new MyExportFile(1, "a company", null, "linson", new IGenerateContext()
        {
            @Override
            public String generateContext(List items)
            {
                return "size:"+items.size();
            }
        });
        myExportFile.export();
    }

    public interface IGenerateContext<T>
    {
        String generateContext(List<T> items);
    }

    //如果需要定制 content的话。可能需要T.
    public static class MyExportFile<T>
    {
        String mHeader;
        List<T> mItems;
        String mBottom;
        int mType;
        private IGenerateContext<T> mHandler;

        public MyExportFile(int fileType, String header, List<T> items,String bottom,IGenerateContext<T> handler)
        {
            mHeader=header;
            mItems=items;
            mBottom=bottom;
            mType=fileType;
            mHandler=handler;
        }

        public void export()
        {
            String context="";
            if(mHandler!=null)
            {
                mHandler.generateContext(mItems);
            }
            if(mType==1)
            {
                LSLog.Log_INFO("export file:hader:" + mHeader + ".type:"+mType);
            }
            else
            {
                LSLog.Log_INFO("export file:hader:" + mHeader + ".type:"+mType);
            }
        }
    }
}